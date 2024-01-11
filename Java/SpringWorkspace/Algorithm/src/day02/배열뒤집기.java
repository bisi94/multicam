package day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 배열뒤집기 {

	public static void main(String[] args) {
		int arr[]= {1,3,1,1,1,2};
		Solution1 s=new Solution1();
		int brr[]=s.solution(arr);
		
		System.out.println(Arrays.toString(brr));
		
		Integer crr[]=s.solution2(arr);
		System.out.println(Arrays.toString(crr));
	}

}//////////////////////////////////////////
class Solution1 {
	//java.util.Collection의 reverse(List)
	//java.util.Collection의 sort(List)
	//java.util.Arrays의 sort(배열)
	public Integer[] solution2(int[] num_list) {
		//배열을 ArrayList로 옮긴다
		//List<int[]> arrList=Arrays.asList(num_list);
		List<Integer> arrList=new ArrayList<Integer>();
		for(int num: num_list) {
			arrList.add(num);
		}
		System.out.println(arrList);
		
		//Collections.reverse(arrList);//역순 정렬
		Collections.sort(arrList);// 오름차순 정렬
		System.out.println(arrList);
		
		Integer[] answer=new Integer[num_list.length];
		arrList.toArray(answer);
		
		return answer;
	}
	
    public int[] solution(int[] num_list) {
        int[] answer = new int[num_list.length]; //선언, 메모리할당
        
        for(int i=0;i<num_list.length;i++) {
        	answer[i]=num_list[num_list.length-1-i];
        }
        
        return answer;
    }
}//////////////////////////////////////////