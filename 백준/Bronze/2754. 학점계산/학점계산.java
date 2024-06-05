import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String grade = br.readLine();
        String rslt;
        switch (grade){
            case "A+" : rslt = "4.3"; break;
            case "A0" : rslt = "4.0"; break;
            case "A-" : rslt = "3.7"; break;
            case "B+" : rslt = "3.3"; break;
            case "B0" : rslt = "3.0"; break;
            case "B-" : rslt = "2.7"; break;
            case "C+" : rslt = "2.3"; break;
            case "C0" : rslt = "2.0"; break;
            case "C-" : rslt = "1.7"; break;
            case "D+" : rslt = "1.3"; break;
            case "D0" : rslt = "1.0"; break;
            case "D-" : rslt = "0.7"; break;
            case "F" : rslt = "0.0"; break;
            default: rslt = "";
        }

        System.out.println(rslt);
    }
}