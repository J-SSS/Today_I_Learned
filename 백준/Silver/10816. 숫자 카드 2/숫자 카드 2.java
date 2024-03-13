
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer,Integer> inputMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int thisInt = Integer.parseInt(st.nextToken());
            Integer thisVal = inputMap.get(thisInt);

            if(thisVal == null){
                inputMap.put(thisInt,1);
            } else {
                inputMap.put(thisInt,++thisVal);
            }
        }
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int thisInt = Integer.parseInt(st.nextToken());
            Integer thisVal = inputMap.get(thisInt);

            if(thisVal == null){
                bw.write("0");
            } else {
                bw.write(String.valueOf(thisVal));
            }

            if(i < M-1){
                bw.write(" ");
            }
        }

        bw.flush();
        bw.close();
    }
}