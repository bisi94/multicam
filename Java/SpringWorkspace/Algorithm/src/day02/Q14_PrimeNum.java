package day02;

import java.util.Arrays;

public class Q14_PrimeNum {

	public static void getPrime() {
		int prime[]=new int[500];//�Ҽ����� ������ �迭
		int ptr=0;
		prime[ptr++]=2; //prime[0]=2, ptr=1�� ����
		int cnt=0;
		for(int i=3;i<=1000;i+=2) {//�Ǻ� ����� Ȧ���� �����Ͽ� i�� 2��ŭ�� ������Ų��
			int num;
			for(num=1;num<ptr;num++) {
				cnt++;
				if(i%prime[num]==0)
					break;
			}//for-----------------------
			if(ptr==num) {
				prime[ptr++]+=i;
			}
		}//for---------------------------
		//System.out.println(Arrays.toString(prime));
		for(int i=0;i<ptr;i++) {
			System.out.print(prime[i]+", ");
		}
		System.out.println();
		System.out.println("����Ƚ��: "+cnt);
	}
	
	public static void main(String[] args) {
		getPrime();
	}
}
