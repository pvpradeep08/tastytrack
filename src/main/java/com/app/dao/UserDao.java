package com.app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.app.entity.User;
import com.app.util.HibernateConnect;

@SuppressWarnings("CallToPrintStackTrace")
public class UserDao {

    private static Session session;

    public static boolean addUser(User user) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.persist(user);
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

    public static User validateUser(String usernameOrEmail, String password) {

        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            String hql = "FROM User u WHERE (u.username = :usernameOrEmail OR u.email = :usernameOrEmail) AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);

            // Set the parameters
            query.setParameter("usernameOrEmail", usernameOrEmail);
            query.setParameter("password", password);

            User user = query.uniqueResult(); // Get the result

            session.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            // e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static User getUserById(int userId) {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.get(User.class, userId);
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static User getUserByUsername(String username) {
        try {
            session = HibernateConnect.getSession().openSession();
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static User getUserByEmail(String email) {
        try {
            session = HibernateConnect.getSession().openSession();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static boolean updateUser(User user) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.merge(user);
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

    public static boolean deleteUserById(int userId) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.remove(session.get(User.class, userId));
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

    public static boolean deleteUserByUsername(String username) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.remove(session.get(User.class, username));
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

    public static boolean deleteAllUsers() {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
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

    public static boolean updatePassword(String username, String password) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "update User u set u.password=:password where u.username=:username", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            query.executeUpdate();
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

    public static List<User> getAllUsers() {
        try {
            session = HibernateConnect.getSession().openSession();
            return session.createQuery("from User", User.class).getResultList();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            HibernateConnect.closeSession();
        }
    }

    public static boolean updateLastLogin(User user) {
        try {
            session = HibernateConnect.getSession().openSession();
            session.beginTransaction();
            session.merge(user);
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
