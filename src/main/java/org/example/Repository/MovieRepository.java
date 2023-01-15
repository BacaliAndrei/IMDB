package org.example.Repository;

import org.example.Entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MovieRepository implements CRUDRepository<Movie> {

    private final SessionFactory sessionFactory;

    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie create(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(movie);
        transaction.commit();
        session.close();
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        Session session = sessionFactory.openSession();
        List<Movie> movies = session.createQuery("from Movie", Movie.class).getResultList();
        session.close();
        return movies;
    }
}
