import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       StringBuilder sb = new StringBuilder();

       while (true){
           st = new StringTokenizer(br.readLine());
           int A = Integer.parseInt(st.nextToken());
           int B = Integer.parseInt(st.nextToken());

           if(A == 0 && B == 0) break;

           if(A>B){
               sb.append("Yes \n");
           } else {
               sb.append("No \n");
           }
       }

        System.out.println(sb);
    }
}