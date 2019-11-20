package parservacancies;

/**
 * Configuration settings for connecting to the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public interface Config {

    /**
     * @param key Property key:
     * jdbc.url
     * jdbc.username
     * jdbc.password
     * jdbc.driver
     * cron.time
     * @return Property value.
     */
    String getProperty(String key);
}