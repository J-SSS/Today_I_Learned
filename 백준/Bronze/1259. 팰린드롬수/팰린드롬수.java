import java.io.*;
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while (true){
            str = String.valueOf(Integer.parseInt(br.readLine()));
            String flag = "yes";
            if(str.equals("0")) break;
            
            for (int i = 0; i < str.length()/2; i++) {
                if(str.charAt(i) != str.charAt(str.length()-i-1)) flag = "no";
            }

            System.out.println(flag);
        }
        br.close();
    }
}