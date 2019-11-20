package parservacancies.sqlru.config;
import parservacancies.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Configuration settings from resources file for connecting to the database.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class ConfigFromResources implements Config {
    private static final Logger LOG = LogManager.getLogger(ConfigFromResources.class.getName());
    private final Properties properties;

    /**
     * Loads database settings and cron.time from "app.properties" file to the Properties values.
     */
    public ConfigFromResources(String resourceFileName) {
        properties = new Properties();
        try (InputStream in = ConfigFromResources.class.getClassLoader().getResourceAsStream(resourceFileName)) {
            properties.load(Objects.requireNonNull(in));
        } catch (IOException e) {
            LOG.error("Resource properties connection error: ", e.fillInStackTrace());
        }
    }

    /**
     * @param key:
     * jdbc.url
     * jdbc.username
     * jdbc.password
     * jdbc.driver
     * cron.time
     *
     * @return Property value.
     */
    @Override
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}