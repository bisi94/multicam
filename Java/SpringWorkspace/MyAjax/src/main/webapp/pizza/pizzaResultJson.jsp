<%@ page language="java" contentType="application/json; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>

<%

//contentType을 application/json으로 설정해야 함에 유의하자
	String phone=request.getParameter("phone");

	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="multishop";
	String pwd="tiger";
	
	Connection con=DriverManager.getConnection(url, user, pwd);
	String sql="SELECT * FROM pizza_user WHERE phone=?";
	
	PreparedStatement ps=con.prepareStatement(sql);
	ps.setString(1,phone);
	ResultSet rs=ps.executeQuery();
	String idx="0", name="", addr="", tel="";
	while(rs.next()){
		idx=rs.getString("idx");
		name=rs.getString("name");
		addr=rs.getString("addr");
		tel=rs.getString("phone");
	}
	
	if(rs!=null) rs.close();
	if(ps!=null) ps.close();
	if(con!=null) con.close();
%>


{
	"idx":"<%=idx %>",
	"name":"<%=name %>",
	"addr":"<%=addr %>",
	"phone":"<%=tel %>"
}