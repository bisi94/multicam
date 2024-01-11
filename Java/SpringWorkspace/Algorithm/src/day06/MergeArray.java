package day06;

import java.util.Arrays;

//�迭 2���� �����ϴ� ������ ���캸��.
//������ �Ϸ�� 2���� �迭�� �ϳ��� �����Ͽ� �迭c�� �����غ���.
public class MergeArray {

	public static void main(String[] args) {
		int[] a= {2,4,6,8,11}; //5��
		int[] b= {1,2,3,5,9,21,25}; //7��
		int[] c= new int[a.length + b.length];//�迭c�� ũ��=13
		System.out.println("---�迭 ���� ��-------------------------------");
		System.out.println("a: "+Arrays.toString(a));
		System.out.println("b: "+Arrays.toString(b));
		System.out.println("c: "+Arrays.toString(c));
		
		merge(a, b, c);
		System.out.println("---�迭 a�� b�� �����Ͽ� c�� ������ ��--------------");
		System.out.println("c:"+Arrays.toString(c));
		
	}

	private static void merge(int[] a, int[] b, int[] c) {
		int pa=0; //a�迭���� ����� ������
		int pb=0; //b�迭���� ����� ������
		int pc=0; //c�迭���� ����� ������
		
		//a�� ����� ���� b�� ����� ���� ���ؼ� �� ���� ���� c�� �����ϰ� 
		//���� �� �������� �����͸� ������Ų��.
		while(pa<a.length && pb<b.length) {
			if(a[pa] <= b[pb]) {
				c[pc++]=a[pa++];
			}else {
				c[pc++]=b[pb++];
			}
		}//while----------------------------------------
		while(pa<a.length) { //a�迭�� �����ִ� �����Ͱ� �ִٸ� �����ִ� ��Ҹ� ��� c�� �ű��
			c[pc++]=a[pa++];
		}
		while(pb<b.length) { //b�迭�� �����ִ� ��Ҹ� ��� c�� �ű��
			c[pc++]=b[pb++];
		}
		
	}//-----------------------------------------

}
