package com.example.barkiko;

public class Producto {

    private int codigo;
    private String nombre;
    private int cantidadEnStock;
    private String descripcion;
    private double precioVenta;
    private double precioCompra;

    public Producto() {
    }

    public Producto(int codigo, String nombre, int cantidadEnStock, double precioVenta, double precioCompra) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadEnStock = cantidadEnStock;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }


    @Override
    public String toString() {
        return nombre;
    }
}
