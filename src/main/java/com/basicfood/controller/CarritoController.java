package com.basicfood.controller;

import com.basicfood.api.Producto;
import com.basicfood.service.CarritoService;
import java.util.List;

public class CarritoController {
    private CarritoService service;

    public CarritoController() {
        this.service = new CarritoService();
    }

    public boolean addProduct(Producto producto) {
        return this.service.addProduct(producto);
    }

    public String mostrarCarrito(){
        return this.service.mostrarCarrito();
    }

    public boolean eliminarProducto(String nombre){
        return this.service.eliminarProducto(nombre);
    }
    public List<Producto> getListaProductos() {
        return this.service.getListaProductos();
    }
}
