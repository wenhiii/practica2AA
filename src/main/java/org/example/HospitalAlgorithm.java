package org.example;

public class HospitalAlgorithm {

    /**
     * Greedy by distance: pick a hospital if it is strictly more than 5 units
     * away from the previously chosen hospital. Returns the total population
     * served.
     *
     * @param xs positions of the hospitals
     * @param ps population served by each hospital
     * @return total population served by the selected hospitals
     */
    public static int calculateGreedyDistance(int[] xs, int[] ps) {
        int total = 0;

        int n = xs.length;
        int lastChosen = -1;

        for (int i = 0; i < n; i++) {
            if (xs[i] - lastChosen > 5) {
                total += ps[i];
                lastChosen = xs[i];
            }
        }

        return total;
    }

    /**
     * Heuristic selection by distance from max: Picks firstly the highest value
     * hospital,
     * and then picks hospital from the max value hospital.
     * Returns total population served.
     *
     * @param xs positions of the hospitals
     * @param ps population served by each hospital
     * @return total population served by the selected hospitals
     */
    public static int calculateGreedyFromMax(int[] xs, int[] ps) {
        int n = xs.length;

        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (ps[i] > ps[maxIndex]) {
                maxIndex = i;
            }
        }

        int total = ps[maxIndex];
        int lastChosen = xs[maxIndex];

        for (int i = maxIndex + 1; i < n; i++) {
            if (xs[i] - lastChosen > 5) {
                total += ps[i];
                lastChosen = xs[i];
            }
        }

        lastChosen = xs[maxIndex];
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (lastChosen - xs[i] > 5) {
                total += ps[i];
                lastChosen = xs[i];
            }
        }

        return total;
    }

    /**
     * Heuristic selection by population: Picks hospitals in order of highest
     * population, adding them if they are >5 units apart from already chosen
     * hospitals. Returns total population served.
     *
     * @param xs positions of the hospitals
     * @param ps population served by each hospital
     * @return total population served by the selected hospitals
     */
    public static int sortIndexByValueDescendent(int[] xs, int[] ps) {
        int[] sortedIndices = sortIndices(ps);
        boolean[] xsOccupied = new boolean[xs.length];
        int maxCapacity = ps[sortedIndices[0]];
        xsOccupied[sortedIndices[0]] = true;
        int index;

        for (int i = 0; i < xs.length; i++) {
            index = sortedIndices[i];
            if (!xsOccupied[index]) {
                int index2;
                boolean validCandidate = true;
                for (int j = 0; j < xs.length && validCandidate; j++) {
                    index2 = sortedIndices[j];
                    if (xsOccupied[index2]) {
                        if ((xs[index] - xs[index2] <= 5) && (xs[index2] - xs[index] <= 5)) {
                            validCandidate = false;
                        }
                    }
                }
                xsOccupied[index] = validCandidate;
                if (validCandidate) {
                    maxCapacity += ps[index];
                }
            }
        }

        return maxCapacity;
    }

    private static int[] sortIndices(int[] array) {
        int[] sortedIndices = new int[array.length];
        sortedIndices[0] = 0;
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[sortedIndices[j]] < value; j--) {
                sortedIndices[j + 1] = sortedIndices[j];
            }
            sortedIndices[j + 1] = i;
        }
        return sortedIndices;
    }

    /**
     * Optimal selection using backtracking: choose hospitals to maximize total
     * population
     * served, ensuring any two chosen hospitals are >5 units apart.
     *
     * @param xs positions of the hospitals
     * @param ps population served by each hospital
     * @return total population served by the selected hospitals
     */
    public static int optimalBacktrack(int[] xs, int[] ps) {
        int n = xs.length;
        int[] best = new int[1];
        backtrack(0, -1, 0, best, xs, ps);
        return best[0];
    }

    // backtrack(i, lastIncludedIndex, currentSum, best, xs, ps)
    private static void backtrack(int i, int lastIncludedIndex, int currentSum, int[] best, int[] xs, int[] ps) {
        int n = xs.length;

        if (i == n) {
            if (currentSum > best[0])
                best[0] = currentSum;
            return;
        }

        backtrack(i + 1, lastIncludedIndex, currentSum, best, xs, ps);

        if ((lastIncludedIndex == -1) || (xs[lastIncludedIndex] < xs[i] - 5)) {
            backtrack(i + 1, i, currentSum + ps[i], best, xs, ps);
        }
    }
}
