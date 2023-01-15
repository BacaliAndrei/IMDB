package org.example.Repository;

import org.example.Entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewRepository implements CRUDRepository<Review> {

    private final SessionFactory sessionFactory;

    public ReviewRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Review create(Review review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(review);
        transaction.commit();
        session.close();
        return review;
    }

    @Override
    public List<Review> findAll() {
        Session session = sessionFactory.openSession();
        List<Review> reviews = session.createQuery("from Review").getResultList();
        session.close();
        return reviews;
    }

}
