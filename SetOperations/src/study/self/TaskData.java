package study.self;

import java.util.*;

public class TaskData {

    private static String tasks = """
            Infrastructure, Logging, High
            Infrastructure, DB Access, Medium
            Infrastructure, Security, High
            Infrastructure, Password Policy, Medium
            Data Design, Task Table, Medium
            Data Design, Employee Table, Medium
            Data Design, Cross Reference Tables, High
            Data Design, Encryption Policy, High
            Data Access, Write Views, Low
            Data Access, Set Up Users, Low
            Data Access, Set Up Access Policy, Low
            """;

    private static String annsTasks = """
            Infrastructure, Security, High, In Progress
            Infrastructure, Password Policy,Medium, In Progress
            Research, Cloud solutions, Medium, In Progress
            Data Design, Encryption Policy, High
            Data Design, Project Table, Medium
            Data Access, Write Views, Low, In Progress
            """;

    private static String bobsTasks = """
            Infrastructure, Security, High, In Progress
            Infrastructure, Password Policy, Medium
            Data Design, Encryption Policy, High
            Data Access, Write Views, Low, In Progress
            """;

    private static String carolsTasks = """
            Infrastructure, Logging, High, In Progress
            Infrastructure, DB Access, Medium
            Infrastructure, Password Policy, Medium
            Data Design, Task Table, High
            Data Access, Write Views, Low
            """;

    public static Set<Task> getTasks (String owner){

        Set<Task> taskList = new HashSet<>();
        String worker = ("ann, bob, carol".contains(owner.toLowerCase())) ? owner : null;

        String ownerList = switch (owner.toLowerCase()){
            case "ann" -> annsTasks;
            case "bob" -> bobsTasks;
            case "carol" -> carolsTasks;
            default -> tasks;
        };

        // o laço for irá gerenciar uma nova string linha por linha
        for (String taskData : ownerList.split("\n")){
            //quebra-se as strings na virgula, gerando 3 a 4 campos
            String[] data =  taskData.split(",");
            //limpando os espaços das strings
            Arrays.asList(data).replaceAll(String::trim);
            Priority priority = Priority.valueOf(data[2].toUpperCase());
            Status status = (data.length <= 3) ? Status.IN_QUEUE : Status.valueOf(data[3].toUpperCase().replace(' ', '_'));
            taskList.add(new Task(data[0], data[1], worker, priority, status));
        }
        return taskList;
    }


}


