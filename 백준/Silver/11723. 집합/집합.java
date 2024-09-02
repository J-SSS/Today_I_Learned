import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cnt = Integer.parseInt(br.readLine());

        int rslt = 0b0;

        for (int i = 0; i < cnt ; i++) {
            st = new StringTokenizer(br.readLine());

            int num = 0b0;
            String order = st.nextToken();
            if(st.hasMoreTokens()) {
                num = 0b1 << Integer.parseInt(st.nextToken());
            }


            if(order.equals("add")){
                rslt = rslt | num;
            } else if (order.equals("remove")){
                if((rslt & num) != 0){
                    rslt -= num;
                }
            } else if (order.equals("check")){
                if((rslt & num) != 0){
                    bw.write("1" + "\n");
                } else {
                    bw.write("0" + "\n");
                }
            } else if (order.equals("toggle")){
                if((rslt & num) != 0){
                    rslt -= num;
                } else {
                    rslt = rslt | num;
                }
            } else if (order.equals("all")){
                rslt = 0b111111111111111111111;
            } else if (order.equals("empty")){
                rslt = 0b0;
            }
        }

        bw.flush();
        bw.close();
    }
}