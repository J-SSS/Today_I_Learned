import java.util.*;
import java.io.*;
public class Main {
    static Map<Integer,Integer> inputMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        inputStr = inputStr.replaceAll("-",",-").replaceAll("\\+",",+");
        StringTokenizer st = new StringTokenizer(inputStr,",");

        int sum = 0;
        boolean isMinus = false;

        while (st.hasMoreTokens()){
            int thisNum = Integer.parseInt(st.nextToken());
            if(thisNum < 0) isMinus = true;
            if(!isMinus && thisNum > 0){
                sum += thisNum;
            } else {
                sum -= Math.abs(thisNum);
            }
        }
        System.out.println(sum);
    }
}