package day04;
//데이터를 저장 => enque => rear++
//데이터를 꺼내기 => deque ==> front ++
//큐 FIFO(선입선출)
public class MyQueue {
	
	private Object[] que;//배열 => 큐
	private int capacity;//큐의 크기
	private int front;//맨 앞 요소 커서
	private int rear;//맨 끝 요소 커서
	private int cnt;//현재 데이터 수
	
	public MyQueue(int capa) {
		this.capacity=capa;
		cnt=0;
		front=0;
		rear=0;
		que=new Object[capacity];//배열 메모리 할당
	}
	
	//데이터삽입=> 인큐. 인큐할때는 맨 뒤(rear)에서 들어간다
	public Object enque(Object val) {
		if(cnt>=this.capacity) throw new OverflowException();
		que[rear++]=val;
		cnt++;
		if(rear>=this.capacity) {
			rear=0;
		}
		return val;
	}//---------------------------
	
	//데이터꺼내기=>디큐. 디큐할때는 맨 앞(front)에서부터 나와야 한다. FIFO구조이므로
	public Object deque() {
		if(cnt<=0) throw new EmptyException();
		Object val=que[front++];
		cnt--;
		if(front>=this.capacity) {
			front=0;
		}
		return val;
	}//---------------------
	
	//front 데이터를 반환하는 메서드
	public Object peek() {
		if(cnt<=0) throw new EmptyException();
		return que[front];
	}
	
	//큐를 비우는 메서드
	public void clear() {
		cnt=0;
		front=0;
		rear=0;
	}//---------------------
	//현재 큐에 저장된 데이터 개수를 반환
	public int size() {
		return cnt;
	}//------------------------
	
	public boolean isEmpty() {
		return cnt<=0;
	}//-------------------------
	public boolean isFull() {
		return cnt>=this.capacity;
	}//------------------------
	
	//큐에 저장된 데이터를 출력하는 메서드 -FIFO==> front 커서를 활용해야 함
	public void printQueue() {
		for(int i=0;i<cnt;i++) {
			System.out.println(que[(i+front) %capacity]);
		}
	}//-------------------------
	//큐에서 검색하는 메서드
	public int indexOf(Object val) {
		for(int i=0;i<cnt;i++) {
			int index=(i+front)%capacity;
			if(que[index].equals(val)) {
				return index;
			}			
		}//for-------
		return -1;
	}//---------------------------
}///////////////////////////////



















