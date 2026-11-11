package com.basicfood.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.basicfood.api.Producto;
import com.basicfood.controller.CarritoController;
import com.basicfood.controller.ProductoController;
import com.basicfood.web.model.LineaCarrito;
import com.basicfood.web.model.ProductoVista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Pinta el catálogo y el carrito. Es la única página del front.
 */
@WebServlet(name = "catalogo", urlPatterns = { "", "/catalogo" })
public class CatalogoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {

        ProductoController productos = new ProductoController();
        CarritoController carrito = CarritoDeSesion.obtener(peticion.getSession());

        List<ProductoVista> catalogo = new ArrayList<>();
        for (Producto producto : productos.getProductos()) {
            catalogo.add(ProductoVista.desde(producto));
        }

        // Agrupamos los productos repetidos para mostrar "Pizza x3" en una línea.
        Map<String, LineaCarrito> agrupadas = new LinkedHashMap<>();
        for (Producto producto : carrito.getCarrito().getListaProductos()) {
            agrupadas.computeIfAbsent(producto.getNombre(), clave -> new LineaCarrito(producto))
                    .sumarUnaUnidad();
        }
        List<LineaCarrito> lineas = new ArrayList<>(agrupadas.values());

        double total = 0;
        for (LineaCarrito linea : lineas) {
            total += linea.getSubtotal();
        }

        HttpSession sesion = peticion.getSession();
        String mensaje = CarritoDeSesion.leerMensaje(sesion);
        if (mensaje != null) {
            peticion.setAttribute("mensaje", mensaje);
            peticion.setAttribute("mensajeTipo", CarritoDeSesion.leerTipo(sesion));
            CarritoDeSesion.limpiarMensaje(sesion);
        }

        peticion.setAttribute("productos", catalogo);
        peticion.setAttribute("lineas", lineas);
        peticion.setAttribute("total", total);
        peticion.setAttribute("cantidad", carrito.getCarrito().getListaProductos().size());
        peticion.setAttribute("tope", carrito.getCarrito().getCANTIDAD_TOPE());

        peticion.getRequestDispatcher("/WEB-INF/views/catalogo.jsp").forward(peticion, respuesta);
    }
}
