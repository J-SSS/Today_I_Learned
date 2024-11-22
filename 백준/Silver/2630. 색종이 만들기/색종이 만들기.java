import java.util.*;
import java.io.*;
public class Main {
    static int[][] arr;
    static int white = 0;
    static int blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int s = Integer.parseInt(br.readLine());
        arr = new int[s][s];

        // 2차원 배열에 입력값 할당
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < s; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(0,0, s);
        System.out.println(white);
        System.out.println(blue);
    }

    static void func(int r, int c, int s){

        boolean isValid = true;
        int color = 0;
        outer:
        for (int i = r; i < r+s; i++) {
            for (int j = c; j < c+s; j++) {
                if(i == r && j == c) {
                    color = arr[i][j]; // 첫 사각형의 색
                } else {
                    if(color != arr[i][j]){
                        isValid = false;
                        break outer;
                    }
                }
            }
        }

        if(isValid){ // 색이 일치하는 경우
            if(color == 0) white++;
            if(color == 1) blue++;
        } else { // 색이 일치하지 않는 경우
            func(r,c+s/2,s/2); // 1사분면
            func(r,c,s/2); // 2사분면
            func(r+s/2,c,s/2); // 3사분면
            func(r+s/2,c+s/2,s/2); // 4사분면
        }
    }
}