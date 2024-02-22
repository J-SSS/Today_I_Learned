
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int count = 0;
        Map<String, Integer> inputMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            inputMap.put(br.readLine(), 0);
        }
        for (int i = 0; i < M; i++) {
            if (inputMap.containsKey(br.readLine())) count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}