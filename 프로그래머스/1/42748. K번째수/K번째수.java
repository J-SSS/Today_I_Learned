import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i = 0 ; i < commands.length ; i++){
            int a = commands[i][0];
            int b = commands[i][1];
            int c = commands[i][2];
            answer[i] = Arrays.stream(Arrays.copyOfRange(array,a-1,b)).sorted().toArray()[c-1];
        }
        
        return answer;
    }
}