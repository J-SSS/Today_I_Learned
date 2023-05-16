package step_by_step.s04_1차원배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz10813 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[Integer.parseInt(st.nextToken())];

        int cnt = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < arr.length ; i++){
            arr[i] = i+1;
        }

        int tempN = 0;
        for(int i = 0 ; i < cnt ; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken())-1;
            int num2 = Integer.parseInt(st.nextToken())-1;

            tempN = arr[num1];
            arr[num1] = arr[num2];
            arr[num2] = tempN;
        }

        for(int i = 0 ; i < arr.length ; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
