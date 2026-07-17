class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] cntG = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long divisible = 0;

            for (int m = g; m <= max; m += g) {
                divisible += freq[m];
                cntG[g] -= cntG[m];
            }

            cntG[g] += divisible * (divisible - 1) / 2;
        }

        for (int i = 2; i <= max; i++) {
            cntG[i] += cntG[i - 1];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = 1, r = max;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (cntG[mid] > queries[i])
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}
