package day02;

import java.util.Arrays;

public class Q14_PrimeNum {

	public static void getPrime() {
		int prime[]=new int[500];//소수들을 저장할 배열
		int ptr=0;
		prime[ptr++]=2; //prime[0]=2, ptr=1인 상태
		int cnt=0;
		for(int i=3;i<=1000;i+=2) {//판별 대상을 홀수로 지정하여 i를 2만큼씩 증가시킨다
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
		System.out.println("수행횟수: "+cnt);
	}
	
	public static void main(String[] args) {
		getPrime();
	}
}
