package com.basicfood.controller;

import com.basicfood.api.Carrito;
import com.basicfood.api.Producto;
import com.basicfood.service.CarritoService;

public class CarritoController {
    private CarritoService service;

    public CarritoController() {
        this.service = new CarritoService();
    }

    public Carrito getCarrito() {
        return this.service.getCarrito();
    }

    public boolean addProduct(Producto producto) {
        return this.service.addProduct(producto);
    }

    public boolean removeProducto(String nombre) {
        return this.service.removeProducto(nombre);
    }

    public void vaciar() {
        this.service.vaciar();
    }
}