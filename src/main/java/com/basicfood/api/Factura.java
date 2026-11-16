package com.basicfood.api;

import java.util.Date;
import java.util.List;

public class Factura {
    private List<Producto> listaProductos;
    private Date fecha;

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}