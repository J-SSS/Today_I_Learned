package bronze5;

import java.math.BigInteger;
import java.util.Scanner;

public class Quiz1271 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger n = sc.nextBigInteger(), m = sc.nextBigInteger();
        System.out.println(n.divide(m) + "\n" + n.remainder(m));
    }
}
