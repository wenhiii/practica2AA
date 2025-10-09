package org.example;

import java.util.ArrayList;
import java.util.List;

public class Heuristic {
    // Método con nombre propio
    public static Result calcularGreedyDistance(int[] xs, int[] ps) {
        // implementación
        return new Result(0, new ArrayList<>());
    }

    public static Result calcularGreedyValue(int[] xs, int[] ps) {
        // implementación
        return new Result(0, new ArrayList<>());
    }

    public static class Result {
        public final int total;
        public final List<Integer> indices;

        public Result(int total, List<Integer> indices) {
            this.total = total;
            this.indices = indices;
        }
    }
}
