package org.uca.proyectobancocentral.data_access_object; // 00078323 Paquete que contiene las clases de acceso a datos

import org.uca.proyectobancocentral.Clases.Cliente; // 00078323 Importa la clase Cliente
import org.uca.proyectobancocentral.Clases.Tarjeta; // 00078323 Importa la clase Tarjeta
import org.uca.proyectobancocentral.singleton.DatabaseConnection; // 00078323 Importa la clase DatabaseConnection para la conexión a la base de datos

import java.sql.*; // 00078323 Importa todas las clases del paquete java.sql
import java.util.ArrayList; // 00078323 Importa la clase ArrayList para listas dinámicas
import java.util.List; // 00078323 Importa la interfaz List

public class TarjetaDAO { // 00078323 Clase que maneja las operaciones de la tabla Tarjeta
    public void crearTablaTarjeta(){ // 00078323 Método para crear la tabla Tarjeta
        String query = "create table Tarjeta (" + // 00078323 Consulta SQL para crear la tabla Tarjeta
                "id int primary key identity," + // 00078323 Columna id como clave primaria con incremento automático
                "numero varchar(20) not null, " + // 00078323 Columna numero con máximo 20 caracteres y no nulo
                "fecha_expiracion date not null, " + // 00078323 Columna fecha_expiracion de tipo date y no nulo
                "tipo varchar(15) not null, " + // 00078323 Columna tipo con máximo 15 caracteres y no nulo
                "facilitador varchar(20) not null, " + // 00078323 Columna facilitador con máximo 20 caracteres y no nulo
                "id_cliente int not null, " + // 00078323 Columna id_cliente de tipo int y no nulo
                "foreign key (id_cliente) references Cliente(id) on delete cascade);"; // 00078323 Llave foránea referenciando a id de la tabla Cliente con borrado en cascada
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para crear la tabla
            System.out.println("Tabla Tarjeta registrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    public void registrarTarjeta(Tarjeta tarjeta){ // 00078323 Método para registrar una tarjeta en la base de datos
        String query = "insert into Tarjeta (numero, fecha_expiracion, tipo, facilitador, id_cliente) values (?,?,?,?,?)"; // 00078323 Consulta SQL para insertar una tarjeta
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            PreparedStatement statement = connection.prepareStatement(query); // 00078323 Crea un objeto PreparedStatement para ejecutar la consulta
            statement.setString(1, tarjeta.getNumeroTarjeta()); // 00078323 Asigna el valor del número de tarjeta al primer parámetro
            statement.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion())); // 00078323 Asigna el valor de la fecha de expiración al segundo parámetro
            statement.setString(3, tarjeta.getTipoTarjeta()); // 00078323 Asigna el valor del tipo de tarjeta al tercer parámetro
            statement.setString(4, tarjeta.getFacilitador()); // 00078323 Asigna el valor del facilitador al cuarto parámetro
            statement.setInt(5, tarjeta.getClienteId()); // 00078323 Asigna el valor del ID del cliente al quinto parámetro
            statement.executeUpdate(); // 00078323 Ejecuta la consulta de inserción
            statement.close(); // 00078323 Cierra el PreparedStatement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
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
    public void BorrarTablaTarjeta(){ // 00078323 Método para borrar la tabla Tarjeta
        String query = "drop table if exists Tarjeta"; // 00078323 Consulta SQL para borrar la tabla Tarjeta si existe
        try{ // 00078323 Bloque try para manejar posibles excepciones SQL
            Connection connection = DatabaseConnection.getInstance().getConnection(); // 00078323 Obtiene la conexión a la base de datos
            Statement statement = connection.createStatement(); // 00078323 Crea un objeto Statement para ejecutar la consulta
            statement.execute(query); // 00078323 Ejecuta la consulta para borrar la tabla
            System.out.println("Tabla Tarjeta borrada con exito"); // 00078323 Imprime mensaje de éxito en consola
            statement.close(); // 00078323 Cierra el Statement
            connection.close(); // 00078323 Cierra la conexión a la base de datos
        }catch(SQLException e){ // 00078323 Captura excepciones SQL
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }
}
