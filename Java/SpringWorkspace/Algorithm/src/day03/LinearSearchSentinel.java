package day03;
import java.util.*;
public class LinearSearchSentinel {

	
	//종료조건 1,2를 모두 판단해야 함 ==> 비용을 반으로 줄이는 방법이 보초법(Sentinel)
	public static int searchSentinel(int[] arr, int n, int key) {
		//보초값을 배열 마지막에 저장
		arr[n]=key;
		
		System.out.println("-----마지막 값은 보초값 : 저장 후-----");
		System.out.println(Arrays.toString(arr));
		System.out.println("-----------------------------");
		int idx=0;
		while(true) {
			//if(idx==n) return -1;
			if(arr[idx]==key) {
				break;
			}
			idx++;
		}//while----------------------------
		
		return(idx==n)? -1:idx;
	}//---------------------------------------
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("배열 크기 입력: ");
		int n=sc.nextInt();
		
		int arr[]=new int[n+1];//보초값을 저장하기 위해 배열크기+1 로 준비한다
		
		System.out.println("저장할 정수값 입력: ");
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}//for----------------------------------
		System.out.println("-----마지막 값은 보초값 : 저장 전-----");
		System.out.println(Arrays.toString(arr));
		System.out.println("-----------------------------");
		
		System.out.println("검색할 정수값 입력: ");
		int key=sc.nextInt();
		
		int idx=searchSentinel(arr, n, key);
		
		String msg=(idx<0)? key+"값은 배열에 없습니다":key+"값은 배열 인덱스 "+idx+"번에 있습니다";
		System.out.println(msg);
		
	}

}
