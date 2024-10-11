import java.util.*;
import java.io.*;
public class Main {
    static int rslt = 0;
    static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt =  Integer.parseInt(br.readLine());

        while (cnt-- > 0){
            rslt = 0;
            int num = Integer.parseInt(br.readLine());
            func(num);
            map.put(num,rslt);
            System.out.println(rslt);;
        }
        
        br.close();
    }
    public static void func(int num){
        if(map.get(num) != null){
            rslt += map.get(num);
        } else {
            if(num == 1){
                rslt ++;
            } else if (num == 2) {
                rslt ++;
                func(num-1);
            } else if (num == 3) {
                rslt ++;
                func(num-1);
                func(num-2);
            } else {
                func(num-1);
                func(num-2);
                func(num-3);
            }
        }
    }
}