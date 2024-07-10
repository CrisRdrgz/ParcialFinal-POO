package org.uca.proyectobancocentral.data_access_object; // 00078323 Paquete que contiene las clases de acceso a datos

import org.uca.proyectobancocentral.Clases.Compra; // 00078323 Importa la clase Compra
import org.uca.proyectobancocentral.Clases.Tarjeta; // 00078323 Importa la clase Tarjeta
import org.uca.proyectobancocentral.singleton.DatabaseConnection; // 00078323 Importa la clase DatabaseConnection para la conexión a la base de datos

import java.sql.*; // 00078323 Importa todas las clases del paquete java.sql

public class CompraDAO { // 00078323 Clase que maneja las operaciones de la tabla Compra
    public void crearTablaCompra(){ // 00078323 Método para crear la tabla Compra
        String query = "create table Compra (" + // 00078323 Consulta SQL para crear la tabla Compra
                "id int primary key identity," + // 00078323 Columna id como clave primaria con incremento automático
                "fecha date not null, " + // 00078323 Columna fecha de tipo date y no nulo
                "total decimal(18,2) not null, " + // 00078323 Columna total de tipo decimal con 18 dígitos en total y 2 decimales, no nulo
                "descripcion varchar(50) not null, " + // 00078323 Columna descripcion con máximo 50 caracteres y no nulo
                "id_tarjeta int not null, " + // 00078323 Columna id_tarjeta de tipo int y no nulo
                "foreign key (id_tarjeta) references Tarjeta(id) on delete cascade);"; // 00078323 Llave foránea referenciando a id de la tabla Tarjeta con borrado en cascada
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para crear la tabla
            System.out.println("Tabla Compra registrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    public void registrarCompra(Compra compra){ // 00078323 Método para registrar una compra en la base de datos
        String query = "insert into Compra (fecha, total, descripcion, id_tarjeta) values (?,?,?,?)"; // 00078323 Consulta SQL para insertar una compra
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            PreparedStatement statement = connection.prepareStatement(query); // 00078323 Crea un objeto PreparedStatement para ejecutar la consulta
            statement.setDate(1, Date.valueOf(compra.getFechaCompra())); // 00078323 Asigna el valor de la fecha al primer parámetro
            statement.setDouble(2, compra.getMontoTotal()); // 00078323 Asigna el valor del monto total al segundo parámetro
            statement.setString(3, compra.getDescripcion()); // 00078323 Asigna el valor de la descripción al tercer parámetro
            statement.setInt(4, compra.getTarjetaId()); // 00078323 Asigna el valor del ID de la tarjeta al cuarto parámetro
            statement.executeUpdate(); // 00078323 Ejecuta la consulta de inserción
            statement.close(); // 00078323 Cierra el PreparedStatement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    public void BorrarTablaCompra(){ // 00078323 Método para borrar la tabla Compra
        String query = "drop table if exists Compra"; // 00078323 Consulta SQL para borrar la tabla Compra si existe
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para borrar la tabla
            System.out.println("Tabla Compra borrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }
}

