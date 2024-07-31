import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int tCnt = Integer.parseInt(st2.nextToken());
        int pCnt = Integer.parseInt(st2.nextToken());

        int rslt1 = 0;
        String rslt2 = "";

        while (st.hasMoreTokens()){
            int thisCnt = Integer.parseInt(st.nextToken());
            if(thisCnt == 0) continue;
            if(thisCnt <= tCnt){
                rslt1++;
            } else if(thisCnt%tCnt == 0) {
                rslt1 += thisCnt/tCnt;
            } else {
                rslt1 += thisCnt/tCnt + 1;
            }
        }

        rslt2 = cnt/pCnt + " " +cnt%pCnt;
        System.out.println(rslt1);
        System.out.println(rslt2);

    }
}