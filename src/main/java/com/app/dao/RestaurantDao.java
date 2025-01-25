package com.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.app.entity.Restaurant;
import com.app.util.HibernateConnect;

@SuppressWarnings("CallToPrintStackTrace")
public class RestaurantDao {

    private static Session session;

    public static boolean addRestaurant(Restaurant restaurant) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.persist(restaurant);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Restaurant> getAllRestaurants() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Restaurant", Restaurant.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Restaurant> getAllPopularRestaurants() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Restaurant r where r.popular=true", Restaurant.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static Restaurant getRestaurant(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.get(Restaurant.class, id);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static boolean updateRestaurant(Restaurant restaurant) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.merge(restaurant);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static boolean deleteRestaurant(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.remove(session.get(Restaurant.class, id));
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }
}