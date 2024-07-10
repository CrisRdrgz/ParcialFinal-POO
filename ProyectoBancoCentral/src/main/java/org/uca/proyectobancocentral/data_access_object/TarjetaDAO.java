package org.uca.proyectobancocentral.data_access_object; // 00078323 Paquete que contiene las clases de acceso a datos

import org.uca.proyectobancocentral.Clases.Cliente; // 00078323 Importa la clase Cliente
import org.uca.proyectobancocentral.Clases.Tarjeta; // 00078323 Importa la clase Tarjeta
import org.uca.proyectobancocentral.singleton.DatabaseConnection; // 00078323 Importa la clase DatabaseConnection para la conexión a la base de datos

import java.sql.*; // 00078323 Importa todas las clases del paquete java.sql
import java.util.ArrayList; // 00078323 Importa la clase ArrayList para listas dinámicas
import java.util.List; // 00078323 Importa la interfaz List

public class TarjetaDAO { // 00078323 Clase que maneja las operaciones de la tabla Tarjeta
    public void crearTablaTarjeta(){ // 00078323 Método para crear la tabla Tarjeta
        String query = "create table Tarjeta (" + // 00078323 Consulta SQL para crear la tabla Tarjeta
                "id int primary key identity," + // 00078323 Columna id como clave primaria con incremento automático
                "numero varchar(20) not null, " + // 00078323 Columna numero con máximo 20 caracteres y no nulo
                "fecha_expiracion date not null, " + // 00078323 Columna fecha_expiracion de tipo date y no nulo
                "tipo varchar(15) not null, " + // 00078323 Columna tipo con máximo 15 caracteres y no nulo
                "facilitador varchar(20) not null, " + // 00078323 Columna facilitador con máximo 20 caracteres y no nulo
                "id_cliente int not null, " + // 00078323 Columna id_cliente de tipo int y no nulo
                "foreign key (id_cliente) references Cliente(id) on delete cascade);"; // 00078323 Llave foránea referenciando a id de la tabla Cliente con borrado en cascada
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para crear la tabla
            System.out.println("Tabla Tarjeta registrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    public void registrarTarjeta(Tarjeta tarjeta){ // 00078323 Método para registrar una tarjeta en la base de datos
        String query = "insert into Tarjeta (numero, fecha_expiracion, tipo, facilitador, id_cliente) values (?,?,?,?,?)"; // 00078323 Consulta SQL para insertar una tarjeta
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            PreparedStatement statement = connection.prepareStatement(query); // 00078323 Crea un objeto PreparedStatement para ejecutar la consulta
            statement.setString(1, tarjeta.getNumeroTarjeta()); // 00078323 Asigna el valor del número de tarjeta al primer parámetro
            statement.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion())); // 00078323 Asigna el valor de la fecha de expiración al segundo parámetro
            statement.setString(3, tarjeta.getTipoTarjeta()); // 00078323 Asigna el valor del tipo de tarjeta al tercer parámetro
            statement.setString(4, tarjeta.getFacilitador()); // 00078323 Asigna el valor del facilitador al cuarto parámetro
            statement.setInt(5, tarjeta.getClienteId()); // 00078323 Asigna el valor del ID del cliente al quinto parámetro
            statement.executeUpdate(); // 00078323 Ejecuta la consulta de inserción
            statement.close(); // 00078323 Cierra el PreparedStatement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    public void BorrarTablaTarjeta(){ // 00078323 Método para borrar la tabla Tarjeta
        String query = "drop table if exists Tarjeta"; // 00078323 Consulta SQL para borrar la tabla Tarjeta si existe
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para borrar la tabla
            System.out.println("Tabla Tarjeta borrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }
}
