class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for(int i = 0 ; i < quiz.length ; i++){
            String[] str = quiz[i].split(" ");
            String oper = str[1];
            int X = Integer.parseInt(str[0]);
            int Y = Integer.parseInt(str[2]);
            int Z = Integer.parseInt(str[4]);
            String rslt = "";
            switch(oper) {
                case "+" :  {
                    rslt = X + Y == Z ? "O" : "X";
                } break;
                case "-" :  {
                    rslt = X - Y == Z ? "O" : "X";
                } break;
                default : 
            }
            answer[i] = rslt;
        }
        return answer;
    }
}