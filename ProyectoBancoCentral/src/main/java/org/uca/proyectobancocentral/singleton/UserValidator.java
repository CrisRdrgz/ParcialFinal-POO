
package org.uca.proyectobancocentral.singleton; // 00078323 Paquete que contiene clases Singleton

public class UserValidator { // 00078323 Clase Singleton para validar usuarios
    private static UserValidator instance; // 00078323 Instancia única de UserValidator
    private String servername; // 00078323 Nombre del servidor
    private String userPath; // 00078323 Ruta del usuario

    private UserValidator() { // 00078323 Constructor privado que inicializa las variables según el nombre de usuario del sistema
        String username = System.getProperty("user.name"); // 00078323 Obtiene el nombre del usuario del sistema
        if("dalet".equals(username)) { // 00078323 Si el nombre de usuario es "dalet"
            this.servername = "LAPTOP-SJ7S2DJL"; // 00078323 Asigna el nombre del servidor
            this.userPath = "C:/Users/dalet/Documents/ParcialFinal-POO/ProyectoBancoCentral/src/main/resources/org/uca/proyectobancocentral/Reportes/"; // 00078323 Asigna la ruta del usuario
        } else if ("".equals(username)) {
            this.servername = "";
            this.userPath = "";
        } else if ("".equals(username)) {
            this.servername = "";
            this.userPath = "";
        } else if("".equals(username)){
            this.servername = "";
            this.userPath = "";
        } else {
            this.servername = username; // 00078323 Asigna el nombre de usuario como nombre del servidor
        }
    }

    public static UserValidator getInstance() { // 00078323 Método para obtener la instancia única de UserValidator
        if(instance == null) { // 00078323 Si la instancia es nula, crea una nueva instancia
            instance = new UserValidator(); // 00078323 Crea una nueva instancia de UserValidator
        }
        return instance; // 00078323 Retorna la instancia única de UserValidator
    }

    public String getServername() { // 00078323 Método para obtener el nombre del servidor
        return servername; // 00078323 Retorna el nombre del servidor
    }

    public String getUserPath(){ // 00078323 Método para obtener la ruta del usuario
        return userPath; // 00078323 Retorna la ruta del usuario
    }
}
