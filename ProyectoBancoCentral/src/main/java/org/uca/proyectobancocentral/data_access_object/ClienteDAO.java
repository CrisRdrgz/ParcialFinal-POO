package org.uca.proyectobancocentral.data_access_object;

import org.uca.proyectobancocentral.Clases.Cliente;
import org.uca.proyectobancocentral.singleton.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void crearTablaCliente(){
        String query = "create table Cliente (" +
                "id int primary key identity," +
                "nombre_completo varchar(50) not null, " +
                "direccion varchar(100) not null, " +
                "telefono varchar(20) not null);";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabla Cliente registrada con exito");
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void registrarCliente(Cliente cliente){
        String query = "insert into Cliente (nombre_completo, direccion, telefono) values (?,?,?)";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cliente.getNombreCompleto());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getTelefono());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Cliente> comprasPorFacilitadorTarjeta(String facilitador){
        List<Cliente> clientes = new ArrayList<Cliente>();
        String query = "select c.*, count(co.id) as cantidad_compras, sum(co.monto) as total_gastado" +
                " from Cliente c inner join Tarjeta t on t.id_cliente = c.id " +
                "inner join Compra co on co.id_tarjeta = t.id where facilitador = ?" +
                " group by c.id, c.nombre_completo, c.direccion, c.telefono";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, facilitador);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNombreCompleto(resultSet.getString("nombre_completo"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setCantidadCompras(resultSet.getInt("cantidad_compras"));
                cliente.setTotalGastado(resultSet.getDouble("total_gastado"));
                clientes.add(cliente);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    public void BorrarTablaCliente(){
        String query = "drop table Cliente";
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tabla Cliente borrada con exito");
            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
