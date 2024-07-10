package org.uca.proyectobancocentral;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uca.proyectobancocentral.Clases.Cliente;
import org.uca.proyectobancocentral.Clases.Compra;
import org.uca.proyectobancocentral.Clases.Tarjeta;
import org.uca.proyectobancocentral.data_access_object.ClienteDAO;
import org.uca.proyectobancocentral.data_access_object.CompraDAO;
import org.uca.proyectobancocentral.data_access_object.TarjetaDAO;
import org.uca.proyectobancocentral.singleton.UserValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelloController{
    @FXML
    private ComboBox cbReporteD;
    @FXML
    private ComboBox<String>  mesComboBoxB; // 00082923 declaramos el Combo Box de mes
    @FXML
    private ComboBox<String>  anioComboBoxB; // 00082923 declaramos el Combo Box de año
    @FXML
    private TextField idTextFieldB; // 00082923 declaramos el Text field donde va el ID
    @FXML
    private Label totalLabelB; // 00082923 declaramos el Label donde se pondra el total
    @FXML
    private TableView<Cliente> tvReporteD;
    @FXML
    private TableColumn<Cliente, Integer> colIdReporteD;
    @FXML
    private TableColumn<Cliente, String> colNombreReporteD;
    @FXML
    private TableColumn<Cliente, String> colDireccionReporteD;
    @FXML
    private TableColumn<Cliente, String> colTelefonoReporteD;
    @FXML
    private TableColumn<Cliente, Integer> colCantComprasReporteD;
    @FXML
    private TableColumn<Cliente, Double> colTotalGastadoReporteD;
    @FXML
    private TableView<Tarjeta> tvTarjetas;//00011223 declaro un table view que mostrara la lista de Tarjetas en la interfaz grafica
    @FXML
    private TableColumn<Tarjeta, String> colNumeroTarjeta;//00011223 declaro una columna de la tbla view que mostrar el numero de la tarjeta en la interfaza grafica
    @FXML
    private TableColumn<Tarjeta, String> colTipoTarjeta;//00011223 declaro una columna de la table viwe que mostrara de que tipo es la tarjeta
    @FXML

    private TextField tfClienteId;//0011223 declaro un textfield donde se ingresara el id del cliente para ver sus tarjetas
    @FXML
    private TableView<Compra> tvComprasReporteA;
    @FXML
    private TableColumn<Compra, String> colIDReporteA;
    @FXML
    private TableColumn<Compra, String> colClienteCompraReporteA;
    @FXML
    private Button btnBuscarReporteA;
    @FXML
    private Button btnGuardarArchivoReporteA;
    @FXML
    private TextField txIDReporteA;
    @FXML
    private DatePicker dtFechaInicioReporteA;
    @FXML
    private DatePicker dtFechaFinalReporteA;


    private ClienteDAO clienteDAO = new ClienteDAO();
    private TarjetaDAO tarjetaDAO = new TarjetaDAO();
    private CompraDAO compraDAO = new CompraDAO();

    @FXML
    public void initialize() {
        colIdReporteD.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreReporteD.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colDireccionReporteD.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefonoReporteD.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCantComprasReporteD.setCellValueFactory(new PropertyValueFactory<>("cantidadCompras"));
        colTotalGastadoReporteD.setCellValueFactory(new PropertyValueFactory<>("totalGastado"));
        cbReporteD.getItems().addAll("Visa", "MasterCard", "American Express", "Discover", "Diners Club");
        mesComboBoxB.getItems().addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
                "Septiembre", "Octubre", "Noviembre", "Diciembre"); // 00082923 le ponemos las opciones de meses
        anioComboBoxB.getItems().addAll("2023", "2024"); // 00082923 le ponemos opciones de años
        colNumeroTarjeta.setCellValueFactory(new PropertyValueFactory<>("numeroTarjeta"));//00011223  valores para colNumeroTarjeta para que use la propiedad numeroTarjeta de los objetos Tarjeta
        colTipoTarjeta.setCellValueFactory(new PropertyValueFactory<>("tipoTarjeta"));//00011223 valores para colTipoTarjeta para que use la propiedad tipoTarjeta de los objetos Tarjeta
        colIDReporteA.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colClienteCompraReporteA.setCellValueFactory(new PropertyValueFactory<>("listaCompraCliente"));


        tvReporteD.getItems().clear();

        compraDAO.BorrarTablaCompra();
        tarjetaDAO.BorrarTablaTarjeta();
        clienteDAO.BorrarTablaCliente();
        clienteDAO.crearTablaCliente();
        tarjetaDAO.crearTablaTarjeta();
        compraDAO.crearTablaCompra();

        Cliente cliente1 = new Cliente();
        cliente1.setNombreCompleto("Juan Miguel Perez Lopez");
        cliente1.setDireccion("Calle La Mascota 923b, San Salvador");
        cliente1.setTelefono("70001000");
        clienteDAO.registrarCliente(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNombreCompleto("Maria Teresa Gomez Hernandez");
        cliente2.setDireccion("Avenida Roosevelt 123, San Miguel");
        cliente2.setTelefono("78752310");
        clienteDAO.registrarCliente(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setNombreCompleto("Carlos Alberto Ruiz Garcia");
        cliente3.setDireccion("Calle Arce 456, Santa Ana");
        cliente3.setTelefono("75310970");
        clienteDAO.registrarCliente(cliente3);

        Cliente cliente4 = new Cliente();
        cliente4.setNombreCompleto("Ana Patricia Sanchez Diaz");
        cliente4.setDireccion("Avenida Masferrer 789, San Salvador");
        cliente4.setTelefono("78431000");
        clienteDAO.registrarCliente(cliente4);

        Cliente cliente5 = new Cliente();
        cliente5.setNombreCompleto("Jose Ricardo Fuentes Martinez");
        cliente5.setDireccion("Calle San Antonio 101, La Libertad");
        cliente5.setTelefono("70215001");
        clienteDAO.registrarCliente(cliente5);

        Cliente cliente6 = new Cliente();
        cliente6.setNombreCompleto("Sofia Margarita Castillo Lopez");
        cliente6.setDireccion("Avenida Los Héroes 202, San Salvador");
        cliente6.setTelefono("70588799");
        clienteDAO.registrarCliente(cliente6);

        Cliente cliente7 = new Cliente();
        cliente7.setNombreCompleto("Miguel Angel Hernandez Torres");
        cliente7.setDireccion("Calle San Juan 303, San Miguel");
        cliente7.setTelefono("70201579");
        clienteDAO.registrarCliente(cliente7);

        Cliente cliente8 = new Cliente();
        cliente8.setNombreCompleto("Jose Luis Martinez Reyes");
        cliente8.setDireccion("Calle Principal 456, Santa Ana");
        cliente8.setTelefono("70123456");
        clienteDAO.registrarCliente(cliente8);

        Cliente cliente9 = new Cliente();
        cliente9.setNombreCompleto("Carlos Roberto Perez Lopez");
        cliente9.setDireccion("Calle Libertad 789, San Salvador");
        cliente9.setTelefono("70345678");
        clienteDAO.registrarCliente(cliente9);

        Cliente cliente10 = new Cliente();
        cliente10.setNombreCompleto("Ana Maria Lopez Garcia");
        cliente10.setDireccion("Calle Independencia 123, Santa Tecla");
        cliente10.setTelefono("70456789");
        clienteDAO.registrarCliente(cliente10);

        Cliente cliente11 = new Cliente();
        cliente11.setNombreCompleto("Rosa Elena Morales Jimenez");
        cliente11.setDireccion("Calle Roosevelt 456, San Miguel");
        cliente11.setTelefono("70567890");
        clienteDAO.registrarCliente(cliente11);

        Cliente cliente12 = new Cliente();
        cliente12.setNombreCompleto("Luis Alfredo Ramirez Castro");
        cliente12.setDireccion("Calle Cuscatlan 789, Santa Ana");
        cliente12.setTelefono("70678901");
        clienteDAO.registrarCliente(cliente12);

        Cliente cliente13 = new Cliente();
        cliente13.setNombreCompleto("Sofia Beatriz Flores Vasquez");
        cliente13.setDireccion("Calle 15 de Septiembre 123, La Libertad");
        cliente13.setTelefono("70789012");
        clienteDAO.registrarCliente(cliente13);

        Cliente cliente14 = new Cliente();
        cliente14.setNombreCompleto("Carlos David Sanchez Hernandez");
        cliente14.setDireccion("Calle Los Heroes 456, San Salvador");
        cliente14.setTelefono("70890123");
        clienteDAO.registrarCliente(cliente14);

        Cliente cliente15 = new Cliente();
        cliente15.setNombreCompleto("Maria Fernanda Gonzalez Mejia");
        cliente15.setDireccion("Calle Arce 789, Santa Tecla");
        cliente15.setTelefono("70901234");
        clienteDAO.registrarCliente(cliente15);

        Tarjeta tarjeta1 = new Tarjeta();
        tarjeta1.setNumeroTarjeta("4535-7650-6743-0080");
        tarjeta1.setFechaExpiracion(LocalDate.parse("2028-11-01"));
        tarjeta1.setTipoTarjeta("Debito");
        tarjeta1.setFacilitador("MasterCard");
        tarjeta1.setClienteId(4);
        tarjetaDAO.registrarTarjeta(tarjeta1);

        Tarjeta tarjeta2 = new Tarjeta();
        tarjeta2.setNumeroTarjeta("4535-1234-5678-9101");
        tarjeta2.setFechaExpiracion(LocalDate.parse("2029-05-01"));
        tarjeta2.setTipoTarjeta("Credito");
        tarjeta2.setFacilitador("Visa");
        tarjeta2.setClienteId(1);
        tarjetaDAO.registrarTarjeta(tarjeta2);

        Tarjeta tarjeta3 = new Tarjeta();
        tarjeta3.setNumeroTarjeta("5432-0987-6543-2109");
        tarjeta3.setFechaExpiracion(LocalDate.parse("2027-08-01"));
        tarjeta3.setTipoTarjeta("Debito");
        tarjeta3.setFacilitador("American Express");
        tarjeta3.setClienteId(2);
        tarjetaDAO.registrarTarjeta(tarjeta3);

        Tarjeta tarjeta4 = new Tarjeta();
        tarjeta4.setNumeroTarjeta("6011-1111-1111-1117");
        tarjeta4.setFechaExpiracion(LocalDate.parse("2026-12-01"));
        tarjeta4.setTipoTarjeta("Credito");
        tarjeta4.setFacilitador("Discover");
        tarjeta4.setClienteId(6);
        tarjetaDAO.registrarTarjeta(tarjeta4);

        Tarjeta tarjeta5 = new Tarjeta();
        tarjeta5.setNumeroTarjeta("3530-1113-3330-0000");
        tarjeta5.setFechaExpiracion(LocalDate.parse("2025-10-01"));
        tarjeta5.setTipoTarjeta("Debito");
        tarjeta5.setFacilitador("Diners Club");
        tarjeta5.setClienteId(5);
        tarjetaDAO.registrarTarjeta(tarjeta5);

        Tarjeta tarjeta6 = new Tarjeta();
        tarjeta6.setNumeroTarjeta("5500-0000-0000-0004");
        tarjeta6.setFechaExpiracion(LocalDate.parse("2024-07-01"));
        tarjeta6.setTipoTarjeta("Credito");
        tarjeta6.setFacilitador("MasterCard");
        tarjeta6.setClienteId(3);
        tarjetaDAO.registrarTarjeta(tarjeta6);

        Tarjeta tarjeta7 = new Tarjeta();
        tarjeta7.setNumeroTarjeta("4111-1456-8960-1111");
        tarjeta7.setFechaExpiracion(LocalDate.parse("2023-11-01"));
        tarjeta7.setTipoTarjeta("Debito");
        tarjeta7.setFacilitador("Visa");
        tarjeta7.setClienteId(10);
        tarjetaDAO.registrarTarjeta(tarjeta7);

        Tarjeta tarjeta8 = new Tarjeta();
        tarjeta8.setNumeroTarjeta("5423-3421-5648-3412");
        tarjeta8.setFechaExpiracion(LocalDate.parse("2028-03-01"));
        tarjeta8.setTipoTarjeta("Credito");
        tarjeta8.setFacilitador("Diners Club");
        tarjeta8.setClienteId(9);
        tarjetaDAO.registrarTarjeta(tarjeta8);

        Tarjeta tarjeta9 = new Tarjeta();
        tarjeta9.setNumeroTarjeta("4350-8793-5423-6611");
        tarjeta9.setFechaExpiracion(LocalDate.parse("2029-09-01"));
        tarjeta9.setTipoTarjeta("Debito");
        tarjeta9.setFacilitador("Discover");
        tarjeta9.setClienteId(7);
        tarjetaDAO.registrarTarjeta(tarjeta9);

        Tarjeta tarjeta10 = new Tarjeta();
        tarjeta10.setNumeroTarjeta("3530-3412-6548-3340");
        tarjeta10.setFechaExpiracion(LocalDate.parse("2030-01-01"));
        tarjeta10.setTipoTarjeta("Credito");
        tarjeta10.setFacilitador("MasterCard");
        tarjeta10.setClienteId(10);
        tarjetaDAO.registrarTarjeta(tarjeta10);

        Tarjeta tarjeta11 = new Tarjeta();
        tarjeta11.setNumeroTarjeta("5432-3457-1230-1258");
        tarjeta11.setFechaExpiracion(LocalDate.parse("2029-03-01"));
        tarjeta11.setTipoTarjeta("Debito");
        tarjeta11.setFacilitador("Visa");
        tarjeta11.setClienteId(14);
        tarjetaDAO.registrarTarjeta(tarjeta11);

        Tarjeta tarjeta12 = new Tarjeta();
        tarjeta12.setNumeroTarjeta("6897-1090-5643-1246");
        tarjeta12.setFechaExpiracion(LocalDate.parse("2028-06-01"));
        tarjeta12.setTipoTarjeta("Credito");
        tarjeta12.setFacilitador("American Express");
        tarjeta12.setClienteId(15);
        tarjetaDAO.registrarTarjeta(tarjeta12);

        Tarjeta tarjeta13 = new Tarjeta();
        tarjeta13.setNumeroTarjeta("6534-1234-4321-4560");
        tarjeta13.setFechaExpiracion(LocalDate.parse("2031-01-01"));
        tarjeta13.setTipoTarjeta("Debito");
        tarjeta13.setFacilitador("Visa");
        tarjeta13.setClienteId(11);
        tarjetaDAO.registrarTarjeta(tarjeta13);

        Tarjeta tarjeta14 = new Tarjeta();
        tarjeta14.setNumeroTarjeta("4509-1257-1090-3467");
        tarjeta14.setFechaExpiracion(LocalDate.parse("2029-11-01"));
        tarjeta14.setTipoTarjeta("Debito");
        tarjeta14.setFacilitador("Visa");
        tarjeta14.setClienteId(13);
        tarjetaDAO.registrarTarjeta(tarjeta14);

        Tarjeta tarjeta15 = new Tarjeta();
        tarjeta15.setNumeroTarjeta("1209-4624-0900-0000");
        tarjeta15.setFechaExpiracion(LocalDate.parse("2030-09-01"));
        tarjeta15.setTipoTarjeta("Credito");
        tarjeta15.setFacilitador("American Express");
        tarjeta15.setClienteId(12);
        tarjetaDAO.registrarTarjeta(tarjeta15);

        Tarjeta tarjeta16 = new Tarjeta();
        tarjeta16.setNumeroTarjeta("4590-0943-2367-8943");
        tarjeta16.setFechaExpiracion(LocalDate.parse("2027-07-01"));
        tarjeta16.setTipoTarjeta("Credito");
        tarjeta16.setFacilitador("Diners Club");
        tarjeta16.setClienteId(9);
        tarjetaDAO.registrarTarjeta(tarjeta16);

        Tarjeta tarjeta17 = new Tarjeta();
        tarjeta17.setNumeroTarjeta("2950-3928-1111-2311");
        tarjeta17.setFechaExpiracion(LocalDate.parse("2032-05-01"));
        tarjeta17.setTipoTarjeta("Debito");
        tarjeta17.setFacilitador("Visa");
        tarjeta17.setClienteId(11);
        tarjetaDAO.registrarTarjeta(tarjeta17);

        Tarjeta tarjeta18 = new Tarjeta();
        tarjeta18.setNumeroTarjeta("0909-0000-2387-4610");
        tarjeta18.setFechaExpiracion(LocalDate.parse("2028-02-01"));
        tarjeta18.setTipoTarjeta("Credito");
        tarjeta18.setFacilitador("MasterCard");
        tarjeta18.setClienteId(15);
        tarjetaDAO.registrarTarjeta(tarjeta18);

        Tarjeta tarjeta19 = new Tarjeta();
        tarjeta19.setNumeroTarjeta("9043-3213-5439-1246");
        tarjeta19.setFechaExpiracion(LocalDate.parse("2029-08-01"));
        tarjeta19.setTipoTarjeta("Credito");
        tarjeta19.setFacilitador("MasterCard");
        tarjeta19.setClienteId(8);
        tarjetaDAO.registrarTarjeta(tarjeta19);

        Tarjeta tarjeta20 = new Tarjeta();
        tarjeta20.setNumeroTarjeta("8902-1457-3090-3400");
        tarjeta20.setFechaExpiracion(LocalDate.parse("2031-12-01"));
        tarjeta20.setTipoTarjeta("Credito");
        tarjeta20.setFacilitador("Diners Club");
        tarjeta20.setClienteId(12);
        tarjetaDAO.registrarTarjeta(tarjeta20);

        Compra compra1 = new Compra();
        compra1.setFechaCompra(LocalDate.parse("2023-12-24"));
        compra1.setMontoTotal(491.90);
        compra1.setDescripcion("Regalos de Navidad");
        compra1.setTarjetaId(5);
        compraDAO.registrarCompra(compra1);

        Compra compra2 = new Compra();
        compra2.setFechaCompra(LocalDate.parse("2023-11-15"));
        compra2.setMontoTotal(257.10);
        compra2.setDescripcion("Compra de electrónica");
        compra2.setTarjetaId(1);
        compraDAO.registrarCompra(compra2);

        Compra compra3 = new Compra();
        compra3.setFechaCompra(LocalDate.parse("2023-10-10"));
        compra3.setMontoTotal(75.20);
        compra3.setDescripcion("Cena en restaurante");
        compra3.setTarjetaId(8);
        compraDAO.registrarCompra(compra3);

        Compra compra4 = new Compra();
        compra4.setFechaCompra(LocalDate.parse("2023-09-05"));
        compra4.setMontoTotal(49.99);
        compra4.setDescripcion("Compra de libros");
        compra4.setTarjetaId(10);
        compraDAO.registrarCompra(compra4);

        Compra compra5 = new Compra();
        compra5.setFechaCompra(LocalDate.parse("2023-08-20"));
        compra5.setMontoTotal(307.45);
        compra5.setDescripcion("Gastos médicos");
        compra5.setTarjetaId(4);
        compraDAO.registrarCompra(compra5);

        Compra compra6 = new Compra();
        compra6.setFechaCompra(LocalDate.parse("2023-07-30"));
        compra6.setMontoTotal(150.75);
        compra6.setDescripcion("Pago de servicios");
        compra6.setTarjetaId(6);
        compraDAO.registrarCompra(compra6);

        Compra compra7 = new Compra();
        compra7.setFechaCompra(LocalDate.parse("2024-06-14"));
        compra7.setMontoTotal(128.90);
        compra7.setDescripcion("Ropa nueva");
        compra7.setTarjetaId(7);
        compraDAO.registrarCompra(compra7);

        Compra compra8 = new Compra();
        compra8.setFechaCompra(LocalDate.parse("2024-05-25"));
        compra8.setMontoTotal(220.90);
        compra8.setDescripcion("Muebles para el hogar");
        compra8.setTarjetaId(9);
        compraDAO.registrarCompra(compra8);

        Compra compra9 = new Compra();
        compra9.setFechaCompra(LocalDate.parse("2024-04-10"));
        compra9.setMontoTotal(45.60);
        compra9.setDescripcion("Suscripción a servicios");
        compra9.setTarjetaId(2);
        compraDAO.registrarCompra(compra9);

        Compra compra10 = new Compra();
        compra10.setFechaCompra(LocalDate.parse("2024-03-18"));
        compra10.setMontoTotal(99.99);
        compra10.setDescripcion("Accesorios de tecnología");
        compra10.setTarjetaId(4);
        compraDAO.registrarCompra(compra10);

        Compra compra11 = new Compra();
        compra11.setFechaCompra(LocalDate.parse("2024-02-07"));
        compra11.setMontoTotal(800.00);
        compra11.setDescripcion("Viaje de fin de semana");
        compra11.setTarjetaId(3);
        compraDAO.registrarCompra(compra11);

        Compra compra12 = new Compra();
        compra12.setFechaCompra(LocalDate.parse("2024-01-22"));
        compra12.setMontoTotal(69.00);
        compra12.setDescripcion("Material de oficina");
        compra12.setTarjetaId(7);
        compraDAO.registrarCompra(compra12);

        Compra compra13 = new Compra();
        compra13.setFechaCompra(LocalDate.parse("2024-05-05"));
        compra13.setMontoTotal(1340.50);
        compra13.setDescripcion("Juguetes para niños");
        compra13.setTarjetaId(6);
        compraDAO.registrarCompra(compra13);

        Compra compra14 = new Compra();
        compra14.setFechaCompra(LocalDate.parse("2024-02-10"));
        compra14.setMontoTotal(254.20);
        compra14.setDescripcion("Compra en tienda de deportes");
        compra14.setTarjetaId(10);
        compraDAO.registrarCompra(compra14);

        Compra compra15 = new Compra();
        compra15.setFechaCompra(LocalDate.parse("2024-06-15"));
        compra15.setMontoTotal(198.90);
        compra15.setDescripcion("Supermercado");
        compra15.setTarjetaId(1);
        compraDAO.registrarCompra(compra15);

        Compra compra16 = new Compra();
        compra16.setFechaCompra(LocalDate.parse("2024-01-30"));
        compra16.setMontoTotal(199.99);
        compra16.setDescripcion("Equipos de cocina");
        compra16.setTarjetaId(8);
        compraDAO.registrarCompra(compra16);

        Compra compra17 = new Compra();
        compra17.setFechaCompra(LocalDate.parse("2023-08-10"));
        compra17.setMontoTotal(105.80);
        compra17.setDescripcion("Herramientas de jardín");
        compra17.setTarjetaId(1);
        compraDAO.registrarCompra(compra17);

        Compra compra18 = new Compra();
        compra18.setFechaCompra(LocalDate.parse("2023-07-05"));
        compra18.setMontoTotal(500.00);
        compra18.setDescripcion("Regalos de cumpleaños");
        compra18.setTarjetaId(4);
        compraDAO.registrarCompra(compra18);

        Compra compra19 = new Compra();
        compra19.setFechaCompra(LocalDate.parse("2024-06-22"));
        compra19.setMontoTotal(130.00);
        compra19.setDescripcion("Pago de membresía anual");
        compra19.setTarjetaId(8);
        compraDAO.registrarCompra(compra19);

        Compra compra20 = new Compra();
        compra20.setFechaCompra(LocalDate.parse("2024-05-11"));
        compra20.setMontoTotal(49.99);
        compra20.setDescripcion("Suscripción a revista");
        compra20.setTarjetaId(6);
        compraDAO.registrarCompra(compra20);

        Compra compra21 = new Compra();
        compra21.setFechaCompra(LocalDate.parse("2024-06-12"));
        compra21.setMontoTotal(149.99);
        compra21.setDescripcion("Compra de electrodomésticos");
        compra21.setTarjetaId(11);
        compraDAO.registrarCompra(compra21);

        Compra compra22 = new Compra();
        compra22.setFechaCompra(LocalDate.parse("2024-07-03"));
        compra22.setMontoTotal(679.02);
        compra22.setDescripcion("Compra de muebles");
        compra22.setTarjetaId(12);
        compraDAO.registrarCompra(compra22);

        Compra compra23 = new Compra();
        compra23.setFechaCompra(LocalDate.parse("2023-08-15"));
        compra23.setMontoTotal(400.90);
        compra23.setDescripcion("Compra de electrónica");
        compra23.setTarjetaId(13);
        compraDAO.registrarCompra(compra23);

        Compra compra24 = new Compra();
        compra24.setFechaCompra(LocalDate.parse("2023-09-05"));
        compra24.setMontoTotal(600.89);
        compra24.setDescripcion("Compra de ropa");
        compra24.setTarjetaId(14);
        compraDAO.registrarCompra(compra24);

        Compra compra25 = new Compra();
        compra25.setFechaCompra(LocalDate.parse("2023-10-01"));
        compra25.setMontoTotal(79.90);
        compra25.setDescripcion("Compra de calzado");
        compra25.setTarjetaId(15);
        compraDAO.registrarCompra(compra25);

        Compra compra26 = new Compra();
        compra26.setFechaCompra(LocalDate.parse("2023-11-18"));
        compra26.setMontoTotal(780.04);
        compra26.setDescripcion("Compra de accesorios");
        compra26.setTarjetaId(16);
        compraDAO.registrarCompra(compra26);

        Compra compra27 = new Compra();
        compra27.setFechaCompra(LocalDate.parse("2023-12-25"));
        compra27.setMontoTotal(569.70);
        compra27.setDescripcion("Compra de regalos");
        compra27.setTarjetaId(17);
        compraDAO.registrarCompra(compra27);

        Compra compra28 = new Compra();
        compra28.setFechaCompra(LocalDate.parse("2023-01-30"));
        compra28.setMontoTotal(129.95);
        compra28.setDescripcion("Compra de libros");
        compra28.setTarjetaId(18);
        compraDAO.registrarCompra(compra28);

        Compra compra29 = new Compra();
        compra29.setFechaCompra(LocalDate.parse("2023-02-15"));
        compra29.setMontoTotal(240.00);
        compra29.setDescripcion("Compra de herramientas");
        compra29.setTarjetaId(19);
        compraDAO.registrarCompra(compra29);

        Compra compra30 = new Compra();
        compra30.setFechaCompra(LocalDate.parse("2023-03-20"));
        compra30.setMontoTotal(567.80);
        compra30.setDescripcion("Compra de muebles de jardín");
        compra30.setTarjetaId(20);
        compraDAO.registrarCompra(compra30);

        Compra compra31 = new Compra();
        compra31.setFechaCompra(LocalDate.parse("2024-07-02"));
        compra31.setMontoTotal(124.00);
        compra31.setDescripcion("Compra de alimentos");
        compra31.setTarjetaId(18);
        compraDAO.registrarCompra(compra31);

        Compra compra32 = new Compra();
        compra32.setFechaCompra(LocalDate.parse("2024-03-22"));
        compra32.setMontoTotal(324.80);
        compra32.setDescripcion("Compra de material de oficina");
        compra32.setTarjetaId(11);
        compraDAO.registrarCompra(compra32);

        Compra compra33 = new Compra();
        compra33.setFechaCompra(LocalDate.parse("2023-09-30"));
        compra33.setMontoTotal(95.79);
        compra33.setDescripcion("Compra de medicinas");
        compra33.setTarjetaId(12);
        compraDAO.registrarCompra(compra33);

        Compra compra34 = new Compra();
        compra34.setFechaCompra(LocalDate.parse("2023-10-12"));
        compra34.setMontoTotal(209.67);
        compra34.setDescripcion("Compra de accesorios para automóvil");
        compra34.setTarjetaId(17);
        compraDAO.registrarCompra(compra34);

        Compra compra35 = new Compra();
        compra35.setFechaCompra(LocalDate.parse("2024-03-05"));
        compra35.setMontoTotal(305.00);
        compra35.setDescripcion("Compra de muebles de oficina");
        compra35.setTarjetaId(15);
        compraDAO.registrarCompra(compra35);

        Compra compra36 = new Compra();
        compra36.setFechaCompra(LocalDate.parse("2023-12-20"));
        compra36.setMontoTotal(40.70);
        compra36.setDescripcion("Compra de productos de limpieza");
        compra36.setTarjetaId(13);
        compraDAO.registrarCompra(compra36);

        Compra compra37 = new Compra();
        compra37.setFechaCompra(LocalDate.parse("2024-01-15"));
        compra37.setMontoTotal(25.99);
        compra37.setDescripcion("Compra de artículos deportivos");
        compra37.setTarjetaId(8);
        compraDAO.registrarCompra(compra37);

        Compra compra38 = new Compra();
        compra38.setFechaCompra(LocalDate.parse("2024-02-28"));
        compra38.setMontoTotal(60.00);
        compra38.setDescripcion("Compra de juguetes");
        compra38.setTarjetaId(10);
        compraDAO.registrarCompra(compra38);

        Compra compra39 = new Compra();
        compra39.setFechaCompra(LocalDate.parse("2024-03-10"));
        compra39.setMontoTotal(368.10);
        compra39.setDescripcion("Compra de productos electrónicos");
        compra39.setTarjetaId(6);
        compraDAO.registrarCompra(compra39);

        Compra compra40 = new Compra();
        compra40.setFechaCompra(LocalDate.parse("2023-11-05"));
        compra40.setMontoTotal(1673.25);
        compra40.setDescripcion("Compra de material de construcción");
        compra40.setTarjetaId(4);
        compraDAO.registrarCompra(compra40);
    }

    @FXML
    private void handleBotonSeleccionarReporteD(){
        String facilitador = cbReporteD.getValue().toString();
        System.out.println(facilitador);
        if(facilitador != null){
            tvReporteD.getItems().clear();
            try{
                List<Cliente> clientes = clienteDAO.comprasPorFacilitadorTarjeta(facilitador);
                System.out.println("Clientes agregados a la tabla: " + clientes.size());
                clientes.forEach(cliente -> {
                    System.out.println(cliente.getNombreCompleto());
                    tvReporteD.getItems().add(cliente);
                });
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void handleBotonGuardarArchivoReporteD(){
        ObservableList<Cliente> clientes = tvReporteD.getItems();
        if(clientes.isEmpty()){
            return;
        }
        String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        System.out.println(ahora);
        System.out.println(System.getProperty("user.dir"));
        String path = UserValidator.getInstance().getUserPath() + "ReporteD " + cbReporteD.getValue().toString() + " " + ahora + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Cliente cliente : clientes) {
                String line = String.format("ID: %d, Nombre: %s, Dirección: %s, Teléfono: %s, Cantidad Compras: %d, Total Gastado: $%.2f", cliente.getId(), cliente.getNombreCompleto(), cliente.getDireccion(), cliente.getTelefono(), cliente.getCantidadCompras(), cliente.getTotalGastado());
                writer.write(line);
                writer.newLine();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00082923 declaramos alerta para avisar se creo
            alert.setTitle("Guardado..."); // 00082923 titulo de alerta
            alert.setHeaderText("Consulta almacenada"); // 00082923 encabezado de alerta
            alert.setContentText("La consulta que realizaste se guardo correctamente, puedes encontrarla en reportes!"); // 00082923 texto de la alerta
            alert.showAndWait(); // 00082923 muestra alerta hasta que se cierre
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {e.printStackTrace();//imprime si ocurre un error
        }
    }

    @FXML
    private void mostrarComprasPorID() {
        int clienteId = Integer.parseInt(txIDReporteA.getText());
        LocalDate inicio = dtFechaInicioReporteA.getConverter().fromString(dtFechaInicioReporteA.getEditor().getText());
        LocalDate fin = dtFechaFinalReporteA.getConverter().fromString(dtFechaFinalReporteA.getEditor().getText());
        List<Compra> compras = compraDAO.mostrarIDPorCompra(clienteId, inicio, fin);
        System.out.println(compras);
        tvComprasReporteA.getItems().setAll(compras);
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


