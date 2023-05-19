package step_by_step.s06_심화1;

import java.util.Scanner;

public class Quiz10988 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int size = str.length();
        int result = 1;
        if(size%2 ==0){
            for (int i = 0 ; i<size/2 ; i++){
                if(str.charAt(i) != str.charAt(size-i-1)){
                    result = 0;
                    break;
                }
            }
        } else {
            for (int i = 0 ; i<size/2 ; i++){
                if(str.charAt(i) != str.charAt(size-i-1)){
                    result = 0;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
