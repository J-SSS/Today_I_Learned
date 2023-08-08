package bronze1;

import java.util.*;

public class Quiz11653{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean ctn = true;
        while(ctn && num!=1){
            for(int i=2; i<=num; i++){
                if(i==num){
                    ctn = false;
                    System.out.println(num);
                    break;
                }
                if(num%i==0){
                    System.out.println(i);
                    num = num/i;
                    break;
                }
            }
        }
    }
}
