package day04;

public class MyArrayList<E> {
	
	private Object[] data;
	private int capacity;
	private int index;
	
	public MyArrayList() {
		this(5);
	}//-----------------------
	public MyArrayList(int capa) {
		this.capacity=capa;
		data=new Object[capacity];
		index=0;
	}//-----------------------
	//����Ʈ�� ������ �����ͼ� ��ȯ
	public int size() {
		return index;
	}//----------------------
	
	//������ ����
	public void add(E obj) {
		if(index >= capacity-1) {//�����Ͱ� �� á�ٸ� ==> �ι�� ������Ű��
			doubling();
		}
		data[index++]=obj;
	}//------------------------
	private void doubling() {
		this.capacity=this.capacity*2;
		//2�� �뷮�� ���ο� �迭 ����
		Object[] newData=new Object[this.capacity];
		//���� �迭(data)�� ����� �����͸� ���ο� �迭(newData)�� �ű���
		for(int i=0;i<data.length;i++) {
			newData[i]=data[i];
		}
		this.data =newData;
		//������� data�� ���θ��� �迭�� �ּҰ��� �Ҵ�
		System.out.printf("index: %d, capacity: %d, size: %d%n", index, capacity, size());
	}//---------------------------
	//������ ������
	public E get(int i) {
		if(i>=index) {
			throw new ArrayIndexOutOfBoundsException();
		}else if(i<0) {
			throw new RuntimeException("Negative Index Value");
		}
		Object val=data[i];
		return (E)val;
	}//-----------------------------
	
	//Ư�� �ε����� ������ ����
	public void remove(int i) {
		if(i>=index) {
			throw new ArrayIndexOutOfBoundsException();
		}else if(i<0) {
			throw new RuntimeException("Negative Index Value");
		}
		for(int k=i;k< data.length-1;k++) {
			data[k]=data[k+1];
		}
		index--;
		
	}//---------------------------
	
	
	

}/////////////////////////////















