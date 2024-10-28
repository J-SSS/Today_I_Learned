import java.util.*;
import java.io.*;
public class Main {
    static boolean[] isVisited;
    static Map<Integer,String> netMap = new HashMap<>();
    static int rslt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cCnt = Integer.parseInt(br.readLine());
        int pCnt = Integer.parseInt(br.readLine());

        isVisited = new boolean[cCnt+1];

        if(pCnt != 0){
            // 초기값
            while (pCnt-- > 0){
                st = new StringTokenizer(br.readLine());
                int numA = Integer.parseInt(st.nextToken());
                int numB = Integer.parseInt(st.nextToken());

                if(netMap.get(numA) == null) {
                    netMap.put(numA, String.valueOf(numB));
                } else if(netMap.get(numA) != null) {
                    netMap.put(numA, netMap.get(numA).concat("|" + String.valueOf(numB)));
                }

                if(netMap.get(numB) == null) {
                    netMap.put(numB, String.valueOf(numA));
                } else if(netMap.get(numB) != null) {
                    netMap.put(numB, netMap.get(numB).concat("|" + String.valueOf(numA)));
                }
            } // ~~ while

            String orgin = netMap.get(1);
            if(orgin != null && orgin.length() > 0){
                isVisited[1] = true; // 1번 방문
                bfs(netMap.get(1));

            }
        }
        
        System.out.println(rslt);
    }


    static void bfs(String str){
        String[] strArr = str.split("\\|");

        if(strArr.length > 0){
            for (String s : strArr) {
                int thisNum = Integer.parseInt(s);
                if(!isVisited[thisNum]){
                    isVisited[thisNum] = true;
                    rslt++;
                    bfs(netMap.get(thisNum));
                }
            }
        }
    }
}