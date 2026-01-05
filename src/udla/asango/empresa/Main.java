package udla.asango.empresa;

import udla.asango.empresa.dao.ClienteDAO;
import udla.asango.empresa.dao.MedicamentoDAO;
import udla.asango.empresa.dao.VentaDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        var medDAO = new MedicamentoDAO();
        VentaDAO ventaDAO = new VentaDAO();

        int op;

        do {
            System.out.println("\n=== FARMACIA ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar medicamento");
            System.out.println("3. Listar medicamentos");
            System.out.println("4. Realizar venta");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    clienteDAO.insertar(sc.nextLine(), sc.next());
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("Precio: ");
                    double p = sc.nextDouble();
                    System.out.print("Stock: ");
                    int s = sc.nextInt();
                    medDAO.insertar(n, p, s);
                    break;

                case 3:
                    medDAO.listar();
                    break;

                case 4:
                    System.out.print("ID Cliente: ");
                    int c = sc.nextInt();
                    System.out.print("ID Medicamento: ");
                    int m = sc.nextInt();
                    System.out.print("Cantidad: ");
                    int q = sc.nextInt();
                    System.out.print("Precio unitario: ");
                    double pr = sc.nextDouble();
                    ventaDAO.registrarVenta(c, m, q, pr);
                    break;
            }
        } while (op != 0);
    }
}
