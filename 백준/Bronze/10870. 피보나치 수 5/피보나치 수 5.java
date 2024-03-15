
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(func(N));

    }
    static int func(int N) {
        if (N == 0)	return 0;
        if (N == 1)	return 1;
        return func(N - 1) + func(N - 2);
    }
}