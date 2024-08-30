import java.util.*;
import java.io.*;
public class Main {
    public static int[] arr;
    public static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        func(1, 0);
    }

    static void func (int num, int seq) {
        if (seq == M) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }

        for (int i = num; i <= N; i++) {
            arr[seq] = i;
            func(i + 1, seq + 1);
        }
    }
}