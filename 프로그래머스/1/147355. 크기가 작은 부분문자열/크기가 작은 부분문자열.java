class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        Long pNum = Long.parseLong(p);
        for(int i = 0 ; i < t.length() - p.length() + 1 ; i++){
            long thisNum = Long.parseLong(t.substring(i,i+p.length()));
            if(thisNum <= pNum) answer++;
        }
        return answer;
    }
}