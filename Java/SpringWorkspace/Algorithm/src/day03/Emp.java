package day03;

import java.util.Comparator;

//Comparator 인터페이스를 상속받는 클래스를 만들어 정렬할 때 적용
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
		return name+": "+height+"cm, 급여: "+salary;
	}
	
}/////////////////

class HeightOrderComparator implements Comparator<Emp>
{
	@Override
	public int compare(Emp e1, Emp e2) {
		if(e1.height == e2.height) {
			return 0;
		}else if(e1.height > e2.height) {//키 오름차순
			return 1; //양수면 자리바꿈
		}else{
			return -1;
		}
	}
	
}///////////////////////////

class SalaryOrderComparator implements Comparator<Emp>
{
	//급여 내림차순으로 정렬하도록 compare() 구현해보기
	@Override
	public int compare(Emp e1, Emp e2) {
		if(e1.salary == e2.salary)	return 0;
		else if(e1.salary < e2.salary) return 1;//양수면 자리바꿈
		else return -1;
		
		//return e1.salary-e2.salary; //내림차순
		
	}
	
}///////////////////////////////





