package day03;

import java.util.Arrays;
import java.util.Scanner;

class Point implements Comparable<Point>{
	int x;
	int y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	// x좌표 내림차순 정렬, x값이 같으면 y좌표 내림차순
	/*@Override
	public int compareTo(Point obj) {
		if(this.x == obj.x) {
			//y값 내림차순
			if(this.y == obj.y) return 0;
			else if(this.y < obj.y) return 1;
			else return -1;
		}
		else if(this.x < obj.x) return 1;
		else return -1;
	}//----------------------------------*/
	
	@Override
	public int compareTo(Point obj) {
		if(this.x == obj.x) {
				return obj.y-this.y; //y내림차순 정렬
		}else {
			return obj.x-this.x; //x내림차순 정렬
		}
	}
	
}////////////////////////////

public class Q18_PointSort {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		//스캐너를 이용해서 좌표를 여러개 입력받아서 
		System.out.println("좌표의 개수를 입력하세요 : ");
		int n=sc.nextInt();
		
		Point[] points=new Point[n];
		
		for(int i=0; i<n; i++) {
			System.out.println("x,y 좌표를 입력하세요 : ");
			int x=sc.nextInt();
			int y=sc.nextInt();
			points[i]=new Point(x,y);
		}
        Arrays.sort(points);

        // 정렬된 좌표 출력
        for (Point point : points) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
	}
	
}////////////////////////////

