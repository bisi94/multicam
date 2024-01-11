package day05;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int arr[]= {6,4,8,3,1,9,7};
		System.out.println(Arrays.toString(arr));
		//selectionSort(arr);
		selectionSortDesc(arr);
		System.out.println("----�������� ��----------------");
		System.out.println(Arrays.toString(arr));
	}//main()---------------------------
	//��������: �ּ�(�Ǵ� �ִ�) ���� �����ؼ� �ش� ���� �� ��ġ���� ������ �δ� ����
	public static void selectionSort(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			//�ּҰ��� �ִ°��� �ε�����ȣ�� �˾Ƴ���
			int min=i;
			for(int j=i+1;j<arr.length;j++) {
				//�ּҰ��� �ִ� ��ġ�� ã�Ƴ���
				if(arr[min]>arr[j]) {
					min=j;
				}
			}//for-------
			int tmp=arr[min];
			arr[min]=arr[i];
			arr[i]=tmp;
		}//for-------------------
	}//---------------------------------
	
	//���������� �ϵ� ������������ �����ϼ���
	public static void selectionSortDesc(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			//�ִ밪�� �ִ°��� �ε�����ȣ�� �˾Ƴ���
			int max=i;
			for(int j=i+1;j<arr.length;j++) {
				//�ּҰ��� �ִ� ��ġ�� ã�Ƴ���
				if(arr[max]<arr[j]) {
					max=j;
				}
			}//for-------
			int tmp=arr[max];
			arr[max]=arr[i];
			arr[i]=tmp;
		}//for-------------------
	}//---------------------------------

}////////////////////////////////












