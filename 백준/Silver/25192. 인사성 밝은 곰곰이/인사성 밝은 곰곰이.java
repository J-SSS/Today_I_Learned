import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

        Set<String> logged = new HashSet<>();
        int rsltCnt = 0;

        for (int i = 0; i < cnt; i++) {
            String thisStr = br.readLine();
            if(thisStr.equals("ENTER")){
                logged = new HashSet<>();
            } else {
                if (!logged.contains(thisStr)){
                    rsltCnt++;
                    logged.add(thisStr);
                }
            }
        }
        System.out.println(rsltCnt);
    }
}