package day01;
import java.util.*;

public class Q04_MaxHeight {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Ű�� �ִ� �ּҰ��� ���մϴ�");
		System.out.println("������� �Է��ϼ���: ");
		int num=sc.nextInt();
		int height[]=new int[num];//Ű�� ������ �迭 ����
		
		//Random Ŭ������ �̿��ؼ� �迭�� Ű �����ϱ�(100~190)
		Random ran=new Random();
		for(int i=0; i<height.length;i++) {
			height[i]=ran.nextInt(91)+100;
		}
		
		int arr[]=max_min(height);
		System.out.println("���� ū Ű: "+arr[0]);
		System.out.println("���� ���� Ű: "+arr[1]);
	}
	
	public static int[] max_min(int[] height) {
		int[] answer= new int[2]; //�ִ�Ű, �ּ�Ű
		
		int max=height[0];
		System.out.println("-------------------------------");
		for(int h:height) {
			if(h>max) {
				max=h;
			}
		}//formax----------------
		
		int min=height[0];
		for(int h:height) {
			if(h<min) {
				min=h;
			}
		}//formin----------------
		
		answer[0]=max;
		answer[1]=min;
		
		return answer;
	}

}
