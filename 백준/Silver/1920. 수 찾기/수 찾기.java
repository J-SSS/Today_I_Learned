
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Long> inputList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inputList.add(Long.parseLong(st.nextToken()));
        }

        inputList.sort(Comparator.naturalOrder());

        Long[] arr = inputList.toArray(new Long[N]);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int rslt = Arrays.binarySearch(arr, Long.parseLong(st.nextToken()));
            if(rslt >-1){
                sb.append("1"+'\n');
            } else {
                sb.append("0"+'\n');
            }
        }

        System.out.println(sb);
    }
}