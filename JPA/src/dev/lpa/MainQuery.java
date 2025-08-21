package dev.lpa;

import dev.lpa.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainQuery {
    public static void main(String[] args) {

        List<Artist> artists = null;
        //First: instantiating an EntityManagerFactory from the Persistence type
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev.lpa.music");
             EntityManager em = emf.createEntityManager();) {

            var transaction = em.getTransaction();
            transaction.begin();
            artists = getArtistJPQL(em, "%Greatest Hits%");
            artists.forEach(System.out::println);

            System.out.println("//".repeat(90));

            Stream<Artist> sartists = getArtistSQL(em, "Bl%");
            var map = sartists
                    .limit(10)
                            .collect(Collectors.toMap(
                                    Artist::getArtistName,
                                    (a) -> a.getAlbums().size(),
                                    Integer::sum,
                                    TreeMap::new
                            ));

            map.forEach((k,v) -> System.out.println(k + " : " + v));

//            var names = getArtistNames(em, "%Stev%");
////            names.forEach(System.out::println);
//            names.map(
//                    a -> new Artist(a.get("id", Integer.class), (String) a.get("name")))
//                            .forEach(System.out::println);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //I want a List of entity types, based on some query
    private static List<Artist> getArtistJPQL(EntityManager em, String matchedValue) {

        //String for my query statement
//        String jpql = "SELECT a FROM Artist a WHERE a.artistName LIKE ?1";
        String jpql = "SELECT a FROM Artist a JOIN albums album WHERE album.albumName LIKE ?1 " +
                "OR album.albumName LIKE ?2";

        //Queries are executed using the EntityManager
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(2, "%Best of%");
        query.setParameter(1, matchedValue);

        //The query object i get back, has a method called getResultList
        //It will return a List of type Artist, in this case

        return query.getResultList();

    }

    private static Stream<Tuple> getArtistNames(EntityManager em, String matchedValue) {

        String jpql = "SELECT a.artistId id, a.artistName AS name FROM Artist a WHERE a.artistName LIKE ?1";
        var query = em.createQuery(jpql, Tuple.class);
        query.setParameter(1, matchedValue);
        return query.getResultStream();

    }

    private static Stream<Artist> getArtistsBuilder(EntityManager em, String matchedValue) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = builder.createQuery(Artist.class);
        Root<Artist> root = criteriaQuery.from(Artist.class);

        //They can be chained
        criteriaQuery.select(root)
                .where(builder.like(root.get("artistName"), matchedValue))
                .orderBy(builder.asc(root.get("artistName")));

//        criteriaQuery.select(root);
//        criteriaQuery.where(builder.like(root.get("artistName"), matchedValue));
//        criteriaQuery.orderBy(builder.asc(root.get("artistName")));
        return em.createQuery(criteriaQuery).getResultStream();
    }

    private static Stream<Artist> getArtistSQL(EntityManager em, String matchedValue) {

        var query = em.createNativeQuery(
                "SELECT * FROM music.artists WHERE artist_name LIKE ?1", Artist.class
        );
        query.setParameter(1, matchedValue);
        return query.getResultStream();
    }
}
