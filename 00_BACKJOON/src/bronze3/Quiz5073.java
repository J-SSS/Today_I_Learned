package bronze3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Quiz5073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String thisLine;
        while (true) {
            thisLine = br.readLine();
            int[] num = {
                    Integer.parseInt(thisLine.split(" ")[0]),
                    Integer.parseInt(thisLine.split(" ")[1]),
                    Integer.parseInt(thisLine.split(" ")[2])
            };
            Arrays.sort(num);
            if(num[2]<num[0]+num[1]){
                if(num[0] == num[1] && num[1] == num[2]) System.out.println("Equilateral");
                else if(num[0] == num[1] || num[1] == num[2] || num[0] == num[2]) System.out.println("Isosceles");
                else System.out.println("Scalene");
            } else {
                if (thisLine.equals("0 0 0")) {
                    br.close();
                    break;
                }
                System.out.println("Invalid");
            }
        };
    }
}
