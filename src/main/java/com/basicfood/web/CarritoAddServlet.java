package com.basicfood.web;

import java.io.IOException;

import com.basicfood.api.Producto;
import com.basicfood.controller.CarritoController;
import com.basicfood.controller.ProductoController;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Añade un producto al carrito y vuelve al catálogo (POST + redirect + GET).
 */
@WebServlet(name = "carritoAdd", urlPatterns = "/carrito/add")
public class CarritoAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws IOException {
        peticion.setCharacterEncoding("UTF-8");

        String nombre = peticion.getParameter("nombre");
        CarritoController carrito = CarritoDeSesion.obtener(peticion.getSession());
        Producto producto = nombre == null ? null : new ProductoController().buscarProducto(nombre);

        if (producto == null) {
            CarritoDeSesion.avisar(peticion.getSession(), "error",
                    "Ese producto no existe en el catálogo.");
        } else if (carrito.addProduct(producto)) {
            CarritoDeSesion.avisar(peticion.getSession(), "ok",
                    "«" + producto.getNombre() + "» añadido al carrito.");
        } else {
            CarritoDeSesion.avisar(peticion.getSession(), "error",
                    "El carrito está lleno: máximo " + carrito.getCarrito().getCANTIDAD_TOPE()
                            + " productos por pedido.");
        }

        respuesta.sendRedirect(peticion.getContextPath() + "/catalogo");
    }
}
