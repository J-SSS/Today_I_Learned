package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz9506 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int num = Integer.parseInt(br.readLine());
        int sum = 0;
        while(num != -1) {
            sb.setLength(0);
            sb.append(num + " = 1");
            sum = 1;
            for(int i=2; i<num; i++) {
                if(num%i==0) {
                    sb.append(" + " + i);
                    sum += i;
                }
            }
            if(sum == num) {
                System.out.println(sb);
            } else {
                System.out.println(num + " is NOT perfect.");
            }
            num = Integer.parseInt(br.readLine());
        }
    }
}
