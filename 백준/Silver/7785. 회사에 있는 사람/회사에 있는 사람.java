
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Set<String> entered = new HashSet<>();
        Set<String> leaved = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String type = st.nextToken();

            if(type.equals("enter")){
                entered.add(name);
            } else {
                entered.remove(name);
            }
        }
        entered.removeAll(leaved);

        for (String s :  entered.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
            bw.write(String.valueOf(s)+'\n');
        }

        bw.flush();
        bw.close();
    }
}