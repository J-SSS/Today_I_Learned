package silver4;



/*
문제
어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다.
등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다. N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 1,000보다 작거나 같은 자연수 N이 주어진다.

출력
첫째 줄에 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력한다.

 */
import java.util.Scanner;
public class Quiz1065 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        int count = 0;

        for(int i = 1 ; i<=input; i++){
            int a = 0;
            int b = 0;
            int c = 0;
            int num = i;

            boolean isF = true;

            while (isF){
                if(num/1000!=0){
                    isF = false;
                }
                else if(num/100!=0 && num/100!=10){
                    a = num/100;
                    num -= (num/100)*100;
                } else if (num/10!=0){
                    b = num/10;
                    num -= (num/10)*10;
                } else {
                    c = num;
                    isF = false;
                }
            }

            if(a!=0 && (a-b == b-c)){
                count++;
            } else if (a==0 && (a+b+c)!=0){
                count++;
            }
        }
        System.out.println(count);
    }
}
