package com.basicfood.web;

import com.basicfood.controller.CarritoController;

import jakarta.servlet.http.HttpSession;

/**
 * Guarda un CarritoController por sesión.
 *
 * Cada navegador tiene su propio carrito, igual que en la consola cada
 * ejecución tenía el suyo. Cuando pasemos a Spring Boot, esto será un bean
 * con @SessionScope y no habrá que cambiar las JSP.
 */
public final class CarritoDeSesion {

    private static final String CLAVE_CARRITO = "carritoController";
    private static final String CLAVE_MENSAJE = "flashMensaje";
    private static final String CLAVE_TIPO = "flashTipo";

    private CarritoDeSesion() {
    }

    public static CarritoController obtener(HttpSession sesion) {
        CarritoController carrito = (CarritoController) sesion.getAttribute(CLAVE_CARRITO);
        if (carrito == null) {
            carrito = new CarritoController();
            sesion.setAttribute(CLAVE_CARRITO, carrito);
        }
        return carrito;
    }

    /**
     * Deja un mensaje para la siguiente página (patrón POST + redirect + GET).
     */
    public static void avisar(HttpSession sesion, String tipo, String mensaje) {
        sesion.setAttribute(CLAVE_TIPO, tipo);
        sesion.setAttribute(CLAVE_MENSAJE, mensaje);
    }

    public static String leerMensaje(HttpSession sesion) {
        Object mensaje = sesion.getAttribute(CLAVE_MENSAJE);
        return mensaje == null ? null : mensaje.toString();
    }

    public static String leerTipo(HttpSession sesion) {
        Object tipo = sesion.getAttribute(CLAVE_TIPO);
        return tipo == null ? "ok" : tipo.toString();
    }

    public static void limpiarMensaje(HttpSession sesion) {
        sesion.removeAttribute(CLAVE_MENSAJE);
        sesion.removeAttribute(CLAVE_TIPO);
    }
}
