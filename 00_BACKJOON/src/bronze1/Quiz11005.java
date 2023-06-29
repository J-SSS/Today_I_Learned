package bronze1;

import java.util.Scanner;

public class Quiz11005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int r = sc.nextInt();

        System.out.println(Integer.toString(num, r).toUpperCase());

    }
}
