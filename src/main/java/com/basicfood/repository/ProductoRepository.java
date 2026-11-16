package com.basicfood.repository;

import com.basicfood.database.ConexionBD;
import com.basicfood.entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {

    public ProductoRepository() {
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS productos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "precio REAL NOT NULL, " +
                "categoria TEXT" +
                ")";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la tabla productos", e);
        }
    }

    public List<Producto> findAll() throws SQLException {
        String sql = "SELECT * FROM productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("categoria")
                );
                productos.add(producto);
            }
        }

        return productos;
    }

    public Producto findById(int id) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getString("categoria")
                    );
                }
            }
        }

        return null;
    }

    public Producto findByNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM productos WHERE lower(nombre) = lower(?1)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getString("categoria")
                    );
                }
            }
        }

        return null;
    }

    public void save(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, precio, categoria) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getCategoria());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
}
