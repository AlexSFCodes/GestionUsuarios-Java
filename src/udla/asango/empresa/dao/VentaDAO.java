package udla.asango.empresa.dao;

import udla.asango.empresa.database.Conexion;

import java.sql.*;

public class VentaDAO {

    public void registrarVenta(int idCliente, int idMedicamento, int cantidad, double precio) {
        try (Connection c = Conexion.getConnection()) {
            c.setAutoCommit(false);

            String ventaSQL = "INSERT INTO ventas(id_cliente, total) VALUES (?, ?)";
            PreparedStatement psVenta = c.prepareStatement(ventaSQL, Statement.RETURN_GENERATED_KEYS);
            double total = cantidad * precio;

            psVenta.setInt(1, idCliente);
            psVenta.setDouble(2, total);
            psVenta.executeUpdate();

            ResultSet rs = psVenta.getGeneratedKeys();
            rs.next();
            int idVenta = rs.getInt(1);

            String detalleSQL =
                    "INSERT INTO detalle_venta(id_venta, id_medicamento, cantidad, subtotal) VALUES (?, ?, ?, ?)";

            PreparedStatement psDetalle = c.prepareStatement(detalleSQL);
            psDetalle.setInt(1, idVenta);
            psDetalle.setInt(2, idMedicamento);
            psDetalle.setInt(3, cantidad);
            psDetalle.setDouble(4, total);
            psDetalle.executeUpdate();

            new MedicamentoDAO().reducirStock(idMedicamento, cantidad);

            c.commit();
            System.out.println("Venta registrada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
