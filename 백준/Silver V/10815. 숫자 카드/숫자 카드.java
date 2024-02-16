
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int inputCnt = Integer.parseInt(br.readLine());
        int[] inputArr = new int[inputCnt];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < inputCnt ; i++){
            inputArr[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(inputArr);

        String cnt2 = br.readLine();
        String oldCard = br.readLine();

        StringBuilder sb = new StringBuilder();
        List<String> myList = Arrays.asList(oldCard.split(" "));
        
        myList.forEach(e -> {
            if(Arrays.binarySearch(inputArr, Integer.parseInt(e)) > -1){
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
            ;
        });

        System.out.println(sb);
    }
}