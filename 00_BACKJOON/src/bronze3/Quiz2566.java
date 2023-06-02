package bronze3;


import java.util.Scanner;

public class Quiz2566 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = 0;
        int row = 0;
        int col = 0;
        int rowM = 0;
        int colM = 0;
        for(int i =0 ; i<9 ; i++){
            row = i+1;
            for(int j = 0 ; j < 9 ; j++){
                col = j+1;
                int num = sc.nextInt();
                if(num>=max){
                    max = num;
                    rowM = row;
                    colM = col;
                }
            }
        }
        System.out.println(max);
        System.out.println(rowM + " " +colM);
    }
}
