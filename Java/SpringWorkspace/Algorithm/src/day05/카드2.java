package day05;
import java.util.*;
public class ī��2 {
	
	public int solution() {
		Queue<Integer> q=new LinkedList<>();
		//����: add(), offer()
		//�����Ͳ�����: poll()
	
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
			//ť���� ������
			q.poll();
			//ť���� �������� �ٽ� ť�� �ְ�
			int card2=q.poll();
			q.offer(card2);
		}
		answer=q.poll();
		return answer;
	}

	public static void main(String[] args) {

		ī��2 app=new ī��2();
		int result=app.solution();
		System.out.println("���� ī�� 1��: "+result);
	}

}
