package day03;
//객체를 비교할 수 있도록 만들기 위해서는 Comparable인터페이스를 상속받아 compareTo()메서드를 재정의해야 한다
//또는 Comparator인터페이스를 상속받아 compare()메서드를 재정의해야 한다.
/* Comparable과 Comparator의 차이는?
[1] Comparable의 compareTo(T o) 메소드 
[2] Comparator의 compare(T o1, T o2) 메소드

- Comparable은 "자기 자신과 매개변수 객체를 비교"하는 것이고, 
- Comparator는 "두 매개변수 객체를 비교"한다는 것이다.

쉽게 말하자면, Comparable은 자기 자신과 파라미터로 들어오는 객체를 비교하는 것이고, 
Comparator는 자기 자신의 상태가 어떻던 상관없이 파라미터로 들어오는 두 객체를 
비교하는 것이다. 즉, 본질적으로 비교한다는 것 자체는 같지만, 비교 대상이 다르다는 것이다.
* */
public class Member implements Comparable<Member> {
	private String name;
	private int age;
	private int height;
	
	public Member() {
		
	}
	public Member(String n, int a, int h) {
		//setter역할 : 값을 넣을 때 Member(String n, int a, int h)형식의 생성자를 호출
		name=n; age=a; height=h;
	}
	
	//회원의 나이를 기준으로 오름차순 정렬
	//this.age와 매개변수로 들어온 obj.age가 같다면 0을 반환
	@Override
	public int compareTo(Member obj) {
		//양수를 반환하면 비교대상들의 자리를 바꾼다. 음수면 부동
		if(this.age==obj.age) {
			return 0;
		}else if(this.age > obj.age) { //this.age > obj.age일때 양수반환 => 오름차순 정렬
			return 1;
		}else {
			return -1;
		}
		
		//this.age-obj.age; => 오름차순 (this가 무조건 비교주체)
		
		//이름 가나다 순으로 정렬하고 싶다면 => return this.name.compareTo(obj.name);
		//return this.name.compareTo(obj.name)*-1;//이름 가나다 내림차순으로 정렬
		
	}//----------------------------------------
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member user=(Member)obj;
			if(this.name.equals(user.name) 
				&& this.age==user.age
				&& this.height==user.height) {
				return true;
			}
				
			
		}//if--------------------------
		return false;
	}//--------------------------------
	
	@Override
	public String toString() {//getter역할
		return "Member [name=" + name + ", age=" + age + ", height=" + height + "]";
	}


}//////////////////////









