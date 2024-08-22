import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();
        String rslt = "true";

        // 수열 넣기
        for (int i = 0; i < cnt; i++) {
            q.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 1; i <= cnt; i++) {
            int trgtN = q.peek();

            while (!s.isEmpty() && s.peek() == trgtN){
                sb.append("-" + "\n");
                s.pop();
                q.remove();
                trgtN = q.peek();
            }

            if(trgtN == i){
                sb.append("+" + "\n");
                sb.append("-" + "\n");
                q.remove();
                continue;
            } else {
                s.push(i);
                sb.append("+" + "\n");
            }
        }

        int size = s.size();
        for (int i = 0; i < size; i++) {
            if(q.peek().equals(s.peek())){
                sb.append("-" + "\n");
                q.remove();
                s.pop();
            } else {
                rslt = "NO";
            }
        }

        if(rslt.equals("NO") || !q.isEmpty() || !s.isEmpty()){
            System.out.println("NO");
        } else {
            System.out.println(sb);
        }

        br.close();
    }
}