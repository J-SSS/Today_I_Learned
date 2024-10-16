import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long A = Long.parseLong(st.nextToken());
        Long B = Long.parseLong(st.nextToken());

        int rslt = 0;

        Queue<Long> q = new LinkedList<>();
        q.add(A);

        // 뒤에 1을 붙이거나, 2를 곱하거나
        outer :
        while (!q.isEmpty()){
            rslt++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Long thisNum = q.poll();
                Long caseA = thisNum * 2;
                Long caseB = thisNum * 10 + 1;

                if(caseA.equals(B) || caseB.equals(B)){
                    rslt++;
                    break outer;
                }

                if(caseA < B) q.add(caseA);
                if(caseB < B) q.add(caseB);
            }
            if(q.isEmpty()) rslt = -1;
        }

        System.out.println(rslt);
    }
}