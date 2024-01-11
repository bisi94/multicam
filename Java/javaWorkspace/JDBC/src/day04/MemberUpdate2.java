package day04;

import java.sql.*;
import common.db.*;
import java.util.*;

/*
 * PreparedStatement : Statement의 자식 클래스
 * 	- 고정적인 sql문을 미리 dbms 포맷에 맞게 컴파일 시켜 준비해둔다
 * 	  (Statement는 매번 execute할때 마다 새롭게 컴파일을 하는 반면,
 * 		PreparedStatement는 딱 한번만 컴파일 시켜둔다)
 *  - 동적으로 변경되는 값 부분만 그때 그때 컴파일을 한다
 * 
 * */


import java.util.Scanner;

public class MemberUpdate2 {

	public static void main(String[] args) throws Exception{
		
		//java_member 의 userid를 입력받아 해당 회원의 연락처, 비밀번호를 수정처리합시다
			Scanner sc=new Scanner(System.in);
			
			System.out.println("수정할 회원의 아이디: ");
			String userid=sc.nextLine();

			System.out.println("수정할 회원의 연락처: ");
			String tel=sc.nextLine();
			
			System.out.println("수정할 회원의 비밀번호: ");
			String userpw=sc.nextLine();
			
			System.out.println(userid+", "+tel+", "+userpw+"으로 수정합니다");
			
			// 1. db연결
			Connection con=DBUtil.getCon();
			
			// 2.미리 쿼리문을 준비해 둔다
			String sql="UPDATE java_member SET tel=?, userpw=? WHERE userid=?";
			//? : in parameter
			//PreparedStatement는 ?를 제외한 sql문을 DB포맷에 맞게
			//		  컴파일하여 미리 준비시켜 놓는다.
			
			PreparedStatement ps= con.prepareStatement(sql);
			//     과거형                      현재형
			//in parameter값을 setting하는 절차가 필요함
			//setXXX(컬럼인덱스, 값)
			
			ps.setString(1, tel);
			ps.setString(2, userpw);
			ps.setString(3, userid);
			
			//executeXXX() 실행시킨다
			int n=ps.executeUpdate();
			System.out.println(n+"개의 레코드 수정됨!!");
			
			ps.close();
			con.close();
	}

}
