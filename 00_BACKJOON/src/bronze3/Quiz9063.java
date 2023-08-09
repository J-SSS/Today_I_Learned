package bronze3;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz9063 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        int[] arrX = new int[cnt];
        int[] arrY = new int[cnt];
        for(int i = 0 ; i < cnt ; i++){
            arrX[i] = sc.nextInt();
            arrY[i] = sc.nextInt();
        }
        Arrays.sort(arrX); Arrays.sort(arrY);
        System.out.println(Math.abs((arrX[cnt-1]-arrX[0])*(arrY[cnt-1]-arrY[0])));
    }
}
