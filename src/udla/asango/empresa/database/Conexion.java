package udla.asango.empresa.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String URL =
            "jdbc:mysql://localhost:3306/java_sql_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "sasasaxd";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error de conexión");
            return null;
        }
    }
}
