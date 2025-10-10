package org.example;

public class HospitalAlgorithm {

    /**
     * Optimal selection using DP: choose hospitals to maximize total population
     * served, ensuring any two chosen hospitals are >5 units apart.
     */
    public static int calculateOptimalDP(int[] positions, int[] populations) {
        int n = positions.length;
        int[] dp = new int[n];

        dp[0] = populations[0];

        for (int i = 1; i < n; i++) {
            // Find the largest j < i such that positions[j] < positions[i] - 5
            int lo = 0, hi = i - 1, prev = -1;
            int target = positions[i] - 5; // Minimum position that a previous hospital can have (at least 5 units
            // apart)
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (positions[mid] < target) {
                    prev = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            int withCurrent;
            if (prev >= 0) {
                withCurrent = populations[i] + dp[prev];
            } else {
                withCurrent = populations[i];
            }

            int withoutCurrent = dp[i - 1];
            dp[i] = Math.max(withCurrent, withoutCurrent);
        }

        return dp[n - 1];
    }

    /**
     * Greedy by distance: pick a hospital if it is strictly more than 5 units
     * away from the previously chosen hospital. Returns the total population
     * served.
     */
    public static int calculateGreedyDistance(int[] positions, int[] populations) {
        int total = 0;

        int n = positions.length;
        int lastChosen = -1; // ensures first candidate can be chosen if appropriate

        for (int i = 0; i < n; i++) {
            // Select the hospital if it is far enough from the last chosen one
            if (positions[i] - lastChosen > 5) {
                total += populations[i]; // Add the number of people served by this hospital
                lastChosen = positions[i]; // Update the last chosen hospital position
            }
        }

        return total;
    }

    public static int calculateGreedyValue(int[] positions, int[] populations) {
        // Not implemented yet
        return 0;
    }

}
