
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int inputCnt = Integer.parseInt(br.readLine());
        Map<Integer, List<String>> inputMap = new HashMap<>();
        List<Integer> ageList = new ArrayList<>();

        for (int i = 0; i < inputCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            ageList.add(age);
            if (inputMap.get(age) == null) {
                List<String> thisList = new ArrayList<>();
                thisList.add(st.nextToken());
                inputMap.put(age, thisList);
            } else {
                List<String> thisList = inputMap.get(age);
                thisList.add(st.nextToken());
                inputMap.put(age, thisList);
            }
        }

        List<Integer> sortedAgeList = ageList.stream().distinct().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        for (Integer integer : sortedAgeList) {
            inputMap.get(integer).forEach(e -> {
                sb.append(integer + " " + e + "\n");
            });
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }
}