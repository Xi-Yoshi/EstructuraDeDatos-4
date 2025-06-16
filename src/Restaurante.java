import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Restaurante {
    private int mesas2 = 10;
    private int mesas4 = 8;
    private int mesas6 = 6;
    private Queue<Cliente> colaEspera = new LinkedList<>();

    private static class Cliente {
        String nombre;
        int personas;

        public Cliente(String nombre, int personas) {
            this.nombre = nombre;
            this.personas = personas;
        }
    }

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Sistema de Restaurante ---");
            System.out.println("1. Asignar mesa");
            System.out.println("2. Liberar mesa");
            System.out.println("3. Mostrar estado");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    restaurante.asignarMesa(scanner);
                    break;
                case 2:
                    restaurante.liberarMesa(scanner);
                    break;
                case 3:
                    restaurante.mostrarEstado();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void asignarMesa(Scanner scanner) {
        System.out.print("\nNombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Número de personas: ");
        int personas = scanner.nextInt();
        scanner.nextLine();

        if (personas > 6) {
            System.out.println("\nLo sentimos, no tenemos mesas para grupos mayores a 6 personas.");
            System.out.println("El cliente " + nombre + " ha sido colocado en la lista de espera.");
            colaEspera.add(new Cliente(nombre, personas));
            return;
        }

        boolean asignada = false;
        if (personas <= 2 && mesas2 > 0) {
            mesas2--;
            asignada = true;
            System.out.println("\nMesa para 2 asignada a " + nombre);
        } else if (personas <= 4 && mesas4 > 0) {
            mesas4--;
            asignada = true;
            System.out.println("\nMesa para 4 asignada a " + nombre);
        } else if (personas <= 6 && mesas6 > 0) {
            mesas6--;
            asignada = true;
            System.out.println("\nMesa para 6 asignada a " + nombre);
        }

        if (!asignada) {
            System.out.println("\nNo hay mesas disponibles en este momento.");
            System.out.println("El cliente " + nombre + " ha sido colocado en la lista de espera.");
            colaEspera.add(new Cliente(nombre, personas));
        }
    }

    private void liberarMesa(Scanner scanner) {
        System.out.println("\nTipo de mesa a liberar:");
        System.out.println("1. Mesa para 2 personas");
        System.out.println("2. Mesa para 4 personas");
        System.out.println("3. Mesa para 6 personas");
        System.out.print("Seleccione opción: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();
        switch (tipo) {
            case 1:
                mesas2++;
                System.out.println("\nMesa para 2 liberada.");
                break;
            case 2:
                mesas4++;
                System.out.println("\nMesa para 4 liberada.");
                break;
            case 3:
                mesas6++;
                System.out.println("\nMesa para 6 liberada.");
                break;
            default:
                System.out.println("\nOpción no válida");
                return;
        }

        asignarMesaLiberada(tipo);
    }

    private void asignarMesaLiberada(int tipoMesa) {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return;
        }

        Cliente siguiente = colaEspera.peek();
        boolean puedeAsignar = false;

        switch (tipoMesa) {
            case 1:
                puedeAsignar = (siguiente.personas <= 2);
                break;
            case 2:
                puedeAsignar = (siguiente.personas >= 3 && siguiente.personas <= 4);
                break;
            case 3:
                puedeAsignar = (siguiente.personas >= 5 && siguiente.personas <= 6);
                break;
        }

        if (puedeAsignar) {
            colaEspera.remove();
            switch (tipoMesa) {
                case 1:
                    mesas2--;
                    System.out.println("Mesa para 2 asignada a " + siguiente.nombre);
                    break;
                case 2:
                    mesas4--;
                    System.out.println("Mesa para 4 asignada a " + siguiente.nombre);
                    break;
                case 3:
                    mesas6--;
                    System.out.println("Mesa para 6 asignada a " + siguiente.nombre);
                    break;
            }
        } else {
            System.out.println("El siguiente cliente en espera no es compatible con esta mesa.");
        }
    }

    private void mostrarEstado() {
        System.out.println("\n--- Estado Actual ---");
        System.out.println("Mesas disponibles:");
        System.out.println("Para 2 personas: " + mesas2);
        System.out.println("Para 4 personas: " + mesas4);
        System.out.println("Para 6 personas: " + mesas6);

        System.out.println("\nClientes en espera: " + colaEspera.size());
        if (!colaEspera.isEmpty()) {
            System.out.println("Siguiente en la cola: " + colaEspera.peek().nombre +
                    " (" + colaEspera.peek().personas + " personas)");
        }
    }
}
