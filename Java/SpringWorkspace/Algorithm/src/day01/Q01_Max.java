package day01;

public class Q01_Max {

	//1. 정수 2개 중 최대값 반환하는 메소드
	public static int max2(int a, int b) {
		if(a>=b) {
			return a;
		}return b;
	}
	
	//2. 정수 3개 중 최대값 반환하는 메소드
	public static int max3(int a, int b, int c) {
		int max=a;
		if(max<b) {
			max=b;
		}else if(max<c) {
			max=c;
		}
		return max;
	}
	
	//3. 정수 4개 중 최대값 반환하는 메소드
		public static int max4(int a, int b, int c, int d) {
			return max2(max2(max2(a,b),c),d);
		}
		
	//4. 정수 3개 중 최소값 반환하는 메소드
			public static int min3(int a, int b, int c) {
				
				int min=a;
				if(min>b) {
					min=b;
				}else if(min>c) {
					min=c;
				}
				
				return min;
			}
	
	public static void main(String[] args) {
		int a=55, b=70, c=60, d=35;
		
		int mx2=max2(a,b);
		System.out.println(mx2);
		
		int mx3=max3(a,b,c);
		System.out.printf("%d, %d, %d 중 최댓값 : %d\n ",a,b,c,mx3);
		//System.out.println(mx3);
		
		int mx4=max4(a, b, c, d);
		System.out.printf("%d, %d, %d, %d 중 최댓값 : %d\n ",a,b,c,d,mx4);
		
		int mn3=min3(a, b, c);
		System.out.printf("%d, %d, %d 중 최소값 : %d\n ",a,b,c,mn3);
				
	}

}
