import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();

        if(cnt % 2 != 0) q.offer(cnt);

        for (int i = 2; i <= cnt; i+=2) {
            q.offer(i);
        }
        
        while (q.size() != 1){
            q.poll();
            q.offer(q.poll());
        }
        System.out.println(q.peek());

    }
}