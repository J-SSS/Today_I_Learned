package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Quiz26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = Integer.parseInt(br.readLine());
        boolean flag = false;
        HashSet<String> resultSet = new HashSet<>();
        resultSet.add("ChongChong");
        for(int i = 0 ; i < cnt ; i ++){
            st = new StringTokenizer(br.readLine());
            String personA = st.nextToken();
            String personB = st.nextToken();

            if(resultSet.contains(personA) || resultSet.contains(personB)){
                resultSet.add(personA);
                resultSet.add(personB);
            }
        }
        br.close();
        System.out.println(resultSet.size());
    }
}
