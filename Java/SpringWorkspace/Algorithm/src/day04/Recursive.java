package day04;

public class Recursive {
	
	public static void recursive(int n) {
		if(n<=0) {//�⺻�ܰ�
			return;
		}		
		
		recursive(n-1);//��ʹܰ�
		System.out.println(n);
	}
	public static void main(String[] args) {
		recursive(4);
	}
}////////////////////////
