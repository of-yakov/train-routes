package ru.vsu.porkhunov.trainroutes.persistence.provider.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static final String DRIVER_KEY = "database.driver";
    private static final String URL_KEY = "database.connection.url";
    private static final String USER_KEY = "database.connection.user";
    private static final String PASSWORD_KEY = "database.connection.password";
    private static final String PROPERTIES_PATH = "resources/application.properties";

    private static ConnectionProvider connectionProvider;

    private final String url;
    private final String user;
    private final String password;

    private ConnectionProvider() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        url = properties.getProperty(URL_KEY);
        user = properties.getProperty(USER_KEY);
        password = properties.getProperty(PASSWORD_KEY);

        try {
            Class.forName(properties.getProperty(DRIVER_KEY));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionProvider getInstance() {
        if (connectionProvider == null) {
            connectionProvider = new ConnectionProvider();
        }

        return connectionProvider;
    }

    public Connection provide() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
