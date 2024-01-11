package day01;

public class Q02_Mid {

	public static int mid3(int a, int b, int c) {
		
		int mid=0;
		
		if(a>=b) {
			if(b>=c) {
				return b;
			}else if(a>=c) {
				return c;
			}else {
				return a;
			}
			
		}else if(a>c) {
			return a;
		}else if(b>=c) {
			return c;
		}else {
			return b;
		}
		
	}
	
	public static void main(String[] args) {
		int x=44, y=99, z=35;
		System.out.println(mid3(x,y,z));
	}

}
