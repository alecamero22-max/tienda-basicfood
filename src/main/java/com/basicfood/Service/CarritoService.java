package com.basicfood.service;

import com.basicfood.api.Carrito;
import com.basicfood.api.Producto;

public class CarritoService {
    private Carrito carrito;

    public CarritoService() {
        this.carrito = new Carrito();
    }

    public boolean addProduct(Producto producto) {
        if (producto != null && this.carrito.getListaProductos().size() < this.carrito.getCANTIDAD_TOPE()) {
            this.carrito.getListaProductos().add(producto);
            return true;
        }
        return false;
    }
}