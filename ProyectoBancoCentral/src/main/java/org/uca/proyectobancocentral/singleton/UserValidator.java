package org.uca.proyectobancocentral.singleton;

public class UserValidator {
    private static UserValidator instance;
    private String servername;
    private UserValidator() {
        String username = System.getProperty("user.name");
        if("dalet".equals(username)) {
            this.servername = "LAPTOP-SJ7S2DJL";
        } else if ("abra3".equals(username)) {
            this.servername = "GERMAN";
        } else if ("".equals(username)) {
            this.servername = "";
        } else if("".equals(username)){
            this.servername = "";
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
}
