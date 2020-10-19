package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            DatabaseMetaData dataBase = connection.getMetaData();
            ResultSet rs = dataBase.getTables(null, null, "nelly123", null);
            if (rs.next()) {
                System.out.println("Такая таблица уже есть.");
            } else {
                System.out.println("Создание таблицы в выбранной базе данных.");
                String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age TINYINT not NULL)";

                statement.executeUpdate(sql);
                System.out.println("Таблица успешно создана.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            DatabaseMetaData dataBase = connection.getMetaData();
            ResultSet rs = dataBase.getTables(null, null, "user", null);
            if (!rs.next()) {
                System.out.println("Такая таблица отсутствует.");
            } else {
                System.out.println("Удаление таблицы из выбранной базы данных.");
                String sql = "DROP TABLE user";
                statement.executeUpdate(sql);
                System.out.println("Таблица удалена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = getConnection();
        System.out.println("Добавление в таблицу данных.");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        System.out.println("Удаление User из таблицы по id");
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User удален.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = getConnection();
        Statement statement = null;
        List<User> users = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user");

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable () {
        Connection connection = getConnection();
        Statement statement = null;
        System.out.println("Очистка содержания таблицы.");
        try {
                statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM user");
                System.out.println("Таблица очищена.");
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
