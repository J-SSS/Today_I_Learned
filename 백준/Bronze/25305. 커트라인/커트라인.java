
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int participantCnt = Integer.parseInt(st.nextToken());
		int cutLine = Integer.parseInt(st.nextToken());
		int[] intList = new int[participantCnt];
		st = new StringTokenizer(br.readLine());
		br.close();
		for(int i = 0 ; i < participantCnt ; i++){
			intList[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(intList);
		System.out.println(intList[participantCnt-cutLine]);
	}
}