class Solution {
    public String defangIPaddr(String address) {

        StringBuilder ans = new StringBuilder();

        for (char ch : address.toCharArray()) {

            if (ch == '.') {
                ans.append("[.]");
            } else {
                ans.append(ch);
            }
        }

        return ans.toString();
    }
}
