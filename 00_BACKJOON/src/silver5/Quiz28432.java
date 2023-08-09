package silver5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz28432 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sentenceN = sc.nextInt();
        List<String> senList = new ArrayList<>();
        char front, back;
        int qIndex = 0;
        for(int i = 0 ; i < sentenceN ; i++){
            String str = sc.next();
            if(str.equals("?")) qIndex = i;
            senList.add(str);
        }

        front = qIndex == 0 ? '*' : senList.get(qIndex - 1).charAt(senList.get(qIndex - 1).length()-1);
        back = qIndex == sentenceN-1 ? '*' : senList.get(qIndex + 1).charAt(0);

        int candidateN = sc.nextInt();
        for(int i = 0 ; i < candidateN ; i++){
            String str = sc.next();
            if((str.charAt(0)==front || front == '*')
                && ((str.charAt(str.length()-1) == back || back == '*'))
                && senList.indexOf(str)==-1) System.out.println(str);
        }
    }
}
