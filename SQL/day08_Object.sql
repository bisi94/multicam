--day08_Object.sql
--# ORACLE의 객체
--[1] SEQUENCE
--[2] VIEW
--[3] INDEX
--[4] SYNONYM
--
-- #[1] 시퀀스란? 
--
--◈ 유일(UNIQUE)한 값을 생성해주는 오라클 객체다. 
--◈ 시퀀스를 생성하면 기본키와 같이 순차적으로 
--   증가하는 컬럼을 
--   자동적으로 생성할수 있다. 
--◈ 보통 primary key 값을 생성하기 위해 사용한다. 
--◈ 메모리에 Cache되었을 때 Sequence 값의 
--   액세스 효율이 증가 한다. 
--◈ Sequence는 테이블과는 독립적으로 저장되고 생성됩니다. 
--    따라서 하나의 sequence를  여러 테이블에서 쓸 수 있다
--구문
--CREATE SEQUENCE 시퀀스명
--[INCREMENT BY n] -- 증가치
--[START WITH n] -- 시작값
--[{MAXVALUE n | NOMAXVALE}] - 시퀀스 최대값
--[{MINVALUE n | NOMINVALUE}] - 시퀀스 최소값
--[{CYCLE | NOCYCLE}] -- 반복여부
--[{CACHE | NOCACHE}] -- 캐시 사용 여부

SELECT * FROM DEPT2;

CREATE SEQUENCE DEPT2_SEQ
START WITH 60
INCREMENT BY 10
MAXVALUE 99
NOCACHE
NOCYCLE;

--PRIMARY KEY 에는 CYCLE 옵션 사용 불가

--  데이터 사전에서 조회
--  - USER_OBJECTS
--  - USER_SEQUENCES

SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='SEQUENCE';

SELECT * FROM USER_SEQUENCES;

--DEPT2에 'SALES2' 부서를 삽입하세요 위치 : '부산'
INSERT INTO DEPT2 ( DEPTNO, DNAME, LOC)
VALUES(DEPT2_SEQ.NEXTVAL, 'SALES2', 'BUSAN');

SELECT * FROM DEPT2 ORDER BY DEPTNO;

--'RESEARCH2' 'SUWON' 삽입하세요
INSERT INTO DEPT2
VALUES(DEPT2_SEQ.NEXTVAL, 'RESEARCH2', 'SUWON');

ROLLBACK;

--- 시퀀스 속성
--[1] NEXTVAL : NEXT VALUE (다음값)
--[2] CURRVAL : CURRENT VALUE (현재값)
--
--# SEQUENCE 사용법
--
--- NEXTVAL, CURRVAL을 사용하여 시퀀스 값을 참조한다.
--- NEXTVAL은 다음 사용가능한 시퀀스 값을 반환한다.
--- 시퀀스가 참조될 때마다 다른 사용자에게 조차도 유일한 값을 반환한다.
--- CURRVAL은 현재 SEQUENCE값을 얻는다.
--- CURRVAL이 참조되기 전에 NEXTVAL이 사용되어야 한다.
--
--**참고]
--어떤 세션에서 NEXTVAL 을 하지 않은 채 CURRVAL 을 요구하게 되면 에러가 
--난다. 그것은 CURRVAL 은 바로 그 세션이 지금 현재 가지고 있는 최종 시퀸스 값을 의미 
--하므로 한번도 NEXTVAL 을 요구한 적이 없다면 보유하고 있는 CURRVAL 값이 없기 
--때문이다.

SELECT DEPT2_SEQ.CURRVAL FROM DUAL;

--시퀀스명:  TEMP_SEQ
--시작값: 100
--증가치: -5씩 감소
--최소값: 0
--CYCLE옵션주기
--CACHE사용하기

CREATE SEQUENCE TEMP_SEQ
START WITH 100
INCREMENT BY -5
MAXVALUE 100
MINVALUE 0
CYCLE
CACHE 20;

SELECT TEMP_SEQ.CURRVAL FROM DUAL;

SELECT TEMP_SEQ.NEXTVAL FROM DUAL;

-- # 시퀀스 수정
-- ALTER SEQUENCE 시퀀스명
-- INCREMENT BY N
-- MINVALUE
-- MAXVALUE
-- CYCLE|NOCYCLE
-- CACHE|NOCACHE

-- # 시퀀스 삭제
--DROP SEQUENCE 시퀀스명;

--TEMP_SEQ를 다음과 같이 수정하세요
--증가치: 2
--MINVALUE : 100
--MAXVALUE: 150
--NOCYCLE
--NOCACHE
--로 수정하세요

ALTER SEQUENCE TEMP_SEQ
INCREMENT BY 2
MINVALUE 80
MAXVALUE 100
NOCYCLE
NOCACHE;

SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME='TEMP_SEQ';

--# TEMP_SEQ 시퀀스를 삭제하세요

DROP SEQUENCE TEMP_SEQ;

----------------------------------------------------------

--# [2] VIEW
--
--- 가상의 테이블
--- 복잡한 쿼리문을 단순화시킬 수 있다.
--- 데이터를 다양한 관점으로 볼 수 있다
--
--
-- # 뷰를 만드는 규칙
--	CREATE VIEW 뷰이름
--	AS
--	SELECT 컬럼명1, 컬럼명2...
--	FROM 뷰에 사용할 테이블명
--	WHERE 조건
--
--** 서브쿼리는 조인,그룹 등 복합 SELECT문을 포함할 수 있고 ORDER BY절을
--	    포함할 수 없다.
--	    VIEW를 삭제하거나 재생산 하지 않고VIEW의 정의를 변경하려면
--	    OR REPLACE옵션을 사용한다.
--	...주의] view를 만들기 위해서는 권한이 필요[dba권한으로 CREATE VIEW권한을 주자]

conn system/oracle

show user
--USER이(가) "SYSTEM"입니다.
grant create view to scott;
--Grant을(를) 성공했습니다.

conn scott/tiger
show user
-----------------------------------------------------------------------

--[실습] 
--    EMP테이블에서 20번 부서의 모든 컬럼을 포함하는 EMP20_VIEW를 생성하라.

CREATE VIEW EMP20_VIEW
AS
SELECT * FROM EMP WHERE DEPTNO=20;

SELECT * FROM EMP20_VIEW;

-- EMP20_VIEW에 사번, 부서번호, 이름, 업무만 포함되도록 수정하세요
--# VIEW의 수정은 OR REPLACE 옵션을 준다

CREATE OR REPLACE VIEW EMP20_VIEW  --생성하던지 대치해
AS SELECT EMPNO, ENAME, JOB, DEPTNO 
FROM EMP WHERE DEPTNO=20;

SELECT * FROM EMP20_VIEW;

-- [1] EMP테이블에서 30번 부서만 EMPNO를 EMP_NO로 ENAME을 NAME으로
--	SAL를 SALARY로 바꾸어 EMP30_VIEW를 생성하여라.
    CREATE OR REPLACE VIEW EMP30_VIEW
--  CREATE OR REPLACE VIEW EMP30_VIEW(EMP_NO, NAME, SALARY) >> 이것도 가능
    AS SELECT EMPNO "EMP_NO", ENAME "NAME", SAL "SALARY"
    FROM EMP WHERE DEPTNO=30;
    
    SELECT * FROM EMP30_VIEW;
    
-- [2] 고객테이블의 고객 정보 중 나이가 19세 이상인
--	고객의 정보를 확인하는 뷰를 만들어보세요.
--	단 뷰의 이름은 MEMBER_19로 하세요.

    CREATE OR REPLACE VIEW MEMBER_19
    AS
    SELECT USERID ID, NAME, AGE, JOB, ADDR ADDRESS 
    FROM MEMBER WHERE AGE>=19;
    
    SELECT * FROM MEMBER_19;
    
--  # 데이터 사전에서 조회 : USER_VIEWS

SELECT * FROM USER_VIEWS WHERE VIEW_NAME = UPPER('MEMBER_19');

-- # 뷰 삭제
--DROP VIEW 뷰이름
DROP VIEW MEMBER_19;

SELECT * FROM java_MEMBER;

--# view에서 dml연산

CREATE OR REPLACE VIEW SALES_VW
AS
SELECT * FROM EMP2 WHERE JOB='SALESMAN';

SELECT * FROM SALES_VW;
--#새로운 신입사원 등록
INSERT INTO EMP2 VALUES(8021,'SUSAN', 'SALESMAN', 7698, SYSDATE, 2000, 200, 30);
INSERT INTO SALES_VW VALUES(8022,'ANN', 'SALESMAN', 7698, SYSDATE, 1000, 500, 30);

SELECT * FROM EMP2;
COMMIT;

-- EMP2에서 7844번의 JOB을 'MANAGER'로 수정하세요
UPDATE EMP2 SET JOB='MANAGER', COMM = NULL WHERE EMPNO='7844';

SELECT * FROM EMP2;
SELECT * FROM SALES_VW;

--#DML 연산 부정=> WITH READ ONLY 옵션을 주어 생성한다

--JOB이 'ANALYST'인 사원의 뷰를 만들되, 읽기 전용으로 만드세요
--뷰이름 : ANALYST_VW

CREATE OR REPLACE VIEW ANALYST_VW
AS
SELECT * FROM EMP2 WHERE JOB='ANALYST' WITH READ ONLY;
SELECT * FROM ANALYST_VW;

UPDATE ANALYST_VW SET DEPTNO=10 WHERE EMPNO=7788;
--읽기 전용 뷰는 DML연산 불가

UPDATE EMP2 SET DEPTNO=10 WHERE EMPNO=7788;
-- 이렇게 원본에서 수정 가능
COMMIT;
-----------------------------------------------
--# WITH CHECK OPTION 절
--: WHERE 조건절에 부합되지 않는 데이터의 생성,수정을 허용하지 않는다.

-- EMP20VW 뷰를 생성하되 WITH CHECK OPTION 을 주세요

CREATE OR REPLACE VIEW EMP20VW
AS
SELECT * FROM EMP2 WHERE DEPTNO=20
WITH CHECK OPTION CONSTRAINT EMP20_CK;

SELECT * FROM EMP20VW;

--뷰에서 특정 사원 1명을 골라서 부서번호를 30번 부서로 수정하세요
    
UPDATE EMP20VW SET JOB='MANAGER' WHERE EMPNO=7369;

UPDATE EMP20VW SET DEPTNO=30 WHERE EMPNO=7566; -- 제약조건 안에서만 변경가능
    
UPDATE EMP2 SET DEPTNO=30 WHERE EMPNO=7369;

SELECT * FROM EMP20VW;
    
ROLLBACK;

SELECT * FROM USER_VIEWS WHERE VIEW_NAME='EMP20VW';

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='EMP20VW';

--# JOIN 문을 이용한 뷰를 생성하세요
--
--상품 정보를 보여주되 카테고리명과, 상품명, 판매가, 공급업체명, 공급가를
--함께 보여주는 뷰를 생성하세요
--
--CATEGORY, PRODUCTS, SUPPLY_COMP

CREATE OR REPLACE VIEW PRODUCTS_VIEW
AS
SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE, EP_NAME, INPUT_PRICE
FROM CATEGORY C JOIN PRODUCTS P
ON C.CATEGORY_CODE = P.CATEGORY_FK
LEFT OUTER JOIN SUPPLY_COMP S
ON P.EP_CODE_FK = S.EP_CODE;

SELECT * FROM PRODUCTS_VIEW;

-- JOIN문에 의해 생성된 뷰는 DML연산 불가
UPDATE PRODUCTS_VIEW SET OUTPUT_PRICE=35000 WHERE PRODUCTS_NAME='무따기시리즈';

--# INLINE VIEW
--- FROM 절에 오는 SUBQUERY 를 인라인 뷰라고 한다

SELECT ROWNUM, EMP2.* FROM EMP ORDER BY HIREDATE;

SELECT ROWNUM, EMP2VW.* FROM (
(SELECT * FROM EMP2 ORDER BY HIREDATE ASC) EMP2VW) WHERE ROWNUM <6;

--EMP2VW=>인라인뷰
--------------------------------------------------------------
--# [3] INDEX
--- 데이터 검색을 신속하게 할 수 있도록 하는 객체

--PRIMARY KEY, UNIQUE  제약조건을 주면 자동으로  UNIQUE 인덱스가 생성된다

--CREATE INDEX 인덱스명 ON 테이블명(컬럼명[,컬럼명]...)

--**주의: 인덱스는 NOT NULL인 컬럼에만 사용할 수 있다.
--	  NULL인 경우에는 인덱스를 정렬할 수 없기 때문에 사용 불가.

--EMP2에서 ENAME에 INDEX를 생성하세요
--인덱스명: EMP2_ENAME_INDX

CREATE INDEX EMP2_ENAME_INDX ON EMP2 (ENAME);

SELECT * FROM EMP2 WHERE ENAME LIKE '%S%';

--데이터 사전에서 조회
SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='INDEX';

SELECT * FROM USER_INDEXES WHERE INDEX_NAME='EMP2_ENAME_INDX';

SELECT * FROM USER_INDEXES WHERE TABLE_NAME='EMP2';

--생성한 인덱스 컬럼정보 조회: USER_IND_COLUMNS
--SELECT * FROM USER_IND_COLUMNS WHERE TABLE_NAME='EMP2';

--# 인덱스 수정=> 불가능
--수정하려면 삭제하고 다시 생성한다
--
--# 인덱스 삭제
--DROP INDEX 인덱스명;

--[문제1 ] 상품 테이블에서 인덱스를 걸어두면 좋을 컬럼(FK등등)을 찾아 인덱스를 만드세요.
--        2개 컬럼에 인덱스 생성하기
    CREATE INDEX PRODUCTS_CATEGORY_IDX ON PRODUCTS (CATEGORY_FK);
    CREATE INDEX PRODUCTS_EP_CODE_IDX ON PRODUCTS (EP_CODE_FK);
    
--        데이터 사전에서 조회하기
    SELECT * FROM USER_INDEXES WHERE TABLE_NAME='PRODUCTS';
--        
--[문제2] 2개 인덱스 중 1개를 골라 삭제하세요
--        데이터 사전에서 조회하기
    DROP INDEX PRODUCTS_EP_CODE_IDX;
    
    ------------------------------------------------------------
--# [4] SYNONYM (동의어)
--    
--    - 구문
--  CREATE [PUBLIC] SYNONYM 시노님명
--  FOR object_name;
--
--  PUBLIC: 모든 사용자가 접근 가능한 시노님을 생성
--          한다. PUBLIC시노님의 생성,삭제는 DBA만이
--	  할 수 있다.

