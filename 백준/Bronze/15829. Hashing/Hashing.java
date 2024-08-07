import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String input = br.readLine();
        long rslt = 0;

        for (int i = 0; i < length; i++) {
            int num = input.charAt(i) - 96;
            rslt += num * (int) Math.pow(31,i);
        }

        System.out.println(rslt);

    }
}