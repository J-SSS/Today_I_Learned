package bronze4;

import java.util.Scanner;

public class Quiz10101 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] num = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        if(num[0] == 60 & num[1] == 60 & num[2] == 60) System.out.println("Equilateral");
        else if (num[0] + num[1] + num[2] == 180){
            if(num[0] == num[2] || num[1] == num[2] || num[0] == num[2]) System.out.println("Isosceles");
            else System.out.println("Scalene");
        } else System.out.println("Error");
    }
}
