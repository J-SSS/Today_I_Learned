package bronze3;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz14215 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numArr = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        Arrays.sort(numArr);

        if(numArr[0] == numArr[1] && numArr[1] == numArr[2] && numArr[0] == numArr[2] ) System.out.println(numArr[0]*3);
        else if (numArr[0]+numArr[1]>numArr[2]) System.out.println(numArr[0]+numArr[1]+numArr[2]);
        else System.out.println(numArr[0]+numArr[1]+(numArr[0]+numArr[1]-1));
    }
}
