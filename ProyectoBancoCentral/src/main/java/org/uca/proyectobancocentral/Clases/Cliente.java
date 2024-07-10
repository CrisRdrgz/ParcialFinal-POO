package org.uca.proyectobancocentral.Clases; //Paquete donde se encuentra

public class Cliente { //00082923 inicio clase
    private int id; //00082923 declaramos ID unico del cliente
    private String nombreCompleto; //00082923 declaramos Nombre completo del cliente
    private String direccion; //00082923 declaramos Direccion del cliente
    private String telefono; //00082923 declaramos Número de telefono del cliente
    private int cantidadCompras; //00082923 declaramos Cantidad de compras hechas
    private double totalGastado; //00082923 declaramos total gastado

    public Cliente(int id, String nombreCompleto, String direccion, String telefono) { //00082923 constructor
        this.id = id; //00082923 Pone el ID
        this.nombreCompleto = nombreCompleto; //00082923 Pone el nombre completo
        this.direccion = direccion; //00082923 Pone la direccion
        this.telefono = telefono; //00082923 Pone el telefono
    }

    public Cliente() { //00082923 constructor vacio

    }

    public int getId() {
        return id; //00082923 Devuelve el ID del cliente
    }

    public void setId(int id) {
        this.id = id; //00082923 Pone el ID del cliente
    }

    public String getNombreCompleto() {
        return nombreCompleto; //00082923 Devuelve el nombre completo del cliente
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto; //00082923 Pone el nombre completo del cliente
    }

    public String getDireccion() {
        return direccion; //00082923 Devuelve la direccion del cliente
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion; //00082923 Pone la direccion del cliente
    }

    public String getTelefono() {
        return telefono; //00082923 Devuelve el telefono del cliente
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; //00082923 Pone el telefono del cliente
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    } //00082923 devuelve cantidad de compras

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras; //00082923 pone la cantidad de compras
    }

    public double getTotalGastado() {
        return totalGastado;
    } //00082923 devuelve total gastado

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    } //00082923 pone el total gastado
}

