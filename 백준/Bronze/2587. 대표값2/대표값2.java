
import java.io.*;
		import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[5];
		int sum = 0;
		for(int i = 0 ; i < 5 ; i++){
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(Arrays.stream(arr).sum()/5);
		System.out.println(arr[2]);
	}
}