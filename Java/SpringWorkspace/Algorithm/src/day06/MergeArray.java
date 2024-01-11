package day06;

import java.util.Arrays;

//배열 2개를 병합하는 과정을 살펴보자.
//정렬이 완료된 2개의 배열을 하나로 병합하여 배열c에 저장해본다.
public class MergeArray {

	public static void main(String[] args) {
		int[] a= {2,4,6,8,11}; //5개
		int[] b= {1,2,3,5,9,21,25}; //7개
		int[] c= new int[a.length + b.length];//배열c의 크기=13
		System.out.println("---배열 병합 전-------------------------------");
		System.out.println("a: "+Arrays.toString(a));
		System.out.println("b: "+Arrays.toString(b));
		System.out.println("c: "+Arrays.toString(c));
		
		merge(a, b, c);
		System.out.println("---배열 a와 b를 병합하여 c에 저장한 후--------------");
		System.out.println("c:"+Arrays.toString(c));
		
	}

	private static void merge(int[] a, int[] b, int[] c) {
		int pa=0; //a배열에서 사용할 포인터
		int pb=0; //b배열에서 사용할 포인터
		int pc=0; //c배열에서 사용할 포인터
		
		//a에 저장된 값과 b에 저장된 값을 비교해서 더 작은 쪽을 c에 저장하고 
		//저장 후 작은쪽의 포인터를 증가시킨다.
		while(pa<a.length && pb<b.length) {
			if(a[pa] <= b[pb]) {
				c[pc++]=a[pa++];
			}else {
				c[pc++]=b[pb++];
			}
		}//while----------------------------------------
		while(pa<a.length) { //a배열에 남아있는 데이터가 있다면 남아있는 요소를 모두 c에 옮긴다
			c[pc++]=a[pa++];
		}
		while(pb<b.length) { //b배열에 남아있는 요소를 모두 c로 옮긴다
			c[pc++]=b[pb++];
		}
		
	}//-----------------------------------------

}
