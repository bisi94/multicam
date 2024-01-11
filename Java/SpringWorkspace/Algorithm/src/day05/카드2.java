package day05;
import java.util.*;
public class 카드2 {
	
	public int solution() {
		Queue<Integer> q=new LinkedList<>();
		//삽입: add(), offer()
		//데이터꺼내기: poll()
	
		q.offer(31);
		q.offer(25);
		q.offer(33);
		q.offer(45);
		q.offer(100);
		q.offer(99);
		q.offer(8);
		System.out.println("q.size(): "+q.size());
		int answer=0;
		while(q.size()>1) {
			//큐에서 꺼내고
			q.poll();
			//큐에서 꺼낸것을 다시 큐에 넣고
			int card2=q.poll();
			q.offer(card2);
		}
		answer=q.poll();
		return answer;
	}

	public static void main(String[] args) {

		카드2 app=new 카드2();
		int result=app.solution();
		System.out.println("남은 카드 1장: "+result);
	}

}
