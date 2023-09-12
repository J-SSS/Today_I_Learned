
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[10001];
		int cnt = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < cnt ; i++){
			arr[Integer.parseInt(br.readLine())] += 1;
		}
		br.close();
		for(int i = 0 ; i < 10001 ; i++){
			for(int j = 1 ; j <= arr[i] ; j++){
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}
}