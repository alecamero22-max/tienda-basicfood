package com.basicfood.web;

import java.io.IOException;

import com.basicfood.controller.CarritoController;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Quita una unidad de un producto del carrito.
 */
@WebServlet(name = "carritoRemove", urlPatterns = "/carrito/remove")
public class CarritoRemoveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws IOException {
        peticion.setCharacterEncoding("UTF-8");

        String nombre = peticion.getParameter("nombre");
        CarritoController carrito = CarritoDeSesion.obtener(peticion.getSession());

        if (nombre != null && carrito.removeProducto(nombre)) {
            CarritoDeSesion.avisar(peticion.getSession(), "ok",
                    "Una unidad de «" + nombre + "» fuera del carrito.");
        } else {
            CarritoDeSesion.avisar(peticion.getSession(), "error",
                    "Ese producto no estaba en el carrito.");
        }

        respuesta.sendRedirect(peticion.getContextPath() + "/catalogo");
    }
}
