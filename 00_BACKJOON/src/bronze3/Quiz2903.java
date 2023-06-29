package bronze3;

import java.util.Scanner;

public class Quiz2903 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cnt = sc.nextInt();
        double dot = Math.pow(2,cnt) + 1;
        int result = (int) Math.pow(dot,2);

        System.out.println(result);
    }
}
