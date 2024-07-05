package org.uca.proyectobancocentral.Clases; //Paquete donde se encuentra

public class Compra {
    private int id; // ID de la compra
    private String fechaCompra; // Fecha de la compra
    private double montoTotal; // total de la compra
    private String descripcion; // Descripcion de la compra
    private int tarjetaId; // ID de la tarjeta

    public Compra(int id, String fechaCompra, double montoTotal, String descripcion, int tarjetaId) {
        this.id = id; // Pone el identificador
        this.fechaCompra = fechaCompra; // Pone la fecha de la compra
        this.montoTotal = montoTotal; // Pone el monto total de la compra
        this.descripcion = descripcion; // Pone la descripcion de la compra
        this.tarjetaId = tarjetaId; // Pone el identificador de la tarjeta utilizada
    }

    public int getId() {
        return id; // Devuelve el ID de la compra
    }

    public void setId(int id) {
        this.id = id; // Pone el ID de la compra
    }

    public String getFechaCompra() {
        return fechaCompra; // Devuelve la fecha de la compra
    }

    public void setFechaCompra(String fechaCompra) {
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

