package user.model;
//DAO Data Access Object(CURD기능)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.db.DBUtil;

public class MemberDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	/*
	 * String : 불변성(immutable) 원본을 편집하지 않는다 문자열을 편집해야 한다면 
	 * 새로운 문자열 객체를 만들어 사용한다
	 * String str="Hello "; str+="Java"; str+="Bye bye";
	 * StringBuffer ==> 문자열을 편집하는 클래스 
	 * StringBuilder
	 * 		- append():문자열을 덧붙인다
	 */
	
	//Create-INSERT
	public int insert(MemberVO vo) throws SQLException {
		try{
			con=DBUtil.getCon();
			int n=0;
			StringBuffer buf=new StringBuffer("INSERT INTO member")
						.append(" VALUES(?,?,?,?,?,?,?,?,?, sysdate, 1000, 0)");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getUserpwd());
			ps.setString(4, vo.getHp1());
			ps.setString(5, vo.getHp2());
			ps.setString(6, vo.getHp3());
			ps.setString(7, vo.getPost());
			ps.setString(8, vo.getAddr1());
			ps.setString(9, vo.getAddr2());
			
			n=ps.executeUpdate();
			
			return n;
		}finally {
			close();
		}
		
		
	}//-------------------------
	/** 회원가입 관련 - 아이디 중복 체크*/
	public boolean idCheck(String userid) throws SQLException{
		try {
			boolean flag=false;
			con=DBUtil.getCon();
			StringBuffer buf=new StringBuffer("select count(userid) cnt")
					.append(" from member where userid=?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			boolean b=rs.next();
			int cnt=rs.getInt("cnt");
					
			flag=(cnt>0)? false:true;
			
			return flag;
		}finally {
			close();
		}
	}
	
	
	public int delete(int no) throws SQLException {
		try {
			con=DBUtil.getCon();
			
			int n=0;
			
			return n;
		}finally {
			close();
		}
	}//------------------------
	
	//회원의 상태정보(mstate)를 수정하는 메소드
	public int manageMstate(String userid, int mstate) throws SQLException{
		
		try {
			con=DBUtil.getCon();
			int n=0;
			StringBuffer sub=new StringBuffer("UPDATE member SET mstate=? WHERE userid=?");
			String sql=sub.toString();
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, mstate);
			ps.setString(2, userid);
			
			n=ps.executeUpdate();
			
			return n;
		}finally {
			close();
		}
	}//----------------------------------------------------
	
	public int update(String loginId, MemberVO vo) throws SQLException {
		try {
			con=DBUtil.getCon();
			int n=0;
			StringBuilder buf=new StringBuilder("update member set name=?,")
										.append(" userid=?, userpwd=?,")
										.append(" hp1=?, hp2=?, hp3=?,")
										.append(" post=?, addr1=?, addr2=?")
										.append(" where userid=?");
			String sql=buf.toString();
			System.out.println(sql);
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getUserid());//중복체크에서 들어온 아이디
			ps.setString(3, vo.getUserpwd());
			ps.setString(4, vo.getHp1());
			ps.setString(5, vo.getHp2());
			ps.setString(6, vo.getHp3());
			ps.setString(7, vo.getPost());
			ps.setString(8, vo.getAddr1());
			ps.setString(9, vo.getAddr2());
			ps.setString(10, loginId); //where절에서 사용할 아이디
			
			n=ps.executeUpdate();
			
			return n;
		}finally {
			close();
		}
		
	}//------------------------
	
	//다중행 결과 가져오는 경우
	/*SELECT MEMBER.*,
		DECODE(mstate, 0,'활동회원', -1,'정지회원', -2,'탈퇴회원', 9,'관리자') mstateSTR
		FROM MEMBER ORDER BY INDATE DESC;*/
	public ArrayList<MemberVO> selectAll() throws SQLException{
		try {
			ArrayList<MemberVO> arr=new ArrayList<>();//table역할
			con=DBUtil.getCon();
			StringBuffer buf=new StringBuffer("SELECT member.*,")
					.append(" DECODE(mstate, 0,'활동회원', -1,'정지회원', -2,'탈퇴회원', 9,'관리자') mstateSTR")
					.append(" FROM MEMBER ORDER BY INDATE DESC");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			arr=makeList(rs);
			
			return arr;
		}finally {
			close();
		}
		
	}//----------------------------
	
	private ArrayList<MemberVO> makeList(ResultSet rs) throws SQLException{//제너릭 memberVO
		ArrayList<MemberVO> arr=new ArrayList();
		while(rs.next()) {
			String name=rs.getString("name");
			String userid=rs.getString("userid");
			String userpwd=rs.getString("userpwd");
			String hp1=rs.getString("hp1");
			String hp2=rs.getString("hp2");
			String hp3=rs.getString("hp3");
			String post=rs.getString("post");
			String addr1=rs.getString("addr1");
			String addr2=rs.getString("addr2");
			java.sql.Date indate=rs.getDate("indate");
			int mileage=rs.getInt("mileage");
			int mstate=rs.getInt("mstate");
			String mstateStr=rs.getString("mstateStr");
			
			MemberVO user=new MemberVO(name, userid, userpwd, hp1, hp2, hp3, post, addr1, addr2, indate, mileage, mstate, mstateStr);
			
			arr.add(user);
			
		}//while---------
		
		return arr;
	}
	
	//단일행 결과를 가져오는 경우 - where절 PK를 조건으로 가져올 경우
	public MemberVO selectOne(int no) throws SQLException{
		try {
			con=DBUtil.getCon();
			MemberVO user=null;
			
			return user;
			
		}finally {
			close();
		}
		
	}//----------------------------
	/*
	 * 탈퇴회원을 제외한 memberView를 만들자
	 * ************************************
	CREATE OR REPLACE VIEW memberView
	as
	select member.*, 
	decode(mstate,0,'활동회원',-1,'정지회원',-2,'탈퇴회원',9,'관리자') mstateStr
	from member
	where mstate >-2;
	***************************************
	*/
	
	
	//단일행 결과를 가져오는 경우 - where절 UK를 조건으로 가져올 경우
	public MemberVO selectByUserid(String userid) throws SQLException{
	
		try {
			con=DBUtil.getCon();
			
			/*
			 * StringBuilder buf = new StringBuilder("SELECT member.*, ")
			 * .append(" DECODE(mstate, 0,'활동회원',-1,'정지회원',-2,'탈퇴회원',9,'관리자' ) mstateStr")
			 * .append(" FROM member") .append(" WHERE userid=?");
			 */
			
			StringBuilder buf = new StringBuilder("SELECT * FROM MEMBERVIEW WHERE userid=?");
			
	        String sql = buf.toString();
	        
	        ps = con.prepareStatement(sql);
	        ps.setString(1, userid);
	        rs = ps.executeQuery();

	        ArrayList<MemberVO> arr=makeList(rs);
	        
	        MemberVO user=null;
	        if(arr!=null&&arr.size()==1) {
	        	user=arr.get(0);
	        }
			return user;
		}finally {
			close();
		}
	}//----------------------------
	
	public int loginCheck(String userid, String userpwd) throws SQLException{
		try {
			MemberVO dbUser=this.selectByUserid(userid);
			if(dbUser==null) { //아이디가 틀리면
				return -1;
			}
			//아이디가 일치한다면 비밀번호 체크
			if(dbUser.getUserpwd().equals(userpwd)) {
				//관리자인지 체크
				int mstate=dbUser.getMstate(); 
				if(mstate==9) {
					return 9; //관리자면 9를 반환하고
				}
				
				return 1;//id and pw 일치하고 일반유저라면 1반환
				
			}
			return -1; //pw wrong 
		
			
		}finally {
			//close();
		}
		
	}//----------------------------
	

	public void close() throws SQLException{
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close();
	}
	
}









