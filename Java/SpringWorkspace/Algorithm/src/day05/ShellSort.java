package day05;

import java.util.Arrays;

/*������ �˰����� �������� �˰����� ���� �ӵ��� �����ϱ� ���� ������� �˰���.
 * �������� �׷��� ������ �׷� �ȿ��� �� ������ �����ϰ�, �������� ���������� �����ϴ� �˰����̴�.
 * ���������� ����: ��κ��� ���ĵǾ� �ִ� ���¿� ����� �� �ӵ��� ���� ������
 *         ����: ������ ���� �ָ� �������� �̵�(����)�ϴ� Ƚ���� ����.
 *         ex) 1 2 3 4 5 0 6  ���� �迭�� ��� 0�� �����Ϸ��� �� 6ȸ�� ���� ��Ҹ� �̵����Ѿ� �Ѵ�.
 *�������� �ð����⵵�� O(n^1.25), �������� O(n^2)���ٴ� ������. but���������� ���� �ָ� ������ ��Ҹ� ��ȯ������         
 * */
public class ShellSort {

	public static void main(String[] args) {
		int arr[]= {23,12,45,55,2,18,49,7};
		System.out.println(Arrays.toString(arr));
		//intervalSort(arr);
		intervalSort2(arr);
		System.out.println("----�� ���� ����-----------");
		System.out.println(Arrays.toString(arr));
	}//-------------------------

	private static void intervalSort(int[] arr) {
		//interval: 4 2 1 ������ �ǳʶٱ� �ϸ鼭 ���������� �����Ѵ� ==> ������
		for(int interval=arr.length/2;interval>0;interval=interval/2) {
			for(int k=interval;k<arr.length;k++) {
				int j;
				int tmp=arr[k];//arr[4]==>2
				System.out.println("interval: "+interval+", tmp: "+tmp);
				for(j=k-interval;j>=0;j-=interval) {
					System.out.printf("---{%d, %d}---\n", arr[j], tmp);
					if(arr[j] > tmp) {
						arr[j+interval] =arr[j];
					}else {
						break;
					}
				}//for---
				arr[j+interval]=tmp;
			}//for----
			System.out.println(Arrays.toString(arr));
			System.out.println("----------------");
		}//for------
	}//-------------------------
	//interval�� n����� ������Ű�� ���� �׷��� ���� ������ �ʰ� ���� ����� ������ ���� �ִ�
	//�̸� �����ϱ� ���� interval�� [3*n+1=> interval]
	public static void intervalSort2(int[] arr) {
		//interval�� �ʱⰪ�� ���غ���
		int inter;
		for(inter=1;inter<arr.length;inter=inter*3+1);//1 4 13
		System.out.println("inter: "+inter);
		
		for(;inter>0; inter/=3) {
			for(int k=inter;k<arr.length;k++) {
				System.out.println("k: "+k+", inter: "+inter);
				int j;
				int tmp=arr[k];
				for(j=k-inter;j>=0;j-=inter) {
					if(arr[j] >  tmp) {
						arr[j+inter]=arr[j];
					}else {
						break;
					}
				}//for--
				arr[j+inter]=tmp;//tmp ����
				
			}//for
			
		}//for--------------
	}//---------------------------------------------------
	
	
	

}/////////////////////////////////////




