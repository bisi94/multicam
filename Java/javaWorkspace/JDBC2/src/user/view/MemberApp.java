package user.view;
/*****************************************************************
MVC 패턴===>Web도입
MFC===>JFC (swing)
---------------------------------------------------------------
M : Model==> 데이터를 가지고 있는 부분
	<1> DAO (Data Access Object): crud기능을 갖는다. biz logic
		MemberDAO
	<2> VO(Value Object) or DTO(Data Transfer Object)
		Student,Teacher, EmpDTO, MemberVO

V : View ==> UI부분(cli, swing,html,jsp)
C : Controller==> 제어하는 부분
	화면통해 들어온 데이터를 모델에게 전달하여 반영하도록
	모델에서 변경된 부분이 화면단에 렌더링하게 하는 역할
	XXXEventHandler, XXXRenderer
	web=> Servlet
---------------------------------------------------------------
******************************************************************/
//Presentation Tier : 화면 담당
import user.model.*;
import java.util.*;
import java.sql.*;
public class MemberApp { //View
	
	private MemberDAO dao; //Model
	Scanner sc=new Scanner(System.in);

	public MemberApp() {
		dao=new MemberDAO();
		
	}
	public void menu() {
		System.out.println("******회원관리 App v1.1******");
		System.out.println("1. 회원 가입");//C
		System.out.println("2. 모든 회원 보기");//R
		System.out.println("3. 회원정보 수정");//U
		System.out.println("4. 회원 검색");//R
		System.out.println("5. 회원 탈퇴");//D
		System.out.println("9. 종   료");
		System.out.println("***************************");
		System.out.println("메뉴 번호를 입력하세요 =>");
		System.out.println("***************************");
	}//----------------------------------------
	/**회원가입을 처리하는 메소드*/
	public void register() {
		//1. 회원정보 입력받기
		MemberVO user=new MemberVO();
		user.input();
		System.out.println(user);
		System.out.println(">>위 정보를 저장할까요? [1.예 2.아니오]<<");
		int yn=sc.nextInt();
		if(yn==2) return;
		//2. MemberDAO의 insert()호출
		try {
			int n=dao.insert(user);
			//3. 실행 결과 메시지 호출
			String msg=(n>0)?"회원가입완료!!":"회원가입실패 - 다시시도하세요";
			System.out.println(msg);
		}catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("아이디가 중복되었어요 다른 아이디를 입력하세요");
		}catch(SQLException e) {
			System.out.println("DB에러: "+e);
			System.out.println("아이디 중복! 다른 아이디 입력: ");
		}
	}//--------------------------------------------
	/*모든 회원 정보를 출력하는 메소드*/
	public void allMember() {
		try {
			ArrayList<MemberVO> arr=dao.selectAll();
			if(arr==null||arr.size()==0) {
				System.out.println("현재 등록된 데이터가 없습니다");
				return;
			}
			System.out.println("---------------------------------------");
			System.out.println("번호\t이름\t아이디\t연락처");
			System.out.println("---------------------------------------");
			for(MemberVO user:arr) {
//				System.out.println(user);
				System.out.printf("%d \t %s \t %s \t %s \n",user.getNo(),user.getName(),user.getUserid(),user.getTel());
			}
			System.out.println("---------------------------------------");
			
		}catch(SQLException e) {
			System.out.println("DBError: "+e);
		}
		
	}//--------------------------------------------
	
	public void findMember() {
		//검색할 회원의 아이디를 입력받아, 해당 회원이 있으면 보여주고, 없으면 "회원이 아닙니다"를 출력
		System.out.println("검색할 항목을 선택하세요 1. 아이디 2. 회원번호");
		int num=sc.nextInt();
		if(num<1||num>2) {
			System.out.println("메뉴에 없는 번호입니다");
			return;
		}
		
		sc.skip("\r\n"); //엔터값 건너뛰기 mac의경우 \n만 적용해주기
		
		try {
			switch(num) {
				case 1:{
					System.out.println("검색할 회원의 아이디: ");
					String uid=sc.nextLine();
					MemberVO findUser=dao.selectByUserid(uid);
					showUser(findUser);
				}
				break;
				
				case 2:{
					System.out.println("검색할 회원의 회원번호: ");
					int no=sc.nextInt();
					MemberVO findUser=dao.selectOne(no);
					showUser(findUser);
				}
				break;
			}//switch
		}catch(SQLException e) {
			System.out.println("DBError: "+e);
		}
		
	}//--------------------------------------------
	
	public void showUser(MemberVO user) {
		if(user==null) {System.out.println("--------------------------------------");
			System.out.println("검색한 회원정보는 없습니다");
			return;
		}
		System.out.println("--------------------------------------");
		System.out.println("번호\t이름\t아이디\t연락처");
		System.out.println("--------------------------------------");
		System.out.printf("%d\t%s\t%s\t%s\n",user.getNo(), user.getName(), user.getUserid(), user.getTel());
		System.out.println("--------------------------------------");
	}
	
	public void deleteMember() {
		System.out.println("탈퇴할 회원의 번호를 입력하세요");
		int no=sc.nextInt();
		System.out.println(no+"번 회원님의 정보를 정말 삭제할까요?[1. 예 2. 아니오]");
		int yn=sc.nextInt();
		if(yn==2) return;
		
		//1. dao의 delete()호출하기
		try {
			int n=dao.delete(no);
	
			//2. 그 결과 메시지 처리 => 회원정보 삭제완료: 회원정보 삭제 실패
			String msg=(n>0)? "회원 탈퇴 처리 완료":"회원 탈퇴 실패 - 다시 시도하세요";
			System.out.println(msg);
		}catch(SQLException e) {
			System.out.println("DBError: "+e);
		}
	}//-------------------------------------------- 삭제는 되는데 없는번호 오류캐치를 못한다야..
	
	public void updateMember() {
		//수정할 회원정보 입력받기
		System.out.println("수정할 회원정보를 입력하세요");
		int no=sc.nextInt();
		sc.skip("\r\n"); //엔터값 스킵
		
		MemberVO user=new MemberVO();
		user.input();//이름, 아이디, 비번, 연락처 입력받기
		user.setNo(no);//회원번호값 세팅
		try {
			int n=dao.update(user);
			String msg=(n>0)? "회원정보 수정 완료":"회원정보 수정 실패 - 존재하지 않는 회원";
			System.out.println(msg);
		}catch(SQLException e) {
			System.out.println("DBError: "+e);
		}
		
	}

	public static void main(String[] args) {
		//menu()호출하기
		MemberApp app=new MemberApp();
		while(true) {
			app.menu();
			int num=app.sc.nextInt();
			System.out.println("num: "+num);
			if(num==9) {
				System.out.println("Bye Bye~");
				//break;
				System.exit(0); //종료
			}
			
			if(num<1||num>5) {
				System.out.println("메뉴에 없는 번호입니다. 다시 입력하세요");
				continue;
			}//if
			
			switch(num) {
			case 1: //회원가입
				app.register();
				break;
			case 2: //모든멤버보기
				app.allMember();
				break;
			case 3:// 회원정보 수정
				app.updateMember();
				break;
			case 4:
				app.findMember();
				break;
			case 5: //회원 탈퇴
				app.deleteMember();
				break;
			}//switch------------
			
		}//while-----

	}//--------------

}
