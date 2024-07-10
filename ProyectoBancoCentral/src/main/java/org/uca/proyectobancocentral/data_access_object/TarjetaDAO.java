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

    public List<Tarjeta> bucarTarjetasPorId(int clienteId) {//00011223 metodo que devuelve una lista de objetos Tarjeta,busacndo por el id del cliente
        List<Tarjeta> tarjetas = new ArrayList<>();//00011223 lista para almacenar las tarjetas encontradas
        String query = "SELECT " +//00011223 creamos la consulta
                "CONCAT('XXXX XXXX XXXX ', RIGHT(numero, 4)) AS numero_censurado, " +//00011223 como queremos mostrar unicamente los ultimos 4 numeros utilizamos rigth para extraer los ultimos 4 numeros de la columna donde se encuentra el numero de tarjeta  y usamos el concat para representar los numeros que van antes con X
                "tipo " +//00011223 tambien seleccionamos el tipo de la tarjeta
                "FROM Tarjeta WHERE id_cliente = ?";//00011223 de la tabla Tarjeta y solo selecionaremos las filas donde la columna id_cliente coincida con el valor que vamos a obtener mas adelante
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();//00011223 obtenes la conexion a la base de datos
            PreparedStatement statement = connection.prepareStatement(query);//00011223 preparamos la consulta
            statement.setInt(1, clienteId);//00011223 establecemos el valor del parametro clienteId en la consulta
            ResultSet resultSet = statement.executeQuery();//00011223  ejecutamos la consulta y obtenemos el resultado en un ResultSet
            boolean tieneTC = false;//00011223 inicializamos una variable para verificar que si exita la tarjeta de credito
            boolean tieneTD = false;//00011223 inicializamos una variable para verificar que si exist la tarjeta de debito

            while (resultSet.next()) {//0011223 iteramos sobre el resultado de la consulta
                String numeroCensurado = resultSet.getString("numero_censurado");//00011223 obtiene el numero de la tarjeta censurado del resultado de la consulta
                String tipo = resultSet.getString("tipo");//00011223 obtiene el tipo de la tarjeta del resultado de la consulta
                Tarjeta tarjeta = new Tarjeta();//00011223 instancia de Tarjeta
                tarjeta.setNumeroTarjeta(numeroCensurado);//00011223 establece el nuemro de tarjeta censurado
                tarjeta.setTipoTarjeta(tipo);//00011223 establece el tipo de tarjeta
                tarjetas.add(tarjeta);//00011223 agreagamos la tarjeta a la lista de tarjetas

                if (tipo.equalsIgnoreCase("Credito")) {//00011223 verifica si el tipo de tarjeta es Credito
                    tieneTC = true;//00011223 cambiamos a true porque si es tarjeta de credito
                } else if (tipo.equalsIgnoreCase("Debito")) {//00011223 verifica si el tipo de tarjetea aes de Debito
                    tieneTD = true;//00011223 cambiamos a true porque si es de debito
                }
            }

            if (!tieneTC) {//00011223 condicion de si no hay tarjeta de credito
                Tarjeta noCredito = new Tarjeta();//00011223 se crea una instancia de Tarjeta
                noCredito.setNumeroTarjeta("N/A");//00011223 como no hay tarjeta le ponemos N/A en el numero de tarjeta
                noCredito.setTipoTarjeta("credito");//00011223 le ponemos en el tipo de tarjeta credito porque ese es el tipo que estamos verificando
                tarjetas.add(noCredito);//00011223 se agrega a la lista
            }

            if (!tieneTD) {//00011223 condicion de si no hay tarjeta de debito
                Tarjeta noDebito = new Tarjeta();//00011223 se crea una instancia de Tarjeta
                noDebito.setNumeroTarjeta("N/A");//00011223 como no tiene tarjeta le ponemos NA en el numero de tarjeta
                noDebito.setTipoTarjeta("Debito");//00011223 y como tipo le ponemos debito porque esa es el tipo de tarjeta que estamos verificando
                tarjetas.add(noDebito);//00011223 se agreaga ala lista
            }
            resultSet.close();//00011223 cierra el resultset
            statement.close();//00011223 cierra el preparedstatement
            connection.close();//00011223 cierra la conexion de la bd
        } catch (SQLException e) {e.printStackTrace();//00011223 impirme la excepcion si es que ocurre
        }
        return tarjetas;//00011223 retorna la lista de tarjetas
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
