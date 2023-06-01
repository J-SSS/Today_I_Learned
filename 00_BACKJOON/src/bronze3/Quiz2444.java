package bronze3;


import java.util.*;

public class Quiz2444 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();

        for(int i = 1; i <= cnt ; i++) {
            for(int j = 0; j < cnt-i; j++)
                System.out.print(" ");
            for(int j = 0; j < i*2-1; j++)
                System.out.print("*");
            System.out.println();
        }

        for(int i = cnt-1; i >= 0 ; i--) {
            for(int j = 0; j < cnt-i; j++)
                System.out.print(" ");
            for(int j = 0; j < i*2-1; j++)
                System.out.print("*");
            System.out.println();
        }
    }
}