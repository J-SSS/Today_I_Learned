import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++) {

            Queue<Map<String,Integer>> seqQ = new LinkedList<>();
            int[] priority = new int[10];
            boolean loop = true;
            int rslt = 0;
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int trgtSeq = Integer.parseInt(st.nextToken());
            int trgtPri = 0;
            
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int num = Integer.parseInt(st.nextToken());

                Map<String,Integer> thisMap = new HashMap<>();
                thisMap.put("p",num);
                if(j == trgtSeq){
                    trgtPri = num;
                    thisMap.put("t",1);
                } else {
                    thisMap.put("t",0);
                }

                priority[num] = priority[num] + 1;
                seqQ.add(thisMap);
            }

            while (loop){
                Map<String,Integer> thisMap = seqQ.peek();
                int thisP = thisMap.get("p");
                int thisT = thisMap.get("t");

                for (int j = 9; j >= thisP; j--) {
                    int pCnt = priority[j];

                    if(pCnt != 0 && j > thisP){
                        seqQ.add(seqQ.poll());
                        break;
                    } else if(pCnt != 0 && j == thisP ){
                        rslt ++;
                        priority[j] = priority[j] - 1;
                        seqQ.remove();
                        if (thisT == 1) {
                            System.out.println(rslt);
                            loop = false;
                        }
                        break;
                    }
                }
            }
        }

        br.close();
    }
}