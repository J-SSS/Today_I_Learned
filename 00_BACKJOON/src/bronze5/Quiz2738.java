package bronze5;

import java.util.Scanner;

public class Quiz2738 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numR = sc.nextInt();
        int numC = sc.nextInt();
        int[][] arr = new int[numR][numC];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numR; j++) {
                for (int k = 0; k < numC; k++) {
                    arr[j][k] += sc.nextInt();
                }
            }
        }

        for (int i = 0; i < numR; i++) {
            for (int j = 0; j < numC; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
