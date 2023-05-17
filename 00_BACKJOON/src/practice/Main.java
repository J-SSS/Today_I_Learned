package practice;

import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
	public static void main(String[] args) {

//		String a = "바보";
//		String b = "바보";
//		String c = new String("바보");
//
//
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(c.hashCode());
//		System.out.println(System.identityHashCode(a));
//		System.out.println(System.identityHashCode(b));
//		System.out.println(System.identityHashCode(c));
//		System.out.println(a==b);


		int a = 12;

		String b = "1";
		int c = 2;
//		System.out.println((a+"")+c);

//		Collections.

		Runnable r = ()->{

		};

		Function f = (e)->{

			return e;
		};

		Consumer cc = (e)->{
			System.out.println(e);
		};

		cc.accept(a);

		System.out.println(f.apply(a));;





	}
}
