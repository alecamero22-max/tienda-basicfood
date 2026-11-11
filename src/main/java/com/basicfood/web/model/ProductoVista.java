package com.basicfood.web.model;

import java.util.Locale;

import com.basicfood.api.Producto;

/**
 * Producto preparado para pintarlo en la JSP.
 *
 * El emoji y la descripción son solo decoración del front: no forman parte
 * del modelo de negocio del alumno. Si añade productos nuevos, se les asigna
 * un emoji genérico automáticamente.
 */
public class ProductoVista {

    private final String nombre;
    private final double precio;
    private final String emoji;
    private final String descripcion;

    public ProductoVista(String nombre, double precio, String emoji, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.emoji = emoji;
        this.descripcion = descripcion;
    }

    public static ProductoVista desde(Producto producto) {
        return new ProductoVista(
                producto.getNombre(),
                producto.getPrecio(),
                emojiDe(producto.getNombre()),
                descripcionDe(producto.getNombre()));
    }

    public static String emojiDe(String nombre) {
        String clave = normalizar(nombre);
        return switch (clave) {
            case "pizza" -> "🍕";
            case "patata", "patatas" -> "🍟";
            case "aquarius" -> "🧃";
            case "hamburguesa" -> "🍔";
            case "ensalada" -> "🥗";
            case "agua" -> "💧";
            case "helado" -> "🍦";
            default -> "🍽️";
        };
    }

    public static String descripcionDe(String nombre) {
        String clave = normalizar(nombre);
        return switch (clave) {
            case "pizza" -> "Masa artesana, tomate y mozzarella.";
            case "patata", "patatas" -> "Patatas fritas crujientes.";
            case "aquarius" -> "Refresco de limón bien frío.";
            case "hamburguesa" -> "Carne, queso y verduras frescas.";
            case "ensalada" -> "Verde, fresca y de temporada.";
            case "agua" -> "Botella de agua mineral.";
            case "helado" -> "Vainilla y chocolate.";
            default -> "Producto de la tienda BasicFood.";
        };
    }

    private static String normalizar(String nombre) {
        return nombre == null ? "" : nombre.trim().toLowerCase(Locale.ROOT);
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

    public String getDescripcion() {
        return descripcion;
    }
}
