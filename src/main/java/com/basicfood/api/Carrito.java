package com.basicfood.api;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private final int CANTIDAD_TOPE = 20;
    private List<Producto> listaProductos = new ArrayList<>();

    public int getCANTIDAD_TOPE() {
        return CANTIDAD_TOPE;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}