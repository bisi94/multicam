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
	//리스트에 저장한 데이터수 반환
	public int size() {
		return index;
	}//----------------------
	
	//데이터 저장
	public void add(E obj) {
		if(index >= capacity-1) {//데이터가 꽉 찼다면 ==> 두배로 증가시키자
			doubling();
		}
		data[index++]=obj;
	}//------------------------
	private void doubling() {
		this.capacity=this.capacity*2;
		//2배 용량의 새로운 배열 생성
		Object[] newData=new Object[this.capacity];
		//기존 배열(data)에 저장된 데이터를 새로운 배열(newData)에 옮기자
		for(int i=0;i<data.length;i++) {
			newData[i]=data[i];
		}
		this.data =newData;
		//멤버변수 data에 새로만든 배열의 주소값을 할당
		System.out.printf("index: %d, capacity: %d, size: %d%n", index, capacity, size());
	}//---------------------------
	//데이터 꺼내기
	public E get(int i) {
		if(i>=index) {
			throw new ArrayIndexOutOfBoundsException();
		}else if(i<0) {
			throw new RuntimeException("Negative Index Value");
		}
		Object val=data[i];
		return (E)val;
	}//-----------------------------
	
	//특정 인덱스의 데이터 삭제
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















