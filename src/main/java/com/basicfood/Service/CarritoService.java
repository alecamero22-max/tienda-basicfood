package com.basicfood.service;

import com.basicfood.api.Carrito;
import com.basicfood.api.Producto;
import java.util.List;

public class CarritoService {
    private Carrito carrito;

    public CarritoService() {
        this.carrito = new Carrito();
    }

    public boolean addProduct(Producto producto) {
        if (producto != null && this.carrito.getListaProductos().size() < this.carrito.getCANTIDAD_TOPE()) {
            this.carrito.getListaProductos().add(producto);
            return true;
        }
        return false;
    }
    public String mostrarCarrito(){
        String productos = "";
        for (Producto producto : this.carrito.getListaProductos()){
            productos += producto.getNombre() + producto.getPrecio() + "€\n";
        }
        return productos;
    }
    public boolean eliminarProducto(String nombre){
        for(int i = 0; i< this.carrito.getListaProductos().size();i++){
            if(this.carrito.getListaProductos().get(i).getNombre().equals(nombre)){
                this.carrito.getListaProductos().remove(i);
                return true;
            }
        }
        return false;
    }
    public List<Producto> getListaProductos() {
        return this.carrito.getListaProductos();
    }
}
