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
import org.uca.proyectobancocentral.Clases.Tarjeta;
import org.uca.proyectobancocentral.data_access_object.ClienteDAO;
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

    public void initialize() {
        colIdReporteD.setCellFactory(new PropertyValueFactory<>("ID"));
        colNombreReporteD.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colDireccionReporteD.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        colTelefonoReporteD.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colCantComprasReporteD.setCellValueFactory(new PropertyValueFactory<>("Cantidad compras"));
        colTotalGastadoReporteD.setCellValueFactory(new PropertyValueFactory<>("Total gastado"));
        cbReporteD.getItems().addAll("Visa", "MasterCard", "American Express", "Discover", "Diners Club");

        clienteDAO.crearTablaCliente();
        tarjetaDAO.crearTablaTarjeta();

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

        Platform.runLater(() -> {
            Stage stage = (Stage) cbReporteD.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                tarjetaDAO.BorrarTablaTarjeta();
                clienteDAO.BorrarTablaCliente();
            });
        });
    }

    @FXML
    private void handleBotonSeleccionar(){
        String facilitador = cbReporteD.getValue().toString();
        if(facilitador != null){
            clienteDAO.comprasPorFacilitadorTarjeta(facilitador);
        }
    }
}