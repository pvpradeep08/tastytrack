package com.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.app.entity.Orders;
import com.app.util.HibernateConnect;

@SuppressWarnings("CallToPrintStackTrace")
public class OrdersDao {

    private static Session session;

    public static boolean addOrder(Orders orders) {
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

    public static boolean updateOrder(Orders orders) {
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
            session.remove(session.get(Orders.class, id));
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
            session.createQuery("delete from Orders").executeUpdate();
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

    public static List<Orders> getAllOrders() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Orders", Orders.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static Orders getOrder(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.get(Orders.class, id);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Orders> getAllOrdersById(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Orders o where o.user_id=" + id + "", Orders.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Orders> getAllOrdersByStatus(String status) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Orders o where o.status='" + status + "'", Orders.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<Orders> getRecentOrders() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from Orders o order by o.date desc", Orders.class).setMaxResults(3)
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
