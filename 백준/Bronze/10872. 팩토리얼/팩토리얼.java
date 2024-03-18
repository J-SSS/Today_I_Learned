
import java.util.Scanner;

public class Main {
    static int result = 1;
    static int function(int e) {
        if (e > 1) {
            function(e-1);
        }
        if (e>0){
            result = result*e;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(function(sc.nextInt()));
    }
}