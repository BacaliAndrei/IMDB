package org.example.Repository;

import org.example.Entity.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AppUserRepository implements CRUDRepository<AppUser> {

    private final SessionFactory sessionFactory;

    public AppUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AppUser create(AppUser appUser) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(appUser);
        transaction.commit();
        session.close();
        return appUser;
    }

    @Override
    public List<AppUser> findAll() {
        Session session = sessionFactory.openSession();
        List<AppUser> appUsers = session.createQuery("from AppUser", AppUser.class).getResultList();
        session.close();
        return appUsers;
    }
}
