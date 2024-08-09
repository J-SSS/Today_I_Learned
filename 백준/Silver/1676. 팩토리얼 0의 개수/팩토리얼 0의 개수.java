import java.math.*;
import java.io.*;
public class Main {

    static BigInteger ten = BigInteger.valueOf(10);
    static BigInteger zero = BigInteger.ZERO;
    static BigInteger one =  BigInteger.ONE;
    static int rslt = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        BigInteger factorialNum = factorial(num, one);

        System.out.println(findZero(factorialNum));
    }

    private static int findZero(BigInteger num){
        if(num.compareTo(ten) == -1) {
            return rslt;
        }

        if(num.remainder(ten) == zero) {
            rslt++;
            return findZero(num.divide(ten));
        } else {
            return rslt;
        }
    }


    private static BigInteger factorial(int n, BigInteger sum) {
        if (n <= 1) {
            return sum;
        } else {
            sum = sum.multiply(BigInteger.valueOf(n));
            return factorial(n-1, sum);
        }
    }
}