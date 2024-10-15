import java.util.*;
import java.io.*;
public class Main {
    static int[][] COST;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cnt = Integer.parseInt(br.readLine());

        COST = new int[cnt][3];
        DP = new int[cnt][3];

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            COST[i][0] = Integer.parseInt(st.nextToken()); // R
            COST[i][1] = Integer.parseInt(st.nextToken()); // G
            COST[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        DP[0][0] = COST[0][0];
        DP[0][1] = COST[0][1];
        DP[0][2] = COST[0][2];

        System.out.println(Math.min(lowCost(cnt-1,0), Math.min(lowCost(cnt-1,1),lowCost(cnt-1,2))));
        br.close();
    }

    public static int lowCost (int seq, int color){

        if(DP[seq][color] == 0){
            if (color == 0){ // R
                   DP[seq][color] = Math.min(lowCost(seq-1,1),lowCost(seq-1,2)) + COST[seq][color];
            } else if (color == 1) { // G                       
                   DP[seq][color] = Math.min(lowCost(seq-1,0),lowCost(seq-1,2)) + COST[seq][color];
            } else if (color == 2) { // B
                   DP[seq][color] = Math.min(lowCost(seq-1,0),lowCost(seq-1,1)) + COST[seq][color];
            }
        }
        return DP[seq][color];
    }
}