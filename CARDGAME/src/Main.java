import java.awt.datatransfer.FlavorListener;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {





        int[] nList = new int[24];
        for (int i = 0 ; i<nList.length ; i++){
            boolean  overCheck=false;
            while (!overCheck) {
                int reCount=0;
                System.out.print(reCount);
                int preNum = (int) ((Math.random() * 49) - 1); //임의의 수를 뽑아서
                for (int k = 0; k < i; k++) { // 생성된 배열 내에 있는지를 검사
                    if (nList[k] == preNum) reCount=1; // 배열중에 일치하는 수를 발견하면 표시
                }
                if (reCount==0) {//무사히 빠져나왔다면 반복문을 종료시킴
                    nList[i]=preNum;
                    overCheck = true;
                }
            }

        }


        for (int i = 0 ; i<nList.length ; i++){
            System.out.print(nList[i] + " ");
        }

//        System.out.println();
//        for (int i = 0 ; i<24 ; i++){
//            int preNum = (int) ((Math.random()*49)-1);
//            System.out.print(preNum + " ");
//        }


    }
}