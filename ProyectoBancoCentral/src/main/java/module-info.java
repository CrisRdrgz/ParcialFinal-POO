module org.uca.proyectobancocentral {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uca.proyectobancocentral to javafx.fxml;
    exports org.uca.proyectobancocentral;
}