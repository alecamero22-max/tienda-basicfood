package com.basicfood.controller;

import com.basicfood.api.Producto;
import com.basicfood.service.PaymentService;

import java.util.List;

public class PaymentController {
    private PaymentService service;

    public PaymentController() {
        this.service = new PaymentService();
    }

    public String emitirFactura(List<Producto> listaProductos) {
        return this.service.emitirFactura(listaProductos);
    }
}