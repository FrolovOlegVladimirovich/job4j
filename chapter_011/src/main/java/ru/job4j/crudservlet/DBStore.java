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
    private static final DBStore INSTANCE = new DBStore();
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

    public static DBStore getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public User add(User model) {
        String login = model.getLogin();
        String name = model.getName();
        String email = model.getEmail();
        String photoId = model.getPhotoId();
        long time = model.getCreateDate().getTime();
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, login, email, createdate, photo_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, email);
            statement.setTimestamp(4, new Timestamp(time));
            statement.setString(5, photoId);
            statement.execute();
            PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM users WHERE name = ? AND login = ? AND email = ?");
            selectStatement.setString(1, name);
            selectStatement.setString(2, login);
            selectStatement.setString(3, email);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                model.setId(String.valueOf(resultSet.getInt("id")));
            }
            statement.close();
            selectStatement.close();
            LOG.info("Added user to database");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return model;
    }

    @Override
    public void update(User model) {
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?");
            statement.setString(1, model.getName());
            statement.setInt(2, Integer.parseInt(model.getId()));
            statement.execute();
            statement.close();
            LOG.info("Updated user in database");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
    }

    @Override
    public void delete(User model) {
        String photoId = getUserById(model).getPhotoId();
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, Integer.parseInt(model.getId()));
            statement.execute();
            statement.close();
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
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setCreateDate(resultSet.getTimestamp("createdate"));
                user.setPhotoId(resultSet.getString("photo_id"));
                result.add(user);
            }
            resultSet.close();
            statement.close();
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
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
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
            resultSet.close();
            statement.close();
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
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login = ?");
            statement.setString(1, model.getLogin());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            resultSet.close();
            statement.close();
            LOG.info("Checked user availability in the database by login");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return result;
    }

    @Override
    public boolean containsEmail(User model) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE email = ?");
            statement.setString(1, model.getEmail());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            resultSet.close();
            statement.close();
            LOG.info("Checked user availability in the database by email");
        } catch (SQLException e) {
            LOG.error("Database access error", e.fillInStackTrace());
        }
        return result;
    }
}