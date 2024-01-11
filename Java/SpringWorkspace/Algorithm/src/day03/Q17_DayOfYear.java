package day03;

import java.util.Scanner;

public class Q17_DayOfYear {

	Scanner sc=new Scanner(System.in);
	
	int[]mdays[]= {
			{31,28,31,30,31,30,31,31,30,31,30,31},//��� ���� �ϼ�
			{31,29,31,30,31,30,31,31,30,31,30,31} //���� ���� �ϼ�
	};
	//mdays[0][1] => 28�� => ���� �ε����� 0�̸� ���,
	//mdays[1][1] => 29�� => ���� �ε����� 1�̸� ����
	
	public void input_date() {
		int no=0;
		do {
			System.out.print("�⵵ : ");
			int year=sc.nextInt();
			
			System.out.print("�� : ");
			int month=sc.nextInt();
			
			System.out.print("�� : ");
			int day=sc.nextInt();
			System.out.printf("%d�� %d�� %d���� �ϼ��� ���մϴ�\n", year, month, day);
			////////////////////////////////////////////
			int totalDay=getDayOfYear(year, month, day);
			////////////////////////////////////////////
			System.out.printf("%d�� %d��° �Դϴ�\n", year, totalDay);
			System.out.println("�ѹ� �� �Ͻðڽ��ϱ�? [1.yes 2.no]");
			no=sc.nextInt();
		}while(no!=2);	
		System.out.println("ByeBye~");
	}//------------------------------
	/* ����
	 * - �⵵�� 4�� ������ �������鼭 100���δ� ������ �������� �ȵ�
	 * - �ٸ�, 400���� ������ �������� �⵵�� ������*/
	
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
		//mm-1�� �ϼ� + dd
		for(int i=0; i<mm-1; i++) {
			total+=mdays[check][i];
			System.out.println((i+1)+"�� : "+mdays[check][i]+"��");
		}
		System.out.println(mm+"�� : "+dd+"��");
		total+=dd;
		return total;
	}//-----------------------------------------------
	
	public static void main(String[] args) {
		Q17_DayOfYear app=new Q17_DayOfYear();
		app.input_date();
	}
}
