package bronze3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz3009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] co1 = br.readLine().split(" ");
        String[] co2 = br.readLine().split(" ");
        String[] co3 = br.readLine().split(" ");

        String x,y;

        if(co1[0].equals(co2[0])){
            x = co3[0];
        } else if (co1[0].equals(co3[0])) {
            x = co2[0];
        } else {
            x = co1[0];
        }

        if (co1[1].equals(co2[1])) {
            y = co3[1];
        } else if (co1[1].equals(co3[1])) {
            y = co2[1];
        } else {
            y = co1[1];
        }

        System.out.println(x + " " + y);
    }
}
