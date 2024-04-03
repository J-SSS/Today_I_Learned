
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int min = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        int trgtNum = min;
        while (trgtNum <= max){
            boolean isP = isPrime(trgtNum);
            if(!isP || trgtNum < 2) {
                trgtNum++;
            } else {
                sb.append(trgtNum+"\n");
                trgtNum++;
            }
        }
        System.out.println(sb);
    }

    static boolean isPrime(int num){
        boolean flag = true;
        int sqrtNum = (int) Math.sqrt(num);

        for (int i = 2; i <= sqrtNum; i++) {
            if(num % i == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }
}