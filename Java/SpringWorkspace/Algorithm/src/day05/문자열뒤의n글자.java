package day05;

import java.util.Stack;

public class 문자열뒤의n글자 {

	public static void main(String[] args) {
		String str="He110W0r1d";
		int n=5;
		Solution2 app=new Solution2();
		String result=app.solution(str, n);
		System.out.println("result: "+result);
	}

}//////////////////////////
class Solution2 {
    public String solution(String my_string, int n) {
        String answer = "";
        int cnt=0;
        
        Stack<String> sk=new Stack<>();
        
        for(int i=0;i<my_string.length();i++) {
        	if(cnt<n) {
	        	char ch=my_string.charAt(my_string.length()-1 -i);
	        	sk.push(ch+"");
	        	cnt++;
        	}
        }//for----------------
        
        while(!sk.isEmpty()) {
        	answer+=sk.pop();
        }
        return answer;
    }
}
