import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger b = new BigInteger(br.readLine());
        BigInteger num = new BigInteger("3");

        BigInteger rslt = new BigInteger("0");
        while (b.getLowestSetBit() != -1){
            rslt = rslt.add(num.pow(b.getLowestSetBit()));
            b = b.clearBit(b.getLowestSetBit());
        }

        System.out.println(rslt);
    }
}