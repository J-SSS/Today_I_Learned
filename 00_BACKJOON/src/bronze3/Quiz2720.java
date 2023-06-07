package bronze3;

import java.util.Scanner;

public class Quiz2720 {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int quarter = 25;
        int dime = 10;
        int nickel = 5;
        int penny = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int money = sc.nextInt();
            sb.append(money / quarter + " ");
            money %= quarter;
            sb.append(money / dime + " ");
            money %= dime;
            sb.append(money / nickel + " ");
            money %= nickel;
            sb.append(money / penny + "\n");
        }
        System.out.print(sb);
    }

}
