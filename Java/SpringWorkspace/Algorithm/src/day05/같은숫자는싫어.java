package day05;
import java.util.*;
public class 같은숫자는싫어 {

	public static void main(String[] args) {
		int[] arr= {4,4,4,3,3,1,5,6,6,9,9,1,9,9};
		Solution1 app=new Solution1();
		int[] result=app.solution(arr);
		
		System.out.println(Arrays.toString(result));
	}

}/////////////////////////////////
class Solution1 {
    public int[] solution(int []arr) {
        int[] answer = {};
        Stack<Integer> s=new Stack<>();
        s.push(arr[0]);
        for(int i=1;i<arr.length;i++) {
        	if(s.peek()!=arr[i]) {
        		s.push(arr[i]);
        	}
        }//for-------------
        answer=new int[s.size()];
        	
        for(int i=0;i<s.size();i++) {
        	answer[i]=s.get(i);
        }
        return answer;
    }
}///////////////////////////////////
