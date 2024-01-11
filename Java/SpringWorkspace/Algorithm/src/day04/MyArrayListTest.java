package day04;

import day03.Emp;

public class MyArrayListTest {

	public static void main(String[] args) {
		//MyArrayList생성해서 Integer유형의 객체를 3개 저장한 뒤 반복문 이용해서 출력해보세요
		
		MyArrayList<Integer> arr=new MyArrayList<>();
		arr.add(Integer.valueOf(100));
		arr.add(200);
		arr.add(300);
		System.out.println("arr.size(): "+arr.size());
		arr.add(45);;
		arr.add(55);
		arr.add(66);
		System.out.println("arr.size(): "+arr.size());
		System.out.println("--------------------");
		for(int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i));
		}
		arr.remove(2);
		
		System.out.println("------삭제후--------------");
		for(int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i));
		}
		
		MyArrayList<Emp> arr2=new MyArrayList<>();
		arr2.add(new Emp("Scott",177,3000));
		arr2.add(new Emp("King",160,5000));
		
		System.out.println(arr2.get(0));

	}

}
