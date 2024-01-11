package day02;
import java.sql.*;
/*
 * java_mamber 테이블에 레코드를 삽입해보자
 * 
 * 1 홍길동 hong  123 010-1111-2222
 * 
 * */
public class MemberInsert {

	public static void main(String[] args) throws Exception {
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
				sql+=" VALUES(JAVA_MEMBER_SEQ.NEXTVAL, 'kim'||JAVA_MEMBER_SEQ.CURRVAL, '김길동', 'abc', '010-4111-5222')";
				
		System.out.println(sql);
	//4. Statement 얻기
		Statement stmt = con.createStatement();
	
	//5. execute()로 실행시키기
		boolean isSel=stmt.execute(sql);
		
		System.out.println("isSel: "+isSel);
		System.out.println("scott계정 java_member테이블에 홍길동 삽입 완료!!");
	
	//6. 연결자원 반납
		stmt.close();
		con.close();
	}

}
