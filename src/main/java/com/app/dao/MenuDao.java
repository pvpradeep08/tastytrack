package com.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.app.entity.Menu;
import com.app.util.HibernateConnect;

@SuppressWarnings("CallToPrintStackTrace")
public class MenuDao {

    private static Session session;

    public static boolean addMenu(Menu menu) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.persist(menu);
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

    public static List<Menu> getAllMenus() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Menu", Menu.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static Menu getMenu(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.get(Menu.class, id);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Menu> getAllMenusById(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Menu m where m.restaurantId=" + id + "", Menu.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static boolean updateMenu(Menu menu) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.merge(menu);
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

    public static boolean deleteMenu(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.remove(session.get(Menu.class, id));
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
