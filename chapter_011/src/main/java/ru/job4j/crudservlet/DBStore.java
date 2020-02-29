package ru.job4j.crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of Store that uses PostgreSQL database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Store INSTANCE = new DBStore();
    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    private DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/servlet");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static Store getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public User add(User model) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format("%s %s",
                     "INSERT INTO users (name, login, email, createdate, photo_id, password, role_id)",
                     "VALUES (?, ?, ?, ?, ?, ?, (SELECT id FROM user_role WHERE name = ?)) RETURNING id")
             )
        ) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getLogin());
            statement.setString(3, model.getEmail());
            statement.setTimestamp(4, new Timestamp(model.getCreateDate().getTime()));
            statement.setString(5, model.getPhotoId());
            statement.setString(6, model.getPassword());
            statement.setString(7, model.getRole());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                model.setId(String.valueOf(resultSet.getInt("id")));
            }
            LOG.info("Added user to database");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return model;
    }

    @Override
    public void update(User model) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET name = ?, role_id = (SELECT id FROM user_role WHERE name = ?) WHERE id = ?")
        ) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getRole());
            statement.setInt(3, Integer.parseInt(model.getId()));
            statement.execute();
            LOG.info("Updated user in the data base");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
    }

    @Override
    public void delete(User model) {
        String photoId = getUserById(model).getPhotoId();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")
        ) {
            statement.setInt(1, Integer.parseInt(model.getId()));
            statement.execute();
            File file = new File("images" + File.separator + photoId);
            file.delete();
            LOG.info("Deleted user from database");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(
                     "%s %s",
                     "select u.id, u.name, u.login, u.email, u.createdate, u.photo_id, r.name from users as u",
                     "inner join user_role as r on u.role_id = r.id"
                     )
             )
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setCreateDate(resultSet.getTimestamp("createdate"));
                user.setPhotoId(resultSet.getString("photo_id"));
                user.setRole(resultSet.getString(7));
                result.add(user);
            }
            LOG.info("Found all users in the database");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return result;
    }

    @Override
    public String findById(User model) {
        return getUserById(model).toString();
    }

    @Override
    public User getUserById(User model) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")
        ) {
            statement.setInt(1, Integer.parseInt(model.getId()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new User();
                result.setId(resultSet.getString("id"));
                result.setName(resultSet.getString("name"));
                result.setLogin(resultSet.getString("login"));
                result.setEmail(resultSet.getString("email"));
                result.setCreateDate(resultSet.getTimestamp("createdate"));
                result.setPhotoId(resultSet.getString("photo_id"));
            }
            LOG.info("Found user by id in the database");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return result;
    }

    @Override
    public boolean containsUser(User model) {
        return getUserById(model) != null;
    }

    @Override
    public boolean containsLogin(User model) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login = ?")
        ) {
            statement.setString(1, model.getLogin());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            LOG.info("Checked user availability in the database by login");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return result;
    }

    @Override
    public boolean containsEmail(User model) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE email = ?")
        ) {
            statement.setString(1, model.getEmail());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            LOG.info("Checked user availability in the database by email");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return result;
    }

    @Override
    public User isCredential(User model) {
        User user = null;
        String login = model.getLogin();
        String password = model.getPassword();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(
                     "%s %s",
                     "SELECT u.id, u.name, r.name FROM users AS u INNER JOIN user_role AS r",
                     "ON u.role_id = r.id WHERE u.login = ? AND u.password = ?"
                     )
             )
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(String.valueOf(resultSet.getInt(1)));
                user.setName(resultSet.getString(2));
                user.setRole(String.valueOf(resultSet.getString(3)));
                user.setLogin(login);
            }
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return user;
    }
}