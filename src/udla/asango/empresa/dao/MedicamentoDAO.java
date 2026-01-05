package udla.asango.empresa.dao;

import udla.asango.empresa.database.Conexion;

import java.sql.*;

public class MedicamentoDAO {

    public void insertar(String nombre, double precio, int stock) {
        String sql = "INSERT INTO medicamentos(nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.executeUpdate();
            System.out.println("Medicamento agregado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        String sql = "SELECT * FROM medicamentos";

        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_medicamento") + " | " +
                                rs.getString("nombre") + " | $" +
                                rs.getDouble("precio") + " | Stock: " +
                                rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reducirStock(int id, int cantidad) throws Exception {
        String sql = "UPDATE medicamentos SET stock = stock - ? WHERE id_medicamento = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}
