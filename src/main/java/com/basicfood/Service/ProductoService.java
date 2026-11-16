package com.basicfood.service;

import com.basicfood.entity.Producto;
import com.basicfood.repository.ProductoRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService() {
        this.productoRepository = new ProductoRepository();
    }

    public List<Producto> getProductos() {
        try {
            return productoRepository.findAll();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public String mostrarCatalogo() {
        StringBuilder productos = new StringBuilder();
        for (Producto producto : getProductos()) {
            productos.append(producto.getNombre())
                    .append(" - ")
                    .append(producto.getPrecio())
                    .append("€\n");
        }
        return productos.toString();
    }

    public Producto buscarProducto(String nombre) {
        try {
            return productoRepository.findByNombre(nombre);
        } catch (SQLException e) {
            return null;
        }
    }
}
