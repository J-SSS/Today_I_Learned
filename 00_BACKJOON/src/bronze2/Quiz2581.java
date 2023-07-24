package bronze2;

import java.util.Scanner;

public class Quiz2581 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int M = scanner.nextInt();
            int N = scanner.nextInt();
            int sum=0;
            int min=M;

            for(int i=N; i>=M; i--) {
                if(prime(i)==true) {
                    min=i;
                    sum += i;
                }
            }
            if(sum==0) {
                System.out.println(-1);
            }
            else {
                System.out.println(sum);
                System.out.println(min);}
        }
        static boolean prime(int i) {
            if(i<2) {
                return false;
            }
            for(int j=2; j<i; j++) {
                if(i%j==0) {
                    return false;
                }
            }
            return true;
        }
}

