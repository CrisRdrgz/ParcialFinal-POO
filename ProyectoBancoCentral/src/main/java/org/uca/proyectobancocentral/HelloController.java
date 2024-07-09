package org.uca.proyectobancocentral;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uca.proyectobancocentral.Clases.Cliente;
import org.uca.proyectobancocentral.Clases.Compra;
import org.uca.proyectobancocentral.Clases.Tarjeta;
import org.uca.proyectobancocentral.data_access_object.ClienteDAO;
import org.uca.proyectobancocentral.data_access_object.CompraDAO;
import org.uca.proyectobancocentral.data_access_object.TarjetaDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class HelloController{
    @FXML
    private ComboBox cbReporteD;
    @FXML
    private TableView tvReporteD;
    @FXML
    private TableColumn colIdReporteD;
    @FXML
    private TableColumn colNombreReporteD;
    @FXML
    private TableColumn colDireccionReporteD;
    @FXML
    private TableColumn colTelefonoReporteD;
    @FXML
    private TableColumn colCantComprasReporteD;
    @FXML
    private TableColumn colTotalGastadoReporteD;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private TarjetaDAO tarjetaDAO = new TarjetaDAO();
    private CompraDAO compraDAO = new CompraDAO();

    public void initialize() {
        colIdReporteD.setCellFactory(new PropertyValueFactory<>("ID"));
        colNombreReporteD.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colDireccionReporteD.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        colTelefonoReporteD.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colCantComprasReporteD.setCellValueFactory(new PropertyValueFactory<>("Cantidad compras"));
        colTotalGastadoReporteD.setCellValueFactory(new PropertyValueFactory<>("Total gastado"));
        cbReporteD.getItems().addAll("Visa", "MasterCard", "American Express", "Discover", "Diners Club");

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
        tarjeta4.setClienteId(3);
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
        tarjeta6.setClienteId(6);
        tarjetaDAO.registrarTarjeta(tarjeta6);

        Tarjeta tarjeta7 = new Tarjeta();
        tarjeta7.setNumeroTarjeta("4111-1111-1111-1111");
        tarjeta7.setFechaExpiracion(LocalDate.parse("2023-11-01"));
        tarjeta7.setTipoTarjeta("Debito");
        tarjeta7.setFacilitador("Visa");
        tarjeta7.setClienteId(7);
        tarjetaDAO.registrarTarjeta(tarjeta7);

        Tarjeta tarjeta8 = new Tarjeta();
        tarjeta8.setNumeroTarjeta("3000-0000-0000-04");
        tarjeta8.setFechaExpiracion(LocalDate.parse("2028-03-01"));
        tarjeta8.setTipoTarjeta("Credito");
        tarjeta8.setFacilitador("Diners Club");
        tarjeta8.setClienteId(1);
        tarjetaDAO.registrarTarjeta(tarjeta8);

        Tarjeta tarjeta9 = new Tarjeta();
        tarjeta9.setNumeroTarjeta("6011-6011-6011-6611");
        tarjeta9.setFechaExpiracion(LocalDate.parse("2029-09-01"));
        tarjeta9.setTipoTarjeta("Debito");
        tarjeta9.setFacilitador("Discover");
        tarjeta9.setClienteId(2);
        tarjetaDAO.registrarTarjeta(tarjeta9);

        Tarjeta tarjeta10 = new Tarjeta();
        tarjeta10.setNumeroTarjeta("3530-3530-3530-3530");
        tarjeta10.setFechaExpiracion(LocalDate.parse("2030-01-01"));
        tarjeta10.setTipoTarjeta("Credito");
        tarjeta10.setFacilitador("MasterCard");
        tarjeta10.setClienteId(3);
        tarjetaDAO.registrarTarjeta(tarjeta10);

        Compra compra1 = new Compra();
        compra1.setFechaCompra(LocalDate.parse("2024-12-24"));
        compra1.setMontoTotal(491.90);
        compra1.setDescripcion("Regalos de Navidad");
        compra1.setTarjetaId(5);
        compraDAO.registrarCompra(compra1);

        Compra compra2 = new Compra();
        compra2.setFechaCompra(LocalDate.parse("2024-11-15"));
        compra2.setMontoTotal(257.10);
        compra2.setDescripcion("Compra de electrónica");
        compra2.setTarjetaId(1);
        compraDAO.registrarCompra(compra2);

        Compra compra3 = new Compra();
        compra3.setFechaCompra(LocalDate.parse("2024-10-10"));
        compra3.setMontoTotal(75.20);
        compra3.setDescripcion("Cena en restaurante");
        compra3.setTarjetaId(8);
        compraDAO.registrarCompra(compra3);

        Compra compra4 = new Compra();
        compra4.setFechaCompra(LocalDate.parse("2024-09-05"));
        compra4.setMontoTotal(49.99);
        compra4.setDescripcion("Compra de libros");
        compra4.setTarjetaId(10);
        compraDAO.registrarCompra(compra4);

        Compra compra5 = new Compra();
        compra5.setFechaCompra(LocalDate.parse("2024-08-20"));
        compra5.setMontoTotal(307.45);
        compra5.setDescripcion("Gastos médicos");
        compra5.setTarjetaId(4);
        compraDAO.registrarCompra(compra5);

        Compra compra6 = new Compra();
        compra6.setFechaCompra(LocalDate.parse("2024-07-30"));
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
        compra13.setFechaCompra(LocalDate.parse("2024-12-05"));
        compra13.setMontoTotal(1340.50);
        compra13.setDescripcion("Juguetes para niños");
        compra13.setTarjetaId(6);
        compraDAO.registrarCompra(compra13);

        Compra compra14 = new Compra();
        compra14.setFechaCompra(LocalDate.parse("2024-11-10"));
        compra14.setMontoTotal(254.20);
        compra14.setDescripcion("Compra en tienda de deportes");
        compra14.setTarjetaId(10);
        compraDAO.registrarCompra(compra14);

        Compra compra15 = new Compra();
        compra15.setFechaCompra(LocalDate.parse("2024-10-15"));
        compra15.setMontoTotal(198.90);
        compra15.setDescripcion("Supermercado");
        compra15.setTarjetaId(1);
        compraDAO.registrarCompra(compra15);

        Compra compra16 = new Compra();
        compra16.setFechaCompra(LocalDate.parse("2024-09-30"));
        compra16.setMontoTotal(199.99);
        compra16.setDescripcion("Equipos de cocina");
        compra16.setTarjetaId(8);
        compraDAO.registrarCompra(compra16);

        Compra compra17 = new Compra();
        compra17.setFechaCompra(LocalDate.parse("2024-08-10"));
        compra17.setMontoTotal(105.80);
        compra17.setDescripcion("Herramientas de jardín");
        compra17.setTarjetaId(1);
        compraDAO.registrarCompra(compra17);

        Compra compra18 = new Compra();
        compra18.setFechaCompra(LocalDate.parse("2024-07-05"));
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
    }

    @FXML
    private void handleBotonSeleccionar(){
        String facilitador = cbReporteD.getValue().toString();
        if(facilitador != null){
            clienteDAO.comprasPorFacilitadorTarjeta(facilitador);
        }
    }
}