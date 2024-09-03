import java.util.*;
import java.io.*;
public class Main {
    public static String[] arr;
    public static int min = 64;
    public static int startW = 0b10101010;
    public static int startB = 0b01010101;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new String[N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            str = str.replaceAll("W","1");
            str = str.replaceAll("B","0");

            arr[i] = str;
        }

        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                func(i, j);
            }
        }

        System.out.println(min);
    }


    public static void func(int x, int y) {
        boolean isW = true;
        int cnt = 0;

        for (int i = x; i < x + 8; i++) {
            String str = arr[i];

            if(isW) {
                cnt += Integer.bitCount(Integer.parseInt(str.substring(y,y+8),2) ^ startW);
            } else {
                cnt += Integer.bitCount(Integer.parseInt(str.substring(y,y+8),2) ^ startB);
            }
            isW = !isW;
        }

        min = Math.min(min,Math.min(cnt,64-cnt));
    }
}