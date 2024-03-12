
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> unListened = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            unListened.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            String nm = br.readLine();
            if(unListened.contains(nm)){
                result.add(nm);
            }
        }

        result.sort(Comparator.naturalOrder());
        
        bw.write(String.valueOf(result.size())+'\n');
        for (String s : result) {
            bw.write(String.valueOf(s)+'\n');
        }

        bw.flush();
        bw.close();
    }
}