import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       long sum = 0;
       while (st.hasMoreTokens()){
           int num = Integer.parseInt(st.nextToken());
           sum += Math.pow(num,2);
       }

        System.out.println(sum%10);
    }
}