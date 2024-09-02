import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        String binaryStr = Integer.toBinaryString(num);

        int rslt = 0;
        for (int i = 0; i < binaryStr.length(); i++) {
            if(binaryStr.charAt(i) == '1') rslt ++;
        }

        System.out.println(rslt);
    }
}