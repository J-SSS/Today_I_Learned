import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int rslt = 0;
        for (int i = 0; i < len; i++) {
            if(str.charAt(i) == '1') rslt ++;
        }

        System.out.println(rslt);
    }
}