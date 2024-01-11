package day02;
//소수 구하기
//소수: 1과 자신으로만 나누어지는 수
//1 ~ 1000까지의 정수 중 소수를 구해 출력하세요
public class Q13_PrimeNum {

	public static void getPrime() {
		int cnt=0;
		for(int i=2;i<=1000;i++) {
			int num;
			for(num=2;num<=i;num++) {
				cnt++;
				if(i%num==0) break;
			}
			if(num==i) {
				System.out.println(i);
			}
		}//for-------------------
		System.out.println("수행횟수:"+cnt);
		
	}
	
	public static void main(String[] args) {
		getPrime();
	}
}
