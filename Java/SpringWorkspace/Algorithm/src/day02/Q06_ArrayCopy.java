package day02;
import java.util.*;
public class Q06_ArrayCopy {

	public static int[] inputArray(int n) {
		Scanner sc=new Scanner(System.in);
		System.out.println("��Ҽ� �Է� : ");
		int num=sc.nextInt();
		int[] arr=new int[num];
		for(int i=0;i<arr.length;i++) {
			System.out.printf("arr%d[%d]: ",n,i);
			arr[i]=sc.nextInt();
		}
		
		return arr;
	}//---------------------------------------
	
	
	public static void main(String[] args) {
		int[] a=inputArray(1);
		int[] b=inputArray(2);
		reverse_copy(a, b);
		
		/*
		 * for(int x:a) System.out.println(x);
		 */
		System.out.println(Arrays.toString(a));
		System.out.println("-----------------------");
		System.out.println(Arrays.toString(b));
		/*
		 * for(int x:b) System.out.println(x);
		 */
		
	}//-------------------------------------------
	public static void copy(int[] a, int[] b) {
		//ũ�Ⱑ ���� �� �迭���� ū �� �迭�� ī���ϼ���
		int num=(a.length<b.length)? a.length:b.length;
		for(int i=0;i<num;i++) {
			if(a.length<b.length) {
				b[i]=a[i];
				
			}else {
				a[i]=b[i];
			}
		}
	}//------------------------------------------------
	public static void reverse_copy(int[] a, int[] b) {
		//ũ�Ⱑ ���� �� �迭���� ū �� �迭�� �������� ī���ϼ���
		int num=(a.length<b.length)? a.length:b.length;
		for(int i=0;i<num;i++) {
			if(a.length<b.length) {
				b[i]=a[num-1-i];
				
			}else {
				a[i]=b[num-1-i];
			}
		}
	}//-----------------------------------------------
	
}
