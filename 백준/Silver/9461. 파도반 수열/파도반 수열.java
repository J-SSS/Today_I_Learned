import java.io.*;
public class Main {
    static long[] DP = new long[100];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        DP[0] = 1;
        DP[1] = 1;
        DP[2] = 1;
        DP[3] = 2;
        for (int i = 0; i < 100; i++) {
            if(DP[i] == 0){
                DP[i] = DP[i-2] + DP[i-3];
            }
        }

        while (cnt-- > 0){
            int seq = Integer.parseInt(br.readLine());
            System.out.println(DP[seq-1]);
        }
    }
}