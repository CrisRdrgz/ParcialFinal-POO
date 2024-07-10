package org.uca.proyectobancocentral; // 00078323 Paquete principal de la aplicación

import javafx.application.Platform; // 00078323 Importa la clase Platform de JavaFX
import javafx.collections.FXCollections; // 00078323 Importa la clase FXCollections para manejar colecciones JavaFX
import javafx.collections.ObservableList; // 00078323 Importa la clase ObservableList para listas observables de JavaFX
import javafx.fxml.FXML; // 00078323 Importa la anotación FXML para inyectar componentes en JavaFX
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory; // 00078323 Importa la clase PropertyValueFactory para las celdas de TableView
import javafx.stage.Stage; // 00078323 Importa la clase Stage de JavaFX
import org.uca.proyectobancocentral.Clases.Cliente; // 00078323 Importa la clase Cliente
import org.uca.proyectobancocentral.Clases.Compra; // 00078323 Importa la clase Compra
import org.uca.proyectobancocentral.Clases.Tarjeta; // 00078323 Importa la clase Tarjeta
import org.uca.proyectobancocentral.data_access_object.ClienteDAO; // 00078323 Importa la clase ClienteDAO
import org.uca.proyectobancocentral.data_access_object.CompraDAO; // 00078323 Importa la clase CompraDAO
import org.uca.proyectobancocentral.data_access_object.TarjetaDAO; // 00078323 Importa la clase TarjetaDAO
import org.uca.proyectobancocentral.singleton.UserValidator; // 00078323 Importa la clase UserValidator

import java.io.BufferedWriter; // 00078323 Importa la clase BufferedWriter para escritura en archivos
import java.io.FileWriter; // 00078323 Importa la clase FileWriter para escribir archivos
import java.io.IOException; // 00078323 Importa la clase IOException para manejar excepciones de E/S
import java.sql.SQLException; // 00078323 Importa la clase SQLException para manejar excepciones SQL
import java.time.LocalDate; // 00078323 Importa la clase LocalDate para manejo de fechas
import java.time.LocalDateTime; // 00078323 Importa la clase LocalDateTime para manejo de fecha y hora
import java.time.format.DateTimeFormatter; // 00078323 Importa la clase DateTimeFormatter para formateo de fechas
import java.util.List; // 00078323 Importa la interfaz List

public class HelloController { // 00078323 Controlador principal de la aplicación JavaFX
    @FXML
    private ComboBox cbReporteD; // 00078323 ComboBox para seleccionar el tipo de reporte
    @FXML
    private ComboBox<String>  mesComboBoxB; // 00082923 declaramos el Combo Box de mes
    @FXML
    private ComboBox<String>  anioComboBoxB; // 00082923 declaramos el Combo Box de año
    @FXML
    private TextField idTextFieldB; // 00082923 declaramos el Text field donde va el ID
    @FXML
    private Label totalLabelB; // 00082923 declaramos el Label donde se pondra el total
    @FXML
    private TableView<Cliente> tvReporteD; // 00078323 TableView para mostrar los clientes en el reporte
    @FXML
    private TableColumn<Cliente, Integer> colIdReporteD; // 00078323 Columna para el ID del cliente
    @FXML
    private TableColumn<Cliente, String> colNombreReporteD; // 00078323 Columna para el nombre completo del cliente
    @FXML
    private TableColumn<Cliente, String> colDireccionReporteD; // 00078323 Columna para la dirección del cliente
    @FXML
    private TableColumn<Cliente, String> colTelefonoReporteD; // 00078323 Columna para el teléfono del cliente
    @FXML
    private TableColumn<Cliente, Integer> colCantComprasReporteD; // 00078323 Columna para la cantidad de compras del cliente
    @FXML
    private TableView<Tarjeta> tvTarjetas;//00011223 declaro un table view que mostrara la lista de Tarjetas en la interfaz grafica
    @FXML
    private TableColumn<Tarjeta, String> colNumeroTarjeta;//00011223 declaro una columna de la tbla view que mostrar el numero de la tarjeta en la interfaza grafica
    @FXML
    private TableColumn<Tarjeta, String> colTipoTarjeta;//00011223 declaro una columna de la table viwe que mostrara de que tipo es la tarjeta
    @FXML
    private TextField tfClienteId;//0011223 declaro un textfield donde se ingresara el id del cliente para ver sus tarjetas
    @FXML
    private TableView<Compra> tvComprasReporteA; //00125123 declarando tabla de la vista
    @FXML
    private TableColumn<Compra, Integer> colIDReporteA; //00125123 declarando columna de id de la tabla A
    @FXML
    private TableColumn<Compra, LocalDate> colFechaReporteA; //00125123 declarando columna de fecha de la tabla A
    @FXML
    private TableColumn<Compra, String> colDescripcionReporteA; //00125123 declarando columna de la descripcion de la tabla A
    @FXML
    private TableColumn<Compra, Double> colTotalReporteA; //00125123 declarando columna del total de la tabla A
    @FXML
    private TableColumn<Compra, Integer> colIDTarjetaReporteA; //00125123 declarando columna de la tarjeta de la tabla A
    @FXML
    private TextField txIDReporteA; //00125123 declarando el text field para el id
    @FXML
    private DatePicker dtFechaReporteA; //00125123 declarando el date picker para seleccion de fecha inicio
    @FXML
    private DatePicker dtFechaFinalReporteA;
    @FXML
    private TableColumn<Cliente, Double> colTotalGastadoReporteD; // 00078323 Columna para el total gastado por el cliente

    private ClienteDAO clienteDAO = new ClienteDAO(); // 00078323 Instancia de ClienteDAO para acceso a datos de clientes
    private TarjetaDAO tarjetaDAO = new TarjetaDAO(); // 00078323 Instancia de TarjetaDAO para acceso a datos de tarjetas
    private CompraDAO compraDAO = new CompraDAO(); // 00078323 Instancia de CompraDAO para acceso a datos de compras

    @FXML        
    public void initialize() { // 00078323 Método initialize para inicializar el controlador
        colIdReporteD.setCellValueFactory(new PropertyValueFactory<>("id")); // 00078323 Configura la columna ID
        colNombreReporteD.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto")); // 00078323 Configura la columna Nombre
        colDireccionReporteD.setCellValueFactory(new PropertyValueFactory<>("direccion")); // 00078323 Configura la columna Dirección
        colTelefonoReporteD.setCellValueFactory(new PropertyValueFactory<>("telefono")); // 00078323 Configura la columna Teléfono
        colCantComprasReporteD.setCellValueFactory(new PropertyValueFactory<>("cantidadCompras")); // 00078323 Configura la columna Cantidad de Compras
        colTotalGastadoReporteD.setCellValueFactory(new PropertyValueFactory<>("totalGastado")); // 00078323 Configura la columna Total Gastado
        cbReporteD.getItems().addAll("Visa", "MasterCard", "American Express", "Discover", "Diners Club"); // 00078323 Añade opciones al ComboBox
      mesComboBoxB.getItems().addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
                "Septiembre", "Octubre", "Noviembre", "Diciembre"); // 00082923 le ponemos las opciones de meses
        anioComboBoxB.getItems().addAll("2023", "2024"); // 00082923 le ponemos opciones de años
        colNumeroTarjeta.setCellValueFactory(new PropertyValueFactory<>("numeroTarjeta"));//00011223  valores para colNumeroTarjeta para que use la propiedad numeroTarjeta de los objetos Tarjeta
        colTipoTarjeta.setCellValueFactory(new PropertyValueFactory<>("tipoTarjeta"));//00011223 valores para colTipoTarjeta para que use la propiedad tipoTarjeta de los objetos Tarjeta
            // Reporte A
                    colIDReporteA.setCellValueFactory(new PropertyValueFactory<>("id"));
                    colFechaReporteA.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
                    colTotalReporteA.setCellValueFactory(new PropertyValueFactory<>("montoTotal"));
                    colDescripcionReporteA.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
                    colIDTarjetaReporteA.setCellValueFactory(new PropertyValueFactory<>("tarjetaId"));


        tvReporteD.getItems().clear(); // 00078323 Limpia los ítems de la TableView

        compraDAO.BorrarTablaCompra(); // 00078323 Borra la tabla de compras si existe
        tarjetaDAO.BorrarTablaTarjeta(); // 00078323 Borra la tabla de tarjetas si existe
        clienteDAO.BorrarTablaCliente(); // 00078323 Borra la tabla de clientes si existe

        clienteDAO.crearTablaCliente(); // 00078323 Crea la tabla de clientes
        tarjetaDAO.crearTablaTarjeta(); // 00078323 Crea la tabla de tarjetas
        compraDAO.crearTablaCompra(); // 00078323 Crea la tabla de compras

        Cliente cliente1 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente1.setNombreCompleto("Juan Miguel Perez Lopez"); // 00078323 Establece el nombre del cliente
        cliente1.setDireccion("Calle La Mascota 923b, San Salvador"); // 00078323 Establece la dirección del cliente
        cliente1.setTelefono("70001000"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente1); // 00078323 Registra el cliente en la base de datos

        Cliente cliente2 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente2.setNombreCompleto("Maria Teresa Gomez Hernandez"); // 00078323 Establece el nombre del cliente
        cliente2.setDireccion("Avenida Roosevelt 123, San Miguel"); // 00078323 Establece la dirección del cliente
        cliente2.setTelefono("78752310"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente2); // 00078323 Registra el cliente en la base de datos

        Cliente cliente3 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente3.setNombreCompleto("Carlos Alberto Ruiz Garcia"); // 00078323 Establece el nombre del cliente
        cliente3.setDireccion("Calle Arce 456, Santa Ana"); // 00078323 Establece la dirección del cliente
        cliente3.setTelefono("75310970"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente3); // 00078323 Registra el cliente en la base de datos

        Cliente cliente4 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente4.setNombreCompleto("Ana Patricia Sanchez Diaz"); // 00078323 Establece el nombre del cliente
        cliente4.setDireccion("Avenida Masferrer 789, San Salvador"); // 00078323 Establece la dirección del cliente
        cliente4.setTelefono("78431000"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente4); // 00078323 Registra el cliente en la base de datos

        Cliente cliente5 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente5.setNombreCompleto("Jose Ricardo Fuentes Martinez"); // 00078323 Establece el nombre del cliente
        cliente5.setDireccion("Calle San Antonio 101, La Libertad"); // 00078323 Establece la dirección del cliente
        cliente5.setTelefono("70215001"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente5); // 00078323 Registra el cliente en la base de datos

        Cliente cliente6 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente6.setNombreCompleto("Sofia Margarita Castillo Lopez"); // 00078323 Establece el nombre del cliente
        cliente6.setDireccion("Avenida Los Héroes 202, San Salvador"); // 00078323 Establece la dirección del cliente
        cliente6.setTelefono("70588799"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente6); // 00078323 Registra el cliente en la base de datos

        Cliente cliente7 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente7.setNombreCompleto("Miguel Angel Hernandez Torres"); // 00078323 Establece el nombre del cliente
        cliente7.setDireccion("Calle San Juan 303, San Miguel"); // 00078323 Establece la dirección del cliente
        cliente7.setTelefono("70201579"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente7); // 00078323 Registra el cliente en la base de datos

        Cliente cliente8 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente8.setNombreCompleto("Jose Luis Martinez Reyes"); // 00078323 Establece el nombre del cliente
        cliente8.setDireccion("Calle Principal 456, Santa Ana"); // 00078323 Establece la dirección del cliente
        cliente8.setTelefono("70123456"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente8); // 00078323 Registra el cliente en la base de datos

        Cliente cliente9 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente9.setNombreCompleto("Carlos Roberto Perez Lopez"); // 00078323 Establece el nombre del cliente
        cliente9.setDireccion("Calle Libertad 789, San Salvador"); // 00078323 Establece la dirección del cliente
        cliente9.setTelefono("70345678"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente9); // 00078323 Registra el cliente en la base de datos

        Cliente cliente10 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente10.setNombreCompleto("Ana Maria Lopez Garcia"); // 00078323 Establece el nombre del cliente
        cliente10.setDireccion("Calle Independencia 123, Santa Tecla"); // 00078323 Establece la dirección del cliente
        cliente10.setTelefono("70456789"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente10); // 00078323 Registra el cliente en la base de datos

        Cliente cliente11 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente11.setNombreCompleto("Rosa Elena Morales Jimenez"); // 00078323 Establece el nombre del cliente
        cliente11.setDireccion("Calle Roosevelt 456, San Miguel"); // 00078323 Establece la dirección del cliente
        cliente11.setTelefono("70567890"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente11); // 00078323 Registra el cliente en la base de datos

        Cliente cliente12 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente12.setNombreCompleto("Luis Alfredo Ramirez Castro"); // 00078323 Establece el nombre del cliente
        cliente12.setDireccion("Calle Cuscatlan 789, Santa Ana"); // 00078323 Establece la dirección del cliente
        cliente12.setTelefono("70678901"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente12); // 00078323 Registra el cliente en la base de datos

        Cliente cliente13 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente13.setNombreCompleto("Sofia Beatriz Flores Vasquez"); // 00078323 Establece el nombre del cliente
        cliente13.setDireccion("Calle 15 de Septiembre 123, La Libertad"); // 00078323 Establece la dirección del cliente
        cliente13.setTelefono("70789012"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente13); // 00078323 Registra el cliente en la base de datos

        Cliente cliente14 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente14.setNombreCompleto("Carlos David Sanchez Hernandez"); // 00078323 Establece el nombre del cliente
        cliente14.setDireccion("Calle Los Heroes 456, San Salvador"); // 00078323 Establece la dirección del cliente
        cliente14.setTelefono("70890123"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente14); // 00078323 Registra el cliente en la base de datos

        Cliente cliente15 = new Cliente(); // 00078323 Crea un nuevo cliente
        cliente15.setNombreCompleto("Maria Fernanda Gonzalez Mejia"); // 00078323 Establece el nombre del cliente
        cliente15.setDireccion("Calle Arce 789, Santa Tecla"); // 00078323 Establece la dirección del cliente
        cliente15.setTelefono("70901234"); // 00078323 Establece el teléfono del cliente
        clienteDAO.registrarCliente(cliente15); // 00078323 Registra el cliente en la base de datos

        Tarjeta tarjeta1 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta1.setNumeroTarjeta("4535-7650-6743-0080"); // 00078323 Establece el número de la tarjeta
        tarjeta1.setFechaExpiracion(LocalDate.parse("2028-11-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta1.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta1.setFacilitador("MasterCard"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta1.setClienteId(4); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta1); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta2 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta2.setNumeroTarjeta("4535-1234-5678-9101"); // 00078323 Establece el número de la tarjeta
        tarjeta2.setFechaExpiracion(LocalDate.parse("2029-05-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta2.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta2.setFacilitador("Visa"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta2.setClienteId(1); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta2); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta3 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta3.setNumeroTarjeta("5432-0987-6543-2109"); // 00078323 Establece el número de la tarjeta
        tarjeta3.setFechaExpiracion(LocalDate.parse("2027-08-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta3.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta3.setFacilitador("American Express"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta3.setClienteId(2); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta3); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta4 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta4.setNumeroTarjeta("6011-1111-1111-1117"); // 00078323 Establece el número de la tarjeta
        tarjeta4.setFechaExpiracion(LocalDate.parse("2026-12-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta4.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta4.setFacilitador("Discover"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta4.setClienteId(6); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta4); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta5 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta5.setNumeroTarjeta("3530-1113-3330-0000"); // 00078323 Establece el número de la tarjeta
        tarjeta5.setFechaExpiracion(LocalDate.parse("2025-10-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta5.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta5.setFacilitador("Diners Club"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta5.setClienteId(5); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta5); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta6 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta6.setNumeroTarjeta("5500-0000-0000-0004"); // 00078323 Establece el número de la tarjeta
        tarjeta6.setFechaExpiracion(LocalDate.parse("2024-07-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta6.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta6.setFacilitador("MasterCard"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta6.setClienteId(3); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta6); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta7 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta7.setNumeroTarjeta("4111-1456-8960-1111"); // 00078323 Establece el número de la tarjeta
        tarjeta7.setFechaExpiracion(LocalDate.parse("2023-11-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta7.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta7.setFacilitador("Visa"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta7.setClienteId(10); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta7); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta8 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta8.setNumeroTarjeta("5423-3421-5648-3412"); // 00078323 Establece el número de la tarjeta
        tarjeta8.setFechaExpiracion(LocalDate.parse("2028-03-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta8.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta8.setFacilitador("Diners Club"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta8.setClienteId(9); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta8); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta9 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta9.setNumeroTarjeta("4350-8793-5423-6611"); // 00078323 Establece el número de la tarjeta
        tarjeta9.setFechaExpiracion(LocalDate.parse("2029-09-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta9.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta9.setFacilitador("Discover"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta9.setClienteId(7); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta9); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta10 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta10.setNumeroTarjeta("3530-3412-6548-3340"); // 00078323 Establece el número de la tarjeta
        tarjeta10.setFechaExpiracion(LocalDate.parse("2030-01-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta10.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta10.setFacilitador("MasterCard"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta10.setClienteId(10); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta10); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta11 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta11.setNumeroTarjeta("5432-3457-1230-1258"); // 00078323 Establece el número de la tarjeta
        tarjeta11.setFechaExpiracion(LocalDate.parse("2029-03-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta11.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta11.setFacilitador("Visa"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta11.setClienteId(14); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta11); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta12 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta12.setNumeroTarjeta("6897-1090-5643-1246"); // 00078323 Establece el número de la tarjeta
        tarjeta12.setFechaExpiracion(LocalDate.parse("2028-06-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta12.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta12.setFacilitador("American Express"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta12.setClienteId(15); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta12); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta13 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta13.setNumeroTarjeta("6534-1234-4321-4560"); // 00078323 Establece el número de la tarjeta
        tarjeta13.setFechaExpiracion(LocalDate.parse("2031-01-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta13.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta13.setFacilitador("Visa"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta13.setClienteId(11); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta13); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta14 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta14.setNumeroTarjeta("4509-1257-1090-3467"); // 00078323 Establece el número de la tarjeta
        tarjeta14.setFechaExpiracion(LocalDate.parse("2029-11-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta14.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta14.setFacilitador("Visa"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta14.setClienteId(13); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta14); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta15 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta15.setNumeroTarjeta("1209-4624-0900-0000"); // 00078323 Establece el número de la tarjeta
        tarjeta15.setFechaExpiracion(LocalDate.parse("2030-09-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta15.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta15.setFacilitador("American Express"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta15.setClienteId(12); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta15); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta16 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta16.setNumeroTarjeta("4590-0943-2367-8943"); // 00078323 Establece el número de la tarjeta
        tarjeta16.setFechaExpiracion(LocalDate.parse("2027-07-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta16.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta16.setFacilitador("Diners Club"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta16.setClienteId(9); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta16); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta17 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta17.setNumeroTarjeta("2950-3928-1111-2311"); // 00078323 Establece el número de la tarjeta
        tarjeta17.setFechaExpiracion(LocalDate.parse("2032-05-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta17.setTipoTarjeta("Debito"); // 00078323 Establece el tipo de tarjeta
        tarjeta17.setFacilitador("Visa"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta17.setClienteId(11); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta17); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta18 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta18.setNumeroTarjeta("0909-0000-2387-4610"); // 00078323 Establece el número de la tarjeta
        tarjeta18.setFechaExpiracion(LocalDate.parse("2028-02-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta18.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta18.setFacilitador("MasterCard"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta18.setClienteId(15); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta18); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta19 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta19.setNumeroTarjeta("9043-3213-5439-1246"); // 00078323 Establece el número de la tarjeta
        tarjeta19.setFechaExpiracion(LocalDate.parse("2029-08-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta19.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta19.setFacilitador("MasterCard"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta19.setClienteId(8); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta19); // 00078323 Registra la tarjeta en la base de datos

        Tarjeta tarjeta20 = new Tarjeta(); // 00078323 Crea una nueva tarjeta
        tarjeta20.setNumeroTarjeta("8902-1457-3090-3400"); // 00078323 Establece el número de la tarjeta
        tarjeta20.setFechaExpiracion(LocalDate.parse("2031-12-01")); // 00078323 Establece la fecha de expiración de la tarjeta
        tarjeta20.setTipoTarjeta("Credito"); // 00078323 Establece el tipo de tarjeta
        tarjeta20.setFacilitador("Diners Club"); // 00078323 Establece el facilitador de la tarjeta
        tarjeta20.setClienteId(12); // 00078323 Establece el ID del cliente asociado a la tarjeta
        tarjetaDAO.registrarTarjeta(tarjeta20); // 00078323 Registra la tarjeta en la base de datos

        Compra compra1 = new Compra(); // 00078323 Crea una nueva compra
        compra1.setFechaCompra(LocalDate.parse("2023-12-24")); // 00078323 Establece la fecha de la compra
        compra1.setMontoTotal(491.90); // 00078323 Establece el monto total de la compra
        compra1.setDescripcion("Regalos de Navidad"); // 00078323 Establece la descripción de la compra
        compra1.setTarjetaId(5); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra1); // 00078323 Registra la compra en la base de datos

        Compra compra2 = new Compra(); // 00078323 Crea una nueva compra
        compra2.setFechaCompra(LocalDate.parse("2023-11-15")); // 00078323 Establece la fecha de la compra
        compra2.setMontoTotal(257.10); // 00078323 Establece el monto total de la compra
        compra2.setDescripcion("Compra de electrónica"); // 00078323 Establece la descripción de la compra
        compra2.setTarjetaId(1); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra2); // 00078323 Registra la compra en la base de datos

        Compra compra3 = new Compra(); // 00078323 Crea una nueva compra
        compra3.setFechaCompra(LocalDate.parse("2023-10-10")); // 00078323 Establece la fecha de la compra
        compra3.setMontoTotal(75.20); // 00078323 Establece el monto total de la compra
        compra3.setDescripcion("Cena en restaurante"); // 00078323 Establece la descripción de la compra
        compra3.setTarjetaId(8); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra3); // 00078323 Registra la compra en la base de datos

        Compra compra4 = new Compra(); // 00078323 Crea una nueva compra
        compra4.setFechaCompra(LocalDate.parse("2023-09-05")); // 00078323 Establece la fecha de la compra
        compra4.setMontoTotal(49.99); // 00078323 Establece el monto total de la compra
        compra4.setDescripcion("Compra de libros"); // 00078323 Establece la descripción de la compra
        compra4.setTarjetaId(10); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra4); // 00078323 Registra la compra en la base de datos

        Compra compra5 = new Compra(); // 00078323 Crea una nueva compra
        compra5.setFechaCompra(LocalDate.parse("2023-08-20")); // 00078323 Establece la fecha de la compra
        compra5.setMontoTotal(307.45); // 00078323 Establece el monto total de la compra
        compra5.setDescripcion("Gastos médicos"); // 00078323 Establece la descripción de la compra
        compra5.setTarjetaId(4); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra5); // 00078323 Registra la compra en la base de datos

        Compra compra6 = new Compra(); // 00078323 Crea una nueva compra
        compra6.setFechaCompra(LocalDate.parse("2023-07-30")); // 00078323 Establece la fecha de la compra
        compra6.setMontoTotal(150.75); // 00078323 Establece el monto total de la compra
        compra6.setDescripcion("Pago de servicios"); // 00078323 Establece la descripción de la compra
        compra6.setTarjetaId(6); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra6); // 00078323 Registra la compra en la base de datos

        Compra compra7 = new Compra(); // 00078323 Crea una nueva compra
        compra7.setFechaCompra(LocalDate.parse("2024-06-14")); // 00078323 Establece la fecha de la compra
        compra7.setMontoTotal(128.90); // 00078323 Establece el monto total de la compra
        compra7.setDescripcion("Ropa nueva"); // 00078323 Establece la descripción de la compra
        compra7.setTarjetaId(7); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra7); // 00078323 Registra la compra en la base de datos

        Compra compra8 = new Compra(); // 00078323 Crea una nueva compra
        compra8.setFechaCompra(LocalDate.parse("2024-05-25")); // 00078323 Establece la fecha de la compra
        compra8.setMontoTotal(220.90); // 00078323 Establece el monto total de la compra
        compra8.setDescripcion("Muebles para el hogar"); // 00078323 Establece la descripción de la compra
        compra8.setTarjetaId(9); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra8); // 00078323 Registra la compra en la base de datos

        Compra compra9 = new Compra(); // 00078323 Crea una nueva compra
        compra9.setFechaCompra(LocalDate.parse("2024-04-10")); // 00078323 Establece la fecha de la compra
        compra9.setMontoTotal(45.60); // 00078323 Establece el monto total de la compra
        compra9.setDescripcion("Suscripción a servicios"); // 00078323 Establece la descripción de la compra
        compra9.setTarjetaId(2); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra9); // 00078323 Registra la compra en la base de datos

        Compra compra10 = new Compra(); // 00078323 Crea una nueva compra
        compra10.setFechaCompra(LocalDate.parse("2024-03-18")); // 00078323 Establece la fecha de la compra
        compra10.setMontoTotal(99.99); // 00078323 Establece el monto total de la compra
        compra10.setDescripcion("Accesorios de tecnología"); // 00078323 Establece la descripción de la compra
        compra10.setTarjetaId(4); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra10); // 00078323 Registra la compra en la base de datos

        Compra compra11 = new Compra(); // 00078323 Crea una nueva compra
        compra11.setFechaCompra(LocalDate.parse("2024-02-07")); // 00078323 Establece la fecha de la compra
        compra11.setMontoTotal(800.00); // 00078323 Establece el monto total de la compra
        compra11.setDescripcion("Viaje de fin de semana"); // 00078323 Establece la descripción de la compra
        compra11.setTarjetaId(3); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra11); // 00078323 Registra la compra en la base de datos

        Compra compra12 = new Compra(); // 00078323 Crea una nueva compra
        compra12.setFechaCompra(LocalDate.parse("2024-01-22")); // 00078323 Establece la fecha de la compra
        compra12.setMontoTotal(69.00); // 00078323 Establece el monto total de la compra
        compra12.setDescripcion("Material de oficina"); // 00078323 Establece la descripción de la compra
        compra12.setTarjetaId(7); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra12); // 00078323 Registra la compra en la base de datos

        Compra compra13 = new Compra(); // 00078323 Crea una nueva compra
        compra13.setFechaCompra(LocalDate.parse("2024-05-05")); // 00078323 Establece la fecha de la compra
        compra13.setMontoTotal(1340.50); // 00078323 Establece el monto total de la compra
        compra13.setDescripcion("Juguetes para niños"); // 00078323 Establece la descripción de la compra
        compra13.setTarjetaId(6); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra13); // 00078323 Registra la compra en la base de datos

        Compra compra14 = new Compra(); // 00078323 Crea una nueva compra
        compra14.setFechaCompra(LocalDate.parse("2024-02-10")); // 00078323 Establece la fecha de la compra
        compra14.setMontoTotal(254.20); // 00078323 Establece el monto total de la compra
        compra14.setDescripcion("Compra en tienda de deportes"); // 00078323 Establece la descripción de la compra
        compra14.setTarjetaId(10); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra14); // 00078323 Registra la compra en la base de datos

        Compra compra15 = new Compra(); // 00078323 Crea una nueva compra
        compra15.setFechaCompra(LocalDate.parse("2024-06-15")); // 00078323 Establece la fecha de la compra
        compra15.setMontoTotal(198.90); // 00078323 Establece el monto total de la compra
        compra15.setDescripcion("Supermercado"); // 00078323 Establece la descripción de la compra
        compra15.setTarjetaId(1); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra15); // 00078323 Registra la compra en la base de datos

        Compra compra16 = new Compra(); // 00078323 Crea una nueva compra
        compra16.setFechaCompra(LocalDate.parse("2024-01-30")); // 00078323 Establece la fecha de la compra
        compra16.setMontoTotal(199.99); // 00078323 Establece el monto total de la compra
        compra16.setDescripcion("Equipos de cocina"); // 00078323 Establece la descripción de la compra
        compra16.setTarjetaId(8); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra16); // 00078323 Registra la compra en la base de datos

        Compra compra17 = new Compra(); // 00078323 Crea una nueva compra
        compra17.setFechaCompra(LocalDate.parse("2023-08-10")); // 00078323 Establece la fecha de la compra
        compra17.setMontoTotal(105.80); // 00078323 Establece el monto total de la compra
        compra17.setDescripcion("Herramientas de jardín"); // 00078323 Establece la descripción de la compra
        compra17.setTarjetaId(1); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra17); // 00078323 Registra la compra en la base de datos

        Compra compra18 = new Compra(); // 00078323 Crea una nueva compra
        compra18.setFechaCompra(LocalDate.parse("2023-07-05")); // 00078323 Establece la fecha de la compra
        compra18.setMontoTotal(500.00); // 00078323 Establece el monto total de la compra
        compra18.setDescripcion("Regalos de cumpleaños"); // 00078323 Establece la descripción de la compra
        compra18.setTarjetaId(4); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra18); // 00078323 Registra la compra en la base de datos

        Compra compra19 = new Compra(); // 00078323 Crea una nueva compra
        compra19.setFechaCompra(LocalDate.parse("2024-06-22")); // 00078323 Establece la fecha de la compra
        compra19.setMontoTotal(130.00); // 00078323 Establece el monto total de la compra
        compra19.setDescripcion("Pago de membresía anual"); // 00078323 Establece la descripción de la compra
        compra19.setTarjetaId(8); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra19); // 00078323 Registra la compra en la base de datos

        Compra compra20 = new Compra(); // 00078323 Crea una nueva compra
        compra20.setFechaCompra(LocalDate.parse("2024-05-11")); // 00078323 Establece la fecha de la compra
        compra20.setMontoTotal(49.99); // 00078323 Establece el monto total de la compra
        compra20.setDescripcion("Suscripción a revista"); // 00078323 Establece la descripción de la compra
        compra20.setTarjetaId(6); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra20); // 00078323 Registra la compra en la base de datos

        Compra compra21 = new Compra(); // 00078323 Crea una nueva compra
        compra21.setFechaCompra(LocalDate.parse("2024-06-12")); // 00078323 Establece la fecha de la compra
        compra21.setMontoTotal(149.99); // 00078323 Establece el monto total de la compra
        compra21.setDescripcion("Compra de electrodomésticos"); // 00078323 Establece la descripción de la compra
        compra21.setTarjetaId(11); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra21); // 00078323 Registra la compra en la base de datos

        Compra compra22 = new Compra(); // 00078323 Crea una nueva compra
        compra22.setFechaCompra(LocalDate.parse("2024-07-03")); // 00078323 Establece la fecha de la compra
        compra22.setMontoTotal(679.02); // 00078323 Establece el monto total de la compra
        compra22.setDescripcion("Compra de muebles"); // 00078323 Establece la descripción de la compra
        compra22.setTarjetaId(12); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra22); // 00078323 Registra la compra en la base de datos

        Compra compra23 = new Compra(); // 00078323 Crea una nueva compra
        compra23.setFechaCompra(LocalDate.parse("2023-08-15")); // 00078323 Establece la fecha de la compra
        compra23.setMontoTotal(400.90); // 00078323 Establece el monto total de la compra
        compra23.setDescripcion("Compra de electrónica"); // 00078323 Establece la descripción de la compra
        compra23.setTarjetaId(13); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra23); // 00078323 Registra la compra en la base de datos

        Compra compra24 = new Compra(); // 00078323 Crea una nueva compra
        compra24.setFechaCompra(LocalDate.parse("2023-09-05")); // 00078323 Establece la fecha de la compra
        compra24.setMontoTotal(600.89); // 00078323 Establece el monto total de la compra
        compra24.setDescripcion("Compra de ropa"); // 00078323 Establece la descripción de la compra
        compra24.setTarjetaId(14); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra24); // 00078323 Registra la compra en la base de datos

        Compra compra25 = new Compra(); // 00078323 Crea una nueva compra
        compra25.setFechaCompra(LocalDate.parse("2023-10-01")); // 00078323 Establece la fecha de la compra
        compra25.setMontoTotal(79.90); // 00078323 Establece el monto total de la compra
        compra25.setDescripcion("Compra de calzado"); // 00078323 Establece la descripción de la compra
        compra25.setTarjetaId(15); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra25); // 00078323 Registra la compra en la base de datos

        Compra compra26 = new Compra(); // 00078323 Crea una nueva compra
        compra26.setFechaCompra(LocalDate.parse("2023-11-18")); // 00078323 Establece la fecha de la compra
        compra26.setMontoTotal(780.04); // 00078323 Establece el monto total de la compra
        compra26.setDescripcion("Compra de accesorios"); // 00078323 Establece la descripción de la compra
        compra26.setTarjetaId(16); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra26); // 00078323 Registra la compra en la base de datos

        Compra compra27 = new Compra(); // 00078323 Crea una nueva compra
        compra27.setFechaCompra(LocalDate.parse("2023-12-25")); // 00078323 Establece la fecha de la compra
        compra27.setMontoTotal(569.70); // 00078323 Establece el monto total de la compra
        compra27.setDescripcion("Compra de regalos"); // 00078323 Establece la descripción de la compra
        compra27.setTarjetaId(17); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra27); // 00078323 Registra la compra en la base de datos

        Compra compra28 = new Compra(); // 00078323 Crea una nueva compra
        compra28.setFechaCompra(LocalDate.parse("2023-01-30")); // 00078323 Establece la fecha de la compra
        compra28.setMontoTotal(129.95); // 00078323 Establece el monto total de la compra
        compra28.setDescripcion("Compra de libros"); // 00078323 Establece la descripción de la compra
        compra28.setTarjetaId(18); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra28); // 00078323 Registra la compra en la base de datos

        Compra compra29 = new Compra(); // 00078323 Crea una nueva compra
        compra29.setFechaCompra(LocalDate.parse("2023-02-15")); // 00078323 Establece la fecha de la compra
        compra29.setMontoTotal(240.00); // 00078323 Establece el monto total de la compra
        compra29.setDescripcion("Compra de herramientas"); // 00078323 Establece la descripción de la compra
        compra29.setTarjetaId(19); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra29); // 00078323 Registra la compra en la base de datos

        Compra compra30 = new Compra(); // 00078323 Crea una nueva compra
        compra30.setFechaCompra(LocalDate.parse("2023-03-20")); // 00078323 Establece la fecha de la compra
        compra30.setMontoTotal(567.80); // 00078323 Establece el monto total de la compra
        compra30.setDescripcion("Compra de muebles de jardín"); // 00078323 Establece la descripción de la compra
        compra30.setTarjetaId(20); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra30); // 00078323 Registra la compra en la base de datos

        Compra compra31 = new Compra(); // 00078323 Crea una nueva compra
        compra31.setFechaCompra(LocalDate.parse("2024-07-02")); // 00078323 Establece la fecha de la compra
        compra31.setMontoTotal(124.00); // 00078323 Establece el monto total de la compra
        compra31.setDescripcion("Compra de alimentos"); // 00078323 Establece la descripción de la compra
        compra31.setTarjetaId(18); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra31); // 00078323 Registra la compra en la base de datos

        Compra compra32 = new Compra(); // 00078323 Crea una nueva compra
        compra32.setFechaCompra(LocalDate.parse("2024-03-22")); // 00078323 Establece la fecha de la compra
        compra32.setMontoTotal(324.80); // 00078323 Establece el monto total de la compra
        compra32.setDescripcion("Compra de material de oficina"); // 00078323 Establece la descripción de la compra
        compra32.setTarjetaId(11); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra32); // 00078323 Registra la compra en la base de datos

        Compra compra33 = new Compra(); // 00078323 Crea una nueva compra
        compra33.setFechaCompra(LocalDate.parse("2023-09-30")); // 00078323 Establece la fecha de la compra
        compra33.setMontoTotal(95.79); // 00078323 Establece el monto total de la compra
        compra33.setDescripcion("Compra de medicinas"); // 00078323 Establece la descripción de la compra
        compra33.setTarjetaId(12); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra33); // 00078323 Registra la compra en la base de datos

        Compra compra34 = new Compra(); // 00078323 Crea una nueva compra
        compra34.setFechaCompra(LocalDate.parse("2023-10-12")); // 00078323 Establece la fecha de la compra
        compra34.setMontoTotal(209.67); // 00078323 Establece el monto total de la compra
        compra34.setDescripcion("Compra de accesorios para automóvil"); // 00078323 Establece la descripción de la compra
        compra34.setTarjetaId(17); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra34); // 00078323 Registra la compra en la base de datos

        Compra compra35 = new Compra(); // 00078323 Crea una nueva compra
        compra35.setFechaCompra(LocalDate.parse("2024-03-05")); // 00078323 Establece la fecha de la compra
        compra35.setMontoTotal(305.00); // 00078323 Establece el monto total de la compra
        compra35.setDescripcion("Compra de muebles de oficina"); // 00078323 Establece la descripción de la compra
        compra35.setTarjetaId(15); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra35); // 00078323 Registra la compra en la base de datos

        Compra compra36 = new Compra(); // 00078323 Crea una nueva compra
        compra36.setFechaCompra(LocalDate.parse("2023-12-20")); // 00078323 Establece la fecha de la compra
        compra36.setMontoTotal(40.70); // 00078323 Establece el monto total de la compra
        compra36.setDescripcion("Compra de productos de limpieza"); // 00078323 Establece la descripción de la compra
        compra36.setTarjetaId(13); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra36); // 00078323 Registra la compra en la base de datos

        Compra compra37 = new Compra(); // 00078323 Crea una nueva compra
        compra37.setFechaCompra(LocalDate.parse("2024-01-15")); // 00078323 Establece la fecha de la compra
        compra37.setMontoTotal(25.99); // 00078323 Establece el monto total de la compra
        compra37.setDescripcion("Compra de artículos deportivos"); // 00078323 Establece la descripción de la compra
        compra37.setTarjetaId(8); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra37); // 00078323 Registra la compra en la base de datos

        Compra compra38 = new Compra(); // 00078323 Crea una nueva compra
        compra38.setFechaCompra(LocalDate.parse("2024-02-28")); // 00078323 Establece la fecha de la compra
        compra38.setMontoTotal(60.00); // 00078323 Establece el monto total de la compra
        compra38.setDescripcion("Compra de juguetes"); // 00078323 Establece la descripción de la compra
        compra38.setTarjetaId(10); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra38); // 00078323 Registra la compra en la base de datos

        Compra compra39 = new Compra(); // 00078323 Crea una nueva compra
        compra39.setFechaCompra(LocalDate.parse("2024-03-10")); // 00078323 Establece la fecha de la compra
        compra39.setMontoTotal(368.10); // 00078323 Establece el monto total de la compra
        compra39.setDescripcion("Compra de productos electrónicos"); // 00078323 Establece la descripción de la compra
        compra39.setTarjetaId(6); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra39); // 00078323 Registra la compra en la base de datos

        Compra compra40 = new Compra(); // 00078323 Crea una nueva compra
        compra40.setFechaCompra(LocalDate.parse("2023-11-05")); // 00078323 Establece la fecha de la compra
        compra40.setMontoTotal(1673.25); // 00078323 Establece el monto total de la compra
        compra40.setDescripcion("Compra de material de construcción"); // 00078323 Establece la descripción de la compra
        compra40.setTarjetaId(4); // 00078323 Establece el ID de la tarjeta asociada a la compra
        compraDAO.registrarCompra(compra40); // 00078323 Registra la compra en la base de datos
    }

    @FXML
    private void handleBotonSeleccionarReporteD() { // 00078323 Maneja la acción del botón para seleccionar el reporte
        String facilitador = cbReporteD.getValue().toString(); // 00078323 Obtiene el facilitador seleccionado del ComboBox
        System.out.println(facilitador); // 00078323 Imprime el facilitador seleccionado en la consola
        if (facilitador != null) { // 00078323 Verifica que el facilitador no sea nulo
            tvReporteD.getItems().clear(); // 00078323 Limpia los ítems de la TableView
            try { // 00078323 Bloque try para manejar excepciones
                List<Cliente> clientes = clienteDAO.comprasPorFacilitadorTarjeta(facilitador); // 00078323 Obtiene la lista de clientes por facilitador
                System.out.println("Clientes agregados a la tabla: " + clientes.size()); // 00078323 Imprime el número de clientes agregados a la tabla
                clientes.forEach(cliente -> { // 00078323 Itera sobre la lista de clientes
                    System.out.println(cliente.getNombreCompleto()); // 00078323 Imprime el nombre del cliente en la consola
                    tvReporteD.getItems().add(cliente); // 00078323 Añade el cliente a la TableView
                });
            } catch (Exception e) { // 00078323 Captura excepciones
                e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
            }
        }
    }

    @FXML
    private void handleBotonGuardarArchivoReporteD() { // 00078323 Maneja la acción del botón para guardar el reporte en un archivo
        ObservableList<Cliente> clientes = tvReporteD.getItems(); // 00078323 Obtiene los ítems de la TableView
        if (clientes.isEmpty()) { // 00078323 Verifica si la lista de clientes está vacía
            return; // 00078323 Sale del método si la lista está vacía
        }
        String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")); // 00078323 Obtiene la fecha y hora actual formateada
        System.out.println(ahora); // 00078323 Imprime la fecha y hora actual en la consola
        System.out.println(System.getProperty("user.dir")); // 00078323 Imprime el directorio de trabajo actual en la consola
        String path = UserValidator.getInstance().getUserPath() + "ReporteD " + cbReporteD.getValue().toString() + " " + ahora + ".txt"; // 00078323 Construye la ruta del archivo de reporte

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) { // 00078323 Bloque try-with-resources para escribir en el archivo
            for (Cliente cliente : clientes) { // 00078323 Itera sobre la lista de clientes
                String line = String.format("ID: %d, Nombre: %s, Dirección: %s, Teléfono: %s, Cantidad Compras: %d, Total Gastado: $%.2f", cliente.getId(), cliente.getNombreCompleto(), cliente.getDireccion(), cliente.getTelefono(), cliente.getCantidadCompras(), cliente.getTotalGastado()); // 00078323 Formatea una línea con los datos del cliente
                writer.write(line); // 00078323 Escribe la línea en el archivo
                writer.newLine(); // 00078323 Escribe una nueva línea en el archivo
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00082923 declaramos alerta para avisar se creo
            alert.setTitle("Guardado..."); // 00082923 titulo de alerta
            alert.setHeaderText("Consulta almacenada"); // 00082923 encabezado de alerta
            alert.setContentText("La consulta que realizaste se guardo correctamente, puedes encontrarla en reportes!"); // 00082923 texto de la alerta
            alert.showAndWait(); // 00082923 muestra alerta hasta que se cierre
        } catch (IOException e) { // 00078323 Captura excepciones de E/S
            e.printStackTrace(); // 00078323 Imprime el stack trace de la excepción
        }
    }

    @FXML
    private void mostrarTarjetasPorId() {//00011223 creamos el metodo para mostrar las tarjetas
        int clienteId = Integer.parseInt(tfClienteId.getText());//00011223 agarramos el texto que se ingreso el el tfClienteid y lo convertimos a entero y lo asignamos a la variable que acabamos de crear clienteId
        List<Tarjeta> tarjetas = tarjetaDAO.bucarTarjetasPorId(clienteId);//00011223  llamamos el metodo buscarTarjetasPorId de la clase tarjetaDAO y le pasamos como parametro el clienteId se almacena el resultafo en la variable tarjetas que acabamos de crear
        System.out.println(tarjetas);//00011223 imprime las tarjetas en la consola (para ver que si funcionara xd)
        tvTarjetas.getItems().setAll(tarjetas);//00011223 actualiza ael table view con la lista de tarjetas
    }

    @FXML
    private void guardarArchivoReporteC() {//00011223 metodo para guardar el reporte en un txt
        ObservableList<Tarjeta> tarjetas = tvTarjetas.getItems();//00011223 obtiene la lista de lso objetos Tarjeta desde el table view
        if (tarjetas.isEmpty()) {return;}//00011223 verifica si la lista de tarjetas esta vacia

        String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));//00011223 variable para tener la fecha y hora actual
        String path = UserValidator.getInstance().getUserPath() + "ReporteC " + ahora + ".txt";//00011223 construye la ruta del archivo de reporte utilizando el camino del usuario y la fecha y hora actual.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {//00011223 intenta abrir un BufferedWriter para escribir en el archivo especificado en path
            for (Tarjeta tarjeta : tarjetas) {//00011223 recorre cada objeto Tarjeta en la lista tarjetas
                String line = "Numero de Tarjeta: " + tarjeta.getNumeroTarjeta() + ", Tipo de Tarjeta: " + tarjeta.getTipoTarjeta();//00011223 variable con el numero de tarjeta y el tipo de tarjeta
                writer.write(line);//00011223 escribe la linea en el archivo
                writer.newLine();//00011223 escribe una nueva linea
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00082923 declaramos alerta para avisar se creo
            alert.setTitle("Guardado..."); // 00082923 titulo de alerta
            alert.setHeaderText("Consulta almacenada"); // 00082923 encabezado de alerta
            alert.setContentText("La consulta que realizaste se guardo correctamente, puedes encontrarla en reportes!"); // 00082923 texto de la alerta
            alert.showAndWait(); // 00082923 muestra alerta hasta que se cierre

        } catch (IOException e) {e.printStackTrace();//imprime si ocurre un error
        }
    }

    @FXML
    private void mostrarComprasPorID() {
        int clienteId = Integer.parseInt(txIDReporteA.getText()); //00125123 Obtiene el ID del cliente del campo de texto txIDReporteA
        LocalDate inicio = dtFechaReporteA.getConverter().fromString(dtFechaReporteA.getEditor().getText()); //00125123 Obtiene la fecha de inicio del campo de fecha dtFechaReporteA
        LocalDate fin = dtFechaFinalReporteA.getConverter().fromString(dtFechaFinalReporteA.getEditor().getText()); //00125123 Obtiene la fecha de fin del campo de fecha dtFechaFinalReporteA
        List<Compra> compras = compraDAO.mostrarIDPorCompra(clienteId, inicio, fin);  //00125123 Llama al método mostrarIDPorCompra del objeto compraDAO para obtener la lista de compras
        tvComprasReporteA.getItems().setAll(compras); //00125123 Establece la lista de compras en la tabla tvComprasReporteA
    }

    @FXML
    private void handleBotonGuardarArchivoReporteA(){
        ObservableList<Compra> compras = tvComprasReporteA.getItems(); //00125123 Obtiene las compras de la tabla tvComprasReporteA
        if(compras.isEmpty()){ //00125123 Si la lista de compras está vacía, retorna sin hacer nada
            return;
        }
        String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));  //00125123 Obtiene la fecha y hora actual en el formato "yyyy-MM-dd_HH-mm-ss"
        System.out.println(ahora);
        System.out.println(System.getProperty("user.dir")); //00125123 Imprime el directorio de trabajo actual
        String path = UserValidator.getInstance().getUserPath() + "ReporteA " + ahora + ".txt"; //00125123 Construye la ruta del archivo utilizando el path del usuario y la fecha/hora actual
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) { //00125123 Intenta escribir las compras en el archivo
            for (Compra compra : compras) { //00125123 Itera sobre cada compra en la lista
                String line = String.format("ID: %d, Fecha: %s, Total: %f, Descripcion: %s, ID Tarjeta: %d", //00125123 Construye una línea de texto con los detalles de la compra
                        compra.getId(),
                        compra.getFechaCompra().toString(),
                        compra.getMontoTotal(),
                        compra.getDescripcion(),
                        compra.getTarjetaId());
                writer.write(line); //00125123 Escribe la línea en el archivo
                writer.newLine(); //00125123 Escribe la línea en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace(); //00125123 Imprime el stack trace si ocurre una excepción de entrada/salida
        }
    }

    @FXML
    private void BuscarReporteB() { //00082923 funcion para al darle al boton de buscar
        try { // 00082923 apertura try catch
            int clienteId = Integer.parseInt(idTextFieldB.getText());  // 00082923 toma el ID y lo hace entero
            int mes = mesComboBoxB.getSelectionModel().getSelectedIndex() + 1; // 00082923 toma el mes seleccionado en el ComboBox y le suma 1 para ajustarlo al formato SQL
            int anio = Integer.parseInt(anioComboBoxB.getValue()); //00082923 Obtiene el año seleccionado en el ComboBox

            double totalGasto = compraDAO.obtenerTotalGastoPorClienteYMes(mes, anio, clienteId); // 00082923 toma el valor de la consulta
            totalLabelB.setText("Total gastado: $" + totalGasto); // 00082923 actualiza el label con el total
        } catch (NumberFormatException e) { // 00082923 cierre try catch
            totalLabelB.setText("Datos no validos"); // 00082923 mensaje en caso de excepcion
        } catch (Exception e) {  // 00082923 cierre segundo caso de try catch
            totalLabelB.setText("Error en la busqueda"); // 00082923 mensaje en caso de excepcion general
            e.printStackTrace(); // 00082923 imprime la excepcion
        }
    }

    @FXML
    private void GuardarArchivoReporteB() { //00082923 declaracion funcion para guardar el reporte como txt
        String clienteId = idTextFieldB.getText(); // 00082923 obtiene el ID
        String mes = mesComboBoxB.getValue(); // 00082923 obtiene el mes del combo Box
        String anio = anioComboBoxB.getValue(); // 00082923 obtiene el año del combo Box
        String totalGastado = totalLabelB.getText(); // 00082923 toma el total del label


        if (clienteId.isEmpty() || mes == null || anio.isEmpty() || totalGastado.isEmpty()) { // 00082923 si los campos estan vacios
            Alert alert = new Alert(Alert.AlertType.ERROR); // 00082923 declaramos alerta error
            alert.setTitle("Datos"); // 00082923 titulo de alerta
            alert.setHeaderText("Datos faltantes"); // 00082923 encabezado de alerta
            alert.setContentText("No hay datos que guardar"); // 00082923 texto de la alerta
            alert.showAndWait(); // 00082923 muestra alerta hasta que se cierre
            return; // 00082923 se sale
        }

        String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")); // 00082923 fecha de ahora
        String path = UserValidator.getInstance().getUserPath() + "ReporteB " + ahora + ".txt"; // 00082923 ruta con el nombre de reporte

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Reporte B"); // 00082923 pone el titulo
            writer.newLine(); // 00082923 salta la linea
            writer.write("Cliente ID: " + clienteId); // 00082923 pone el id del cliente
            writer.newLine(); // 00082923 salta la linea
            writer.write("Mes: " + mes); // 00082923 pone el mes en que se hizo
            writer.newLine(); // 00082923 salta la linea
            writer.write("Anio: " + anio); // 00082923 pone el año en que se hizo
            writer.newLine(); // 00082923 salta la linea
            writer.write("Total Gastado: " + totalGastado); // 00082923 pone el total que se gasto
            writer.newLine(); // 00082923 salta la
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00082923 declaramos alerta para avisar se creo
            alert.setTitle("Guardado..."); // 00082923 titulo de alerta
            alert.setHeaderText("Consulta almacenada"); // 00082923 encabezado de alerta
            alert.setContentText("La consulta que realizaste se guardo correctamente, puedes encontrarla en reportes!"); // 00082923 texto de la alerta
            alert.showAndWait(); // 00082923 muestra alerta hasta que se cierre
        } catch (IOException e) { //00082923 cierre try catch
            e.printStackTrace(); // 00082923 imprime la excepcion
        }
    }
}