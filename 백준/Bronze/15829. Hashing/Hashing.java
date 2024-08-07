import java.math.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String input = br.readLine();
        BigInteger rslt = new BigInteger("0");

        for (int i = 0; i < length; i++) {
            int num = input.charAt(i) - 96;
            rslt = rslt.add(BigInteger.valueOf(num).multiply(BigInteger.valueOf(31).pow(i)));
        }

        rslt = rslt.remainder(BigInteger.valueOf(1234567891));

        System.out.println(rslt);
    }
}