class Solution {
    public boolean isNumber(String s) {
        s = s.trim();

        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } 
            else if (c == '+' || c == '-') {
                // Sign is allowed only at the beginning
                // or immediately after e/E
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')
                    return false;
            } 
            else if (c == '.') {
                // Dot cannot appear after exponent
                // and only one dot is allowed
                if (seenDot || seenExponent)
                    return false;
                seenDot = true;
            } 
            else if (c == 'e' || c == 'E') {
                // Only one exponent and must have a digit before it
                if (seenExponent || !seenDigit)
                    return false;

                seenExponent = true;
                seenDigit = false; // Need at least one digit after e/E
            } 
            else {
                return false;
            }
        }

        return seenDigit;
    }
}
