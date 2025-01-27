package com.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.app.entity.Order;
import com.app.util.HibernateConnect;

@SuppressWarnings("CallToPrintStackTrace")
public class OrderDao {

    private static Session session;

    public static boolean addOrder(Order orders) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.persist(orders);
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

    public static boolean updateOrder(Order orders) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.merge(orders);
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

    public static boolean deleteOrder(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.remove(session.get(Order.class, id));
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

    public static boolean deleteAllOrders() {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.createQuery("delete from Order").executeUpdate();
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

    public static List<Order> getAllOrders( int id ) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Order o where o.userId=" + id + " order by orderDate desc", Order.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static Order getOrder(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.get(Order.class, id);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Order> getAllOrdersById(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Order o where o.userId=" + id + " order by o.orderDate desc ", Order.class)
                    .getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Order> getAllOrdersByStatus(String status) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Order o where o.status='" + status + "'", Order.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Order> getRecentOrdersByUserId(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Order o where o.userId=" + id + " order by o.orderDate desc", Order.class)
                    .setMaxResults(3)
                    .getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }

    }

}
