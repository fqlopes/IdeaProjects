package dev.lpa;

import dev.lpa.music.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        //The first thing we need is a EntityManagerFactory, which will serve
        //as a factory for producing EntityManager instances

        //This process creates persistence context, that establishes db connection,
        //and manages connections pools for efficient resource usage.
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory("dev.lpa.music");
             EntityManager entityManager = sessionFactory.createEntityManager();){

            //Inside the try block, we'll get a transaction from the entityManager,
            //and its a good idea to include any changes to a managed entity, with a transaction,
            //giving us the opportunity to test the results, and roll back
            //should the results aren't what we expect
            var transaction = entityManager.getTransaction();

            //Once a transaction is at hand,we call begin() on it
            transaction.begin();

            //Then followed by a call to persist on our entityManager, passing
            //the new instance of the Artist class, using its constructor.
//            entityManager.persist(new Artist("Muddy Water"));
            Artist artist = entityManager.find(Artist.class, 202);
//            Artist artist = new Artist(202, "Muddy Water");
            System.out.println(artist);
//            artist.removeDuplicates();
            artist.addAlbum("The Best of Muddy Waters");
            System.out.println(artist);
            //To edit a column, we may use the setters of our class
//            artist.setArtistName("Muddy Waters");

            //Removing a column using the remove method
//            entityManager.remove(artist);

            //Lastly, we commit the transaction
//            entityManager.merge(artist);
            transaction.commit();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
