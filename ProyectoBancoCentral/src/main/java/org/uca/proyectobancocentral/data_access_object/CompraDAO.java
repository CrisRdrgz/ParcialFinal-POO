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
