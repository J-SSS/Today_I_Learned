package bronze3;
/*
문제
4 × 3 = 12이다.

이 식을 통해 다음과 같은 사실을 알 수 있다.

3은 12의 약수이고, 12는 3의 배수이다.

4도 12의 약수이고, 12는 4의 배수이다.

두 수가 주어졌을 때, 다음 3가지 중 어떤 관계인지 구하는 프로그램을 작성하시오.

첫 번째 숫자가 두 번째 숫자의 약수이다.
첫 번째 숫자가 두 번째 숫자의 배수이다.
첫 번째 숫자가 두 번째 숫자의 약수와 배수 모두 아니다.
 */
import java.util.Scanner;

public class Quiz5086 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();

            if(num1 != 0 && num2 != 0){
                if(num2%num1 == 0) {
                    System.out.println("factor");
                } else if(num1%num2 == 0){
                    System.out.println("multiple");
                } else {
                    System.out.println("neither");
                }
            } else {
                break;
            }
        }

    }
}
