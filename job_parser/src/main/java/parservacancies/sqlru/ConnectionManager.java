package parservacancies.sqlru;

import java.sql.Connection;

/**
 * Connection manager to create and manage database connection.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface ConnectionManager {
    /**
     * @return Connection to database.
     */
    Connection getConnection();
}