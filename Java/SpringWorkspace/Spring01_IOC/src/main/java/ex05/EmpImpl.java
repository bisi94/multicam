package ex05;

import java.util.Random;

public class EmpImpl implements Emp {

	private String name;
	private String dept;
	private int sal;
	private Random ran;
	
	//생성자 오버로드
	public EmpImpl(String name, String dept, int sal) {
		this.name=name;
		this.dept=dept;
		this.sal=sal;
	}
	
	public EmpImpl(String name, String dept) {
		this(name, dept, 0);
	}
	
	public EmpImpl(String name) {
		this(name, "미소속", 0);
	}
	
	public EmpImpl(Random ran) {
		this.ran=ran;
	}
	
	@Override
	public void info1() {
		System.out.println("Name: "+name);
	}

	@Override
	public void info2() {
		this.info1();//Name
		System.out.println("Dept: "+dept);
	}

	@Override
	public void info3() {
		this.info2();//Name, Dept
		System.out.println("Salary: "+sal);
	}

	@Override
	public void info4() {
		this.info3();//Name, Dept, Sal
		System.out.println("Lucky Number: "+(ran.nextInt(100)+1));
	}

}
