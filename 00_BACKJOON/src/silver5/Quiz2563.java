package silver5;

import java.util.Scanner;

public class Quiz2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        int cnt = sc.nextInt();
        boolean[][] arr = new boolean[101][101];

        for (int i = 0; i < cnt; i++) {

            int offX = sc.nextInt();
            int offY = sc.nextInt();

            for (int j = offX; j < offX+10; j++) {
                for (int k = offY; k < offY+10; k++) {
                    if (!arr[j][k]) {
                        arr[j][k] = true;
                        result++;
                    }
                }
            }
        }
        System.out.print(result);
    }
}
