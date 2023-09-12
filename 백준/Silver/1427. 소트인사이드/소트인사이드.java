
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(),"");
		br.close();
		String[] arr;
		arr = st.nextToken().split("");
		Arrays.sort(arr);
		for(String i : arr) {
			sb.append(i);
		}
		System.out.println(sb.reverse());
	}
}