package user.model;
//DAO Data Access Object(CURD기능)
import java.sql.*;
import java.util.*;
import common.db.*;

public class MemberDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//Create-INSERT
	public int insert(MemberVO vo) throws SQLException {
		try{
			con=DBUtil.getCon();
			String sql="INSERT INTO java_member(no, name, userid, userpw, tel) VALUES(";
					sql+=" java_member_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getUserid());
			ps.setString(3, vo.getUserpw());
			ps.setString(4, vo.getTel());
			
			int n=ps.executeUpdate();
			
			return n;
		}finally {
			close();
		}
		
		
	}//-------------------------
	
	public int delete(int no) throws SQLException {
		try {
			con=DBUtil.getCon();
			String sql="DELETE FROM java_member WHERE no=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			int n=ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}//------------------------
	
	public int update(MemberVO vo) throws SQLException {
		try {
			con=DBUtil.getCon();
			String sql="UPDATE java_member SET name=?, userid=?, userpw=?, tel=? WHERE no=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getUserid());
			ps.setString(3, vo.getUserpw());
			ps.setString(4, vo.getTel());
			ps.setInt(5, vo.getNo());
			int n=ps.executeUpdate();
			return n;
		}finally {
			close();
		}
		
	}//------------------------
	
	//다중행 결과 가져오는 경우
	public ArrayList<MemberVO> selectAll() throws SQLException{
		try {
			ArrayList<MemberVO> arr=new ArrayList<>();//table역할
			con=DBUtil.getCon();
			String sql="SELECT no, name, userid, userpw, tel FROM java_member ORDER BY no DESC";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			/*while(rs.next()) {
				int no=rs.getInt("no");//컬럼 데이터 받아오기
				String name=rs.getString("name");
				String userid=rs.getString("userid");
				String userpw=rs.getString("userpw");
				String tel=rs.getString("tel");
				MemberVO record=new MemberVO(no, name, userid, userpw, tel);
//				record.setNo(no);
//				record.setName(null);
//				record.setUserid(userid);
//				record.setUserpw(userpw);
//				record.setTel(tel);
				arr.add(record);
			}//while*/
			arr=makeList(rs);
			return arr;
		}finally {
			close();
		}
		
	}//----------------------------
	
	private ArrayList<MemberVO> makeList(ResultSet rs) throws SQLException{//제너릭 memberVO
		ArrayList<MemberVO> arr=new ArrayList();
		while(rs.next()) {
			int no=rs.getInt("no");
			String name=rs.getString("name");
			String userid=rs.getString("userid");
			String userpw=rs.getString("userpw");
			String tel=rs.getString("tel");
			
			MemberVO record=new MemberVO(no, name, userid, userpw, tel); //하나의 행 (row)이 된다
			arr.add(record); //arr=table
		}//while
		return arr;
	}
	
	//단일행 결과를 가져오는 경우 - where절 PK를 조건으로 가져올 경우
	public MemberVO selectOne(int no) throws SQLException{
		try {
			String sql="SELECT no, name, userid, userpw, tel FROM java_member WHERE no=?";
			con=DBUtil.getCon();
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);//첫번째 ?를 no로 받기
			rs=ps.executeQuery(); //select일때는 executeQuery로 받는다
			
			ArrayList<MemberVO> arr=makeList(rs);
			
			if(arr==null||arr.size()==0) {
				return null;
			}
			//arr의 데이터가 있다면 pk로 가져오므로 1개만 있다
			MemberVO user=arr.get(0);
			return user;
			
		}finally {
			close();
		}
		
	}//----------------------------
	
	public MemberVO selectByUserid(String userid) throws SQLException{
		
		try {
			String sql="SELECT no, name, userid, userpw, tel FROM java_member WHERE userid=?";		
			con=DBUtil.getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			ArrayList<MemberVO> arr=makeList(rs);
			if(arr==null||arr.size()==0)
				return null;
			
			MemberVO user=arr.get(0);
			return user;
		}finally {
			close();
		}
	}//----------------------------

	public void close() throws SQLException{
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close();
	}
	
}









