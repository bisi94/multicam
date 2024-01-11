package day04;
//�����͸� ���� => enque => rear++
//�����͸� ������ => deque ==> front ++
//ť FIFO(���Լ���)
public class MyQueue {
	
	private Object[] que;//�迭 => ť
	private int capacity;//ť�� ũ��
	private int front;//�� �� ��� Ŀ��
	private int rear;//�� �� ��� Ŀ��
	private int cnt;//���� ������ ��
	
	public MyQueue(int capa) {
		this.capacity=capa;
		cnt=0;
		front=0;
		rear=0;
		que=new Object[capacity];//�迭 �޸� �Ҵ�
	}
	
	//�����ͻ���=> ��ť. ��ť�Ҷ��� �� ��(rear)���� ����
	public Object enque(Object val) {
		if(cnt>=this.capacity) throw new OverflowException();
		que[rear++]=val;
		cnt++;
		if(rear>=this.capacity) {
			rear=0;
		}
		return val;
	}//---------------------------
	
	//�����Ͳ�����=>��ť. ��ť�Ҷ��� �� ��(front)�������� ���;� �Ѵ�. FIFO�����̹Ƿ�
	public Object deque() {
		if(cnt<=0) throw new EmptyException();
		Object val=que[front++];
		cnt--;
		if(front>=this.capacity) {
			front=0;
		}
		return val;
	}//---------------------
	
	//front �����͸� ��ȯ�ϴ� �޼���
	public Object peek() {
		if(cnt<=0) throw new EmptyException();
		return que[front];
	}
	
	//ť�� ���� �޼���
	public void clear() {
		cnt=0;
		front=0;
		rear=0;
	}//---------------------
	//���� ť�� ����� ������ ������ ��ȯ
	public int size() {
		return cnt;
	}//------------------------
	
	public boolean isEmpty() {
		return cnt<=0;
	}//-------------------------
	public boolean isFull() {
		return cnt>=this.capacity;
	}//------------------------
	
	//ť�� ����� �����͸� ����ϴ� �޼��� -FIFO==> front Ŀ���� Ȱ���ؾ� ��
	public void printQueue() {
		for(int i=0;i<cnt;i++) {
			System.out.println(que[(i+front) %capacity]);
		}
	}//-------------------------
	//ť���� �˻��ϴ� �޼���
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



















