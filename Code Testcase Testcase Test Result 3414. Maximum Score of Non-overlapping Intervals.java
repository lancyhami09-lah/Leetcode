import java.util.*;

class Solution {
    static class Interval {
        long start, end, weight;
        int index;

        Interval(long start, long end, long weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> picked;

        State(long score, List<Integer> picked) {
            this.score = score;
            this.picked = picked;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, Comparator.comparingLong(a -> a.start));

        long[] starts = new long[n];
        for (int i = 0; i < n; i++) {
            starts[i] = arr[i].start;
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i].end);
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {
                State best = dp[i + 1][k];

                State previous = dp[next[i]][k - 1];
                List<Integer> chosen = new ArrayList<>(previous.picked);
                chosen.add(arr[i].index);
                Collections.sort(chosen);

                State take = new State(previous.score + arr[i].weight, chosen);

                if (isBetter(take, best)) {
                    best = take;
                }

                dp[i][k] = best;
            }
        }

        List<Integer> answer = dp[0][4].picked;
        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int upperBound(long[] starts, long end) {
        int left = 0, right = starts.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (starts[mid] <= end) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    private boolean isBetter(State a, State b) {
        if (a.score != b.score) return a.score > b.score;

        int length = Math.min(a.picked.size(), b.picked.size());

        for (int i = 0; i < length; i++) {
            if (!a.picked.get(i).equals(b.picked.get(i))) {
                return a.picked.get(i) < b.picked.get(i);
            }
        }

        return a.picked.size() < b.picked.size();
    }
}
