package dev.lpa;

import java.sql.*;

public class MusicDML2 {
    /**
     * Exemplo didático de acesso JDBC a um banco MySQL “music”.
     * <p>
     * Objetivos pedagógicos ilustrados:
     * 1. Abertura automática de recursos com try-with-resources.
     * 2. Separação de responsabilidades em métodos pequenos e reutilizáveis.
     * 3. Manipulação básica de dados (CRUD) via Statement.
     * 4. Demonstração de chaves geradas (auto-increment).
     * 5. Boas práticas de formatação e documentação.
     * <p>
     * IMPORTANTE: este código usa java.text.String.formatted() para clareza,
     * mas **NÃO** deve ir para produção assim — ver comentários sobre SQL Injection.
     */

    public static void main(String[] args) {
        /*
         * Registro do driver (JDBC 4.0+ não é mais obrigatório).
         * Mantido comentado apenas para lembrar que, em versões antigas,
         * Class.forName("com.mysql.cj.jdbc.Driver") era necessário.
         */

        /* Configuração da conexão via variáveis de ambiente.
         * Isso evita expor credenciais no código-fonte.
         */
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/music?useSSL=false&serverTimezone=UTC",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
                /* O Statement é criado aqui para ser fechado automaticamente
                pelo try-with-resources. */
             Statement statement = connection.createStatement()) {

            /* Parâmetros de exemplo. Em produção, use PreparedStatement
               e trate SQL Injection. */
            String tableName = "music.artists";
            String columnName = "artist_name";
            String columnValue = "Bob Dylan";

            /* Fluxo básico: se não existe, insere; se existe, atualiza. */
            if (!executeSelect(statement, tableName, columnName, columnValue)) {
                insertArtistAlbum(statement, columnValue, columnValue);
            } else {
                updateRecord(statement, tableName, columnName, columnValue, columnName, columnValue.toUpperCase());
            }

        } catch (SQLException e) {
            /* Em projetos reais, use um logger (SLF4J, Logback, etc.). */
            throw new RuntimeException("Erro ao acessar o banco de dados", e);
        }
    }

    /**
     * Imprime todas as linhas do ResultSet em formato tabular.
     *
     * @param resultSet já posicionado antes da primeira linha.
     * @return true se pelo menos uma linha foi impressa.
     * @throws SQLException em caso de erro de acesso aos dados.
     */
    private static boolean printRecords(ResultSet resultSet) throws SQLException {
        boolean foundData = false;
        ResultSetMetaData meta = resultSet.getMetaData();
        int columnCount = meta.getColumnCount();

        /* Cabeçalho */
        System.out.println("=".repeat(90));
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
        }
        System.out.println();

        /* Dados */
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                /* Optamos por getString para simplificar; para tipos específicos,
                   use getInt, getDate, etc. */
                System.out.printf("%-15s", resultSet.getString(i));
            }
            System.out.println();
            foundData = true;
        }
        return foundData;
    }

    /**
     * Executa um SELECT simples e imprime o resultado.
     * <p>
     * ATENÇÃO: vulnerável a SQL Injection. Em produção prefira PreparedStatement.
     *
     * @param statement   Statement já aberto.
     * @param table       nome da tabela (qualificado com schema se necessário).
     * @param columnName  coluna para filtro WHERE.
     * @param columnValue valor para filtro WHERE.
     * @return true se pelo menos um registro foi encontrado.
     * @throws SQLException erro de sintaxe SQL ou de conexão.
     */
    private static boolean executeSelect(Statement statement, String table, String columnName, String columnValue) throws SQLException {

        String query = "SELECT * FROM %s WHERE %s='%s'".formatted(table, columnName, columnValue);
        System.out.println("[DEBUG] SQL SELECT -> " + query);

        try (ResultSet rs = statement.executeQuery(query)) {
            /* ResultSet nunca é null; se não houver linhas, next() retorna false. */
            return printRecords(rs);
        }
    }

    /**
     * Insere um registro simples (apenas colunas VARCHAR).
     *
     * @param statement    Statement já aberto.
     * @param table        tabela destino.
     * @param columnNames  array com os nomes das colunas.
     * @param columnValues array com os valores (deve ter o mesmo tamanho).
     * @return true se pelo menos uma linha foi inserida.
     * @throws SQLException erro de sintaxe ou violação de constraint.
     */
    private static boolean insertRecord(Statement statement, String table, String[] columnNames, String[] columnValues) throws SQLException {

        String colNames = String.join(",", columnNames);
        String colValues = String.join("','", columnValues);
        String query = "INSERT INTO %s (%s) VALUES ('%s')".formatted(table, colNames, colValues);
        System.out.println("[DEBUG] SQL INSERT -> " + query);

        /* execute() retorna false para INSERT; usamos getUpdateCount() para
           saber quantas linhas foram afetadas. */
        statement.execute(query);
        int recordsInserted = statement.getUpdateCount();
        if (recordsInserted > 0) {
            executeSelect(statement, table, columnNames[0], columnValues[0]);
        }
        return recordsInserted > 0;
    }

    /**
     * Remove registros que atendam ao critério WHERE.
     *
     * @param statement   Statement já aberto.
     * @param table       tabela alvo.
     * @param columnName  coluna do filtro WHERE.
     * @param columnValue valor do filtro WHERE.
     * @return true se pelo menos uma linha foi removida.
     * @throws SQLException erro de sintaxe ou FK constraint.
     */
    private static boolean deleteRecord(Statement statement, String table, String columnName, String columnValue) throws SQLException {

        String query = "DELETE FROM %s WHERE %s='%s'".formatted(table, columnName, columnValue);
        System.out.println("[DEBUG] SQL DELETE -> " + query);

        statement.execute(query);
        int recordsDeleted = statement.getUpdateCount();
        if (recordsDeleted > 0) {
            /* Mostra que não há mais registros. */
            executeSelect(statement, table, columnName, columnValue);
        }
        return recordsDeleted > 0;
    }

    /**
     * Atualiza registros que atendam ao critério WHERE.
     *
     * @param statement     Statement já aberto.
     * @param table         tabela alvo.
     * @param matchedColumn coluna do WHERE.
     * @param matchedValue  valor do WHERE.
     * @param updatedColumn coluna a ser alterada.
     * @param updatedValue  novo valor.
     * @return true se pelo menos uma linha foi alterada.
     * @throws SQLException erro de sintaxe ou constraint.
     */
    private static boolean updateRecord(Statement statement, String table, String matchedColumn, String matchedValue, String updatedColumn, String updatedValue) throws SQLException {

        String query = "UPDATE %s SET %s='%s' WHERE %s='%s'".formatted(table, updatedColumn, updatedValue, matchedColumn, matchedValue);
        System.out.println("[DEBUG] SQL UPDATE -> " + query);

        statement.execute(query);
        int recordsUpdated = statement.getUpdateCount();
        if (recordsUpdated > 0) {
            executeSelect(statement, table, updatedColumn, updatedValue);
        }
        return recordsUpdated > 0;
    }

    /**
     * Transação didática: insere artista, álbum e faixas em sequência,
     * recuperando as chaves geradas.
     *
     * @param statement  Statement já aberto (mesma Connection, sem auto-commit).
     * @param artistName nome do artista a inserir.
     * @param albumName  nome do álbum a inserir.
     * @throws SQLException erro em qualquer etapa da transação.
     */
    private static void insertArtistAlbum(Statement statement, String artistName, String albumName) throws SQLException {

        /* 1. Inserir artista */
        String artistInsert = "INSERT INTO music.artists (artist_name) VALUES (%s)".formatted(statement.enquoteLiteral(artistName));
        System.out.println("[DEBUG] SQL INSERT ARTIST -> " + artistInsert);
        statement.execute(artistInsert, Statement.RETURN_GENERATED_KEYS);

        /* Recuperar chave gerada */
        int artistId;
        try (ResultSet rs = statement.getGeneratedKeys()) {
            artistId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
        }
        if (artistId == -1) {
            throw new SQLException("Falha ao obter ID do artista inserido.");
        }

        /* 2. Inserir álbum */
        String albumInsert = "INSERT INTO music.albums (album_name, artist_id) VALUES (%s, %d)".formatted(statement.enquoteLiteral(albumName), artistId);
        System.out.println("[DEBUG] SQL INSERT ALBUM -> " + albumInsert);
        statement.execute(albumInsert, Statement.RETURN_GENERATED_KEYS);

        int albumId;
        try (ResultSet rs = statement.getGeneratedKeys()) {
            albumId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
        }
        if (albumId == -1) {
            throw new SQLException("Falha ao obter ID do álbum inserido.");
        }

        /* 3. Inserir faixas */
        String[] songs = {"You're no Good", "Talkin' New York", "In my Time of Dyin'", "Man of Constant Sorrow", "Fixin' to Die", "Pretty Peggy-O", "Highway 51 Blues"};

        String songInsert = "INSERT INTO music.songs (track_number, song_title, album_id) VALUES (%d, %s, %d)";

        for (int i = 0; i < songs.length; i++) {
            String songQuery = songInsert.formatted(i + 1, statement.enquoteLiteral(songs[i]), albumId);
            System.out.println("[DEBUG] SQL INSERT SONG -> " + songQuery);
            statement.execute(songQuery);
        }

        /* 4. Verificar resultado final na view */
        executeSelect(statement, "music.albumview", "album_name", artistName);
    }
}

