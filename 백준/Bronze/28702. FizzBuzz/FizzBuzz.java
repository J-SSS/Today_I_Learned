import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 3; i > 0; i--){
            String s = br.readLine();
            if(s.matches("^[1-9]\\d*$")){
                int n = Integer.parseInt(s) + i;
                if(n % 3 == 0){
                    if (n % 5 == 0) {
                        System.out.println("FizzBuzz");
                    }else {
                        System.out.println("Fizz");
                    }
                } else if (n % 5 == 0) {
                    System.out.println("Buzz");
                }else {
                    System.out.println(n);
                }
                return;
            }
        }
        br.close();
    }
}