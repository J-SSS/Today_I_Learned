import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int srchSeq = sc.nextInt(), findSeq = 0, thisNum = 666;
		while(findSeq<srchSeq){
			String thisStr = String.valueOf(thisNum);
			if(thisStr.contains("666")){
				findSeq++;
			}
			thisNum++;
		}
		System.out.println(thisNum-1);
	}
}