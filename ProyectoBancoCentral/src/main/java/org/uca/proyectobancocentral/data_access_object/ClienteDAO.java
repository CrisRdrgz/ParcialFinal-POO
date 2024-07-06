package org.uca.proyectobancocentral.data_access_object;

import org.uca.proyectobancocentral.Clases.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String url = "jdbc:sqlserver://LAPTOP-SJ7S2DJL:1433;databaseName=BancoCentral;user=bancoadmin;password=12345;integratedSecurity=false;encrypt=false;";

    public void crearTablaCliente(){
        String query = "create table Cliente (" +
                "id int primary key identity, " +
                "nombre_completo varchar(50), " +
                "direccion varchar(100), " +
                "telefono varchar(20));";
        try{
            Connection connection = DriverManager.getConnection(url);
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
        String query = "insert into Clientes (nombre_completo, direccion, telefono) values (?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(url);
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
                " from cliente c inner join tarjeta t on t.id_cliente = c.id " +
                "inner join compra co on co.id_tarjeta = t.id where facilitador = ?" +
                " group by c.id, c.nombre_completo, c.direccion, c.telefono";
        try{
            Connection connection = DriverManager.getConnection(url);
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
}
