
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		int[] arr = new int[cnt];
		for(int i = 0 ; i < cnt ; i++){
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		Arrays.stream(arr).forEach(e -> System.out.println(e));
	}
}