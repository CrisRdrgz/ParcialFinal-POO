package org.uca.proyectobancocentral.singleton; // 00078323 Paquete que contiene clases Singleton

import java.sql.Connection; // 00078323 Importa la clase Connection para conexiones a la base de datos
import java.sql.DriverManager; // 00078323 Importa la clase DriverManager para obtener conexiones
import java.sql.SQLException; // 00078323 Importa la clase SQLException para manejar excepciones SQL

public class DatabaseConnection { // 00078323 Clase Singleton para la conexión a la base de datos
    private static DatabaseConnection instance; // 00078323 Instancia única de DatabaseConnection
    private Connection connection; // 00078323 Objeto Connection para la conexión a la base de datos
    private String url; // 00078323 URL de la base de datos

    private DatabaseConnection() throws SQLException { // 00078323 Constructor privado que establece la conexión a la base de datos
        UserValidator userValidator = UserValidator.getInstance(); // 00078323 Obtiene la instancia única de UserValidator
        String serverName = userValidator.getServername(); // 00078323 Obtiene el nombre del servidor desde UserValidator
        this.url = "jdbc:sqlserver://" + serverName + ":1433;databaseName=BancoCentral;user=bancoadmin;password=12345;integratedSecurity=false;encrypt=false;"; // 00078323 Construye la URL de conexión
        this.connection = DriverManager.getConnection(url); // 00078323 Establece la conexión usando DriverManager
    }

    public static DatabaseConnection getInstance() throws SQLException { // 00078323 Método para obtener la instancia única de DatabaseConnection
        if (instance == null || instance.getConnection().isClosed()) { // 00078323 Si la instancia es nula o la conexión está cerrada, crea una nueva instancia
            instance = new DatabaseConnection(); // 00078323 Crea una nueva instancia de DatabaseConnection
        }
        return instance; // 00078323 Retorna la instancia única de DatabaseConnection
    }

    public Connection getConnection() { // 00078323 Método para obtener la conexión a la base de datos
        return connection; // 00078323 Retorna el objeto Connection
    }
}