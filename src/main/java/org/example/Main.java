package org.example;

public class Main {

    public static void main(String[] args) {
        int[][] ejemplos_xs = {
                {6, 7, 12, 14},
                {0, 6, 12, 18},
                {1, 3, 5, 7, 9},
                {2, 4, 10, 11, 17},
                {0, 2, 6, 9, 15, 16, 22}
        };

        int[][] ejemplos_ps = {
                {5, 6, 5, 1},
                {4, 5, 7, 4},
                {2, 3, 4, 5, 6},
                {4, 3, 7, 6, 5},
                {1, 8, 5, 2, 9, 3, 4}
        };

        for (int t = 0; t < ejemplos_xs.length; t++) {
            int[] xs = ejemplos_xs[t];
            int[] ps = ejemplos_ps[t];

            Optimal.Result r = Optimal.calcularOptimo(xs, ps);

            System.out.println("🧩 Ejemplo " + (t + 1));
            System.out.println("Posiciones posibles: " + java.util.Arrays.toString(xs));
            System.out.println("Poblaciones (ps):    " + java.util.Arrays.toString(ps));
            System.out.println("➡️  Total óptimo: " + r.total);
            System.out.print("🏥 Hospitales seleccionados (posiciones): ");
            for (int idx : r.indices) System.out.print(xs[idx] + " ");
            System.out.println("\nÍndices seleccionados: " + r.indices);
            System.out.println("-----------------------------\n");
        }
    }

}