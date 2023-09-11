
import java.io.*;
		import java.util.*;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = Integer.parseInt(br.readLine());
		List<Integer> arr = new ArrayList<>();
		for(int i = 0 ; i < cnt ; i++){
			arr.add(Integer.parseInt(br.readLine()));
		}
		br.close();

		Collections.sort(arr);

		for(int value : arr) {
			sb.append(value).append('\n');
		}
		System.out.println(sb);
	}
}