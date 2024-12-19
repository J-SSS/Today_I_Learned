class Solution {
    public String solution(int[] food) {
        String left = "";
        String answer = "";
        for(int i = 1 ; i < food.length ; i++){
            if(food[i]/2 != 0){
                left += String.valueOf(i).repeat(food[i]/2);
            }
        }
        answer += left + "0";
        for(int i = left.length()-1 ; i >= 0 ; i--){
            answer += String.valueOf(left.charAt(i));
        }
        return answer;
    }
}