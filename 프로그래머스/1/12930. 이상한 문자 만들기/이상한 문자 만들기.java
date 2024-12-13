
class Solution {
    public String solution(String s) {
        String answer = "";
        boolean isUpperCase = true;
        for(char c : s.toCharArray()){
            if(c == ' ') {
                answer += " ";
                isUpperCase = true; 
                continue;
            }
            if(isUpperCase) answer += Character.toUpperCase(c);
            if(!isUpperCase) answer += Character.toLowerCase(c);
            isUpperCase = !isUpperCase;
        }
        return answer;
    }
}