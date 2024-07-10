module org.uca.proyectobancocentral { // 00078323 Define el módulo org.uca.proyectobancocentral
    requires javafx.controls; // 00078323 Requiere el módulo javafx.controls
    requires javafx.fxml; // 00078323 Requiere el módulo javafx.fxml
    requires java.sql; // 00078323 Requiere el módulo java.sql

    opens org.uca.proyectobancocentral to javafx.fxml; // 00078323 Abre el paquete org.uca.proyectobancocentral al módulo javafx.fxml
    opens org.uca.proyectobancocentral.Clases to javafx.base; // 00078323 Abre el paquete org.uca.proyectobancocentral.Clases al módulo javafx.base
    exports org.uca.proyectobancocentral; // 00078323 Exporta el paquete org.uca.proyectobancocentral
}