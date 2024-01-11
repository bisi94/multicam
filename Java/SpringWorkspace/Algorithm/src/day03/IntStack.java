package day03;
/*
#  ť�� ����
1. ť - Queue : Fisrt In First Out ����
    ex) �ý� �ټ��� : ���� ���� �� �� ����� ���� �ýø� ź��.
    - ť���� �����͸� �� �� ����ִ� ������ ��ť(enqueue),
      ť �ȿ� �ִ� �����͸� �� �� ������ ������ ��ť(dequeue)��� ǥ���Ѵ�.
2. ����- Stack : Last In First Out
    ex) ���� �ױ� :  ���ø� �׾� �ø���, ���� �� ���� ������ ���ú��� ������ ��
    - ���ÿ� ������ �ϳ� �ִ� ������ push,
      ���ÿ� �ִ� �����͸� �ϳ� ������ ������ pop��� ǥ���Ѵ�.
 */
public class IntStack {
	private int capacity; //�뷮
	private int ptr; //�迭�� �ε����� ���Ǵ� ������, push()�Ҷ��� ����, pop()�Ҷ��� ����
	private int stk[]; //�������� ������ �迭
	
	public IntStack(int capa) {
		this.capacity=capa;
		ptr=0;
		stk=new int[capacity];
	}//------------------------
	
	//���ÿ� ����Ǿ� �ִ� �����ͼ��� ��ȯ
	public int size() {
		return ptr;
	}
	
	/** �����͸� �����ϴ� �޼ҵ�, ����(�迭)�� �������� �߰��� �� ���� ���� ���ܸ� �߻���Ŵ*/
	public int push(int val) {
		if(ptr>=capacity) throw new RuntimeException("OverflowException");
		return stk[ptr++]=val;//push�� ���� ��ȯ�ϰ�, �����Ͱ��� �ϳ� ������Ų��
	}//-------------------------
	
	/**���ÿ��� �����(top)�� �ִ� �����͸� ��ȯ*/
	public int pop() {
		if(ptr<=0) throw new RuntimeException("EmptyException");
		return stk[--ptr];
	}//-------------------------
	
	/**���� ����⿡ �ִ� �����͸� ��ȯ�ϴ� �޼ҵ�. ���������� �ʴ´�*/
	public int peek() {
		if(ptr<=0) throw new RuntimeException("EmptyException");
		return stk[ptr-1];
	}//-------------------------
	
	//������ ����ִ��� ���θ� �Ǵ��ϴ� �޼ҵ�
	public boolean empty() {
		return ptr<=0;
	}//-------------------------
	
	//������ ���� á���� �Ǵ�
	public boolean isFull() {
		return ptr>=capacity;
	}//-------------------------
	
	//������ ��� �����͸� ���� �޼���
	public void clear() {
		ptr=0;
	}//------------------------
	
	//���ÿ� ����� ��Ұ����� ����ϴ� �޼ҵ�
	//LIFO������ �� => �Ʒ������� �����͸� ���
	public void printStack() {
		for(int i=ptr-1; i>=0; i--) {
			System.out.println(stk[i]);
		}
	}//------------------------
	
	public int search(int val) {
		for(int i=ptr-1; i>=0; i--) {
			if(stk[i]==val) {
				return i;
			}
		}//for----------------
		return -1;
	}//------------------------
	
}//////////////////////










