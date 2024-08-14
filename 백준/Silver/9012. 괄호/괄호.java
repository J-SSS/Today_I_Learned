import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        String str = "";
        String rslt = "";

        for (int i = 0; i < cnt; i++) {
            rslt = "YES";
            stack.clear();
            str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char thisChar = str.charAt(j);
                if(thisChar == '(') {
                    stack.push(thisChar);
                } else {
                    if(stack.isEmpty()) {
                        rslt = "NO";
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            if (stack.isEmpty()) {
                System.out.println(rslt);
            } else {
                System.out.println("NO");
            }
        }

        br.close();
    }
}