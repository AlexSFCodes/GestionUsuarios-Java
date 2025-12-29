package udla.asango.empresa.dao;
import udla.asango.empresa.database.DBConfig;
import udla.asango.empresa.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {
    // INSERTAR USUARIO
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, correo) VALUES (?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.executeUpdate();

            System.out.println("✅ Usuario registrado correctamente");

        } catch (Exception e) {
            System.out.println("❌ Error al insertar usuario");
            e.printStackTrace();
        }
    }

    // MOSTRAR USUARIOS
    public void mostrarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- LISTA DE USUARIOS ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nombre") + " | " +
                                rs.getString("correo")
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error al mostrar usuarios");
            e.printStackTrace();
        }
    }

}
