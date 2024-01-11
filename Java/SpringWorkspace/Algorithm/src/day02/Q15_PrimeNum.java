package day02;

public class Q15_PrimeNum {

	public static void getPrime() {
		int[] prime=new int[500];
		int ptr=0;
		prime[ptr++]=2;
		prime[ptr++]=3;
		int cnt=0;
		for(int n=5; n<=1000; n+=2) {
			boolean flag=false;
			for(int i=1;prime[i]<Math.sqrt(n);i++) {
				cnt++;
				if(n%prime[i]==0) {
				flag=true;
				break;
				}
			}//for--------------
			if(!flag) {
				prime[ptr++]=n;//소수를 저장
				cnt++;
			}
		}//for------------------------
		for(int i=0; i<ptr; i++) {
			System.out.print(prime[i]+", ");
		}
		System.out.println();
		System.out.println("수행횟수: "+cnt);
	}
	
	public static void main(String[] args) {
		getPrime();
	}

}
