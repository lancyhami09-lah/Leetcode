class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            int reversePosition = 26 - (ch - 'a');

            ans += reversePosition * (i + 1);
        }

        return ans;
    }
}
