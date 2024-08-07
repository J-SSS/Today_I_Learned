import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Character> op = new Stack<>();

        while (true){
            String str = br.readLine();
            op.clear();
            if(str.equals(".")){
                br.close();
                break;
            } else {
                str = str.replaceAll("[^\\[\\]()]+", "");
                if(str.length() == 0){
                    sb.append("yes" + "\n");
                } else if (str.length() % 2 != 0) {
                    sb.append("no" + "\n");
                } else {
                    for (int i = 0; i < str.length(); i++) {
                        char thisChar = str.charAt(i);
                        if(thisChar == '(' || thisChar == '[') {
                            op.push(thisChar);
                        } else {
                            if(op.isEmpty()) {
                                sb.append("no" + "\n");
                                break;
                            } else if (Math.abs(thisChar - op.peek()) < 3) {
                                op.pop();
                            } else {
                                sb.append("no" + "\n");
                                break;
                            }
                        }

                        if(i == str.length()-1){
                           if(op.isEmpty()){
                               sb.append("yes" + "\n");
                           } else {
                               sb.append("no" + "\n");
                           }
                        }
                    }
                }
            }
        }

        System.out.println(sb);
    }
}