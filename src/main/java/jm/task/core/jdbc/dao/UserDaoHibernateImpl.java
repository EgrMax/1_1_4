package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;
    private  final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users" +
                "(id SERIAL PRIMARY KEY," +
                " name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age int)";
        try (Session session = sessionFactory.openSession()
        ) {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            session.beginTransaction();
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Исключение create");
        }
    }



    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            String sql = "Drop table if exists users";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Исключение removeUserById");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("from User ").list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "delete from User";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

