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

    public List<Tarjeta> bucarTarjetasPorId(int clienteId) {
        List<Tarjeta> tarjetas = new ArrayList<>();
        String query = "SELECT " +
                "CONCAT('XXXX XXXX XXXX ', RIGHT(numero, 4)) AS numero_censurado, " +
                "tipo " +
                "FROM Tarjeta WHERE id_cliente = ?";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();
            boolean tieneTC = false;
            boolean tieneTD = false;

            while (resultSet.next()) {
                String numeroCensurado = resultSet.getString("numero_censurado");
                String tipo = resultSet.getString("tipo");
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setNumeroTarjeta(numeroCensurado);
                tarjeta.setTipoTarjeta(tipo);
                tarjetas.add(tarjeta);

                if (tipo.equalsIgnoreCase("Credito")) {
                    tieneTC = true;
                } else if (tipo.equalsIgnoreCase("Debito")) {
                    tieneTD = true;
                }
            }

            if (!tieneTC) {
                Tarjeta noCredito = new Tarjeta();
                noCredito.setNumeroTarjeta("N/A");
                noCredito.setTipoTarjeta("credito");
                tarjetas.add(noCredito);
            }

            if (!tieneTD) {
                Tarjeta noDebito = new Tarjeta();
                noDebito.setNumeroTarjeta("N/A");
                noDebito.setTipoTarjeta("Debito");
                tarjetas.add(noDebito);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarjetas;
    }

    public void BorrarTablaTarjeta(){
        String query = "drop table if exists Tarjeta";
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
