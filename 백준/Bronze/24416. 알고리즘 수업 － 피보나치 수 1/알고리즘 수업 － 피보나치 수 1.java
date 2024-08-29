import java.io.*;
public class Main {
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int[] f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        f = new int[num];
        f1(num);
        f2(num);

        System.out.println(cnt1 + " " + cnt2);

    }

    static int f1(int n){
        if((n == 1 || n == 2)){
            cnt1++;
            return 1;
        } else {
            return f1(n-1) + f1(n-2);
        }
    }

    static int f2(int num){
        f[0] = 1; f[1] = 1;
        for(int i = 2 ; i < num ; i++){
            cnt2++;
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[num-1];
    }
}