package org.uca.proyectobancocentral.Clases; //00082923 Paquete donde se encuentra

import java.time.LocalDate;

public class Tarjeta { //00082923 inicio clase
    private int id; //00082923 declaramos ID de la tarjeta
    private String numeroTarjeta; //00082923 declaramos Numero de la tarjeta
    private LocalDate fechaExpiracion; //00082923 declaramos Fecha de expiracion de la tarjeta
    private String tipoTarjeta; //00082923 declaramos Tipo de tarjeta (Credito o Debito)
    private String facilitador; //00082923 declaramos Facilitador de la tarjeta (visa, mastercard)
    private int clienteId; //00082923 declaramos Identificador del cliente asociado

    public Tarjeta(int id, String numeroTarjeta, LocalDate fechaExpiracion, String tipoTarjeta, String facilitador, int clienteId) { //00082923 constructor no vacio
    
        this.id = id; //00082923 Asigna el identificador
        this.numeroTarjeta = numeroTarjeta; //00082923 Asigna el numero de la tarjeta
        this.fechaExpiracion = fechaExpiracion; //00082923 Asigna la fecha de expiracion
        this.tipoTarjeta = tipoTarjeta; //00082923 Asigna el tipo de tarjeta
        this.facilitador = facilitador; //00082923 Asigna el facilitador de la tarjeta
        this.clienteId = clienteId; //00082923 Asigna el identificador del cliente asociado
    }

    public Tarjeta() { //Constructor vacio

    }

    public int getId() { //00082923 getter de ID
        return id; //00082923 Retorna el identificador de la tarjeta
    }

    public void setId(int id) { //00082923 setter de ID
        this.id = id; //00082923 Asigna el identificador de la tarjeta
    }

    public String getNumeroTarjeta() { //00082923 getter de num tarjeta
        return numeroTarjeta; //00082923 Devuelve el número de tarjeta
    }

    public void setNumeroTarjeta(String numeroTarjeta) { //00082923 setter de num tarjeta
        this.numeroTarjeta = numeroTarjeta; //00082923 Pone el numero de  tarjeta
    }

    public LocalDate getFechaExpiracion() { //00082923 getter de fecha expiracion
        return fechaExpiracion; //00082923 Devuelve la fecha de expiracion
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {  //00082923 setter de fecha expiracion
        this.fechaExpiracion = fechaExpiracion; //00082923 Pone la fecha de expiracion
    }

    public String getTipoTarjeta() { //00082923 getter de tipo tarjeta
        return tipoTarjeta; //00082923 Devuelve el tipo de tarjeta
    }

    public void setTipoTarjeta(String tipoTarjeta) { //00082923 setter de tipo tarjeta
        this.tipoTarjeta = tipoTarjeta; //00082923 Pone el tipo de tarjeta
    }

    public String getFacilitador() { //00082923 getter de facilitador
        return facilitador; //00082923 Devuelve el facilitador de la tarjeta
    }

    public void setFacilitador(String banco) { //00082923 setter de facilitador
        this.facilitador = banco; //00082923 Pone el facilitador de la tarjeta
    }

    public int getClienteId() { //00082923 getter de ID
        return clienteId; //00082923 Devuelve el ID del cliente
    }

    public void setClienteId(int clienteId) { //00082923 setter de ID
        this.clienteId = clienteId; //00082923 Pone el ID del cliente
    }

    @Override
    public String toString() {return "Numero tarjeta:"+numeroTarjeta+ " tipo de tarjeta:" + tipoTarjeta;}//00011223 devuelve la cadena con el numero de tarjeta y el tipo de tarjeta

}
