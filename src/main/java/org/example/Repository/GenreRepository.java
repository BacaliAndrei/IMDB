package org.example.Repository;

import org.example.Entity.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class GenreRepository implements CRUDRepository<Genre> {

    private final SessionFactory sessionFactory;

    public GenreRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Genre create(Genre genre) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(genre);
        transaction.commit();
        session.close();
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        Session session = sessionFactory.openSession();
        List<Genre> genre = session.createQuery("from Genre", Genre.class).getResultList();
        session.close();
        return genre;
    }
}
