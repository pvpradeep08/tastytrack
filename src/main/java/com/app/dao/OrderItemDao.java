package com.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.app.entity.OrderItem;
import com.app.util.HibernateConnect;

@SuppressWarnings("CallToPrintStackTrace")
public class OrderItemDao {

    private static Session session;

    public static boolean addOrderItem(OrderItem orderItem) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.persist(orderItem);
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

    public static boolean updateOrderItem(OrderItem orderItem) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.merge(orderItem);
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

    public static boolean deleteOrderItem(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.remove(session.get(OrderItem.class, id));
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

    public static boolean deleteAllOrderItems() {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.createQuery("delete from OrderItem").executeUpdate();
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

    public static OrderItem getOrderItem(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.get(OrderItem.class, id);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<OrderItem> getAllOrderItems() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from OrderItem", OrderItem.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static List<OrderItem> getAllOrderItemsByOrderId(int id) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from OrderItem o where o.orderId=" + id + "", OrderItem.class).getResultList();
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
