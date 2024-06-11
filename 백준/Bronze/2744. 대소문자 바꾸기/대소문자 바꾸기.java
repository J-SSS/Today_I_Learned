import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char thisChar = str.charAt(i);
            if(Character.isLowerCase(thisChar)){
                sb.append(Character.toUpperCase(thisChar));
            } else {
                sb.append(Character.toLowerCase(thisChar));
            }
        }

        System.out.println(sb);
    }
}