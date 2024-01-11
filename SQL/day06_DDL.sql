--day06_DDL.sql
show user
-------------------------------------------------
DDL 문장: CREATE, ALTER, DROP, RENAME,TRUNCATE
DML 문장: UPDATE,INSERT,DELETE,MERGE
DCL 문장: GRANT,REVOKE
DQL : SELECT
TCL(TRANSACTION CONTROL) : COMMIT, ROLLBACK, SAVEPOINT
-------------------------------------------------
# table 생성문장
create table 테이블명(
    컬럼명1 자료형(크기) [default 기본값],
    컬럼명2 자료형 제약조건,
    컬럼명3 자료형,
    ....,
    테이블수준의 제약조건1,
    테이블수준의 제약조건2
);

# 제약조건

-----------------------------------------------------------------------
  <1> PRIMARY KEY (PK) : 유일하게 테이블의 각 행을 식별
				  NOT NULL과 UNIQUE 조건을 만족
  <2> FOREIGN KEY (FK) : 컬럼과 참조된 컬럼 사이의 외래키 관계를 적용하고 설정
  <3> UNIQUE KEY (UK)  : 테이블의 모든 행을 유일하게 하는 값을 가진 열
				  NULL을 허용
  <4> NOT NULL (NN)	: 컬럼은 NULL을 포함할 수 없다.
  <5> CHECK (CK)		: 참이어야 하는 조건을 지정함
				  대부분 업무 규칙을 설정
-----------------------------------------------------------------------

[1] primary key : not null + unique 조건을 만족
  <1> 컬럼 수준의 제약
  
  CREATE TABLE TEST_TAB1(
        ID NUMBER(2) CONSTRAINT TEST_TAB1_ID_PK PRIMARY KEY,
        NAME VARCHAR2(20)
  );
  
  DESC TEST_TAB1;
  
  데이터 사전에서 조회
  
  SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB1';
  
  INSERT INTO TEST_TAB1(ID,NAME)
  VALUES(1,NULL);
  
  SELECT * FROM TEST_TAB1;
  
  INSERT INTO TEST_TAB1(ID,NAME)
  VALUES(2,'홍길동');
  COMMIT;
  
  <2> pk-테이블 수준의 제약

    CREATE TABLE TEST_TAB2(
      ID NUMBER(2),
      NAME VARCHAR2(20),
      TEL VARCHAR2(15),
      -- 테이블 수준의 제약
      CONSTRAINT TEST_TAB2_ID_PK PRIMARY KEY (ID)    
    );
    
    DESC TEST_TAB2;
    
    데이터 사전에서 조회하세요
    
    SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE,INDEX_NAME
    FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
    
    
    # 제약 조건 삭제
    ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명;
    
    # 제약 조건 추가
    ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건유형 (컬럼명)
    
    # 제약 조건의 이름 수정
    ALTER TABLE 테이블명 RENAME CONSTRAINT OLD_NAME TO NEW_NAME;
    
   [실습] 
    [1] TEST_TAB2 테이블의 제약조건 TEST_TAB2_ID_PK 를 삭제하세요
        
        ALTER TABLE TEST_TAB2 DROP CONSTRAINT TEST_TAB2_ID_PK;
        
    데이터 사전에서 확인하기
        SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
        
    [2] TEST_TAB2 테이블에 새로운 제약조건을 추가하되
        제약조건명을 TEST_TAB2_PK로 주고 PRIMARY KEY 제약조건을 ID에 주세요
        
        ALTER TABLE TEST_TAB2 ADD CONSTRAINT TEST_TAB2_PK PRIMARY KEY (ID);
        
    데이터 사전에서 확인하기    
        SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
        
    [3] TEST_TAB2_PK 제약조건 이름을 변경하세요 TEST_TAB2_PRIMARY_KEY
    
        ALTER TABLE TEST_TAB2 RENAME CONSTRAINT TEST_TAB2_PK   TO TEST_TAB2_PRIMARY_KEY;
    
    데이터 사전에서 확인하기    
        SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
--------------------------------------------------------------
    [2] FOREIGN KEY (외래키)
        - PK OR UNIQUE 컬럼들을 외래키로 참조할 수 있다.
        - PK의 컬럼과 자료형,크기 등이 일치해야 한다
        - 외래키에 의해 참조되고 있는 기본키(PK)는 삭제할 수 없다
        - ON DELETE CASCADE 연산자와 함께 정의된 외래키 데이터는
          그 기본키가 삭제될 때 같이 삭제된다
        
     <1>FK-컬럼 수준의 제약
     
     --MASTER TABLE 생성 (부모 테이블)
     DROP TABLE DEPT_TAB;
     
     CREATE TABLE DEPT_TAB(
        DEPTNO NUMBER(2),
        DNAME CHAR(14),
        LOC CHAR(13),
        CONSTRAINT DEPT_TAB_DEPTNO_PK PRIMARY KEY (DEPTNO)
     );
     
     -- DETAIL TABLE (자식 테이블)
     CREATE TABLE EMP_TAB(
        EMPNO NUMBER(4),
        ENAME VARCHAR2(20),
        JOB VARCHAR2(20),
        -- 컬럼수준의 제약
        MGR NUMBER(4) CONSTRAINT EMP_TAB_MGR_FK REFERENCES EMP_TAB (EMPNO),
        HIREDATE DATE,
        SAL NUMBER(7,2),
        COMM NUMBER(7,2),
        DEPTNO NUMBER(2),
        -- 테이블 수준 제약
        CONSTRAINT EMP_TAB_DEPTNO_FK FOREIGN KEY (DEPTNO)
        REFERENCES DEPT_TAB (DEPTNO),
        PRIMARY KEY (EMPNO)
     );
     
     SELECT * FROM USER_CONSTRAINTS
     WHERE TABLE_NAME IN ('DEPT_TAB','EMP_TAB');
     
     데이터 삽입
     DEPT_TAB에
     10 인사부 서울
     20 경영부 부산
     데이터를 INSERT 하기
     
     INSERT INTO DEPT_TAB VALUES(10,'인사부','서울');
     INSERT INTO DEPT_TAB VALUES(20,'경영부','부산');
     
     SELECT * FROM DEPT_TAB;
     COMMIT;
     
     EMP_TAB에 데이터 넣기
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1001,'김사원','영업사원',NULL,SYSDATE,NULL);
     
     SELECT * FROM EMP_TAB;
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1002,'최사원','영업사원',1001,SYSDATE,20);
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1003,'이사원','영업사원',1003,SYSDATE,20);
     
      INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1004,'이사원','영업사원',1005,SYSDATE,20); [X] FK에 위반
     
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1004,'이사원','영업사원',1002,SYSDATE,10);
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1005,'고사원','영업사원',1003,SYSDATE,30); [X] FK에 위반 
     
     SELECT * FROM EMP_TAB;
     COMMIT;
     
     10번 부서를 DEPT_TAB에서 삭제하세요
     
     DELETE FROM DEPT_TAB WHERE DEPTNO=10; [X]
   =>  integrity constraint (SCOTT.EMP_TAB_DEPTNO_FK) violated - child record found
     자식 레코드가 있다면 관련된 부모 레코드 삭제 불가
     
     DELETE FROM EMP_TAB WHERE DEPTNO=10;
     
     SELECT * FROM EMP_TAB;
     
     DELETE FROM DEPT_TAB WHERE DEPTNO=10;
     
     SELECT * FROM DEPT_TAB;
     COMMIT;
     
     ON DELETE CASCADE 연산자 이용
     => 자식레코드가 있어도 관련된 부모 레코드를 삭제할 수 있으며,
        이때 자식 레코드도 같이 삭제된다
     
-----------------------------------------------------------
게시판: BBS (부모테이블) 글번호(PK)
댓글: REPLY (자식테이블) <=부모글의 글번호를 외래키로 참조(FK) 
                    이때 ON DELETE CASCADE 옵션을 주어보자

CREATE TABLE BBS(
   NO NUMBER(4) PRIMARY KEY,
   TITLE VARCHAR2(200) NOT NULL,
   WRITER VARCHAR2(30) NOT NULL,
   CONTENT VARCHAR2(2000),
   WDATE DATE DEFAULT SYSDATE,
   -- WRITER 컬럼을 외래키로 주세요(테이블 수준에서) => JAVA_MEMBER 테이블의 USERID를 참조하도록
   CONSTRAINT BBS_WRITER_FK FOREIGN KEY (WRITER)
   REFERENCES JAVA_MEMBER (USERID) ON DELETE CASCADE
);
DESC BBS;

SELECT * FROM JAVA_MEMBER;
     
BBS에 글을 하나 삽입하세요     

INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(1,'반갑습니다','hong','오늘 처음 글을 써요');

select * from bbs;

INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(2,'저도 반갑습니다','kim','저도 오늘 처음 글을 써요');

INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(3,'반갑습니다','noname','오늘 처음 글을 써요');    [x] 
    
INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(3,'안녕하세요','hong','오늘도 글을 씁니다');     
       
select * from bbs;     
commit;
   
java_member 테이블에서 userid가 'kim'인 회원을 삭제해보세요

select * from java_member;

delete from java_member where userid='kim';
     
select * from java_member;
select * from bbs;

rollback;
     
on delete cascade 는 자식테이블에서 외래키로 참조하면서 설정한다

------------------------------------------------
테이블명: reply
글번호: rno number(4) pk
글내용: rcontent varchar2(500)
작성일: rdate date
작성자: rwriter => 외래키 (java_member의 userid)  on delete cascade
bbs의글번호: no_fk => 외래키 (bbs의 no)  on delete cascade
------------------------------------------------

create table reply(
  rno number(4) primary key,
  rcontent varchar2(500),
  rdate date default sysdate,
  rwriter varchar2(20) references java_member (userid) on delete cascade,
  no_fk number(4) references bbs (no) on delete cascade
);

select * from reply;

select * from bbs;

insert into reply(rno,rcontent,rwriter,no_fk)
values(3,'좋은 날씨에요','kim',2);

select * from reply;

bbs와 reply를 join해서 함께 보여주세요

select b.*, r.*
from bbs b join reply r
on b.no = r.no_fk;

bbs에서 2번글을 삭제하세요

delete from bbs where no=2;

select * from bbs;

rollback;
-----------------------------------------------
# [3]  unique 제약조건

 <1> uk - 컬럼수준의 제약
    
    create table uni_tab(
        deptno number(2) constraint uni_tab_deptno_uk unique,
        dname char(10),
        loc char(10)
    );
    
    select * from user_constraints where table_name=upper('uni_tab');
    
    insert into uni_tab values(1,'accounting','LA');
    insert into uni_tab values(2,'sales','LA');
    insert into uni_tab values(null,'operation','LA');
    
    select * from uni_tab;
    commit;
 
 
 <2> 테이블 수준 제약
 
 create table uni_tab2(
    deptno number(2),
    dname char(10),
    loc char(10),
    constraint uni_tab2_deptno_uk unique (deptno)
 );
 
 # [4] NOT NULL 제약조건
    - 컬럼 수준에서만 제약할 수 있다
 
CREATE TABLE NN_TAB(
   DEPTNO NUMBER(2) CONSTRAINT NN_TAB_DEPTNO_NN NOT NULL,
   DNAME CHAR(10),
   LOC CHAR(10)
);

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='NN_TAB';
     
INSERT INTO NN_TAB VALUES(10,'ACCOUNTING','LA');     
INSERT INTO NN_TAB VALUES(NULL,'ACCOUNTING','LA');
     
CREATE TABLE NN_TAB2(
   DEPTNO NUMBER(2) CONSTRAINT NN_TAB2_DEPTNO_NN NOT NULL,
   DNAME CHAR(10),
   LOC CHAR(10),
   CONSTRAINT DNAME_NN NOT NULL (DNAME) -- [X] NOT NULL은 테이블수준에서는 제약 불가
);

DESC NN_TAB2;
     
     
# CHECK 제약조건
<1> 컬럼 수준
    CREATE TABLE CK_TAB(
        DEPTNO NUMBER(2) CONSTRAINT CK_TAB_DEPTNO_CK CHECK (DEPTNO >= 10 AND DEPTNO <=20 ),
        DNAME CHAR(10)
    );
    
    INSERT INTO CK_TAB VALUES(20,'SALES');
    INSERT INTO CK_TAB VALUES(30,'SALES');
    INSERT INTO CK_TAB VALUES(13,'SALES');
    SELECT * FROM CK_TAB;

<2> 테이블 수준
     SCORE 테이블을 만들되
     NAME VARCHAR2(20)
     KOR 컬럼명 NUMBER(3) 국어 점수는 0 ~100점사이로 제약하세요
     
     CREATE TABLE SCORE(
      NAME VARCHAR2(20) NOT NULL,
      KOR NUMBER(3), 
      -- 테이블 수준 제약
      CONSTRAINT SCORE_KOR_CK CHECK (KOR >=0 AND KOR <=100)
     );
     
     INSERT INTO SCORE VALUES('홍길동',0);
     INSERT INTO SCORE VALUES('홍길동',100);
     INSERT INTO SCORE VALUES('홍길동',88);
     INSERT INTO SCORE VALUES('홍길동',-20);
     INSERT INTO SCORE VALUES('홍길동',120);
     SELECT * FROM SCORE;
     
-- ================================================================================     --
# SUBQUERY를 이용한 테이블 생성
CREATE TABLE 테이블명
AS SELECT 컬럼명1,컬럼명2,... FROM 테이블명2;
     
     
     [실습] 사원 테이블에서 30번 부서에 근무하는 사원의 정보만 추출하여
	          EMP30 테이블을 생성하여라. 단 열은 사번,이름,업무,입사일자,
		  급여,보너스를 포함한다.
         
         CREATE TABLE EMP30
         AS SELECT EMPNO, ENAME, JOB, HIREDATE, SAL, COMM FROM EMP
            WHERE DEPTNO=30;
            
            SELECT * FROM EMP30;
            
         CREATE TABLE EMP20(사번,사원명,업무,입사일,급여,보너스)
         AS
         SELECT EMPNO, ENAME, JOB, HIREDATE, SAL, COMM FROM EMP
         WHERE DEPTNO=20;
            
            SELECT * FROM EMP20;
     
     [문제1]
		EMP테이블에서 부서별로 인원수,평균 급여, 급여의 합, 최소 급여,
		최대 급여를 포함하는 EMP_DEPTNO 테이블을 생성하라.
        
        CREATE TABLE EMP_DEPTNO
        AS
        SELECT DEPTNO, COUNT(EMPNO) CNT, AVG(SAL) AVG_SAL, SUM(SAL) SUM_SAL, MIN(SAL) MIN_SAL, MAX(SAL) MAX_SAL
        FROM EMP GROUP BY DEPTNO;
        
        SELECT * FROM EMP_DEPTNO;
        
		
	[문제2]	EMP테이블에서 사번,이름,업무,입사일자,부서번호만 포함하는
		EMP_TEMP 테이블을 생성하는데 자료는 포함하지 않고 구조만
		생성하여라.
     
        CREATE TABLE EMP_TEMP
        AS 
        SELECT EMPNO,ENAME,JOB,HIREDATE,DEPTNO FROM EMP
        WHERE 1=2;
        
        SELECT * FROM EMP_TEMP;
===================================================================
# 컬럼 추가, 삭제, 수정...

[1]  컬럼 추가
     ALTER TABLE 테이블명 ADD 추가할 컬럼명 자료형 [DEFAULT 기본값];
[2]  컬럼 삭제
    ALTER TABLE 테이블명 DROP COLUMN 삭제할 컬럼명;
[3] 컬럼 자료형 변경
    ALTER TABLE 테이블명 MODIFY 변경할 컬럼정보
[4] 컬럼명 변경
    ALTER TABLE 테이블명 RENAME COLUMN OLD컬럼명 TO NEW컬럼명;
    
     [실습]    		
		
	      상품 테이블에 상품을 설명하는 새로운 칼럼을 추가하세요. 
	      컬럼 이름은 prod_desc VARCHAR2(1000)
          
         ALTER TABLE PRODUCTS ADD PROD_DESC VARCHAR2(1000);  

        SELECT * FROM PRODUCTS;
        
        - PROD_DESC의 자료형을 NUMBER(8)로 변경해보세요
        
        ALTER TABLE PRODUCTS MODIFY PROD_DESC NUMBER(8);
        
        DESC PRODUCTS;

      - PROD_DESC 컬럼명을 PROD_NUM 으로 변경하세요
      
        ALTER TABLE PRODUCTS RENAME COLUMN PROD_DESC TO PROD_NUM;
      
        SELECT * FROM PRODUCTS;
      
      - PRODUCTS 테이블에서 RPOD_NUM 컬럼을 삭제하세요
      
        ALTER TABLE PRODUCTS DROP COLUMN PROD_NUM;
        
        SELECT * FROM PRODUCTS;
      
#     테이블명 변경(객체명 변경)
    - RENAME OLD_NAME TO NEW_NAME;
      
      EMP30 테이블 이름을 EMP_TEMP30으로 변경하세요
      RENAME EMP30 TO EMP_TEMP30;
      
    SELECT * FROM TAB;
    SELECT * FROM EMP_TEMP30;

# 테이블 삭제 
    DROP TABLE 테이블명 [CASCADE CONSTRAINT]
    -   DROP TABLE 문장은 오러클 테이블 정의를 삭제한다.
	  테이블을 삭제할 때 테이터베이스는 테이블에 있는 모든 자료와
	  그와 연관된 모든 INDEX를 DROP 하고 사용하고 있던
	  공간을 돌려준다.

   EMP_TEMP30  테이블을 삭제하세요
   
   DROP TABLE EMP_TEMP30;
    
   SELECT * FROM TAB; 

   -- 휴지통에 가있는 삭제 테이블마저도 완전히 삭제
   DROP TABLE EMP20 PURGE;
   -- 휴지통 비우기
   PURGE RECYCLEBIN;
   
   SELECT * FROM DEPT_TAB;
   SELECT * FROM EMP_TAB;
   
   DEPT_TAB 테이블을 삭제하세요
   
   DROP TABLE DEPT_TAB CASCADE CONSTRAINT;
   
   [1] 자식 테이블을 먼저 DROP하고 부모 테이블을 DROP한다
   [2] DROP할때  CASCADE CONSTRAINT 옵션을 주어 삭제한다
   
   
# TRUNCATE
- 데이터를 삭제하는 DDL문장
- 데이터도 삭제하고, 사용하고 있던 저장공간도 반납한다
- 테이블 INDEX 도 삭제한다

TRUNCATE TABLE 테이블명

CREATE TABLE EMP_COPY
AS SELECT * FROM EMP;

SELECT * FROM EMP_COPY;

EMP_COPY의 데이터를 삭제하세요 (TRUNCATE)

TRUNCATE TABLE EMP_COPY;