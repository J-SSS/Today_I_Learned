import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int cnt = Integer.parseInt(br.readLine());
        Map<String,List<Integer>> charMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            String thisChar = String.valueOf(str.charAt(i));

            if(charMap.get(thisChar) == null){
                List<Integer> innerList = new ArrayList<>();
                innerList.add(i);
                charMap.put(thisChar,innerList);
            } else {
                charMap.get(thisChar).add(i);
            }
        }
        
        for (int i = 0; i < cnt; i++) {
            int thisCnt = 0;
            st = new StringTokenizer(br.readLine());

            String thisChar = st.nextToken();

            int sIdx = Integer.parseInt(st.nextToken());
            int eIdx = Integer.parseInt(st.nextToken());

            if(charMap.get(thisChar) != null){
                for (Integer thisIdx : charMap.get(thisChar)) {
                    if(thisIdx > eIdx) break;
                    else if(thisIdx >= sIdx && thisIdx<=eIdx) thisCnt++;
                }
            }

            System.out.println(thisCnt);
        }
    }
}