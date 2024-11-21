import java.util.*;
import java.io.*;
public class Main {
    static Queue<int[]> q = new LinkedList<>();
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int caseCnt = Integer.parseInt(br.readLine());

        while (caseCnt-- > 0){
            int rslt = 0;
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr = new int[w][h];

            while (c-- > 0){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
            }

            // bfs
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if(arr[i][j] == 1){ // 배추 발견
                        rslt++; // 1마리 증가
                        bfs(i,j,w,h);
                    }
                }
            }
            System.out.println(rslt);
        } // 입력 케이스 반복
        br.close();
    }

    public static void bfs(int x, int y, int w, int h){
        q.clear();
        q.add(new int[]{x,y});

        while (!q.isEmpty()){
            int[] ele = q.poll();
            int r = ele[0];
            int c = ele[1];
            if(r-1 >= 0 && arr[r-1][c] == 1){ // 상
                arr[r-1][c] = 0;
                q.add(new int[]{r-1,c});
            }
            if(r+1 < w && arr[r+1][c] == 1){ // 하
                arr[r+1][c] = 0;
                q.add(new int[]{r+1,c});
            }
            if(c-1 >= 0 && arr[r][c-1] == 1){ // 좌
                arr[r][c-1] = 0;
                q.add(new int[]{r,c-1});
            }
            if(c+1 < h && arr[r][c+1] == 1){ // 우
                arr[r][c+1] = 0;
                q.add(new int[]{r,c+1});
            }
        }
    }
}