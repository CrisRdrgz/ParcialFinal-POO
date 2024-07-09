module org.uca.proyectobancocentral {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.uca.proyectobancocentral to javafx.fxml;
    opens org.uca.proyectobancocentral.Clases to javafx.base;
    exports org.uca.proyectobancocentral;
}