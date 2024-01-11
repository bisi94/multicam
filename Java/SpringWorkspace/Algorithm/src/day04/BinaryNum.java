package day04;

public class BinaryNum {
	
	public static void binaryNum(int num) {
		//��������  => num�� 2�� ���� ���� 0�� �Ǹ� ����
		if(num<=0) return;
		//��ʹܰ� => �ڱ��Լ� ȣ��
		binaryNum(num/2);
		System.out.println(num%2);
	}//----------------------------
	
	public static void radixNum(int num, int radix) {
		if(num<=0) return;
		radixNum(num/radix, radix);
		String str="0123456789ABCDEF";
		int mod=num%radix;
		System.out.print(str.charAt(mod));
	}//------------------------------

	public static void main(String[] args) {		
		//binaryNum(5);//5  2   1  0
		//binaryNum(128);
		radixNum(45, 16);
	}

}
