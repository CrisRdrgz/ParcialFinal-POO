package org.uca.proyectobancocentral.data_access_object;

import org.uca.proyectobancocentral.Clases.Cliente;
import org.uca.proyectobancocentral.Clases.Tarjeta;
import org.uca.proyectobancocentral.singleton.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDAO {
    public void crearTablaTarjeta(){
        String query = "create table Tarjeta (" +
                "id int primary key identity," +
                "numero varchar(20) not null, " +
                "fecha_expiracion date not null, " +
                "tipo varchar(15) not null, " +
                "facilitador varchar(20) not null, " +
                "id_cliente int not null, " +
                "foreign key (id_cliente) references Cliente(id) on delete cascade);";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabla Tarjeta registrada con exito");
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void registrarTarjeta(Tarjeta tarjeta){
        String query = "insert into Tarjeta (numero, fecha_expiracion, tipo, facilitador, id_cliente) values (?,?,?,?,?)";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tarjeta.getNumeroTarjeta());
            statement.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion()));
            statement.setString(3, tarjeta.getTipoTarjeta());
            statement.setString(4, tarjeta.getFacilitador());
            statement.setInt(5, tarjeta.getClienteId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void BorrarTablaTarjeta(){
        String query = "drop table Tarjeta";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabla Tarjeta borrada con exito");
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
