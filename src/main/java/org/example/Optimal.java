package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Optimal {

    // Encuentra el índice j < i más grande con xs[j] < xs[i] - 5 (no se pueden acercar más de 5 km)
    private static int findPreviousCompatible(int[] xs, int i) {
        int targetStrict = xs[i] - 5;
        int lo = 0, hi = i - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (xs[mid] < targetStrict) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    // Devuelve el total óptimo y qué hospitales se construyen
    public static Result calcularOptimo(int[] xs, int[] ps) {
        int n = xs.length;
        if (n == 0) return new Result(0, new ArrayList<>());

        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = findPreviousCompatible(xs, i);
        }

        int[] dp = new int[n];
        dp[0] = ps[0];

        for (int i = 1; i < n; i++) {
            int sin = dp[i - 1];
            int con = ps[i] + (prev[i] >= 0 ? dp[prev[i]] : 0);
            dp[i] = Math.max(sin, con);
        }

        // Reconstruir solución
        List<Integer> elegidos = new ArrayList<>();
        int i = n - 1;
        while (i >= 0) {
            if (i == 0) {
                if (dp[i] == ps[i]) elegidos.add(i);
                break;
            }
            if (dp[i] == dp[i - 1]) {
                i--;
            } else {
                elegidos.add(i);
                i = prev[i];
            }
        }

        Collections.reverse(elegidos);
        return new Result(dp[n - 1], elegidos);
    }

    // Clase interna para devolver resultado completo
    public static class Result {
        public final int total;
        public final List<Integer> indices;

        public Result(int total, List<Integer> indices) {
            this.total = total;
            this.indices = indices;
        }
    }
}
