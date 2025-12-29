package udla.asango.empresa;
import udla.asango.empresa.dao.UsuarioDao;
import udla.asango.empresa.database.DBConfig;
import udla.asango.empresa.modelo.Usuario;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Verificar conexión
        try (Connection conn = DBConfig.getConnection()) {
            System.out.println("✅ Conexión exitosa a MySQL");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión");
            return;
        }

        Scanner sc = new Scanner(System.in);
        UsuarioDao dao = new UsuarioDao();

        System.out.println("""
                -- MENÚ --
                1. Ingresar usuario
                2. Mostrar usuarios
                """);

        int opc = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        switch (opc) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Correo: ");
                String correo = sc.nextLine();

                Usuario u = new Usuario(nombre, correo);
                dao.insertarUsuario(u);
            }

            case 2 -> dao.mostrarUsuarios();

            default -> System.out.println("❌ Opción inválida");
        }
    }
}
