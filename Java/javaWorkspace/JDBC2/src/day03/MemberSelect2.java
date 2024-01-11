package day03;
import java.sql.*;
import java.util.*;
import common.db.DBUtil;

public class MemberSelect2 {

	public static void main(String[] args) throws Exception 
	{
		System.out.println("검색할 회원의 이름을 입력하세요=>");
		Scanner sc=new Scanner(System.in);
		String keyword=sc.nextLine();
		System.out.println("검색어: "+keyword);
		
		Connection con=DBUtil.getCon();
		//select문 작성하기 where절 like 활용
		String sql="SELECT * FROM java_member WHERE name LIKE '%"+keyword+"%'";
		System.out.println(sql);
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(sql);
		System.out.println("---------------------------------");
		System.out.println("번호\t이름\t아이디\t연락처");
		System.out.println("---------------------------------");
		boolean flag=false;
		while(rs.next()) {
			flag=true;
			int no=rs.getInt("no");
			String name=rs.getString("name");
			String userid=rs.getString("userid");
			String tel=rs.getString("tel");
			System.out.println(no+"\t"+name+"\t"+userid+"\t"+tel);
		}
		System.out.println("---------------------------------");
		if(!flag) {
			System.out.println(keyword+" 회원은 없습니다");
		}
		
		
		rs.close();
		st.close();
		con.close();

	}

}




