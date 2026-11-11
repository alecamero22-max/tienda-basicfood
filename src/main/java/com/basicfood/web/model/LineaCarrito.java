package com.basicfood.web.model;

import com.basicfood.api.Producto;

/**
 * Una línea del carrito ya agrupada: si el alumno añade tres pizzas,
 * el carrito guarda tres Producto, pero aquí se ve una sola línea "Pizza x3".
 */
public class LineaCarrito {

    private final String nombre;
    private final double precio;
    private final String emoji;
    private int cantidad;

    public LineaCarrito(Producto producto) {
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.emoji = ProductoVista.emojiDe(producto.getNombre());
        this.cantidad = 0;
    }

    public void sumarUnaUnidad() {
        this.cantidad++;
    }

    public double getSubtotal() {
        return precio * cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getCantidad() {
        return cantidad;
    }
}
