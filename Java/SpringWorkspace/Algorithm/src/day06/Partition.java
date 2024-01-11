package day06;

import java.util.Arrays;

/*�� ���� �˰����� ���캸�� ���� �ǹ��� �ϳ� ���� 2�׷����� ������ �����͸� ��ȯ�ϴ� �۾��� �����غ���.
 * */
public class Partition {

	public static void main(String[] args) {
		int[] arr= {1, 8, 7, 4, 5, 2, 6, 3, 9};
		System.out.println(Arrays.toString(arr));
		partition(arr);
		System.out.println("----��Ƽ�� 1�� ������ ��------------");
		System.out.println(Arrays.toString(arr));
	}//-------------------------

	private static void partition(int[] arr) {
		int pl=0;//Ŀ��, ������, ���� Ŀ���� 1�� ����  ==> (pl<=pr)������ �����Ҷ� ����
		int pr=arr.length-1;//������ Ŀ���� 1�� ����  ==>(pl<=pr)������ �����Ҷ� ����
		int pivot=arr[arr.length/2];
		do {
			//���� ���� Ŀ���� ���� �̵���Ű�� 
			while(arr[pl] <  pivot) pl++;
			//������ Ŀ���� �̵���Ų��
			while(arr[pr] >pivot) pr--;
			if(pl<=pr) {
				swap(arr, pl, pr);//������ ��ȯ
				pl++;//������ ��ȯ�� ��� Ŀ�� �̵�
				pr--;
			}
			System.out.println("pivot��: "+pivot);
		}while(pl<=pr);
	}//---------------------------

	private static void swap(int[] arr, int pl, int pr) {
		int tmp=arr[pl];
		arr[pl]=arr[pr];
		arr[pr]=tmp;
	}//-----------------------------------

}//////////////////////////////////////





