package day03;

import java.util.Arrays;
import java.util.Scanner;

/*�����˻�
 * - ����Ʈ, �迭�� �̿�
 * - ���ĵǾ� �ִ� �ڷᱸ���� �ؾ� ��
 * - O(log n)
 * - 100���� �����Ͱ� �ִٸ� O(log 100) ==>�־��� ��� 7������ �˻��� ��ĥ �� ����
 * */
public class BinarySearch {

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
		
		int idx=binarySearch(arr, n, key);
		System.out.println("�˻��� "+key+"�� �ε���: "+idx);
	}//------------------------------------------
	
	public static int binarySearch(int[] arr, int n, int key) {
		Arrays.sort(arr);//������������ �����ϰ� �����ϱ�
		System.out.println("------------�������� ���� ��-----------");
		System.out.println(Arrays.toString(arr));
		System.out.println("----------------------------------");
		int left=0; //�迭 �� �� �ε��� �Ҵ�
		int right=n-1; //�迭 �� �ε��� �Ҵ�
		int center=(left+right)/2; //�߾��� �ε��� ���ϴ� �뵵
		int cnt=0;
		while(left<=right) {
			cnt++;
			//�˻��ϸ� �ش� �ε�����ȣ ��ȯ
			if(arr[center]==key) {
				System.out.println(cnt+"�� ���� ������ϴ�");
				return center;
			}else if(arr[center] < key) {
				left=center+1;
			}else if(arr[center] > key) {
				right=center-1;
			}
			center=(left+right)/2;
		}//while-----------------------------------
		return -1;
	}//--------------------------------------------

}////////////////////////////////////////////////////////////









