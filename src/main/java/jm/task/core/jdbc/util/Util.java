package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "jdbc:postgresql://localhost:5432/user";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "user";

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
}
