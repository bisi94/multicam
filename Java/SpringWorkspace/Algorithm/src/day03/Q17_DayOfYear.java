package day03;

import java.util.Scanner;

public class Q17_DayOfYear {

	Scanner sc=new Scanner(System.in);
	
	int[]mdays[]= {
			{31,28,31,30,31,30,31,31,30,31,30,31},//평년 월별 일수
			{31,29,31,30,31,30,31,31,30,31,30,31} //윤년 월별 일수
	};
	//mdays[0][1] => 28일 => 행의 인덱스가 0이면 평년,
	//mdays[1][1] => 29일 => 행의 인덱스가 1이면 윤년
	
	public void input_date() {
		int no=0;
		do {
			System.out.print("년도 : ");
			int year=sc.nextInt();
			
			System.out.print("월 : ");
			int month=sc.nextInt();
			
			System.out.print("일 : ");
			int day=sc.nextInt();
			System.out.printf("%d년 %d월 %d일의 일수를 구합니다\n", year, month, day);
			////////////////////////////////////////////
			int totalDay=getDayOfYear(year, month, day);
			////////////////////////////////////////////
			System.out.printf("%d년 %d일째 입니다\n", year, totalDay);
			System.out.println("한번 더 하시겠습니까? [1.yes 2.no]");
			no=sc.nextInt();
		}while(no!=2);	
		System.out.println("ByeBye~");
	}//------------------------------
	/* 윤년
	 * - 년도가 4로 나누어 떨어지면서 100으로는 나누어 떨어지면 안됨
	 * - 다만, 400으로 나누어 떨어지는 년도는 윤년임*/
	
	public static final int LEAP_YEAR=1;
	public static final int NORMAL_YEAR=0;
	
	public int isLeapYear(int yy) {
		if(yy%4==0 && yy%100!=0 || yy%400==0) {
			return LEAP_YEAR;
		}
		return NORMAL_YEAR;
	}
	
	public int getDayOfYear(int yy, int mm, int dd) {
		int total=0;
		int check=isLeapYear(yy);
		
		/*if(yy%4==0 && yy%100!=0 || yy%400==0) {
			check=1;
		}*/
		//mm-1의 일수 + dd
		for(int i=0; i<mm-1; i++) {
			total+=mdays[check][i];
			System.out.println((i+1)+"월 : "+mdays[check][i]+"일");
		}
		System.out.println(mm+"월 : "+dd+"일");
		total+=dd;
		return total;
	}//-----------------------------------------------
	
	public static void main(String[] args) {
		Q17_DayOfYear app=new Q17_DayOfYear();
		app.input_date();
	}
}
