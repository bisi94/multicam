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
	// x��ǥ �������� ����, x���� ������ y��ǥ ��������
	/*@Override
	public int compareTo(Point obj) {
		if(this.x == obj.x) {
			//y�� ��������
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
				return obj.y-this.y; //y�������� ����
		}else {
			return obj.x-this.x; //x�������� ����
		}
	}
	
}////////////////////////////

public class Q18_PointSort {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		//��ĳ�ʸ� �̿��ؼ� ��ǥ�� ������ �Է¹޾Ƽ� 
		System.out.println("��ǥ�� ������ �Է��ϼ��� : ");
		int n=sc.nextInt();
		
		Point[] points=new Point[n];
		
		for(int i=0; i<n; i++) {
			System.out.println("x,y ��ǥ�� �Է��ϼ��� : ");
			int x=sc.nextInt();
			int y=sc.nextInt();
			points[i]=new Point(x,y);
		}
        Arrays.sort(points);

        // ���ĵ� ��ǥ ���
        for (Point point : points) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
	}
	
}////////////////////////////

