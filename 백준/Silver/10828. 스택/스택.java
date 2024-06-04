import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       Stack<Integer> stack = new Stack<>();
       StringBuilder sb = new StringBuilder();

       int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {

            String[] thisInput = br.readLine().split(" ");
            if(thisInput.length > 1) {
                if(thisInput[0].equals("push")){
                    stack.push(Integer.parseInt(thisInput[1]));
                }
            } else {
                if(thisInput[0].equals("top")){
                    if(stack.isEmpty()){
                        sb.append("-1"+"\n");
                    } else {
                        sb.append(stack.peek()+"\n");
                    }
                } else if(thisInput[0].equals("size")){
                    sb.append(stack.size()+"\n");
                } else if(thisInput[0].equals("empty")){
                    if(stack.isEmpty()){
                        sb.append("1"+"\n");
                    } else {
                        sb.append("0"+"\n");
                    }
                } else if(thisInput[0].equals("pop")){
                    if(stack.isEmpty()){
                        sb.append("-1"+"\n");
                    } else {
                        sb.append(stack.pop()+"\n");
                    }

                }
            }
        }

        System.out.println(sb);
    }
}