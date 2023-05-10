package step_by_step.s01_입출력사칙연산;

import java.util.Scanner;

/*
문제
꼬마 정민이는 이제 A + B 정도는 쉽게 계산할 수 있다. 이제 A + B + C를 계산할 차례이다!
 */
public class Quiz11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Double num1 = sc.nextDouble();
        Double num2 = sc.nextDouble();
        Double num3 = sc.nextDouble();


        System.out.println((long)(num1 + num2 + num3));
    }
}
