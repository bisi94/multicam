package day01;
import java.util.*;

public class Q04_MaxHeight {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("키의 최대 최소값을 구합니다");
		System.out.println("사람수를 입력하세요: ");
		int num=sc.nextInt();
		int height[]=new int[num];//키를 저장할 배열 생성
		
		//Random 클래스를 이용해서 배열에 키 저장하기(100~190)
		Random ran=new Random();
		for(int i=0; i<height.length;i++) {
			height[i]=ran.nextInt(91)+100;
		}
		
		int arr[]=max_min(height);
		System.out.println("제일 큰 키: "+arr[0]);
		System.out.println("제일 작은 키: "+arr[1]);
	}
	
	public static int[] max_min(int[] height) {
		int[] answer= new int[2]; //최대키, 최소키
		
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
