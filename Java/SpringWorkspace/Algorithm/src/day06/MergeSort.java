package day06;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int arr[]= {5,6,4,8,3,7,9,0,1,5,2,3};
		System.out.println(Arrays.toString(arr));
		partition(arr,0,arr.length-1);
		System.out.println("----���� ���� ����---------");
		System.out.println(Arrays.toString(arr));
	}//-------------------------------------

	private static void partition(int[] arr, int left, int right) {
		if(left == right) return;//��������. �����ϴ� ���� left���� right�� �������� ������ ����
		
		int mid=(left+right)/2; //�߰��� ���ϱ�
		partition(arr, left, mid); //���ݺ� ����
		partition(arr, mid+1, right); //�Ĺݺ� ����
		merge(arr, left, right); //2���� ���� �迭�� �������ִ� �޼ҵ带 ȣ��
		
	}//-----------------------------------

	private static void merge(int[] arr, int left, int right) {
		int tmp[]=new int[arr.length];
		int index=0;//tmp�迭���� ����� �ε���
		
		int pc=(left+right)/2;
		int pl=left;
		int pr=pc+1;
		index=left;
		
		while(pl<=pc && pr<=right) {
			//�� �迭 �� ���� ���� tmp�迭�� �־��ش�
			
			/*if(arr[pl] < arr[pr]) {
				tmp[index++]=arr[pl++];
			}else {
				tmp[index++]=arr[pr++];
			}*/
			tmp[index++]=(arr[pl] < arr[pr])? arr[pl++]:arr[pr++];
		}//while--------------------------
		
		//���� �͵��� ������ �ű���
		int i;
		if(pl>pc) {//���� �����Ͱ� �߾��� �Ѿ�ٸ� == �����ʿ� �����Ͱ� �������
			for(i=pr; i<=right; i++) {
				tmp[index++]=arr[i];
			}
		}else {//���ʿ� �����Ͱ� ���� ���
			for(i=pl; i<=pc; i++) {
				tmp[index++]=arr[i];
			}
		}
		for(i=left; i<=right; i++) {
			arr[i]=tmp[i];
		}
		System.out.println(left+"/"+right);
		System.out.println(Arrays.toString(tmp));
		System.out.println(Arrays.toString(arr));
		System.out.println("************************");
	}//-----------------------------------
	
	
}








