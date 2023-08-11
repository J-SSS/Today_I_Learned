
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger A = sc.nextBigInteger(), B = sc.nextBigInteger();
		System.out.println((A.add(B)) +"\n"+ (A.subtract(B)) +"\n"+ (A.multiply(B)));
	}
}