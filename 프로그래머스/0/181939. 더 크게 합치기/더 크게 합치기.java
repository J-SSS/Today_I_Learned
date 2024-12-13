class Solution {
    public int solution(int a, int b) {
        return Math.max(
                Integer.parseInt(String.valueOf(a)+String.valueOf(b)),
                Integer.parseInt(String.valueOf(b)+String.valueOf(a))
                    );
    }
}