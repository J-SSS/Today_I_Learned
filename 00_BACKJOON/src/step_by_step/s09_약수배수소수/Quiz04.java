package step_by_step.s09_약수배수소수;
/*
문제
주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

입력
첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

출력
주어진 수들 중 소수의 개수를 출력한다.

 */
import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        int count = 0;

        for(int i = 0 ; i<input ; i++){
            int startNum = 2;
            boolean isF = true;
            int subNum = sc.nextInt();
            if(subNum!=1){
                while (isF){
                    if(subNum%startNum==0){
                        isF = false;
                    } else {
                        startNum++;
                    }
                    if(subNum==startNum){
                        count++;
                        isF = false;
                    }
                }
            }
        }
        System.out.println(count);

    }
}
