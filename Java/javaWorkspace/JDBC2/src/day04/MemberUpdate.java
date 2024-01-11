package day04;
import java.sql.*;
import common.db.*;
import java.util.*;

public class MemberUpdate {

	public static void main(String[] args) 
	throws Exception
	{
		//java_member 의 userid를 입력받아 해당 회원의 연락처, 비밀번호를 수정처리합시다
		Scanner sc=new Scanner(System.in);
		
		System.out.println("수정할 회원의 아이디: ");
		String userid=sc.nextLine();

		System.out.println("수정할 회원의 연락처: ");
		String tel=sc.nextLine();
		
		System.out.println("수정할 회원의 비밀번호: ");
		String userpw=sc.nextLine();
		
		System.out.println(userid+", "+tel+", "+userpw+"으로 수정합니다");
		
		//update문을 작성하여 실행시키세요
		
		Connection con = DBUtil.getCon();
		
		String sql="UPDATE java_member SET tel='"+tel+"', userpw='"+userpw+"' WHERE userid='"+userid+"'";
		
		System.out.println(sql);
		
		Statement st=con.createStatement();
		
		//DML문장 : int excuteUpdate(sql);
		int n=st.executeUpdate(sql);
		System.out.println(n+"n개의 레코드가 수정됨");
		
		st.close();
		con.close();
	}
}
