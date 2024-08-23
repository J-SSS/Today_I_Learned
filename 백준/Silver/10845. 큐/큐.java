import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cnt = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        int lastNum = 0;

        for (int i = 0; i < cnt; i++) {
            String o = br.readLine();

            if(o.indexOf("push") > -1){
                st = new StringTokenizer(o);
                st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                q.add(num);
                lastNum = num;
            } else if (o.equals("pop")) {
                if(q.isEmpty()){
                    sb.append("-1" + "\n");
                } else {
                    sb.append(q.poll() + "\n");
                }
            } else if (o.equals("size")) {
                sb.append(q.size() + "\n");
            } else if (o.equals("empty")) {
                if(q.isEmpty()){
                    sb.append("1" + "\n");
                } else {
                    sb.append("0" + "\n");
                }
            } else if (o.equals("front")) {
                if(q.isEmpty()){
                    sb.append("-1" + "\n");
                } else {
                    sb.append(q.peek() + "\n");
                }
            } else if (o.equals("back")) {
                if(q.isEmpty()){
                    sb.append("-1" + "\n");
                } else {
                    sb.append(lastNum + "\n");
                }
            }
        }
        
        System.out.println(sb);
        br.close();
    }
}