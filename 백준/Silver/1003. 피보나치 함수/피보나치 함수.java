import java.util.*;
import java.io.*;
public class Main {
    static Map<Integer,int[]> DP = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        
        DP.put(0, new int[]{1, 0});
        DP.put(1, new int[]{0, 1});
        DP.put(2, new int[]{1, 1});
        DP.put(3, new int[]{1, 2});
        while (cnt-- > 0){
            int idx = Integer.parseInt(br.readLine());
            int[] rslt = func(idx);
            System.out.println(rslt[0] + " " + rslt[1]);
        }
    }

    public static int[] func(int idx){
        if(DP.get(idx) != null){
            return DP.get(idx);
        } else {
            DP.put(idx,new int[]{func(idx-1)[0]+func(idx-2)[0], func(idx-1)[1]+func(idx-2)[1]});
            return DP.get(idx);
        }
    }
}