package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws NullPointerException {
        try (Connection connection = Util.connection()){
            //connection.setAutoCommit(false);
            String string = "CREATE TABLE IF NOT EXISTS user.users (id int not null, name VARCHAR(50), lastname VARCHAR(50), age int, PRIMARY KEY (id))\n";
            PreparedStatement preparedStatement = connection.prepareStatement(string);
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана");
            connection.commit();
        } catch (RuntimeException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }
    public void dropUsersTable() {
        try (Connection connection = Util.connection()){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("Drop table if exists user");
            System.out.println("Таблица удалена");
            connection.commit();


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();


        } catch (RuntimeException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connection()){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users where id");
            preparedStatement.executeUpdate();
            System.out.println("User удален");
            connection.commit();


        } catch (RuntimeException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from users";

        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){


            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUser.add(user);
            }


        } catch (RuntimeException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }

        return allUser;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection connection = Util.connection()){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
            connection.commit();


        } catch (RuntimeException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }
}
