package org.uca.proyectobancocentral.data_access_object;

import org.uca.proyectobancocentral.Clases.Compra;
import org.uca.proyectobancocentral.Clases.Tarjeta;
import org.uca.proyectobancocentral.singleton.DatabaseConnection;

import java.sql.*;

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
}
