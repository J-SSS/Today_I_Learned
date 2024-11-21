import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rslt = 0;
        for (int i = 0; i < 5; i++) {
           rslt += Integer.parseInt(br.readLine());
        }

        System.out.println(rslt);
    }
}