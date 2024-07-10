package org.uca.proyectobancocentral.data_access_object; // 00078323 Paquete que contiene las clases de acceso a datos

import org.uca.proyectobancocentral.Clases.Cliente; // 00078323 Importa la clase Cliente
import org.uca.proyectobancocentral.singleton.DatabaseConnection; // 00078323 Importa la clase DatabaseConnection para la conexión a la base de datos

import java.sql.*; // 00078323 Importa todas las clases del paquete java.sql
import java.util.ArrayList; // 00078323 Importa la clase ArrayList para listas dinámicas
import java.util.List; // 00078323 Importa la interfaz List

public class ClienteDAO { // 00078323 Clase que maneja las operaciones de la tabla Cliente
    public void crearTablaCliente(){ // 00078323 Método para crear la tabla Cliente
        String query = "create table Cliente (" + // 00078323 Consulta SQL para crear la tabla Cliente
                "id int primary key identity," + // 00078323 Columna id como clave primaria con incremento automático
                "nombre_completo varchar(50) not null, " + // 00078323 Columna nombre_completo con máximo 50 caracteres y no nulo
                "direccion varchar(100) not null, " + // 00078323 Columna direccion con máximo 100 caracteres y no nulo
                "telefono varchar(20) not null);"; // 00078323 Columna telefono con máximo 20 caracteres y no nulo
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para crear la tabla
            System.out.println("Tabla Cliente registrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    public void registrarCliente(Cliente cliente){ // 00078323 Método para registrar un cliente en la base de datos
        String query = "insert into Cliente (nombre_completo, direccion, telefono) values (?,?,?)"; // 00078323 Consulta SQL para insertar un cliente
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            PreparedStatement statement = connection.prepareStatement(query); // 00078323 Crea un objeto PreparedStatement para ejecutar la consulta
            statement.setString(1, cliente.getNombreCompleto()); // 00078323 Asigna el valor del nombre completo al primer parámetro
            statement.setString(2, cliente.getDireccion()); // 00078323 Asigna el valor de la dirección al segundo parámetro
            statement.setString(3, cliente.getTelefono()); // 00078323 Asigna el valor del teléfono al tercer parámetro
            statement.executeUpdate(); // 00078323 Ejecuta la consulta de inserción
            statement.close(); // 00078323 Cierra el PreparedStatement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }
    public List<Cliente> comprasPorFacilitadorTarjeta(String facilitador){ // 00078323 Método para obtener clientes y sus compras por facilitador de tarjeta
        List<Cliente> clientes = new ArrayList<Cliente>(); // 00078323 Lista para almacenar los clientes
        String query = "select c.*, count(co.id) as cantidad_compras, sum(co.total) as total_gastado" + // 00078323 Consulta SQL para obtener clientes y sus compras
                " from Cliente c inner join Tarjeta t on t.id_cliente = c.id " + // 00078323 Join con la tabla Tarjeta
                "inner join Compra co on co.id_tarjeta = t.id where facilitador = ?" + // 00078323 Join con la tabla Compra y filtro por facilitador
                " group by c.id, c.nombre_completo, c.direccion, c.telefono"; // 00078323 Agrupación por columnas del cliente
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            PreparedStatement statement = connection.prepareStatement(query); // 00078323 Crea un objeto PreparedStatement para ejecutar la consulta
            statement.setString(1, facilitador); // 00078323 Asigna el valor del facilitador al primer parámetro
            ResultSet resultSet = statement.executeQuery(); // 00078323 Ejecuta la consulta y obtiene el ResultSet
            while(resultSet.next()){ // 00078323 Itera sobre los resultados
                System.out.println("Cliente encontrado: " + resultSet.getString("nombre_completo")); // 00078323 Imprime el nombre del cliente encontrado
                Cliente cliente = new Cliente(); // 00078323 Crea un nuevo objeto Cliente
                cliente.setId(resultSet.getInt("id")); // 00078323 Asigna el ID del cliente
                cliente.setNombreCompleto(resultSet.getString("nombre_completo")); // 00078323 Asigna el nombre completo del cliente
                cliente.setDireccion(resultSet.getString("direccion")); // 00078323 Asigna la dirección del cliente
                cliente.setTelefono(resultSet.getString("telefono")); // 00078323 Asigna el teléfono del cliente
                cliente.setCantidadCompras(resultSet.getInt("cantidad_compras")); // 00078323 Asigna la cantidad de compras del cliente
                cliente.setTotalGastado(resultSet.getDouble("total_gastado")); // 00078323 Asigna el total gastado por el cliente
                clientes.add(cliente); // 00078323 Añade el cliente a la lista
            }
            resultSet.close(); // 00078323 Cierra el ResultSet
            statement.close(); // 00078323 Cierra el PreparedStatement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        } catch (SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
        return clientes; // 00078323 Retorna la lista de clientes
    }

    public void BorrarTablaCliente(){ // 00078323 Método para borrar la tabla Cliente
        String query = "drop table if exists Cliente"; // 00078323 Consulta SQL para borrar la tabla Cliente si existe
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para borrar la tabla
            System.out.println("Tabla Cliente borrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }
}
