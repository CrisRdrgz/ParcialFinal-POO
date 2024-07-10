package org.uca.proyectobancocentral.data_access_object;

import org.uca.proyectobancocentral.Clases.Compra;
import org.uca.proyectobancocentral.Clases.Tarjeta;
import org.uca.proyectobancocentral.singleton.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    public void crearTablaCompra(){
        String query = "create table Compra (" +
                "id int primary key identity," +
                "fecha date not null, " +
                "total decimal(18,2) not null, " +
                "descripcion varchar(50) not null, " +
                "id_tarjeta int not null, " +
                "foreign key (id_tarjeta) references Tarjeta(id) on delete cascade);";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabla Compra registrada con exito");
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void registrarCompra(Compra compra){
        String query = "insert into Compra (fecha, total, descripcion, id_tarjeta) values (?,?,?,?)";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(compra.getFechaCompra()));
            statement.setDouble(2, compra.getMontoTotal());
            statement.setString(3, compra.getDescripcion());
            statement.setInt(4, compra.getTarjetaId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void BorrarTablaCompra(){
        String query = "drop table if exists Compra";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabla Compra borrada con exito");
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
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

     public List<Compra> mostrarIDPorCompra(int clienteId, LocalDate fechaInicio, LocalDate fechaFin) { //00125123 El método mostrarIDPorCompra toma tres parámetros: clienteId, fechaInicio, y fechaFin
        List<Compra> compras = new ArrayList<>(); //00125123 crea una lista vacía de Compra llamada compras
        String query = "SELECT c.id, c.fecha, c.total, c.descripcion, c.id_tarjeta " + "FROM Compra c " + "JOIN Tarjeta t ON c.id_tarjeta = t.id " + "WHERE t.id_cliente = ? " + "AND c.fecha BETWEEN ? AND ?"; //selecciona las compras que coinciden con el ID del cliente y están dentro del rango de fechas especificado. Se usa una unión (JOIN) para vincular las tablas Compra y Tarjeta.
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection(); //00125123 obtener instancia de la conexion a la base de datos
            PreparedStatement pstmt = connection.prepareStatement(query); // 00125123 preparar la query para ejecutarla en la base de datos
            pstmt.setInt(1, clienteId); //00125123 asignando el primer parametro, clienteId de la query
            pstmt.setDate(2, Date.valueOf(fechaInicio)); //00125123 asignando el segundo parametro, clienteId de la query
            pstmt.setDate(3, Date.valueOf(fechaFin)); //00125123 asignando el tercer parametro, clienteId de la query
            ResultSet rs = pstmt.executeQuery(); //00125123 ejecutando la query y obteniendo la respuesta
            while (rs.next()) { //00125123 iterando la respuesta
                int id = rs.getInt("id"); //00125123 obteniendo el id y almacenandolo
                LocalDate fechaCompra = rs.getDate("fecha").toLocalDate(); //00125123 obteniendo la fechaCompra y almacenandolo
                double montoTotal = rs.getDouble("total"); //00125123 obteniendo el montoTotal y almacenandolo
                String descripcion = rs.getString("descripcion"); //00125123 obteniendo la descripcion y almacenandolo
                int tarjetaId = rs.getInt("id_tarjeta"); //00125123 obtenindo la tarjetaID y almacenandolo
                Compra compra = new Compra(id, fechaCompra, montoTotal, descripcion, tarjetaId); //00125123 creando objeto compra con los datos anteriores
                compras.add(compra); //00125123 agregando la compra a la lista de compras
            }
        } catch (SQLException e) { //00125123 controlando excepciones de sql
            e.printStackTrace(); //00125123 retornando el StackTrace de la exepcion
        }
        return compras; //00125123 retornando compras
}

}
