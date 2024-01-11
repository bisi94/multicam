package day03;
import java.util.*;
public class LinearSearch {

	public static int search(int[] arr, int n, int key) {
		//�˻��� ���� ����Ǿ� �ִ� �ε��� ��ȣ ��ȯ
		//�˻��� ���� ���� ���� -1�� ��ȯ
		int idx=0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==key) {
				idx=i;
			}else if(i==arr.length-1) {
				idx=-1;
			}
		}
		
		return idx;
	}
	public static int search1(int[] arr, int n, int key) {
		int idx=0;
		
		for(; idx<n;idx++) {
			if(arr[idx]==key) {
				return idx;
			}
		}//for------------------
		idx=-1;
		
		return idx;
	}
	
	
	public static int search2(int[] arr, int n, int key) {
		//�˻��� ���� ����Ǿ� �ִ� �ε��� ��ȣ ��ȯ
		//�˻��� ���� ���� ���� -1�� ��ȯ
		int idx=0;
		while(true) {
			if(idx==n) { //�Ǵ�1 : �˻��� ���� �߰����� ���ϰ� �迭�� ���� ���� ��
				return -1;
			}
			
			if(arr[idx]==key) { //�Ǵ�2 : �˻��� ���� �߰��� ���
				return idx;
			}
			idx++;
		}//while----------------------
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("�迭 ũ�� �Է�: ");
		int n=sc.nextInt();
		
		int arr[]=new int[n];
		System.out.println("������ ������ �Է�: ");
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}//for----------------------------------
		System.out.println(Arrays.toString(arr));
		
		System.out.println("�˻��� ������ �Է�: ");
		int key=sc.nextInt();
		
		int idx=search2(arr, n, key);
		
		String msg=(idx<0)? key+"���� �迭�� �����ϴ�":key+"���� �迭 �ε��� "+idx+"���� �ֽ��ϴ�";
		System.out.println(msg);
		
	}

}
