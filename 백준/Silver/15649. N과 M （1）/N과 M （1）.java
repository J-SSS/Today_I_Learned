
import java.io.*;
import java.util.*;
public class Main {
    static boolean[] visitFlag;
    static int[] output;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1~N까지
        int M = Integer.parseInt(st.nextToken()); // M개 선택(깊이)

        visitFlag = new boolean[N];
        output = new int[M];

        dfs(N,M,0);
    }

    static void dfs(int N, int M, int D){

        if(D == M){
            for (int i : output) {
                System.out.print(i+" ");
            }
            System.out.println("");
            return;
        }

        for(int i = 0 ; i < N; i++){
            if(visitFlag[i] == false){
                visitFlag[i] = true;
                output[D] = i+1;
                dfs(N,M,D+1);
                visitFlag[i] = false;
            }
        }
    }
}