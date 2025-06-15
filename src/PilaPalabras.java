import java.util.*;

public class PilaPalabras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cantidad de palabras: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        Stack<String> pila = new Stack<>();
        System.out.println("Ingrese las palabras:");
        for (int i = 0; i < cantidad; i++) {
            pila.push(sc.nextLine());
        }

        System.out.print("\nPalabra a buscar: ");
        String buscada = sc.nextLine();

        int posicion = 1;
        boolean encontrada = false;

        for (String palabra : pila) {
            if (palabra.equalsIgnoreCase(buscada)) {
                System.out.println("Encontrada en posición " + posicion + " (de arriba a abajo)");
                encontrada = true;
                break;
            }
            posicion++;
        }

        if (!encontrada) {
            System.out.println("La palabra no está en la pila");
        }

        System.out.println("\nContenido completo de la pila (de arriba a abajo):");
        pila.forEach(System.out::println);
    }
}