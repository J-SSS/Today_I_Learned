import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int r1 = Integer.parseInt(st.nextToken());

        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());

        System.out.println((r1*y2 - y1*r2) / (x1*y2 - y1*x2) + " " + (r1*x2 - x1*r2) / (y1*x2 - x1*y2));

    }
}