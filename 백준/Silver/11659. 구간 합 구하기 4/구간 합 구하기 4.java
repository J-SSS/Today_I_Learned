
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        int seq = 0;
        while (st.hasMoreTokens()){
            int thisNum = Integer.parseInt(st.nextToken());
            if(seq == 0){
                list.add(thisNum);
            } else {
                list.add(list.get(seq-1)+thisNum);
            }
            seq++;
        }
        
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int rslt;
            if(a == 1){
                rslt = list.get(b-1);
            } else {
                rslt = list.get(b-1) - list.get(a-2);
            }
            System.out.println(rslt);
        }
    }
}