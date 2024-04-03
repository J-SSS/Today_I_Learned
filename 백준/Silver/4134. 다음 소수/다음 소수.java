
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++) {
            long thisNum = Long.parseLong(br.readLine());
            System.out.println(findPrime(thisNum));
        }


    }
    static Long findPrime(Long num){
        long trgtNum = num;
        while (true){
            boolean isP = isPrime(trgtNum);
           if(!isP || trgtNum < 2) {
                trgtNum++;
            } else {
                break;
            }
        }
        return trgtNum;
    }

    static boolean isPrime(Long num){
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