package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Optimal {

    // Finds the largest index j < i such that positions[j] < positions[i] - 5
    // (hospitals cannot be closer than 5 km)
    private static int findPreviousCompatible(int[] positions, int i) {
        int targetStrict = positions[i] - 5;
        int lo = 0, hi = i - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (positions[mid] < targetStrict) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    // Returns the optimal total and which hospitals are built
    public static Result calculateOptimal(int[] positions, int[] populations) {
        int n = positions.length;
        if (n == 0) return new Result(0, new ArrayList<>());

        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = findPreviousCompatible(positions, i);
        }

        int[] dp = new int[n];
        dp[0] = populations[0];

        for (int i = 1; i < n; i++) {
            int withoutCurrent = dp[i - 1];
            int withCurrent = populations[i] + (prev[i] >= 0 ? dp[prev[i]] : 0);
            dp[i] = Math.max(withoutCurrent, withCurrent);
        }

        // Reconstruct solution
        List<Integer> chosen = new ArrayList<>();
        int i = n - 1;
        while (i >= 0) {
            if (i == 0) {
                if (dp[i] == populations[i]) chosen.add(i);
                break;
            }
            if (dp[i] == dp[i - 1]) {
                i--;
            } else {
                chosen.add(i);
                i = prev[i];
            }
        }

        Collections.reverse(chosen);
        return new Result(dp[n - 1], chosen);
    }

    // Inner class to return the complete result
    public static class Result {
        public final int total;
        public final List<Integer> indices;

        public Result(int total, List<Integer> indices) {
            this.total = total;
            this.indices = indices;
        }
    }
}
