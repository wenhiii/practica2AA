package org.example;

public class Main {

    public static void main(String[] args) {
        int[][] examplePositions = {
                {6, 7, 12, 14},
                {0, 6, 12, 18},
                {1, 3, 5, 7, 9},
                {2, 4, 10, 11, 17},
                {0, 2, 6, 9, 15, 16, 22}
        };

        int[][] examplePopulations = {
                {5, 6, 5, 1},
                {4, 5, 7, 4},
                {2, 3, 4, 5, 6},
                {4, 3, 7, 6, 5},
                {1, 8, 5, 2, 9, 3, 4}
        };

        for (int t = 0; t < examplePositions.length; t++) {
            int[] positions = examplePositions[t];
            int[] populations = examplePopulations[t];

            Optimal.Result result = Optimal.calculateOptimal(positions, populations);

            System.out.println("🧩 Example " + (t + 1));
            System.out.println("Possible positions: " + java.util.Arrays.toString(positions));
            System.out.println("Populations:        " + java.util.Arrays.toString(populations));
            System.out.println("➡️  Optimal total: " + result.total);
            System.out.print("🏥 Selected hospitals (positions): ");
            for (int idx : result.indices) System.out.print(positions[idx] + " ");
            System.out.println("\nSelected indices: " + result.indices);
            System.out.println("-----------------------------\n");
        }
    }

}
