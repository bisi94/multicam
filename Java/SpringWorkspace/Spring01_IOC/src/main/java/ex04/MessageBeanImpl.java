package ex04;
import java.util.*;
public class MessageBeanImpl implements MessageBean {

	//property:멤버변수
	private String name; //default값:null <=property
	private String greeting;
	
	private Date today;
	private Random ran;

	public void setRan(Random ran) {
		this.ran = ran;
	}

	//setter, getter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public Random getRan() {
		return ran;
	}
	
	@Override
	public void sayHello() {
		System.out.println(greeting+" "+name+"^^");
		System.out.println("오늘은 "+today.toString()+"입니다~");
		System.out.println("----------------------------");
	}//----------------------------------------------

	public void sayHi(String ... names) {
		System.out.println(greeting);
		if(names!=null) {
			for(String name:names) {
				System.out.printf(name+", ");
			}//for
		}//if
		int num=ran.nextInt(100)+1;
		System.out.println("\n행운의 숫자: "+num);
	}//--------------------------------

	}










