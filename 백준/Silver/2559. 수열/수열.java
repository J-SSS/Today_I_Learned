
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int sum = 0;
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int thisNum = Integer.parseInt(st.nextToken());
            arr[i] = thisNum;
            if(i < K){
                max+= thisNum;
                sum+= thisNum;
            }
        }

        for (int i = 0; i < N-K; i++) {
            int thisSum = sum - arr[i] + arr[i+K];
            sum = thisSum;
            if(thisSum >= max) max = thisSum;
        }

        System.out.println(max);
        
    }
}