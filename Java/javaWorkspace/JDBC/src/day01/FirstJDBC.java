package day01;
import java.sql.*;

public class FirstJDBC {

	public static void main(String[] args) {
		//1. 드라이버 로딩 (ojdbc6.jar => Class.forName("패키지명.드라이버클래스명")
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!");
			
			//2. DB 연결=> DriverManager.getConnection(url,user,pwd)==>Connection을 반환한다
			//연결정보 필요
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="scott", pwd="tiger";
			
			Connection con =DriverManager.getConnection(url, user, pwd);
			System.out.println("DB Connected...");
			
			if(con!=null) con.close();
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	}

}
