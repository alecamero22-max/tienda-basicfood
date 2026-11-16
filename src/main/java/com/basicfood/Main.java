package com.basicfood;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.basicfood.controller.CarritoController;
import com.basicfood.controller.ProductoController;
import com.basicfood.entity.Producto;
import com.basicfood.repository.ProductoRepository;

public class Main {
    public static void main(String[] args) {

        probarBaseDeDatos();

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

            if(opcion < 1 || opcion > 4) {
              String comprobacion = "Opción inválida. Por favor, elige una opción entre 1 y 4.";

            }
 
            String comprobacion = opcion < 1 || opcion > 4 ? "Opción inválida. Por favor, elige una opción entre 1 y 4." : "";



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

    private static void probarBaseDeDatos() {
        try {
            ProductoRepository productoRepository = new ProductoRepository();

            Producto productoPrueba = productoRepository.findByNombre("Producto prueba JDBC");
            if (productoPrueba == null) {
                productoPrueba = new Producto("Producto prueba JDBC", 1.99, "Pruebas");
                productoRepository.save(productoPrueba);
            }

            List<Producto> productos = productoRepository.findAll();
            System.out.println("--- Productos en SQLite (findAll) ---");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
            System.out.println("-------------------------------------");
        } catch (SQLException e) {
            System.out.println("Error al probar la base de datos: " + e.getMessage());
        }
    }
}
