package practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main2 {
	public static void main(String[] args) {
		/*
		 * 기본형과 타입의 다형성은 자료형과 개념이 완전히 다르다!!
		 * 
		 * 타입의 다형성 : 객체가 부모의 타입으로 참조되는 것??
		 * Object는 모든 객체의 부모타입니다. -> 모든 객체를 참조할 수 있다.
		 *  
		 */
		Object[] iArr = {13,3,10,12,100,50};
		int sum1 = 0; // iArr의 모든 값을 더하세요
		

		for(int i = 0 ; i< iArr.length ; i++) {	
			sum1 += (int) iArr[i];
		}

		for(Object o : iArr) {
			if(o instanceof Integer) {
		}
		//getClass 찾아보기
		
		Object[] iArr2 = {13,3,10,"12",100,"삼",50};
		int sum2 = 0; //iArr의 모든 값을 더하세요.(정수로 형 불가한 데이터만 제외하고 or 정수만)
		
//		System.out.println(iArr2[3] instanceof Integer);
//		
//		
		
		
		for(int i = 0 ; i< iArr2.length ; i++) {
			try {
				if(!(iArr2[i] instanceof Integer)) {
					sum2 += Integer.parseInt((String) iArr2[i]);
				}else
					sum2 += (int) iArr2[i];
				}
			 catch (Exception e) {
			}	
		}
		System.out.println(sum2);
		}
		

		
//		List iList = new ArrayList();
//		iList.add(13);
//		iList.add(3);
//		iList.add(10);
//		iList.add("12");
//		iList.add(100);
//		iList.add("삼");
//		iList.add(50);
//		//Stream API로 모두 더한 결과를 내세요. 
//		//filter로 정수만 남기고 maptoint로 정수스트림으로 바꾼 후 sum하세요
//		//reduce, map, mapInt, sum, filter 추천
//		
//		iList.stream().filter(i -> i instanceof Integer).mapToInt(i ->(int) i).sum();
//		System.out.println(iList.stream().filter(i -> i instanceof Integer).mapToInt(i ->(int) i).sum());
//	}}

	}
}
