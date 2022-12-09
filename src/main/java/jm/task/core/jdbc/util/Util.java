package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "jdbc:postgresql://localhost:5432/user";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "user";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    public  static Connection connection = connection();
    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connect with DB");
            throw new RuntimeException(e);
        }
        return connection;
    }
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        try{
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", HOST)
                    .setProperty("dialect", DIALECT)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.c3p0.min_size", "5")
                    .setProperty("hibernate.c3p0.max_size", "200")
                    .setProperty("hibernate.c3p0.max_statements", "200");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
