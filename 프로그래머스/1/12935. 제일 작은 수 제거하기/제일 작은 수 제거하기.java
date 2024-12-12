import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        if(arr.length == 1){
            answer = new int[]{-1};
        } else {
            int min = Integer.MAX_VALUE;
            for(int i = 0 ; i < arr.length ; i++){
                if(arr[i] < min) min = arr[i];
                list.add(arr[i]);
            }
            final int min2 = min;
            answer = list.stream().filter(e -> {return e != min2;}).mapToInt(Integer::intValue).toArray();
        }
        
        return answer;
    }
}