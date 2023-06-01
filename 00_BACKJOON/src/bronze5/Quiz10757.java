package bronze5;
/*
문제
두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 A와 B가 주어진다. (0 < A,B < 1010000)

출력
첫째 줄에 A+B를 출력한다.

 */
import java.math.BigInteger;
import java.util.Scanner;

public class Quiz10757 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigInteger a = sc.nextBigInteger();
        BigInteger b = sc.nextBigInteger();

        System.out.println(a.add(b));
    }
}
/*
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        for(int i = 0 ; i< babbling.length ; i++){
            for(int j = 0 ; babbling[i].leghth ; j++ ){
                babbling[i].charAt(j);
            }
        }
        return answer;
    }
}
 */
