import java.io.*;
public class Main {
    static long[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        DP = new long[n];
        DP[0] = 1;
        if(n > 1) {  DP[1] = 2; }

        System.out.println(func(n-1));
    }

    public static long func(int i){
        if(i<0) return 0;

        if(DP[i] == 0){
            DP[i] = (func(i-1) + func(i-2)) % 10007;
        }

        return DP[i];
    }
}