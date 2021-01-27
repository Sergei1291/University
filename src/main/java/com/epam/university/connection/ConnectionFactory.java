package com.epam.university.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String PROPERTIES_FILE = "jdbc.properties";
    private final static String URL_KEY_PROPERTY = "url";

    private final Properties properties = new Properties();

    public ConnectionFactory() {
        try {
            Class classConnectionFactory = ConnectionFactory.class;
            ClassLoader classLoaderConnectionFactory = classConnectionFactory.getClassLoader();
            InputStream inputStream = classLoaderConnectionFactory.getResourceAsStream(PROPERTIES_FILE);
            properties.load(inputStream);
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    public Connection create() {
        try {
            String url = properties.getProperty(URL_KEY_PROPERTY);
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ConnectionException(e.getMessage(), e);
        }
    }

}