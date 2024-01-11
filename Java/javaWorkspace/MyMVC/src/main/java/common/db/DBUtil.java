package common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static String user="multishop";
	private static String pwd="tiger";
	
	//1. 드라이버 로딩
	//static initializer
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded...");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//--------------------
	
	/** DB와 연결하여 Connection을 반환하는 메서드 */
	public static Connection getCon() throws SQLException{
		//Ctrl+Shift+O
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;		
	}//----------------------
	

}////////////////////////
