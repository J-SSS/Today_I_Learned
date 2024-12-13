class Solution {
    public boolean solution(String s) {
        if(s.length() != 4 && s.length() != 6){
            return false;
        } else {
            for(char c : s.toCharArray()){
                if(c+0 > 57 || c+0 < 48) return false;
            }
            return true;
        }
    }
}