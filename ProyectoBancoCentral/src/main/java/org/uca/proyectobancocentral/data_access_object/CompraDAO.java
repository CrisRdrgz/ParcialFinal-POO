package org.uca.proyectobancocentral.data_access_object; // 00078323 Paquete que contiene las clases de acceso a datos

import org.uca.proyectobancocentral.Clases.Compra; // 00078323 Importa la clase Compra
import org.uca.proyectobancocentral.Clases.Tarjeta; // 00078323 Importa la clase Tarjeta
import org.uca.proyectobancocentral.singleton.DatabaseConnection; // 00078323 Importa la clase DatabaseConnection para la conexión a la base de datos

import java.sql.*; // 00078323 Importa todas las clases del paquete java.sql
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public double obtenerTotalGastoPorClienteYMes(int mes, int anio, int clienteId) { // 00082923 declaramos funcion para el query
        double totalGasto = 0; // 00082923 declaramos variable a retornar
        String query = "SELECT SUM(co.total) AS total_gastado " + // 00082923 seleccionamos la sumatoria de los totales
                "FROM Compra co " +  // 00082923 de la tabla de compra que llamaremos co
                "INNER JOIN Tarjeta t ON co.id_tarjeta = t.id " +  // 00082923 donde se iguala la tarjeta de compra con el id de tarjeta
                "WHERE t.id_cliente = ? AND MONTH(co.fecha) = ? AND YEAR(co.fecha) = ?";  // 00082923 donde dejamos los espacios para colocar ID, mes y año
        try { // 00082923 inicio try catch
            Connection connection = DatabaseConnection.getInstance().getConnection();  // 00082923 obtiene una conexion a la base de datos
            PreparedStatement statement = connection.prepareStatement(query); // 00082923 prepara la declaracion con el query dado
            statement.setInt(1, clienteId); // 00082923 establece el primer ? como el de ID
            statement.setInt(2, mes); // 00082923 establece el segundo ? como el de mes
            statement.setInt(3, anio); // 00082923 establece el tercer ? como el de año
            ResultSet resultSet = statement.executeQuery(); // 00082923 Ejecuta el query
            if (resultSet.next()) { // 00082923 si hay resultado
                totalGasto = resultSet.getDouble("total_gastado"); // 00082923 obtiene el valor de la columna y lo asigna a totalGasto
            }
            resultSet.close(); // 00082923 cierra el resultSet
            statement.close(); // 00082923 cierra la declaracion
            connection.close(); // 00082923 cierra la conexion a la BD
        } catch (SQLException e) { // 00082923 cierre try catch
            e.printStackTrace(); // 00082923 imprime error
        }
        return totalGasto; // 00082923 devuelve el resultado del query
    }

    public List<Compra> mostrarIDPorCompra(int clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Compra> compras = new ArrayList<>();
        String query = "SELECT c.id, c.fecha, c.total, c.descripcion, c.id_tarjeta " +
                "FROM Compra c " +
                "JOIN Tarjeta t ON c.id_tarjeta = t.id " +
                "WHERE t.id_cliente = ? " +
                "AND c.fecha BETWEEN ? AND ?";
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://DESKTOP-3OJ0C1N\\instanceName:1433;databaseName=BancoCentral;integratedSecurity=true;encrypt=false;";
            //Connection connection = DriverManager.getConnection(url);
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, clienteId);
            pstmt.setDate(2, Date.valueOf(fechaInicio));
            pstmt.setDate(3, Date.valueOf(fechaFin));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate fechaCompra = rs.getDate("fecha").toLocalDate();
                double montoTotal = rs.getDouble("total");
                String descripcion = rs.getString("descripcion");
                int tarjetaId = rs.getInt("id_tarjeta");
                Compra compra = new Compra(id, fechaCompra, montoTotal, descripcion, tarjetaId);
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        return compras;
}

}

