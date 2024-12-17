class Solution {
    public int[] solution(String s) {
        int[] alpha = new int[27];
        int[] answer = new int[s.length()];
        int idx = 0;
        for(char c : s.toCharArray()){
            if(alpha[c-97] == 0) {
                answer[idx] = -1;
                alpha[c-97] = idx+1;
            } else {
                answer[idx] = idx - alpha[c-97]+1;
                alpha[c-97] = idx+1;
            }
            idx++;
        }
        
        
        return answer;
    }
}