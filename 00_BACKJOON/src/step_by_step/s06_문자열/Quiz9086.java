package step_by_step.s06_문자열;

import java.util.Scanner;

public class Quiz9086 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        for(int i = 0 ; i<cnt ; i++){
            String str = sc.next();
            System.out.println(str.charAt(0)+""+str.charAt(str.length()-1));
        }

    }
}
