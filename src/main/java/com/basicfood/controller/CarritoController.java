package com.basicfood.controller;

import com.basicfood.api.Producto;
import com.basicfood.service.CarritoService;

public class CarritoController {
    private CarritoService service;

    public CarritoController() {
        this.service = new CarritoService();
    }

    public boolean addProduct(Producto producto) {
        return this.service.addProduct(producto);
    }
}