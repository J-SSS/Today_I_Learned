
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer, List<Integer>> map = new HashMap<>();

        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());

           int x = Integer.parseInt(st.nextToken());
           int y = Integer.parseInt(st.nextToken());

           if(map.get(x) == null) {
               List<Integer> tempList = new ArrayList<>();
               tempList.add(y);
               map.put(x,tempList);
           } else {
               List<Integer> tempList = map.get(x);
               tempList.add(y);
               map.put(x,tempList);
           }
        }
       map.keySet().stream().sorted().forEach(e -> {

           map.get(e).sort(Comparator.naturalOrder());
           map.get(e).iterator().forEachRemaining(i -> {
               System.out.println(String.valueOf(e) + " " + String.valueOf(i));
           });
        });
    }
}