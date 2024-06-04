import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 오늘 날짜 가져오기
        LocalDate today = LocalDate.now();

        // 원하는 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 오늘 날짜를 포맷팅
        String formattedDate = today.format(formatter);
        System.out.println(formattedDate);
    }
}