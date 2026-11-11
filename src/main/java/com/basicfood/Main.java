package com.basicfood;

import java.util.Scanner;

import com.basicfood.api.Producto;
import com.basicfood.controller.CarritoController;
import com.basicfood.controller.ProductoController;

public class Main {
    public static void main(String[] args) {

        ProductoController productoController = new ProductoController();
        CarritoController carritoController = new CarritoController();

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("--- TIENDA DE COMIDA VIRTUAL ---");
            System.out.println("1. Ver catálogo de productos");
            System.out.println("2. Añadir comida al carrito");
            System.out.println("3. Ver mi carrito actual");
            System.out.println("4. Finalizar compra y salir");
            System.out.print("Elige una opción (1-4): ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(productoController.mostrarCatalogo());
                    break;
                case 2:
                    System.out.print("Introduce el nombre del producto: ");
                    String nombre = sc.nextLine();

                    Producto productoEncontrado = productoController.buscarProducto(nombre);

                    if (productoEncontrado != null) {
                        boolean anadido = carritoController.addProduct(productoEncontrado);
                        if (anadido) {
                            System.out.println("Producto añadido al carrito correctamente.");
                        } else {
                            System.out.println("No se pudo añadir: el carrito está lleno.");
                        }
                    } else {
                        System.out.println("Error: El producto '" + nombre + "' no existe en el catálogo.");
                    }
                    break;
                case 3:
                    System.out.println("Revisando tu carrito: Tienes productos listos para pedir.");
                    break;
                case 4:
                    System.out.println("¡Pedido confirmado! Gracias por tu compra.");
                    break;
            }
        } while (opcion != 4);

        sc.close();
    }
}