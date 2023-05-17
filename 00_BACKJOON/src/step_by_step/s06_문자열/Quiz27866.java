package step_by_step.s06_문자열;

import java.util.Scanner;

public class Quiz27866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(str.charAt(sc.nextInt()-1));
    }
}
