package org.uca.proyectobancocentral.singleton;

public class UserValidator {
    private static UserValidator instance;
    private String servername;
    private String userPath;
    private UserValidator() {
        String username = System.getProperty("user.name");
        if("dalet".equals(username)) {
            this.servername = "LAPTOP-SJ7S2DJL";
            this.userPath = "C:/Users/dalet/Documents/ParcialFinal-POO/ProyectoBancoCentral/src/main/resources/org/uca/proyectobancocentral/Reportes/";
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
            this.servername = username;
        }
    }
    public static UserValidator getInstance() {
        if(instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    public String getServername() {
        return servername;
    }

    public String getUserPath(){
        return userPath;
    }
}
