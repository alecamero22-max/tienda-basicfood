package com.basicfood.service;

import java.util.ArrayList;
import java.util.List;

import com.basicfood.api.Producto;

public class ProductoService {
    private List<Producto> listaProductos = new ArrayList<>();

    public ProductoService() {
        Producto p1 = new Producto("pizza", 2.48);
        Producto p2 = new Producto("patata", 5);
        Producto p3 = new Producto("aquarius", 2);
        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
    }

    public String mostrarCatalogo() {
        String productos = "";
        for (Producto producto : listaProductos) {
            productos += producto.getNombre() + " - " + producto.getPrecio() + "€\n";
        }
        return productos;
    }

    public Producto buscarProducto(String nombre) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }
}