package day02;

import java.util.Arrays;

public class Q16_PrimeNum {
	
	public static void getPrime() {
		int prime[] = new int[1001];
		//1. �ݺ��� ���鼭 2~1000������ ���� prime�� ����
		for(int i=2;i<prime.length;i++) {
			prime[i]=i;
		}
		//System.out.println(Arrays.toString(prime));
		//2. 2���� �����ؼ� Ư�� ���� ����� �ش��ϴ� ���� ����� ==> 0�� �־��ش�
		//		���ﶧ �ڱ� �ڽ��� ������ �ʰ�, �̹� ������ ���� �ǳʶڴ�
		int cnt=0;
		for(int i=2; i<prime.length; i++) { //����� ������ �� ����
			for(int j=2; j*i<=1000; j++) {
				cnt++;
				prime[j*i]=0;
			}
		}//for----------------------
		System.out.println("-------------------------------------");
		System.out.println(Arrays.toString(prime));
		System.out.println("����Ƚ��: "+cnt);
		//3. prime�� ���� 0�� �ƴ� ���鸸 ����Ѵ�
		for(int i=2; i<prime.length; i++) {
			if(prime[i]!=0) {
				System.out.print(prime[i]+", ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		getPrime();
	}

}
