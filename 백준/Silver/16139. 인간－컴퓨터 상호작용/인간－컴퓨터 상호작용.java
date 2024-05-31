import java.io.*;
import java.util.*;
public class Main {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

        arr = new int[str.length() + 1][26];

        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < 26; j++) {
                arr[i][j] = arr[i - 1][j];
            }
            arr[i][str.charAt(i - 1)-97] += 1;
        }

        int cnt = Integer.parseInt(br.readLine());
        while (cnt-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int thisChar = st.nextToken().charAt(0)-97;
            int sIdx = Integer.parseInt(st.nextToken());
            int eIdx = Integer.parseInt(st.nextToken());
            sb.append(arr[eIdx + 1][thisChar] - arr[sIdx][thisChar]).append("\n");
        }
        System.out.print(sb);

    }
}