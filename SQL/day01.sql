select * from tab;
-- 현재 커서의 sql문 실행: Ctlr+Enter
/*  학사 관리 프로그램
    학생 (Entity) ==> 물리적 (Table)   
*/
--테이블 생성 문장 (DDL - Date Definition Language)
--DDL문장: CREATE, DROP, RENAME TO,...
--CREATE TABLE 테이블명(
--컬럼명1 자료형(크기) 제약조건,
--컬럼명2 자료형(크기)...,
--...
--);
-----------------------------------
CREATE TABLE STUDENT(
    NUM NUMBER(4) PRIMARY KEY, -- UNIQUE + NON NULL
    NAME VARCHAR2(30) NOT NULL, -- 학생명 //CHAR : 고정크기, VARCHAR2 : 크기변동가능
    TEL VARCHAR2(15) NOT NULL, -- 연락처
    ADDR VARCHAR2(50), --주소 
    INDATE DATE DEFAULT SYSDATE, -- 등록일 //등록된 날의 날짜정보를 자동으로 넣어준다
    SNAME VARCHAR2(30), -- 학급명
    SROOM NUMBER(3) -- 교실번호
);

DESC STUDENT;

SELECT * FROM TAB;

--테이블 삭제
--DROP TABLE 테이블명

DROP TABLE STUDENT;
------------------------------------------------------------
--데이터 삽입 - INSERT 문
--
--INSERT INTO 테이블명(컬럼명1, 컬럼명2,...컬럼명N)
--            VALUES (값1, 값2,...값N) -- 1:1 매치가 되어야 함
------------------------------------------------------------
INSERT INTO STUDENT(NUM, NAME, TEL, ADDR, SNAME, SROOM)
            VALUES(1, 'GILDOND HONG', '010-345-6678', '서울','자바반', 201);
            
-- 데이터 조회--------------------------------
--SELECT 컬럼명1, 컬럼명2,... FROM 테이블명;
--SELECT * FROM 테이블명;
--------------------------------------------

SELECT NUM, SNAME, SROOM FROM STUDENT;
SELECT * FROM STUDENT;

2번 최철수  인천 자바반 데이터를 넣으세요

INSERT INTO STUDENT(NUM, NAME, TEL, ADDR, SNAME, SROOM)
            VALUES(2, 'CHEOLSU CHOI', '0103123123', '인천', '자바반', 201);

SELECT * FROM STUDENT;


--DML (INSERT, DELETE, UPDATE문) 문장은 COMMIT 또는 ROLLBACK 명령문을 이용해서
--트랜잭션을 마무리 해야 한다
--- COMMIT : 데이터베이스에 데이터를 영구히 저장
--- ROLLBACK : 방금 했던 DML작업을 취소

COMMIT;

3 이민기 연락처, 수원, 빅데이터, 301호
INSERT INTO STUDENT(NUM, NAME, TEL, ADDR, SNAME, SROOM)
VALUES(3, 'MINKI LEE', '010-123-1234', '수원', '빅데이터', 301);

SELECT *FROM STUDENT;

ROLLBACK;
COMMIT;

--데이터를 모두 넣을시 컬럼명을 지정하지 않아도 된다
--다만 테이블을 생성했을 때 기술한 모든 값을 컬럼 순서대로 삽입하면 된다.
INSERT INTO  STUDENT
VALUES(4, 'BISI MIN', '01010101010', '부천', SYSDATE, '빅데이터','301');

COMMIT;

INSERT INTO  STUDENT
VALUES(5, 'BISI MIN', '01010101234', '세종', '23/09/17', NULL, NULL);

SELECT * FROM STUDENT;
COMMIT;
----------------------------------------------------------------
--5번 학생의 학급명을 '자바반'으로 수정하세요

--UPDATE 문 :
--UPDATE 테이블명 SET 컬럼명1=값1, 컬럼명2=값2
--WHERE 조건절;

UPDATE STUDENT SET SNAME='자바반';
--=>모든 레코드가 수정됨. WHERE절을 명시하는것이 좋다

UPDATE STUDENT SET SNAME='자바반' WHERE NUM=5;

--5번 학생의 학급번호를을 '201'으로 수정하세요
UPDATE STUDENT SET SROOM=201, TEL='010-2222-3333', SNAME='자바반' WHERE NUM=5;

SELECT * FROM STUDENT;
ROLLBACK;
COMMIT;
-------------------------------------------------------------------
--데이터 삭제: DELETE 문
--DELETE FROM 테이블명
--WHERE 조건절
-------------------------------------------------------------------
--2번 최철수 학생의 데이터를 삭제하세요
DELETE FROM STUDENT
WHERE NUM=2;
SELECT * FROM STUDENT;
COMMIT;
--------------------------------------------
응용SW가 기본적으로 갖는 기능
CRUD
C: CREATE ==> INSERT문
R: READ ==> SELECT문
U: UPDATE ==> UADATE문
D: DELETE ==> DELETE문
--------------------------------------------

--테이블 삭제
--DROP TABLE 테이블명;
-- DDL문장은 즉각적으로 영향을 미친다

DROP TABLE STUDENT;
SELECT * FROM TAB;

학급테이블 생성하기
테이블명: SCLASS
컬럼: 
학급번호: SNUM NUMBER(4) PK
학급명: SNAME VATCHAR2(30) NOT NULL
교실번호: SROOM NUMBER(3)

CREATE TABLE SCLASS(
SNUM NUMBER(4) PRIMARY KEY,
SNAME VARCHAR2(30) NOT NULL,
SROOM NUMBER(3)
);

SELECT * FROM TAB;

DESC SCLASS;

-- 학생테이블 만들기
-- 학급정보에 학급테이블의 학급번호를 참조하는 외래키로 만들어 보자

CREATE TABLE STUDENT(
    NUM NUMBER(4) PRIMARY KEY,
    NAME VARCHAR2(30) NOT NULL,
    TEL VARCHAR2(15) NOT NULL,
    ADDR VARCHAR(50),
    INDATE DATE DEFAULT SYSDATE,
    --학급번호를 외래키로
    SNUM_FK NUMBER(4) REFERENCES SCLASS (SNUM)
);

--외래키(fk)는 부모 테이블(SCLASS)의 pk를 참조하거나, UNIQUE 제약조건을 갖는 컬럼을 참조해야 한다
--부모 테이블에 등록되지 않은 값이 자식 테이블에 등록되거나 수정되는 것을 허용하지 않는다

--학급테이블에 
--10 자바반 201
--20 빅데이터반 301
--30 인공지능반 401
--데이터를 넣으시오

INSERT INTO SCLASS(SNUM, SNAME, SROOM)
        VALUES(10, '자바반', 201);
        
INSERT INTO SCLASS
        VALUES(20, '빅데이터반', 301);
        
INSERT INTO SCLASS
        VALUES(30, '인공지능반', 401);
        
SELECT * FROM SCLASS;
COMMIT;
        
--학생정보 넣기
--자바반 3명
--1 홍길동 연락처 서울 등록일 학급번호
--빅데이터반 2명
--인공지능반 1명

INSERT INTO STUDENT
        VALUES(1, 'GILDONG HONG', '010-1234-1234', 'SEOUL', SYSDATE, 10);
INSERT INTO STUDENT
        VALUES(2, 'KIMCHOI KANG', '010-4321-4321', 'JEJU', SYSDATE, 10);
INSERT INTO STUDENT
        VALUES(3, 'GONGMING DONG', '010-3213-1324', 'GANGWON', SYSDATE, 10);
INSERT INTO STUDENT
        VALUES(4, 'DONGIL PARK', '010-3213-1324', 'GANGWON', SYSDATE, 20);
INSERT INTO STUDENT
        VALUES(5, 'MIN LEE', '010-3213-1324', 'SUWON', SYSDATE, 20);        
INSERT INTO STUDENT
        VALUES(6, 'JONGSE LIM', '010-3213-1324', 'SEJONG', SYSDATE, 30);
        
SELECT * FROM STUDENT;
SELECT * FROM SCLASS;
COMMIT;

--INSERT INTO STUDENT
        --VALUES(7, 'JONGSE LIM', '010-3213-1324', 'IMJONG', SYSDATE, 50); --[X]
--integrity constraint (SCOTT.SYS_C007010) violated - parent key not found
--무결성 제약조건에 위배되었음 - 부모키가 발견되지 않았음

SELECT * FROM STUDENT;

---------------------------------------------------------------
--JOIN 문 : 2개 이상의 테이블을 하나로 합쳐서 보여준다
--이 때 PK와 FK가 같은 조건으로 조인을 하는 경우가 많음 => EQUI JOIN

SELECT NUM, NAME, SNUM_FK, SNAME, SROOM, TEL, ADDR, INDATE
FROM SCLASS JOIN STUDENT
ON SCLASS.SNUM = STUDENT.SNUM_FK; -- '=' 비교연산자임

자바반 교실이 201호에서 501호로 변경되었습니다

UPDATE SCLASS SET SROOM=501 WHERE SNAME='자바반';

------------------------------------------------------------

SELECT * FROM SCLASS;

--인공지능반의 개강이 취소되었습니다
--학급테이블에서 인공지능반(30번)을 삭제하세요

DELETE FROM SCLASS WHERE SNUM=30;
--ORA-02292: integrity constraint (SCOTT.SYS_C007010) violated - child record found

--자식 레코드가 존재하고 있는 상태에서 부모 테이블의 관련있는 데이터를 삭제할 수 없다
--자식 레코드를 먼저 지우고, 부모 레코드를 삭제해보자

DELETE FROM STUDENT WHERE NUM=6;
SELECT * FROM STUDENT;
DELETE FROM SCLASS WHERE SNUM=30;
SELECT * FROM SCLASS;

SELECT NUM, NAME, SNUM_FK, SNAME, SROOM, TEL, ADDR, INDATE
FROM SCLASS JOIN STUDENT
ON SCLASS.SNUM = STUDENT.SNUM_FK; -- '=' 비교연산자임


SELECT * FROM TAB;

SELECT * FROM MEMBER;
SELECT * FROM CATEGORY;
SELECT * FROM PRODUCTS;
SELECT * FROM SUPPLY_COMP;

-- 인사관리 관련 데이터베이스
SELECT * FROM DEPT; --부서
SELECT * FROM EMP; --사원
SELECT * FROM SALGRADE; --급여등급 테이블
------------------------------------------------
#데이터 조회
SELECT * FROM EMP;

사원테이블에서 사번, 사원명, 부서번호를 가져와 보여주세요

SELECT EMPNO, ENAME, DEPTNO FROM EMP;

산술표현식을 사용할 수도 있다

사원명, 급여, 급여의 10%인상액을 가져와 보여주세요

SELECT ENAME, SAL, SAL*1.1 AS SAL_UP FROM EMP;
-- ALIAS (별칭)을 지정할때 AS"별칭(공백없어야함)"
-- AS대신 "별칭"로 사용가능 ""사용시 공백가능

사번, 사원명, 급여, 보너스(COMM), 연봉(연산해서)을 가져와 출력하세요
SELECT EMPNO, ENAME, JOB, SAL, COMM, SAL*12+COMM "연봉", SAL*12+NVL(COMM,0) "1YEAR SALARY" FROM EMP;
#NVL함수: NULL값을 지정된 값으로 변환하여 연산하도록 함
NVL(컬럼, 변환할 값)

#NVL2함수
NVL2(컬럼, 값1, 값2): 컬럼값이 NULL이 아닐 경우에는 값1을 반환하고
                    NULL일 경우에는 값2를 반환하는 함수

--사원 테이블에서 관리자(MGR)이 있는 경우는 1, 없으면0을 출력하시오 / 별칭은 "직속상관유무"
SELECT EMPNO, ENAME, MGR, NVL2(MGR, 1,0)"직속상관유무" FROM EMP;

------------------------------------------------------
# 문자열 결합 연산자: ||
SELECT ENAME||' IS A '||JOB FROM EMP;

--문제] EMP테이블에서 이름과 연봉을 "KING: 1 YEAR SALARY = 60000"
--	형식으로 출력하라.

SELECT ENAME||': 1 YEAR SALAEY = '||(SAL*12+NVL(COMM,0)) FROM EMP;











