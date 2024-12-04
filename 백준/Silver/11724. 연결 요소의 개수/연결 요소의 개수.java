import java.util.*;
import java.io.*;
public class Main {
    static int[] visit;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int rslt = 0;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visit = new int[N+1];
        graph = new int[N+1][N+1];

        while (M-- > 0){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A][B] = 1;
            graph[B][A] = 1;
        }
        for (int i = 1; i < visit.length; i++) {
            if(visit[i] == 0){
                rslt++;
                bfs(i, N);
            }
        }

        System.out.println(rslt);
    }

    static void bfs(int i, int N){
        visit[i] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        while (!q.isEmpty()){
            int r = q.poll();
            for (int j = 1; j < N+1; j++) {
                if(graph[r][j] == 1){
                    q.add(j);
                    graph[r][j] = 0;
                    visit[j] = 1;
                }
            }
        }
    }
}