package user.model;
import java.sql.Date;
import java.util.*;

public class MemberVO {
	
	private String name;
	private String userid;
	private String userpwd;
	private String hp1;
	private String hp2;
	private String hp3;
	
	private String post;
	private String addr1;
	private String addr2;
	
	private java.sql.Date indate;
	private int mileage;
	private int mstate; //0:일반회원, -1:정지회원, -2:탈퇴회원, 9:관리자
	private String mstateStr; //회원상태정보 문자열
	
	
	//constructor
	public MemberVO() {
		System.out.println("MemberVO생성자...");
	}


	public MemberVO(String name, String userid, String userpwd, String hp1, String hp2, String hp3, String post,
			String addr1, String addr2) {
		super();
		this.name = name;
		this.userid = userid;
		this.userpwd = userpwd;
		this.hp1 = hp1;
		this.hp2 = hp2;
		this.hp3 = hp3;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}


	public MemberVO(String name, String userid, String userpwd, String hp1, String hp2, String hp3, String post,
			String addr1, String addr2, Date indate, int mileage, int mstate, String mstateStr) {
		super();
		this.name = name;
		this.userid = userid;
		this.userpwd = userpwd;
		this.hp1 = hp1;
		this.hp2 = hp2;
		this.hp3 = hp3;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.indate = indate;
		this.mileage = mileage;
		this.mstate = mstate;
		this.mstateStr = mstateStr;
	}

	//setter, getter
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


	public String getUserpwd() {
		return userpwd;
	}


	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}


	public String getHp1() {
		return hp1;
	}


	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}


	public String getHp2() {
		return hp2;
	}


	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}


	public String getHp3() {
		return hp3;
	}


	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}


	public String getAddr1() {
		return addr1;
	}


	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}


	public String getAddr2() {
		return addr2;
	}


	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}


	public java.sql.Date getIndate() {
		return indate;
	}


	public void setIndate(java.sql.Date indate) {
		this.indate = indate;
	}


	public int getMileage() {
		return mileage;
	}


	public void setMileage(int mileage) {
		this.mileage = mileage;
	}


	public int getMstate() {
		return mstate;
	}


	public void setMstate(int mstate) {
		this.mstate = mstate;
	}
	
	public String getMstateStr() {
		return mstateStr;
	}

	public void setMstateStr(String mstateStr) {
		this.mstateStr = mstateStr;
	}
	
	public String getAllHp(){
		return hp1+"-"+hp2+"-"+hp3;
	}
	
	public String getAllAddr(){
		return "["+post+"] "+addr1+" "+addr2;
	}
	
	
}//////////////////////////


















