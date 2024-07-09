package org.uca.proyectobancocentral.Clases; //Paquete donde se encuentra

import java.time.LocalDate;

public class Tarjeta {
    private int id; // ID de la tarjeta
    private String numeroTarjeta; // Numero de la tarjeta
    private LocalDate fechaExpiracion; // Fecha de expiracion de la tarjeta
    private String tipoTarjeta; // Tipo de tarjeta (Credito o Debito)
    private String facilitador; // Facilitador de la tarjeta (visa, mastercard)
    private int clienteId; // Identificador del cliente asociado 

    public Tarjeta(int id, String numeroTarjeta, LocalDate fechaExpiracion, String tipoTarjeta, String facilitador, int clienteId) {
    
        this.id = id; // Asigna el identificador
        this.numeroTarjeta = numeroTarjeta; // Asigna el numero de la tarjeta
        this.fechaExpiracion = fechaExpiracion; // Asigna la fecha de expiracion
        this.tipoTarjeta = tipoTarjeta; // Asigna el tipo de tarjeta
        this.facilitador = facilitador; // Asigna el facilitador de la tarjeta
        this.clienteId = clienteId; // Asigna el identificador del cliente asociado
    }

    public Tarjeta() {

    }

    public int getId() {
        return id; // Retorna el identificador de la tarjeta
    }

    public void setId(int id) {
        this.id = id; // Asigna el identificador de la tarjeta
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta; // Devuelve el número de tarjeta
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta; // Pone el numero de  tarjeta
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion; // Devuelve la fecha de expiracion
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion; // Pone la fecha de expiracion
    }

    public String getTipoTarjeta() {
        return tipoTarjeta; // Devuelve el tipo de tarjeta
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta; // Pone el tipo de tarjeta
    }

    public String getFacilitador() {
        return facilitador; // Devuelve el facilitador de la tarjeta
    }

    public void setFacilitador(String banco) {
        this.facilitador = banco; // Pone el facilitador de la tarjeta
    }

    public int getClienteId() {
        return clienteId; // Devuelve el ID del cliente
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId; // Pone el ID del cliente
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", tipoTarjeta='" + tipoTarjeta + '\'' +
                ", facilitador='" + facilitador + '\'' +
                ", clienteId=" + clienteId +
                '}';
    }
}
