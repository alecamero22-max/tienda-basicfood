package com.basicfood.controller;

import com.basicfood.api.Producto;
import com.basicfood.service.ProductoService;

public class ProductoController {
    private ProductoService service;

    public ProductoController() {
        this.service = new ProductoService();
    }

    public String mostrarCatalogo() {
        return this.service.mostrarCatalogo();
    }

    public Producto buscarProducto(String nombre) {
        return this.service.buscarProducto(nombre);
    }
}