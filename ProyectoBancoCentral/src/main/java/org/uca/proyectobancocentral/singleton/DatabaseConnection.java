package org.uca.proyectobancocentral.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private String url;

    private DatabaseConnection() throws SQLException {
        UserValidator userValidator = UserValidator.getInstance();
        String serverName = userValidator.getServername();
        this.url = "jdbc:sqlserver://" + serverName + ":1433;databaseName=BancoCentral;user=bandoadmin;password=12345;integratedSecurity=false;encrypt=false;";
        this.connection = DriverManager.getConnection(url);
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
