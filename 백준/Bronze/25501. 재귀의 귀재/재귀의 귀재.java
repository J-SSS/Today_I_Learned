import java.io.*;
import java.util.*;
public class Main {
    static int recursionCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++) {
            int rslt = isPalindrome(br.readLine());
            System.out.println(rslt + " " + recursionCnt);
        }
    }

    public static int isPalindrome(String s){
        recursionCnt = 0;
        return recursion(s, 0, s.length()-1);
    }

    public static int recursion(String s, int l, int r){
        recursionCnt++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
}