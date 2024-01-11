package day02;
import java.util.*;
public class Q06_ArrayCopy {

	public static int[] inputArray(int n) {
		Scanner sc=new Scanner(System.in);
		System.out.println("요소수 입력 : ");
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
		//크기가 작은 쪽 배열값을 큰 쪽 배열에 카피하세요
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
		//크기가 작은 쪽 배열값을 큰 쪽 배열에 역순으로 카피하세요
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
