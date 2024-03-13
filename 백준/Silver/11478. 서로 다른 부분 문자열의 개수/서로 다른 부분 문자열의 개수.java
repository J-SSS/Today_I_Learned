
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<String> rsltSet = new HashSet<>();

       String trgtStr = br.readLine();
       int length = trgtStr.length();
       br.close();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(i < length-j){
                String thisStr = trgtStr.substring(i,length-j);
                    rsltSet.add(thisStr);
                }
            }
        }

        bw.write(String.valueOf(rsltSet.size()));
        
        bw.flush();
        bw.close();
    }
}