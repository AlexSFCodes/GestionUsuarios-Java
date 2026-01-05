package udla.asango.empresa.dao;

import udla.asango.empresa.database.Conexion;

import java.sql.*;

public class ClienteDAO {

    public void insertar(String nombre, String cedula) {
        String sql = "INSERT INTO clientes(nombre, cedula) VALUES (?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, cedula);
            ps.executeUpdate();
            System.out.println("Cliente registrado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        String sql = "SELECT * FROM clientes";

        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_cliente") + " | " +
                                rs.getString("nombre") + " | " +
                                rs.getString("cedula")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
