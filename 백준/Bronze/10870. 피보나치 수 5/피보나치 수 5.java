
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int seq = Integer.parseInt(br.readLine());
        
        List<Integer> rsltList = new ArrayList<>();
        rsltList.add(0);
        rsltList.add(1);
        
        for (int i = 0; i <= seq; i++) {
            if(rsltList.size() < i+1){
                rsltList.add(rsltList.get(i-1) + rsltList.get(i-2));
            }
        }

        System.out.println(rsltList.get(seq));
    }
}