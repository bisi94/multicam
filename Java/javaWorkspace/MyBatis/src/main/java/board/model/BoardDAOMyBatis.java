package board.model;

import org.apache.ibatis.io.*;
import org.apache.ibatis.session.*;
import java.util.*;
import java.io.*;

/*
 * SqlSession : db crud 관련한 메서드를 가지고 있다  
 * ==> SqlSessionFactory 를 통해 얻는다
 * 	=> SqlSessionFactoryBuilder를 통해 공장을 짓고 (설계도를 참조-mybatis-config.xml)
 *     공장에서 SqlSession을 얻는다.
 * */

public class BoardDAOMyBatis {
	
	private SqlSession ses; //제품

	//어떤 mapper를 사용할지 정함(네임스페이스 지정 필수)
	private final String NS="board.model.BoardMapper";
	
	//세션팩토리를 얻는 메소드 구성
	private SqlSessionFactory getSessionFactory(){
		String resource="common/config/mybatis-config.xml"; //설계도 파일
		InputStream is=null;
		try {
			is=Resources.getResourceAsStream(resource);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder(); //건축가
		SqlSessionFactory factory=builder.build(is); //공장을 짓는다
		return factory;
	}//----------------------------------
	
	//테스트용 - multishop 계정의 테이블수를 가져오는 메소드
	public int testMyBatis() {
		try {
			ses=getSessionFactory().openSession();
			
			int count=ses.selectOne(NS+".test");
			return count;
		}finally {
			close();
		}
	}//----------------------------------
	
	public void close() {
		if(ses!=null) ses.close();
	}//----------------------------------

	public int insertBoard(BoardVO vo) {
		try {
			//ses=this.getSessionFactory().openSession(); //dml문장시 디폴트 수동 커밋
			ses=this.getSessionFactory().openSession(true); //true 전달하면 세션받아옴
			
			int n=ses.insert(NS+".insertBoard", vo);
			
			System.out.println("글번호: "+vo.getNo());
			/*if(n>0) {
				ses.commit(); //커밋
			}else {
				ses.rollback(); //롤백
			}*/
			
			return n;
		}finally {
			close();
		}
	}//--------------------------------------
	
	public List<BoardVO> listBoard(Map<String,String> map){
		try {
			ses=this.getSessionFactory().openSession();
			List<BoardVO> arr=ses.selectList(NS+".listBoard", map);
			return arr;
		}finally {
			close();
		}
	}//------------------------------------

	public int getTotalCount() {
		try {
			ses=this.getSessionFactory().openSession();
			int cnt=ses.selectOne(NS+".getTotalCount");
			return cnt;
		}finally {
			close();
		}
	
	}
	//pk(no)로 가져오므로 단일행 반환
	public BoardVO getBoard(int no) {
		try {
			ses=this.getSessionFactory().openSession();
			BoardVO vo=ses.selectOne(NS+".getBoard", no);
			return vo;
		}finally {
			close();
		}
	}

	public int updateReadnum(int no) {
		try {
			ses=this.getSessionFactory().openSession(true); //dml일때 오토커밋하게 true를 넣어줌
			int n=ses.update(NS+".updateReadnum", no);
			return n;
		}finally {
			close();
		}
		
	}

	public int deleteBoard(int no) {
		try {
			ses=this.getSessionFactory().openSession(true);
			
			int n=ses.delete(NS+".deleteBoard", no);
			return n;
		}finally {
			close();
		}
		
	}

	public int updateBoard(BoardVO vo) {
		try {
			ses=this.getSessionFactory().openSession(true);
			
			int n=ses.update(NS+".updateBoard", vo);
			return n;
		}finally {
			close();
		}
	}

	public int getFindTotalCount(Map<String, String> map) {
		try {
			ses=this.getSessionFactory().openSession();
			int cnt=ses.selectOne(NS+".getFindTotalCount", map);
			return cnt;
		}finally {
			close();
		}
	}
	
	
	
}////////////////////////////////////






