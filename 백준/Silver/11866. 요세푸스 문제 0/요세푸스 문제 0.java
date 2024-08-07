import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("<");
        int range = Integer.parseInt(st.nextToken());
        int order = Integer.parseInt(st.nextToken());
        int seq = 1;
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= range; i++) {
            q.add(i);
        }

        while (!q.isEmpty()){
            if(seq != order){
                q.offer(q.poll());
                seq++;
            } else {
                if(sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(q.poll());
                seq = 1;
            }
        }
        sb.append(">");
        System.out.println(sb);

    }
}