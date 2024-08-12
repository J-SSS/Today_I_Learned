import java.util.*;
import java.io.*;
public class Main {
    static int rslt = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true){
            String str = br.readLine();
            if(str.equals("0 0 0")) break;
            st = new StringTokenizer(str);
            List<Integer> list = new ArrayList<>();

            while (st.hasMoreTokens()){
                list.add(Integer.parseInt(st.nextToken()));
            }

            list.sort(Comparator.naturalOrder());

            if(Math.pow(list.get(2),2) == Math.pow(list.get(1),2) + Math.pow(list.get(0),2)){
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
        br.close();
    }
}