package org.example;

import java.util.ArrayList;
import java.util.List;

public class Heuristic {
    // Method with custom name
    public static Result calculateGreedyDistance(int[] positions, int[] populations) {
        // implementation
        return new Result(0, new ArrayList<>());
    }

    public static Result calculateGreedyValue(int[] positions, int[] populations) {
        // implementation
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
