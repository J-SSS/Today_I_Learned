
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String,String> rsltMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            String num = Integer.toString(i+1);

            rsltMap.put(s,num);
            rsltMap.put(num,s);
        }
        for (int i = 0; i < M; i++) {
            sb.append(rsltMap.get(br.readLine())+'\n');
        }

        System.out.println(sb.toString());
    }
}