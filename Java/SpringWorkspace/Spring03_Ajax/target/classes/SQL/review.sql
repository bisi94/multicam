-- 상품 리뷰 게시판
-- products테이블의 pnum(PK)과 member테이블의 userid(PK)를 외래키로 참조해야 함
-- 일단 ajax를 간단하게 테스트하기 위해 관계를 맺지 않고 테이블을 생성해보자

DROP TABLE REVIEW;

CREATE TABLE REVIEW(
	NUM NUMBER(8) PRIMARY KEY, --리뷰 글번호
	USERID VARCHAR2(20) NOT NULL, --회원 아이디 => 후에 FK제약조건 추가 예정
	PNUM NUMBER(8) NOT NULL, --상품번호 =>후에 FK제약조건 추가 예정
	TITLE VARCHAR2(200), --제목
	CONTENT VARCHAR2(500), --리뷰내용
	SCORE NUMBER(1) CONSTRAINT SCORE_CK CHECK(SCORE>0 AND SCORE <=5),
	FILENAME VARCHAR2(300), --첨부파일
	WDATE DATE DEFAULT SYSDATE
	
);

DROP SEQUENCE REVIEW_SEQ;

CREATE SEQUENCE REVIEW_SEQ NOCACHE;