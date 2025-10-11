package org.example;

import java.util.Arrays;

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

            int optimalTotal = HospitalAlgorithm.calculateOptimalDP(positions, populations);
            int greedyTotal = HospitalAlgorithm.calculateGreedyDistance(positions, populations);
            int greedyTotal2 = HospitalAlgorithm.calculateGreedyFromMax(positions, populations);
            int greedyTotal3 = HospitalAlgorithm.sortIndicesByValueDescending(positions, populations);

            System.out.println("=== Example " + (t + 1) + " ===");
            System.out.println("Positions: " + Arrays.toString(positions));
            System.out.println("Populations: " + Arrays.toString(populations));
            System.out.println(" -> Optimal total: " + optimalTotal);
            System.out.println(" -> GreedyFromDistance total:  " + greedyTotal);
            System.out.println(" -> GreedyFromMax total:  " + greedyTotal2);
            System.out.println(" -> GreedyByHospitalValue total:  " + greedyTotal3);
            System.out.println("Selected hospitals (positions):");
            System.out.println("-----------------------------\n");
        }
    }

}
