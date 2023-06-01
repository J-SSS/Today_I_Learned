package bronze2;


import java.io.*;

import java.util.StringTokenizer;

public class Quiz10811 {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(bfr.readLine());

        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());

        int arr[] = new int[num1 + 1];

        int tmp = 0;

        for(int i = 1; i <= num1; i++) {
            arr[i] = i;
        }

        for(int i = 0; i < num2; i++) {
            String as = bfr.readLine();
            StringTokenizer stt = new StringTokenizer(as, " ");
            num1 = Integer.parseInt(stt.nextToken());
            num2 = Integer.parseInt(stt.nextToken());

            for(int j = num1; j <= (num2 - num1) / 2 + num1; j++) {
                tmp = arr[j];
                arr[j] = arr[num2 - j + num1];
                arr[num2 - j + num1] = tmp;
            }
        }

        for(int i = 1; i <= num1; i++) {
            bfw.write(arr[i] + " ");
        }

        bfw.flush();
        bfw.close();
    }
}

