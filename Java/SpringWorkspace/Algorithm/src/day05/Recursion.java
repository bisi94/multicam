package day05;

public class Recursion {
	
	public static void recur(int n) {
		if(n<=0) return;
		recur(n-1);
		System.out.println(n);
		recur(n-2);
	}//----------------------------

	public static void main(String[] args) {
		recur(4);
	}//------------------------------

}////////////////////////////////
