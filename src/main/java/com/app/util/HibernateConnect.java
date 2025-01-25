package com.app.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.app.entity.Menu;
import com.app.entity.Restaurant;
import com.app.entity.User;

public class HibernateConnect {
    static SessionFactory sessionFactory;

    @SuppressWarnings("CallToPrintStackTrace")
    public static SessionFactory getSession() {
        try {
            sessionFactory = new Configuration().configure().addAnnotatedClass(Restaurant.class)
                    .addAnnotatedClass(Menu.class).addAnnotatedClass(User.class).buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void closeSession() {
        sessionFactory.close();
    }

}
