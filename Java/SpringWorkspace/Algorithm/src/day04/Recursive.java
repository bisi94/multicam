package day04;

public class Recursive {
	
	public static void recursive(int n) {
		if(n<=0) {//기본단계
			return;
		}		
		
		recursive(n-1);//재귀단계
		System.out.println(n);
	}
	public static void main(String[] args) {
		recursive(4);
	}
}////////////////////////
