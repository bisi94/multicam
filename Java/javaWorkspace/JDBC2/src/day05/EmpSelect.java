package day05;
import java.sql.*;
import java.util.*;
import common.db.*;
/*[실습] 부서명을 입력하면 해당 부서에 몇명이 있는지 
 * 출력하고, 해당 부서 사원의 사번, 이름,부서명,담당업무,입사일
 * 을 출력하세요.
 * 해당 부서가 없으면 "해당 부서 없습니다."를 출력하세요.
 * => PreparedStatement이용하기
 * */

public class EmpSelect {

	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("부서명 입력: ");
		String dname=sc.nextLine();
		
		Connection con=DBUtil.getCon();

		String sql="select count(deptno) cnt from emp where deptno=(";
			   sql+=" select deptno from dept where dname=upper(?))";
	
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, dname);
		//select문일때 => ResultSet executeQuery()
		ResultSet rs=ps.executeQuery();
		try {
			//결과셋이 단일행 => 커서는 한번만 이동
			boolean b=rs.next();
			int count=0;
			if(b) {
				count=rs.getInt("cnt"); //사원수
			}
			if(count==0) {
				System.out.println(dname+"부서에는 사원이 없습니다");
				return;
			}
			
			System.out.println(dname+"부서에는 "+count+"명의 사원이 있어요");
			
//			sql ="select empno, ename, dname, job, hiredate, loc";
//			sql+= " from emp e join dept d";
//			sql+= " on e.deptno = d.deptno and dname=upper(?)";
			
			sql =" select empno, ename, dname, rpad(job,12,' ') job, hiredate,loc";
		    sql +=" from emp e join dept d";
		    sql +=" on e.deptno = d.deptno and dname = upper(?)";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, dname);
			rs=ps.executeQuery(); 
			//결과셋이 다중행 => 반복해서 커서 이동
			System.out.println("----------------------------------------");
			System.out.println("사번\t사원명\t부서명\t담당업무\t입사일\t근무지");
			System.out.println("----------------------------------------");
			
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String dname2=rs.getString("dname");
				String job=rs.getString("job");
				java.sql.Date hdate=rs.getDate("hiredate");
				String loc=rs.getString("loc");
				System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n"
						, empno, ename, dname2, job, hdate, loc);
				
			}
			System.out.println("----------------------------------------");
			
			
		}finally {
			rs.close();
			ps.close();
			sc.close();
			
			con.close();
		}
		
	}

}















