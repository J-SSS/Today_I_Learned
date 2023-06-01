package silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz25206 {
    public static void main(String[] args) throws IOException {
        String[][] grade = new String[20][3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double sumGrade = 0;
        double sumScore = 0;

        for(int i = 0 ; i < 20 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            grade[i][0] = st.nextToken();
            grade[i][1] = st.nextToken();
            grade[i][2] = st.nextToken();

            if(!grade[i][2].equals("P")) {
                sumScore += parser(Double.parseDouble(grade[i][1]), grade[i][2]);
                sumGrade += Double.parseDouble(grade[i][1]);
            }
        }

        if(sumScore != 0) {
            sumScore /= sumGrade;
        }

        System.out.printf("%f", sumScore);
    }

    private static double parser(double score, String grade) {
        double result = 0;

        switch (grade) {
            case "A+":
                result = score * 4.5;
                break;

            case "A0":
                result = score * 4.0;
                break;

            case "B+":
                result = score * 3.5;
                break;

            case "B0":
                result = score * 3.0;
                break;

            case "C+":
                result = score * 2.5;
                break;

            case "C0":
                result = score * 2.0;
                break;

            case "D+":
                result = score * 1.5;
                break;

            case "D0":
                result = score * 1.0;
                break;
        }
        return result;
    }
}
