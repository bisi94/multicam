package day03;

import java.util.Comparator;

//Comparator �������̽��� ��ӹ޴� Ŭ������ ����� ������ �� ����
public class Emp {
	
	String name;
	int height;
	int salary;
	
	public Emp() {
		
	}
	
	public Emp(String n, int h, int s) {
		name=n; height=h; salary=s;
	}
	
	public String toString() {
		return name+": "+height+"cm, �޿�: "+salary;
	}
	
}/////////////////

class HeightOrderComparator implements Comparator<Emp>
{
	@Override
	public int compare(Emp e1, Emp e2) {
		if(e1.height == e2.height) {
			return 0;
		}else if(e1.height > e2.height) {//Ű ��������
			return 1; //����� �ڸ��ٲ�
		}else{
			return -1;
		}
	}
	
}///////////////////////////

class SalaryOrderComparator implements Comparator<Emp>
{
	//�޿� ������������ �����ϵ��� compare() �����غ���
	@Override
	public int compare(Emp e1, Emp e2) {
		if(e1.salary == e2.salary)	return 0;
		else if(e1.salary < e2.salary) return 1;//����� �ڸ��ٲ�
		else return -1;
		
		//return e1.salary-e2.salary; //��������
		
	}
	
}///////////////////////////////





