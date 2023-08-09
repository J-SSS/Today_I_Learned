package bronze3;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz1085 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt(),
            y = sc.nextInt(),
            w = sc.nextInt(),
            h = sc.nextInt();
        int[] arr = {x,y,w-x,h-y};
        Arrays.sort(arr);
        System.out.println(arr[0]);
    }
}
