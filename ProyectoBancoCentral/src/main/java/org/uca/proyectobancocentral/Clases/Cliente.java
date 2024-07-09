package org.uca.proyectobancocentral.Clases; //Paquete donde se encuentra

public class Cliente {
    private int id; // ID unico del cliente
    private String nombreCompleto; // Nombre completo del cliente
    private String direccion; // Direccion del cliente
    private String telefono; // Número de telefono del cliente
    private int cantidadCompras;
    private double totalGastado;

    public Cliente(int id, String nombreCompleto, String direccion, String telefono) {
        this.id = id; // Pone el ID
        this.nombreCompleto = nombreCompleto; // Pone el nombre completo
        this.direccion = direccion; // Pone la direccion
        this.telefono = telefono; // Pone el telefono
    }

    public Cliente() {

    }

    public int getId() {
        return id; // Devuelve el ID del cliente
    }

    public void setId(int id) {
        this.id = id; // Pone el ID del cliente
    }

    public String getNombreCompleto() {
        return nombreCompleto; // Devuelve el nombre completo del cliente
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto; // Pone el nombre completo del cliente
    }

    public String getDireccion() {
        return direccion; // Devuelve la direccion del cliente
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion; // Pone la direccion del cliente
    }

    public String getTelefono() {
        return telefono; // Devuelve el telefono del cliente
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; // Pone el telefono del cliente
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }
}

