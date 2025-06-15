import java.util.Stack;
import java.util.Collections;
import java.util.Scanner;

public class PilasNumericas {
    private Stack<Integer> pilaA;
    private Stack<Integer> pilaB;
    private int capacidad;

    public PilasNumericas(int capacidad) {
        this.capacidad = capacidad;
        pilaA = new Stack<>();
        pilaB = new Stack<>();
    }

    public void llenarPilaA() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese " + capacidad + " números enteros para la PilaA:");

        while (pilaA.size() < capacidad) {
            try {
                int numero = scanner.nextInt();
                pilaA.push(numero);
            } catch (Exception e) {
                System.out.println("Error: Ingrese solo números enteros.");
                scanner.next();
            }
        }
    }

    public void crearPilaB() {
        Stack<Integer> temp = new Stack<>();
        temp.addAll(pilaA);

        while (!temp.isEmpty()) {
            int num = temp.pop();
            if (num % 2 != 0) {
                pilaB.push(num);
            }
        }

        Collections.sort(pilaB);
        Collections.reverse(pilaB);
    }

    public void mostrarPilas() {
        System.out.println("\n--- Resultados ---");
        System.out.println("PilaA : " + pilaA);
        System.out.println("PilaB (impares descendentes): " + pilaB);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la capacidad de las pilas: ");
        int capacidad = scanner.nextInt();

        PilasNumericas pilas = new PilasNumericas(capacidad);
        pilas.llenarPilaA();
        pilas.crearPilaB();
        pilas.mostrarPilas();
    }
}