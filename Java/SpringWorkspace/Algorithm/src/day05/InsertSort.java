package day05;

import java.util.Arrays;

public class InsertSort {

	public static void main(String[] args) {
		int arr[]= {6, 4, 1, 7, 3, 9, 8};
		System.out.println(Arrays.toString(arr));
		insertSort2(arr);
		System.out.println("----�������� ��------------");
		System.out.println(Arrays.toString(arr));
	}//--------------------------

	private static void insertSort(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			int tmp=arr[i];//�ӽú����� ���� ==>����� �ڸ��� ã�ƺ���
			int j=i-1;
			for(;j>=0;j--) {
				if(arr[j] >  tmp) {
					arr[j+1]=arr[j];//�ڷ� �о��
				}else {
					break;
				}
			}//for---
			arr[j+1]=tmp;//tmp���� ����
			//������ ��ġ�� ã�Ҵٸ� ����
		}//for----------------
	}//-----------------------------
	
	private static void insertSort2(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			int tmp=arr[i];//�ӽú����� ���� ==>����� �ڸ��� ã�ƺ���
			int j=i-1;
			for(;j>=0 && arr[j]>tmp  ;j--) {				
				arr[j+1]=arr[j];//�ڷ� �о��				
			}//for---
			arr[j+1]=tmp;//tmp���� ����
			//������ ��ġ�� ã�Ҵٸ� ����
		}//for----------------
	}//-----------------------------
	
	

}///////////////////////////////////////
