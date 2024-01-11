package day03;
import java.util.*;
public class LinearSearchSentinel {

	
	//�������� 1,2�� ��� �Ǵ��ؾ� �� ==> ����� ������ ���̴� ����� ���ʹ�(Sentinel)
	public static int searchSentinel(int[] arr, int n, int key) {
		//���ʰ��� �迭 �������� ����
		arr[n]=key;
		
		System.out.println("-----������ ���� ���ʰ� : ���� ��-----");
		System.out.println(Arrays.toString(arr));
		System.out.println("-----------------------------");
		int idx=0;
		while(true) {
			//if(idx==n) return -1;
			if(arr[idx]==key) {
				break;
			}
			idx++;
		}//while----------------------------
		
		return(idx==n)? -1:idx;
	}//---------------------------------------
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("�迭 ũ�� �Է�: ");
		int n=sc.nextInt();
		
		int arr[]=new int[n+1];//���ʰ��� �����ϱ� ���� �迭ũ��+1 �� �غ��Ѵ�
		
		System.out.println("������ ������ �Է�: ");
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}//for----------------------------------
		System.out.println("-----������ ���� ���ʰ� : ���� ��-----");
		System.out.println(Arrays.toString(arr));
		System.out.println("-----------------------------");
		
		System.out.println("�˻��� ������ �Է�: ");
		int key=sc.nextInt();
		
		int idx=searchSentinel(arr, n, key);
		
		String msg=(idx<0)? key+"���� �迭�� �����ϴ�":key+"���� �迭 �ε��� "+idx+"���� �ֽ��ϴ�";
		System.out.println(msg);
		
	}

}
