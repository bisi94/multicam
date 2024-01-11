package day02;

import java.util.Arrays;

public class Q16_PrimeNum {
	
	public static void getPrime() {
		int prime[] = new int[1001];
		//1. 반복문 돌면서 2~1000까지의 값을 prime에 저장
		for(int i=2;i<prime.length;i++) {
			prime[i]=i;
		}
		//System.out.println(Arrays.toString(prime));
		//2. 2부터 시작해서 특정 수의 배수에 해당하는 수를 지운다 ==> 0을 넣어준다
		//		지울때 자기 자신은 지우지 않고, 이미 지워진 수는 건너뛴다
		int cnt=0;
		for(int i=2; i<prime.length; i++) { //배수의 기준이 될 숫자
			for(int j=2; j*i<=1000; j++) {
				cnt++;
				prime[j*i]=0;
			}
		}//for----------------------
		System.out.println("-------------------------------------");
		System.out.println(Arrays.toString(prime));
		System.out.println("수행횟수: "+cnt);
		//3. prime에 남은 0이 아닌 값들만 출력한다
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
