import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inCnt = Integer.parseInt(st.nextToken());
        int outCnt = Integer.parseInt(st.nextToken());

        Map<String,String> map = new HashMap<>();

        for (int i = 0; i < inCnt; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(),st.nextToken());
        }

        for (int i = 0; i < outCnt; i++) {
            bw.write(map.get(br.readLine()));
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}