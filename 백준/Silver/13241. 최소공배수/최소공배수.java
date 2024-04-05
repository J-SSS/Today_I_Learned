import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Long A = Long.parseLong(st.nextToken());
        Long B = Long.parseLong(st.nextToken());

        Long GCD = euclidian(A,B);
        System.out.println((A*B)/GCD);
    }

    static Long euclidian(Long A, Long B){
        if(B == 0){
            return A;
        }
        return euclidian(B, A % B);
    }
}