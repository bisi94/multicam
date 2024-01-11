package user.model;
import java.util.*;

public class MemberVO {
	
	private int no;
	private String name;
	private String userid;
	private String userpw;
	private String tel;
	
	//constructor
	public MemberVO() {
		
	}
	
	//MemberVO 오버로드
	public MemberVO(int no, String name, String userid, String userpw, String tel) {
		super();
		this.no = no;
		this.name = name;
		this.userid = userid;
		this.userpw = userpw;
		this.tel = tel;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", name=" + name + ", userid=" + userid + ", userpw=" + userpw + ", tel=" + tel
				+ "]";
	}
	
	/**회원정보를 입력받아 멤버변수에 저장하는 메소드*/
	public void input() {
		Scanner sc=new Scanner(System.in);
		System.out.println("이 름: ");
		String name=sc.nextLine();
		this.setName(name);
		//this.name=name;
		
		System.out.println("아이디: ");
		String uid=sc.nextLine();
		setUserid(uid);
		
		System.out.println("비밀번호: ");
		String upw=sc.nextLine();
		setUserpw(upw);
		
		System.out.println("연락처: ");
		String tel=sc.nextLine();
		setTel(tel);
	}//---------------------------------------
	
	
	//setter, getter

}//////////////////////////


















