
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<Integer> rsltSet = new HashSet<>();

        int cntA = Integer.parseInt(st.nextToken());
        int cntB = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cntA; i++) {
            int thisInt = Integer.parseInt(st.nextToken());
            rsltSet.add(thisInt);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cntB; i++) {
            int thisInt = Integer.parseInt(st.nextToken());
            if(rsltSet.contains(thisInt)){
                rsltSet.remove(thisInt);
            } else {
                rsltSet.add(thisInt);
            }
        }

        bw.write(String.valueOf(rsltSet.size()));

        bw.flush();
        bw.close();
    }
}
