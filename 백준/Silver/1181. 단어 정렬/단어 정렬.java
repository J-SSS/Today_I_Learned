
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer, List<Integer>> map = new HashMap<>();

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();

        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            String thisStr = st.nextToken();
            set.add(thisStr);
            list.add(thisStr);
        }

        List<String> result = list.stream().distinct().sorted(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if(str1.length() != str2.length()){
                    return str1.length() - str2.length();
                } else {
                    return str1.compareTo(str2);
                }
            }
        }).collect(Collectors.toList());

        result.stream().forEach(System.out::println);

    }
}