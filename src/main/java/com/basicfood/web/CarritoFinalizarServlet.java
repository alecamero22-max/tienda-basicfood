package com.basicfood.web;

import java.io.IOException;
import java.util.Locale;

import com.basicfood.api.Producto;
import com.basicfood.controller.CarritoController;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Cierra el pedido: calcula el total, vacía el carrito y muestra la confirmación.
 */
@WebServlet(name = "carritoFinalizar", urlPatterns = "/carrito/finalizar")
public class CarritoFinalizarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws IOException {
        CarritoController carrito = CarritoDeSesion.obtener(peticion.getSession());

        if (carrito.getCarrito().getListaProductos().isEmpty()) {
            CarritoDeSesion.avisar(peticion.getSession(), "error",
                    "Tu carrito está vacío: añade algo antes de finalizar.");
        } else {
            double total = 0;
            for (Producto producto : carrito.getCarrito().getListaProductos()) {
                total += producto.getPrecio();
            }
            carrito.vaciar();
            CarritoDeSesion.avisar(peticion.getSession(), "ok",
                    String.format(Locale.forLanguageTag("es-ES"),
                            "¡Pedido confirmado por %.2f €! Gracias por tu compra.", total));
        }

        respuesta.sendRedirect(peticion.getContextPath() + "/catalogo");
    }
}
