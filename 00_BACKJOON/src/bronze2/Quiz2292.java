package bronze2;

import java.util.Scanner;

public class Quiz2292 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int findN = sc.nextInt();
        int sumN = 1;
        int cnt = 1;
        while (findN != 1){
            sumN += 6*cnt;
            if(findN <= sumN) {cnt++; break;}
            else cnt++;
        }
        System.out.println(cnt);
    }
}
