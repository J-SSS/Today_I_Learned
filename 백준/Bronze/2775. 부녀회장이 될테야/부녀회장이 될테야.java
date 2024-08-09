import java.io.*;
public class Main {
    static int[][] ARR;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        int K, N = 0;
        for (int i = 0; i < cnt; i++) {
            K = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            ARR = new int[K + 1][N + 1];

            // 탐색 횟수가 여러 번 인 경우, 이 전의 탐색 결과를 재활용
            for (int j = 1; j <= K; j++) {
                int sum = ARR[j - 1][0];
                for (int k = 0; k <= N; k++) {
                    sum += ARR[j - 1][k];
                    ARR[j][k] = sum;
                }
            }
            
            search(K, N);
            System.out.println(ARR[K][N]);
        }
    }

    private static int search(int k, int n) {
        if (k == 0) {
            return n;
        }

        if (ARR[k][n] != 0) {
            return ARR[k][n];
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += search(k - 1, i);
        }

        return ARR[k][n] = sum;
    }
}