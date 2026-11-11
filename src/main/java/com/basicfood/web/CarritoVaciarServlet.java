package com.basicfood.web;

import java.io.IOException;

import com.basicfood.controller.CarritoController;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Vacía el carrito entero.
 */
@WebServlet(name = "carritoVaciar", urlPatterns = "/carrito/vaciar")
public class CarritoVaciarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws IOException {
        CarritoController carrito = CarritoDeSesion.obtener(peticion.getSession());
        carrito.vaciar();
        CarritoDeSesion.avisar(peticion.getSession(), "ok", "Carrito vaciado.");
        respuesta.sendRedirect(peticion.getContextPath() + "/catalogo");
    }
}
