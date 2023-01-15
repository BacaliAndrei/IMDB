package org.example;

import org.example.Entity.*;
import org.example.Repository.AppUserRepository;
import org.example.Repository.GenreRepository;
import org.example.Repository.MovieRepository;
import org.example.Repository.ReviewRepository;
import org.example.imdb.Entity.*;
import org.example.imdb.Repository.*;
import org.example.Service.GenreService;
import org.example.Service.MovieService;
import org.example.Service.impl.GenreServiceConsoleImpl;
import org.example.Service.impl.MovieServiceConsoleImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class Main {



    public static void main(String[] args) {


        System.out.println("Creating session factory");
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(AppUser.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        System.out.println("Session factory created");
        try {

            AppUserRepository appUserRepository = new AppUserRepository(sessionFactory);
            GenreRepository genreRepository = new GenreRepository(sessionFactory);
            MovieRepository movieRepository = new MovieRepository(sessionFactory);
            ReviewRepository reviewRepository = new ReviewRepository(sessionFactory);


            GenreService genreService = new GenreServiceConsoleImpl(genreRepository);
            MovieService movieService = new MovieServiceConsoleImpl(movieRepository, genreRepository);

            menu.enterGeneralMenu(genreService, movieService);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

        System.out.println("Closing session");
        sessionFactory.close();
        System.out.println("Session factory closed");

    }

    public static void method1(SessionFactory hibernateSessionFactory) {
        Session session = hibernateSessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Genre horror = new Genre("Horror", "Scary movie that terrifies the audience");
        Movie sleepyHollow = new Movie("Sleepy Hollow", Date.valueOf("1999-05-12"), horror);

        session.persist(horror);
        horror.setName("horror2");
        session.persist(sleepyHollow);

        t.commit();
        session.close();
    }

    private static void eachWithDifferentSession(SessionFactory hibernateSessionFactory) {
        GenreRepository genreRepository = new GenreRepository(hibernateSessionFactory);
        MovieRepository movieRepository = new MovieRepository(hibernateSessionFactory);
        Genre horror = new Genre("Horror", "Scary movie that terrifies the audience");
        Movie sleepyHollow = new Movie("Sleepy Hollow", Date.valueOf("1999-05-12"), horror);
        genreRepository.create(horror);
        horror.setName("horror2");
        movieRepository.create(sleepyHollow);
        System.out.println();
    }

    private static void allInOneSession(SessionFactory hibernateSessionFactory) {
        Session session = hibernateSessionFactory.openSession();
        Transaction t = session.beginTransaction();
        AppUser iulia = new AppUser("iulia@gmail.com", "123");
        session.persist(iulia);

        Genre horror = new Genre("Horror", "Scary movie that terrifies the audience");
        session.persist(horror);

        Actor johnyDepp = new Actor("Johnny", "Depp", Date.valueOf("1963-12-23"));
        Actor christinaRicci = new Actor("Christina", "Ricci", Date.valueOf("1980-02-12"));
        Movie sleepyHollow = new Movie("Sleepy Hollow", Date.valueOf("1999-05-12"), horror);
//        sleepyHollow.addActor(johnyDepp);
//        sleepyHollow.addActor(christinaRicci);


//        Review goodReview = new Review(iulia, 8, Date.valueOf("2020-10-15"), "A good film", sleepyHollow);
//        Review badReview = new Review(iulia, 2, Date.valueOf("2002-10-15"), "Crap", sleepyHollow);
//        session.persist(goodReview);
//        session.persist(badReview);

        session.persist(sleepyHollow);


        System.out.println(horror);
        System.out.println(sleepyHollow);
        System.out.println(johnyDepp);
        System.out.println(christinaRicci);

        t.commit();
        session.close();


        Session session2 = hibernateSessionFactory.openSession();


        session2.beginTransaction();
        System.out.println(session2.createQuery("from Movie", Movie.class).getResultList());
        session2.close();
    }
}