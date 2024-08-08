import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt =  Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
        int rslt = 0;

        if(cnt == 0){
            rslt = 0;
        } else if(cnt < 4) {
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i);
            }
            rslt = Math.round(sum/cnt);
        } else {
            list.sort(Comparator.naturalOrder());
            int overCnt = (int) Math.round(cnt*0.15);
            int remainCnt = cnt-overCnt*2;

            for (int i = 0; i < list.size(); i++) {
                if(i < overCnt || i >= cnt - overCnt){
                    continue;
                } else {
                    sum += list.get(i);
                }
            }
            rslt = (int) Math.round((double) sum/remainCnt);
        }

        System.out.println(rslt);
    }
}