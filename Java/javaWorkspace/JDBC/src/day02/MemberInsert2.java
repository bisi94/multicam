package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

public class MemberInsert2 {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("::회원정보 등록::");
		
		System.out.println("이름 => ");
		String name=sc.nextLine();
		
		System.out.println("id => ");
		String userid=sc.nextLine();
		
		System.out.println("pw => ");
		String userpw=sc.nextLine();
		
		System.out.println("연락처 => ");
		String tel=sc.nextLine();
		
		System.out.println(name+", "+userid+", "+userpw+", "+tel+" => 정보를 저장합니당ㅋ");
		
		
		
	//1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loading Success!!");

	//2. db연결
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "Scott", pwd = "tiger";
		
		Connection con=DriverManager.getConnection(url,user,pwd);
		
		System.out.println("DB Connected...");
	
	//3. sql문 작성-insert문
		String sql="INSERT INTO java_member(no, userid, name, userpw, tel)";
				sql+=" VALUES(JAVA_MEMBER_SEQ.NEXTVAL, '"+userid+"', '"+name+"', '"+userpw+"', '"+tel+"')";
				
		System.out.println(sql);
	//4. Statement 얻기
		Statement stmt = con.createStatement();
	
	//5. execute()로 실행시키기
		boolean isSel=stmt.execute(sql);
		
		System.out.println("isSel: "+isSel);
		System.out.println("scott계정 java_member테이블에 정보 삽입 완료!!");
	
	//6. 연결자원 반납
		stmt.close();
		con.close();
	}//main()-------------
}////
	