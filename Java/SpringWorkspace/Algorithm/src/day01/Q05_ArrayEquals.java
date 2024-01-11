package day01;

import java.util.Scanner;

public class Q05_ArrayEquals {

	Scanner sc=new Scanner(System.in);
	
	public int[] inputArray(int i) {
		System.out.println("��Ҽ� �Է�: ");
		int num=sc.nextInt();
		int	[] arr=new int[num];
		for(int j=0;j<num;j++) {
			System.out.printf("arr%d[%d] :",i,j);
			arr[j]=sc.nextInt();
		}
		System.out.println("----------------------------");
		return arr;
	}//--------------------------------
	public boolean equalsArray(int[] a, int[] b) {
		//a�� b�� ����� ��Ұ��� ������ true��ȯ, �ƴϸ� false��ȯ
		if(a.length!=b.length) return false;
		for(int i=0;i<a.length;i++) {
			if(a[i]!=b[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		//inputArray() ȣ���ϱ�
		Q05_ArrayEquals app=new Q05_ArrayEquals();
		int arr1[]=app.inputArray(1);
		System.out.println("---------------------------");
		int arr2[]=app.inputArray(2);
		
		//equalArray() ȣ��
		boolean b=app.equalsArray(arr1, arr2);
		System.out.println((b)?"�� �迭�� ����":"�� �迭�� �ٸ�");
	}
	
}
