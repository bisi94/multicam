package day04;
import java.sql.*;
import common.db.*;
import java.util.*;

public class EmpInsert {

	public static void main(String[] args) throws Exception {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("등록할 사원명: ");
		String name=sc.nextLine();
		System.out.println("등록할 담당업무: ");
		String job=sc.nextLine();
		System.out.println("등록할 사원번호: ");
		int empno=sc.nextInt();
		System.out.println("등록할 부서번호: ");
		int deptno=sc.nextInt();
		
		System.out.println(name+", "+job+", "+empno+", "+deptno+" 을 등록합니다");
		
		//INSERT 문을 실행하는 프로그램을 작성하시오. 단 PreparedStatement를 사용하세요
		
	// 1. db연결
		Connection con=DBUtil.getCon();
		
		// 2.미리 쿼리문을 준비해 둔다
		String sql="INSERT INTO emp2(empno, ename, job, deptno, hiredate)";
			   sql+=" VALUES(?,?,?,?,sysdate)";
				 
		//? : in parameter
		//PreparedStatement는 ?를 제외한 sql문을 DB포맷에 맞게
		//		  컴파일하여 미리 준비시켜 놓는다.
		
		PreparedStatement ps= con.prepareStatement(sql);
		//     과거형                      현재형
		//in parameter값을 setting하는 절차가 필요함
		//setXXX(컬럼인덱스, 값)
		
		ps.setInt(1, empno);
		ps.setString(2, name);
		ps.setString(3, job);
		ps.setInt(4, deptno);
		
		//executeXXX() 실행시킨다
		int n=ps.executeUpdate();
		
		System.out.println(n+"개의 데이터 삽입!!");
		
		ps.close();
		con.close();
		
	}

}
