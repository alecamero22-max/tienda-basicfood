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
		// Agrega más productos según sea necesario

        // Productos de ejemplo para que el catálogo del front tenga más vida.
        // Puedes borrarlos o cambiarlos sin miedo.
        listaProductos.add(new Producto("hamburguesa", 6.50));
        listaProductos.add(new Producto("ensalada", 4.20));
        listaProductos.add(new Producto("agua", 1.20));
        listaProductos.add(new Producto("helado", 3.10));
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(listaProductos);
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


