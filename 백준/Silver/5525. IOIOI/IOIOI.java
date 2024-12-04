import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rslt = 0;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();
        String sen = "I";
   
        for (int i = 0; i < N; i++) {
            sen += "OI";
        }

        for (int i = 0; i < M-(N*2); i++) {
            if(str.charAt(i) != 'O'){
                if(str.charAt(i+1) != 'I'){
                    String thisStr = str.substring(i,i+(N*2)+1);
                    if(thisStr.equals(sen)) {
                        rslt++;
                        i++;
                    }
                }
            }
        }

        System.out.println(rslt);
    }
}