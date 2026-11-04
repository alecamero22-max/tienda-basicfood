package com.basicfood.api;

public class Carrito {
    private final int CANTIDAD_TOPE = 20;

    private Producto producto;


    public int getCANTIDAD_TOPE() {
        return CANTIDAD_TOPE;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
