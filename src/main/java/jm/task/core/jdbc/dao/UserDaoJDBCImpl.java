package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws NullPointerException {
//        try (Connection connection = Util.connection();
//             Statement statement = connection.createStatement()) {
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
//                    "(id serial PRIMARY KEY, name VARCHAR(45), lastname VARCHAR(45), age int)");
//            System.out.println("Таблица создана");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    public void dropUsersTable() {
//        try (Connection connection = Util.connection();
//             Statement statement = connection.createStatement()) {
//            statement.executeUpdate("Drop table if exists users");
//            System.out.println("Таблица удалилась");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void saveUser(String name, String lastName, byte age) {
//        String sql = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
//        try (Connection connection = Util.connection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2,lastName);
//            preparedStatement.setByte(3,age);
//            preparedStatement.executeUpdate();
//            System.out.println("Новый юзер добавлен в базу данных" + " " + name);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void removeUserById(long id) {
//        try (Connection connection = Util.connection();
//             Statement statement = connection.createStatement()) {
//            String sql = "DELETE FROM users where id";
//            statement.executeUpdate(sql);
//            System.out.println("Юзер удален");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public List<User> getAllUsers() {
//        List<User> allUser = new ArrayList<>();
//        String sql = "SELECT id, name, lastName, age from users";
//        try (Connection connection = Util.connection();
//             Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastName"));
//                user.setAge(resultSet.getByte("age"));
//                allUser.add(user);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }


    public void cleanUsersTable() {
//        String sql = "DELETE FROM users";
//        try (Connection connection = Util.connection();
//             Statement statement = connection.createStatement()) {
//            statement.executeUpdate(sql);
//            System.out.println("Таблица очищена");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Не удалось очистить");
//        }
    }
}
