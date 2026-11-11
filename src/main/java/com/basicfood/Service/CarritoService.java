package com.basicfood.service;

import java.util.Iterator;

import com.basicfood.api.Carrito;
import com.basicfood.api.Producto;

public class CarritoService {
    private Carrito carrito;

    public CarritoService() {
        this.carrito = new Carrito();
    }

    public Carrito getCarrito() {
        return this.carrito;
    }

    public boolean addProduct(Producto producto) {
        if (producto != null && this.carrito.getListaProductos().size() < this.carrito.getCANTIDAD_TOPE()) {
            this.carrito.getListaProductos().add(producto);
            return true;
        }
        return false;
    }

    /**
     * Quita una sola unidad del producto indicado.
     */
    public boolean removeProducto(String nombre) {
        if (nombre == null) {
            return false;
        }
        Iterator<Producto> it = this.carrito.getListaProductos().iterator();
        while (it.hasNext()) {
            Producto producto = it.next();
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void vaciar() {
        this.carrito.getListaProductos().clear();
    }
}