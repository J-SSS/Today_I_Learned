package bronze5;



/*
문제
0보다 크거나 같은 정수 N이 주어진다. 이때, N!을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N(0 ≤ N ≤ 12)이 주어진다.

출력
첫째 줄에 N!을 출력한다.
 */
import java.util.Scanner;

public class Quiz10872 {
    static int result = 1;
    static int function(int e) {
        if (e > 1) {
            function(e-1);
        }
        if (e>0){
            result = result*e;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(function(sc.nextInt()));
    }
}
