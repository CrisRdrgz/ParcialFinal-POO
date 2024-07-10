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
