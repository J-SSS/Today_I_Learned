class Solution {
    public long solution(int a, int b) {
        long answer = (a + b)*((long)Math.abs(b-a)+1)/2;
        return answer;
    }
}