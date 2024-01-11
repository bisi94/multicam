<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Data to OracleDB</title>
</head>
<body>

<%
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        // 데이터베이스 연결 설정
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/yourDatasource");
        connection = dataSource.getConnection();

        // SQL 쿼리 작성 (테이블명, 컬럼명 등은 실제 데이터베이스에 맞게 수정)
        String sql = "INSERT INTO your_table_name (column1, column2) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);

        // 쿼리에 파라미터 설정 (실제 데이터는 동적으로 받아와서 설정)
        preparedStatement.setString(1, "value1");
        preparedStatement.setString(2, "value2");

        // 쿼리 실행
        preparedStatement.executeUpdate();

        out.println("Data inserted successfully!");
    } catch (Exception e) {
        e.printStackTrace();
        out.println("Error inserting data: " + e.getMessage());
    } finally {
        // 자원 해제
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
%>

</body>
</html>
