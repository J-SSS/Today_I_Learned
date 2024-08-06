import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        List<Integer[]> list = new ArrayList<>();

        int cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            Integer[] spec = {w,h};
            list.add(new Integer[]{w,h});
        }

        for (int i = 0; i < list.size(); i++) {
            int myW = list.get(i)[0];
            int myH = list.get(i)[1];
            int rank = 1;

            for (int j = 0; j < list.size(); j++) {
                if(i == j) continue;
                int yourW = list.get(j)[0];
                int yourH = list.get(j)[1];

                if(myW < yourW && myH < yourH) rank++;
            }

            sb.append(rank + " ");
        }

        System.out.println(sb);
    }
}