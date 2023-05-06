package step_by_step.s08_수학2;
/*
문제
주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

입력
첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

출력
주어진 수들 중 소수의 개수를 출력한다.

 */
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class test {

    static class Solution {
        public int solution(String A, String B) {
            int answer = 0;
            int count = 0;
            boolean isF = true;
            if(A.equals(B)){
                answer = 0;
            } else {
                while(isF){
//                    char sub = ' ';
//                    sub = A.charAt(0);
//
                    char a = A.charAt(A.length()-1);
                    char b = A.charAt(0);

                    A.replace(a,b);
//                    A.replace(sub, A.charAt(A.length()-1));
                    count++;
                    System.out.println(A);

                    if(A.equals(B)){
                        answer=count;
                        isF=false;
                    } else if(count>=A.length()){
                        answer=-1;
                        isF=false;
                    }
                }
            }
            return answer;
        }
    }


    public static void main(String[] args) {
       Solution ss = new Solution();
        String s = "123";
        StringTokenizer st = new StringTokenizer(s);

        long sum = 0;
        for(int i = 1 ; i<2500 ; i++){
            sum += 2500*i;
        }
        System.out.println(sum);

        char a ='1';
        System.out.println((int)a);



    }
}
