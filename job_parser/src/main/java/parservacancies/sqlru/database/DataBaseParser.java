package parservacancies.sqlru.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parservacancies.DataBase;
import parservacancies.Vacancy;

import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the vacancy database and the necessary methods through PostgreSQL.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class DataBaseParser implements DataBase {
    private static final Logger LOG = LogManager.getLogger(DataBaseParser.class.getName());
    private final Connection connection;

    public DataBaseParser(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds vacancies to the database.
     * @param vacancies Set of Vacancy instances.
     */
    @Override
    public void addVacancies(Set<Vacancy> vacancies) {
        int numOfNewVacs = vacancies.size();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO vacancies(name, text, link, create_date) VALUES(?, ?, ?, ?);")) {
                for (Vacancy vacancy: vacancies) {
                    statement.setString(1, vacancy.getName());
                    statement.setString(2, vacancy.getText());
                    statement.setString(3, vacancy.getLink());
                    statement.setTimestamp(4, vacancy.getCreationDate());
                    statement.addBatch();
                }
                statement.executeBatch();
                LOG.info("{} vacancies have been successfully added to the database", numOfNewVacs);
            } catch (SQLException e) {
                LOG.error("Database access error", e.fillInStackTrace());
            }
        }
    }

    /**
     * @return A number of vacancies in the database.
     */
    @Override
    public int checkNumOfVacancies() {
        int result = -1;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(id) FROM vacancies;")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = resultSet.getInt("count");
                }
                resultSet.close();
            } catch (SQLException e) {
                LOG.error("Database connection error: ", e.fillInStackTrace());
            }
        }
        return result;
    }

    /**
     * @return The latest vacancy creation date.
     */
    @Override
    public Timestamp getLastCreationDate() {
        Timestamp result = null;
        if (connection != null && checkNumOfVacancies() > 0) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT MAX(create_date) FROM vacancies;")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = resultSet.getTimestamp("max");
                }
                resultSet.close();
                LOG.info("Last vacancy creation date is {}", new Date(Objects.requireNonNull(result).getTime()));
            } catch (SQLException e) {
                LOG.error("Database connection error: ", e.fillInStackTrace());
            }
        }
        return result;
    }
}