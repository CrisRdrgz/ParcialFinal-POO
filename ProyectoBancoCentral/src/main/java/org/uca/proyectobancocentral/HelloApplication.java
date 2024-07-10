package org.uca.proyectobancocentral; // 00078323 Paquete principal de la aplicación

import javafx.application.Application; // 00078323 Importa la clase Application de JavaFX
import javafx.application.Platform; // 00078323 Importa la clase Platform de JavaFX
import javafx.fxml.FXMLLoader; // 00078323 Importa la clase FXMLLoader para cargar archivos FXML
import javafx.scene.Scene; // 00078323 Importa la clase Scene de JavaFX
import javafx.stage.Stage; // 00078323 Importa la clase Stage de JavaFX

import java.io.IOException; // 00078323 Importa la clase IOException para manejar excepciones de E/S
import java.sql.SQLException; // 00078323 Importa la clase SQLException para manejar excepciones SQL

public class HelloApplication extends Application { // 00078323 Clase principal que extiende Application para iniciar la aplicación JavaFX

    @Override
    public void start(Stage stage) throws IOException { // 00078323 Método start sobreescrito para iniciar la aplicación
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")); // 00078323 Carga el archivo FXML para la interfaz
        Scene scene = new Scene(fxmlLoader.load(), 800, 600); // 00078323 Crea una nueva escena con las dimensiones especificadas
        stage.setTitle("Banco Central"); // 00078323 Establece el título de la ventana
        stage.setScene(scene); // 00078323 Establece la escena en el escenario
        stage.setResizable(false); // 00078323 Desactiva la capacidad de redimensionar la ventana
        stage.show(); // 00078323 Muestra la ventana
    }

    public static void main(String[] args) throws SQLException { // 00078323 Método main para iniciar la aplicación
        String username = System.getProperty("user.name"); // 00078323 Obtiene el nombre del usuario del sistema
        System.out.println(username); // 00078323 Imprime el nombre del usuario en la consola
        launch(); // 00078323 Lanza la aplicación JavaFX
    }
}