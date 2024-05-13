import java.io.*;
import java.util.*;
public class Main {
    static boolean[] visitFlag;
    static int[] output;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 1~N까지
        int M = Integer.parseInt(st.nextToken()); // M개 선택(깊이)

        output = new int[M];

        dfs(N,M,0);
        System.out.println(sb);
    }

    static void dfs(int N, int M, int D){

        if(D == M){
            for (int i : output) {
                sb.append(i+" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = 0 ; i < N; i++){
            output[D] = i+1;
            dfs(N,M,D+1);
        }
    }
}