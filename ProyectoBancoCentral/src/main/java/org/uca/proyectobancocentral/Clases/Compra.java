package org.uca.proyectobancocentral.Clases; //Paquete donde se encuentra

import java.time.LocalDate;

public class Compra { //00082923 inicio clase
    private int id; // ID de la compra
    private LocalDate fechaCompra; // Fecha de la compra
    private double montoTotal; // total de la compra
    private String descripcion; // Descripcion de la compra
    private int tarjetaId; // ID de la tarjeta

    public Compra(int id, LocalDate fechaCompra, double montoTotal, String descripcion, int tarjetaId) { //00082923 constructor
        this.id = id; //00082923 Pone el identificador
        this.fechaCompra = fechaCompra; //00082923 Pone la fecha de la compra
        this.montoTotal = montoTotal; //00082923 Pone el monto total de la compra
        this.descripcion = descripcion; //00082923 Pone la descripcion de la compra
        this.tarjetaId = tarjetaId; //00082923 Pone el identificador de la tarjeta utilizada
    }

    public Compra() { //00082923 constructor vacio

    }

    public int getId() {
        return id; // Devuelve el ID de la compra
    }

    public void setId(int id) {
        this.id = id; // Pone el ID de la compra
    }

    public LocalDate getFechaCompra() {
        return fechaCompra; // Devuelve la fecha de la compra
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra; // Pone la fecha de la compra
    }

    public double getMontoTotal() {
        return montoTotal; // Devuelve el total de la compra
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal; // Pone el total de la compra
    }

    public String getDescripcion() {
        return descripcion; // Devuelve la descripcion de la compra
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; // Pone la descripcion de la compra
    }

    public int getTarjetaId() {
        return tarjetaId; // Devuelve el ID de la tarjeta
    }

    public void setTarjetaId(int tarjetaId) {
        this.tarjetaId = tarjetaId; // Pone el ID de la tarjeta
    }
}

