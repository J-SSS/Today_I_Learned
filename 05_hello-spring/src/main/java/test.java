import java.util.Arrays;

public class test {
    public static void main(String[] args) {



        String test = "1aee2b3c4d5e6e";


       String[] test2 =  test.split("[a-z]+");

        System.out.println(Arrays.toString(test2));
//        System.out.println(test2[1]);

    }
}
