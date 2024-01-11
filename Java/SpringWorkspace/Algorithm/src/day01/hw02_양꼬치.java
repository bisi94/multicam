package day01;

public class hw02_�粿ġ {

	public static void main(String[] args) {
		
		int n=32;
		int k=14;
		Solution2 sol=new Solution2();
		int result = sol.solution(n, k);
		System.out.println(result);
		
		
	}
	
}////////////////////////////////////

class Solution2 {
    public int solution(int n, int k) {
        int answer = 0;
        int disc_k=0;
        if(n<10) {
        	answer = n*12000 + k*2000;
        }else if(n>=10 && n<1000 && k>disc_k && k<1000) {
        	disc_k=n/10;
        	answer = n*12000 + (k-disc_k)*2000;   
        }
        
        return answer;
    }
}