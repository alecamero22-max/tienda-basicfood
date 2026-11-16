package com.basicfood.service;

import com.basicfood.api.Factura;
import com.basicfood.api.Producto;

import java.util.Date;
import java.util.List;

public class PaymentService {
    private Factura factura;

    public PaymentService() {
        this.factura = new Factura();
    }

    public String emitirFactura(List<Producto> listaProductos) {
        this.factura.setFecha(new Date());
        this.factura.setListaProductos(listaProductos);

        String texto = "Fecha: " + this.factura.getFecha() + "\n";
        double total = 0;

        while (!this.factura.getListaProductos().isEmpty()) {
            int i = this.factura.getListaProductos().size() - 1;
            Producto item = this.factura.getListaProductos().remove(i);
            texto += item.getNombre() + " - " + item.getPrecio() + "€\n";
            total += item.getPrecio();
        }

        texto += "Total: " + total + "€";
        return texto;
    }
}
