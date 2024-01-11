package day03;
import java.sql.*;
import common.db.DBUtil;
/* select문 사용
 * stmt의 
 * ResultSet executeQuery(String sql)
 * 
 * */
public class MemberSelect {

	public static void main(String[] args) 
	throws ClassNotFoundException, SQLException
	{
		Connection con=DBUtil.getCon();
		System.out.println("DB연결됨..");
		
		String sql="SELECT no, name, userid, userpw, tel FROM java_member ORDER BY no ASC";
		
		Statement st=con.createStatement();
		
		//boolean isSel=st.execute(sql);
		//select문일 경우 true반환
		//System.out.println("isSel: "+isSel);
		
		//ResultSet executeQuery(String sql): select문을 실행시킬때 사용
		ResultSet rs=st.executeQuery(sql);
		//System.out.println(rs);
		
		//boolean next(): 논리적인 커서를 이동시켜서 가리키고 있는 곳에 레코드가 있으면 true반환하고, 없으면 false를 반환한다
		//		논리적인 커서는 처음에 before first에 위치한다
		
		while(rs.next()) {
			int no= rs.getInt(1); //rs.getInt("no");//getXXX(컬럼명|컬럼인덱스)
			String name=rs.getString(2);//rs.getString("name");
			String userid=rs.getString("userid");
			String userpw=rs.getString("userpw");
			String tel=rs.getString("tel");
			
			System.out.println(no+"\t"+name+"\t"+userid+"\t"+userpw+"\t"+tel);
		}//while-----
		
		rs.close();
		st.close();
		con.close();

	}

}
