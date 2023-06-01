package bronze3;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz10810 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[sc.nextInt()];
        int cnt = sc.nextInt();
        String out = "";

        for(int i = 0 ; i < cnt ; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            int num = sc.nextInt();
            for(int j = start-1 ; j < end ; j++){
                arr[j] = num;
            }
        }

        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));

    }
}

