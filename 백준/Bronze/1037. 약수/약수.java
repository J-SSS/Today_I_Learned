
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Long> list = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        list.sort(Comparator.naturalOrder());

        System.out.println(list.get(0) * list.get(cnt-1));
    }
}