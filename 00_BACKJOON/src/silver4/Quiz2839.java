package silver4;

import java.util.Scanner;

public class Quiz2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int kg5 = n/5;

        for(int i = 0; i<kg5+1 ; i++){
            int remain = n-(kg5-i)*5;
            if(remain%3==0){
                System.out.println(kg5-i + remain/3);
                break;
            }
            if(i==kg5 && remain%3!=0){
                System.out.println(-1);
                break;
            }
        }
    }
}
