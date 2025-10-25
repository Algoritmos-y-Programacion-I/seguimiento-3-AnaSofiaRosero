package ui;

import java.util.Scanner;
import model.SchoolController;

public class SchoolApp {

    private Scanner escaner;
    private SchoolController controller;

    public static void main(String[] args) {
        SchoolApp ui = new SchoolApp();
        ui.menu();
    }

    public SchoolApp() {
        this.escaner = new Scanner(System.in);
        this.controller = new SchoolController("Computaricemos");
    }

    public void menu() {

        System.out.println("Bienvenido a Computaricemos");

        int option = 0;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("--------------------------------------------------------");
            System.out.println("Digite alguna de las siguientes opciones");
            System.out.println("1) Registrar computador");
            System.out.println("2) Registrar incidente en computador");
            System.out.println("3) Consultar el computador con más incidentes");
            System.out.println("0) Salir del sistema");
            option = this.escaner.nextInt();

            switch (option) {
                case 1:
                    this.registrarComputador();
                    break;
                case 2:
                    this.registrarIncidenteEnComputador();
                    break;
                case 3:
                    this.consultarComputadorConMasIncidentes();
                    break;
                case 0:
                    System.out.println("\nGracias por usar nuestros servicios amiguito. Adios!");
                    break;
                default:
                    System.out.println("\nUpsi!!! Opcion invalida. Intente nuevamente.");
                    break;
            }

        } while (option != 0);
    }

    public void registrarComputador() {
        System.out.print("Ingrese numero serial del compu: ");
        String serial = this.escaner.next();

        System.out.print("¿Tiene ventana? (true/false): ");
        boolean tieneVentana = this.escaner.nextBoolean();

        System.out.print("Ingrese el piso (0..4): ");
        int piso = this.escaner.nextInt();

        boolean ok = this.controller.agregarComputador(serial, piso, tieneVentana);

        if (ok) {
            System.out.println("Bieenn, computador registrado correctamente.");
        } else {
            System.out.println("Jmmm hay un error, no se pudo registrar (puede ser un serial repetido o piso lleno).");
        }
    }

    public void registrarIncidenteEnComputador() {
        System.out.print("Ingrese el numero serial: ");
        String serial = this.escaner.next();

        this.escaner.nextLine();
        System.out.print("Ingrese la descripcion del incidente: ");
        String descripcion = this.escaner.nextLine();

        System.out.print("Ingrese las horas estimadas de solucion: ");
        int horas = this.escaner.nextInt();

        boolean ok = this.controller.reportarIncidente(serial, descripcion, horas);

        if (ok) {
            System.out.println("Listop, incidente registrado con exito.");
        } else {
            System.out.println("Uyyy hay un error: el computador no existe o se exceden las horas disponibles.");
        }
    }

    public void consultarComputadorConMasIncidentes() {
        String mensaje = this.controller.computadorConMasIncidentes();

        System.out.println(mensaje);
    }
}
