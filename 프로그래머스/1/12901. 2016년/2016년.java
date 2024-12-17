class Solution {
    public String solution(int a, int b) {
        int[] cal = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        int day = 0;
        for(int i = 0 ; i < a-1 ; i++){
            day += cal[i];
        }
        day += b;
        String[] dow = new String[]{"SUN","MON","TUE","WED","THU","FRI","SAT"};
        return dow[day%7-3 < 0 ? day%7+4 : day%7-3];
    }
}