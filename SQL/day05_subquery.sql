--day05_subquery.sql
-- # SUBQUERY
--  #[1] 단일행 서브쿼리

-- 사원테이블에서 scott의 급여보다 많은 사원의 사원번호,이름,업무.급여를 출력하세요.

    SELECT SAL FROM EMP
    WHERE ENAME='SCOTT';
    
    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP
    WHERE SAL > 3000;
    
--  ==> SUBQUERY로 변경해보자

    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP
    WHERE SAL> (SELECT SAL FROM EMP WHERE ENAME='SCOTT');
    
--  문제2]사원테이블에서 급여의 평균보다 적은 사원의 사번,이름
--	업무,급여,부서번호를 출력하세요.  
    SELECT EMPNO, ENAME, JOB, SAL, DEPTNO
    FROM EMP
    WHERE SAL < (SELECT AVG(SAL) FROM EMP) ORDER BY SAL;
    
--    사원테이블에서 사원의 급여가 20번 부서의 최소급여
--		보다 많은 부서의 부서번호와 그 부서의 최소급여를 출력하세요.
    
    SELECT  DEPTNO, MIN(SAL)
    FROM EMP
    GROUP BY DEPTNO 
    HAVING MIN(SAL) > (SELECT MIN(SAL) FROM EMP WHERE DEPTNO=20);
    
--  #[2] 단일행 서브쿼리    
  
--    하나 이상의 행을 반환하는 서브쿼리를 다중행 서브쿼리라고 함
--		다중행 서브쿼리는 단일 행 연산자 대신 다중행 연산자를 사용한다.
--		다중행 연산자는 하나 이상의 값을 요구한다.

--		다중행 SUBQUERY 연산자.
--		* IN 연산자
--		* ANY 연산자
--		* ALL 연산자
--		* EXISTS 연산자

--   업무별로 최대 급여를 받는 사원의 
--	 사원번호와 이름을 출력하세요.

    SELECT EMPNO, ENAME, JOB, MAX(SAL)
    FROM EMP
    GROUP BY JOB; --[X]
    
--  다중행 다중열 서브쿼리 예제    
    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP
    WHERE (JOB, SAL)
    IN
    (SELECT JOB, MAX(SAL)
    FROM EMP
    GROUP BY JOB);
    
--    #ANY 연산자
--     : ANY연산자는 서브쿼리의 결과값중 어느 하나의 값이라도 만족이 되면 
--	    결과값을 반환 한다
    
    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL > ANY (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN의 최소급여보다 큰 급여를 받는 사원정보를 가져오라는 의미

    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL < ANY (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN의 최대급여보다 작은 급여를 받는 사원정보를 가져오라는 의미

--     #ALL 연산자
--     : ALL연산자는 서브쿼리의 결과값중 모든 결과 값이 만족 되야만 
--	    결과값을 반환 한다.
    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL > ALL (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN의 최대급여보다 큰 급여를 받는 사원정보를 가져오라는 의미

    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL < ALL (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN의 최소급여보다 작은 급여를 받는 사원정보를 가져오라는 의미
    
--    # EXISTS 연산자
--     - EXISTS 연산자를 사용하면 서브쿼리의 데이터가 존재하는가의 
--	   여부를 먼저 따져 존재하는 값들만을 결과로 반환해 준다. 
--	 - SUBQUERY에서 적어도 1개의 행을 RETURN하면 논리식은 참이고 
--	    그렇지 않으면 거짓 입니다

    SELECT E.EMPNO, ENAME, JOB
    FROM EMP E
    WHERE EXISTS (SELECT EMPNO FROM EMP WHERE E.EMPNO = MGR);
    
--  #[3] 다중열 서브쿼리
--  다중 열 서브쿼리란 서브쿼리의 결과값이 두개 이상의 컬럼을 반환하는 서브쿼리를 의미. 	
--	- 서브쿼리가 한번 실행되면서 모든 조건을 검색해서 주 쿼리로 넘겨 준다.

--	실습] 부서별로 최소 급여를 받는 사원의 사번,이름,업무,부서번호를 출력하세요. 단 업무별로 정렬하세요.
    
    SELECT EMPNO, ENAME, JOB, DEPTNO, SAL
    FROM EMP
    WHERE (DEPTNO, SAL) IN 
    (SELECT DEPTNO, MIN(SAL)
    FROM EMP
    GROUP BY DEPTNO)
    ORDER BY 4 ASC;
    
--    *SELECT문에서 서브쿼리 사용.
--	
--	84] 고객 테이블에 있는 고객 정보 중 마일리지가 가장 높은 금액의 고객 정보를 보여주세요.
    SELECT *
    FROM MEMBER
    WHERE MILEAGE IN 
    (SELECT MAX(MILEAGE)
    FROM MEMBER);
    
--	85] 상품 테이블에 있는 전체 상품 정보 중 상품의 판매가격이 
--	    판매가격의 평균보다 큰  상품의 목록을 보여주세요. 
--	    단, 평균을 구할 때와 결과를 보여줄 때의 판매 가격이
--	    50만원을 넘어가는 상품은 제외시키세요.
    SELECT *
    FROM PRODUCTS
    WHERE OUTPUT_PRICE <= 500000 AND OUTPUT_PRICE > 
    (SELECT AVG(OUTPUT_PRICE)
    FROM PRODUCTS
    WHERE OUTPUT_PRICE <= 500000);
    

--	86] 상품 테이블에 있는 판매가격에서 평균가격 이상의 상품 목록을 구하되 평균을
--	    구할 때 판매가격이 최대인 상품을 제외하고 평균을 구하세요.
    SELECT AVG(OUTPUT_PRICE)
    FROM PRODUCTS
    WHERE OUTPUT_PRICE > 
    (SELECT AVG(OUTPUT_PRICE)
    FROM PRODUCTS
    WHERE OUTPUT_PRICE != 
    (SELECT MAX(OUTPUT_PRICE)
    FROM PRODUCTS)
    );
    
--  87] 상품 카테고리 테이블에서 카테고리 이름에 컴퓨터라는 단어가 포함된 카테고리에
--	    속하는 상품 목록을 보여주세요.
    SELECT * FROM PRODUCTS WHERE CATEGORY_FK IN
    (SELECT CATEGORY_CODE FROM CATEGORY WHERE CATEGORY_NAME LIKE '%컴퓨터%');
    
--	88] 고객 테이블에 있는 고객정보 중 직업의 종류별로 가장 나이가 많은 사람의 정보를
--	    화면에 보여주세요.
    SELECT * FROM MEMBER
    WHERE (JOB, AGE) IN
    (SELECT JOB, MAX(AGE)
    FROM MEMBER
    GROUP BY JOB)
    ORDER BY AGE DESC;
--------------------------------------------------------------
--    * UPDATE 문에서 서브쿼리
--    UPDATE 테이블명 SET 컬럼명1 = 값1, 컬럼명2 = 값2... N TH
--    WHERE 조건절;

--  89] 고객 테이블에 있는 고객 정보 중 마일리지가 가장 높은 금액을
--	     가지는 고객에게 보너스 마일리지 5000점을 더 주는 SQL을 작성하세요.
    UPDATE MEMBER SET MILEAGE = MILEAGE + 5000
    WHERE MILEAGE IN (SELECT MAX(MILEAGE) FROM MEMBER);
    ROLLBACK;
    SELECT * FROM MEMBER;

--	90] 고객 테이블에서 마일리지가 없는 고객의 등록일자를 고객 테이블의 
--	      등록일자 중 가장 뒤에 등록한 날짜에 속하는 값으로 수정하세요
    UPDATE MEMBER SET REG_DATE = (SELECT MAX(REG_DATE) FROM MEMBER)
    WHERE MILEAGE = 0;
    ROLLBACK;
    SELECT * FROM MEMBER;

-- DELETE에서의 사용
--    DELETE FROM 테이블명 WHERE 조건절
--
--	91] 상품 테이블에 있는 상품 정보 중 공급가가 가장 큰 상품은 삭제 시키는 
--	      SQL문을 작성하세요.
    DELETE FROM PRODUCTS WHERE INPUT_PRICE IN (SELECT MAX(INPUT_PRICE) FROM PRODUCTS);
    ROLLBACK;
    SELECT * FROM PRODUCTS;

--	92] 상품 테이블에서 상품 목록을 공급 업체별로 정리한 뒤,
--	     각 공급업체별로 최소 판매 가격을 가진 상품을 삭제하세요.

    DELETE FROM PRODUCTS
    WHERE (EP_CODE_FK, OUTPUT_PRICE) 
    IN (SELECT EP_CODE_FK, MIN(OUTPUT_PRICE) FROM PRODUCTS GROUP BY EP_CODE_FK);
    ROLLBACK;
    SELECT * FROM PRODUCTS ORDER BY EP_CODE_FK;
----------------------------------------------------------------    
--  * INSERT 문에서 SUBQUERY사용
--    - INSERT INTO 테이블명(컬럼명1, 컬럼명2,...)
--                  VALUES(값1, 값2,...)
--    - INSERT INTO 테이블명(컬럼명1, 컬럼명2,...)
--                  SUBQUERY

--    EMP 테이블의 구조만 복사해서 EMP_20 테이블을 만들어보자
    CREATE TABLE EMP_10
    AS SELECT * FROM EMP WHERE DEPTNO=10;
    
    SELECT * FROM EMP_10;
    
    CREATE TABLE EMP_20
    AS SELECT * FROM EMP WHERE DEPTNO=100;
    
    SELECT * FROM EMP_20;

    CREATE TABLE EMP_30
    AS SELECT * FROM EMP WHERE 1=2;
    
    SELECT * FROM EMP_30;
    
--   EMP_20 테이블에 EMP에서 20번 부서 사원정보를 가져와 삽입하세요
    INSERT INTO EMP_20
    SELECT * FROM EMP WHERE DEPTNO=20;
    
    SELECT * FROM EMP_20;
    ROLLBACK;
    
    COMMIT;
    
--    # FROM절에서의 서브쿼리(INLINE VIEW)
--	
--	SUBQUERY는 FROM절에서도 사용 가능하다.
--	하나의 테이블에서 자료의 양이 많을 경우 FROM절에 테이블 전체를
--	기술하여 사용하면 효율이 떨어질 수 있다.
--	이런 경우 필요한 행과 열만 선택하여 FROM절에 기술하면 오러클
--	서버가 최적화 단계에서 효율적인 검색을 할 수 있다.
--	이처럼 FROM절에 기술한 서브쿼리는 마치 VIEW와 같은 역할을 한다.
--	이런 것을 INLINE VIEW라고 한다.
--
--	[실습]
--	EMP와 DEPT 테이블에서 업무가 MANAGER인 사원의 이름, 업무,부서명,
--	근무지를 출력하세요.

--    - JOIN 문
    SELECT ENAME, JOB, DNAME, LOC
    FROM EMP E JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO AND JOB = 'MANAGER';
    
--    - SUBQUERY 이용
    SELECT ENAME, JOB, DNAME, LOC 
    FROM(SELECT * FROM EMP WHERE JOB = 'MANAGER') A JOIN DEPT D
    ON A.DEPTNO = D.DEPTNO;
    
    
--  EMP TABLE에서 급여가 제일 작은사람 5명을 골라 명단을 구성하세요
    SELECT * FROM(
    SELECT RANK() OVER(ORDER BY SAL ASC) RNK, EMP.*
    FROM EMP
    )WHERE RNK > 0 AND RNK < 6;
------------------------------------------------------------
--  이름을 갖는 뷰를 생성해보자 (다만 뷰를 만들려면 CREATE VIEW 권한을 DBA로부터 부여 받아야 한다)
    CREATE VIEW SAL_ASC_VIEW
    AS 
    SELECT RANK() OVER(ORDER BY SAL ASC) RNK, EMP.* FROM EMP;
    
--  => insufficient privilleges
    
--------------------SCOTT에 CREATE VIEW권한부여-------------------
--    SYSTEM / oracle ==> window
--    SYSTEM / master1234 ==> mac
--    접속 한 뒤
--    show user
--    grant create view to scott;
--    
--    다시 scott/tiger로 접속한 뒤에
--    
--    CREATE VIEW SAL_ASC_VIEW
--    AS 
--    SELECT RANK() OVER(ORDER BY SAL ASC) RNK, EMP.* FROM EMP;
--    
--    실행하기
---------------------------------------------------------------
    SELECT * FROM SAL_ASC_VIEW WHERE RNK BETWEEN 1 AND 5;
    
    
    
    
    
    