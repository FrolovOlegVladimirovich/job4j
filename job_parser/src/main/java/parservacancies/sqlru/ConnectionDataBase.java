package parservacancies.sqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parservacancies.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create connection to database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ConnectionDataBase implements ConnectionManager, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(ConnectionDataBase.class.getName());
    private Connection connection;
    private final Config config;

    /**
     * @param config Configuration settings from resources file for connecting to the database.
     */
    public ConnectionDataBase(Config config) {
        this.config = config;
    }

    /**
     * @return Connection to database.
     */
    @Override
    public Connection getConnection() {
        LOG.info("Connecting to database");
        try {
            Class.forName(config.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password"));
        } catch (SQLException | ClassNotFoundException e) {
            LOG.error("Database connection error: ", e.fillInStackTrace());
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}