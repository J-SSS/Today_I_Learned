
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       Long A = Long.parseLong(st.nextToken());
       Long B = Long.parseLong(st.nextToken());

        System.out.println(Math.abs(A-B));
    }
}