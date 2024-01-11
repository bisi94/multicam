package ex06;

public class Emp {
	
	//property
	private String name, dept;
	private int sal;
	
	public Emp() {
		
	}

	public Emp(String name, String dept, int sal) {
		super();
		this.name = name;
		this.dept = dept;
		this.sal = sal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}
	
	
}//////////////////////////////////
