
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

       while (true){
           st = new StringTokenizer(br.readLine());

           String nm = st.nextToken();
           int age = Integer.parseInt(st.nextToken());
           int weight = Integer.parseInt(st.nextToken());

           if(nm.equals("#")) break;

           if(age > 17 || weight >= 80){
               sb.append(nm + " Senior" + "\n");
           } else {
               sb.append(nm + " Junior" + "\n");
           }
       }

        System.out.println(sb);
    }
}