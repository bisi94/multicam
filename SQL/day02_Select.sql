--day02_select.sql
select * from tab;

SELECT JOB FROM EMP;

--EMP에서 담당하고 있는 업무 종류를 출력하세요
SELECT DISTINCT JOB FROM EMP;
--DISTINCT -중복행의 제거 : SELECT 바로 다음에 기술
--DISTINCT 뒤에 나오는 컬럼들이 모두 DISTINCT의 영향을 받는다.
------------------------------------------------------
--MEMBER 테이블에서 NAME을 가져와서 출력하기
--MEMBER에서 동일한 이름은 한 번만 출력되게 하세요
SELECT NAME FROM MEMBER;
SELECT DISTINCT NAME FROM MEMBER;

/*[문제]
	 1] EMP테이블에서 중복되지 않는 부서번호를 출력하세요.
	 2] MEMBER테이블에서 회원의 이름과 나이 직업을 보여주세요.
	 3] CATEGORY 테이블에 저장된 모든 내용을 보여주세요.
	 4] MEMBER테이블에서 회원의 이름과 적립된 마일리지를 보여주되,
	      마일리지에 13을 곱한 결과를 "MILE_UP"이라는 별칭으로
	      함께 보여주세요*/
          
    --1]
    SELECT DISTINCT DEPTNO FROM EMP;
    --2]
    SELECT NAME, AGE, JOB FROM MEMBER;
    --3]
    SELECT * FROM CATEGORY;
    --4]
    SELECT NAME, MILEAGE, MILEAGE*13 "MILE_UP" FROM MEMBER;

--# 데이터의 제한
--WHERE  절을 이용
--SELECT 컬럼명,... FROM 테이블명
--WHERE 조건절
--
--[실습]
--	EMP테이블에서 급여가 3000 이상인 사원의 사원번호,이름,
--	담당업무,급여를 출력하세요.
    
    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP WHERE SAL >= 3000;
        
--EMP테이블에서 담당업무가 MANAGER인 사원의
--	정보를 사원번호,이름,업무,급여,부서번호로 출력하세요.

    SELECT EMPNO, ENAME, JOB, DEPTNO
    FROM EMP WHERE JOB = upper('manager');  
-- 값 자체는 대소문자를 구분함
    
--    
--	EMP테이블에서 1982년 1월1일 이후에 입사한 사원의 
--	사원번호,성명,업무,급여,입사일자를 출력하세요.
    SELECT EMPNO, ENAME, JOB, SAL, HIREDATE
    FROM EMP WHERE HIREDATE >= '1982-01-01';

--  emp테이블에서 급여가 1300에서 1500사이의 사원의 이름,업무,급여,
--	부서번호를 출력하세요.
    -- BETWEEN 연산자
    SELECT ENAME, JOB, SAL, DEPTNO
    FROM EMP WHERE SAL BETWEEN 1300 AND 1500;
    --AND OR 연산자
    SELECT ENAME, JOB, SAL, DEPTNO
    FROM EMP WHERE SAL >=1300 AND SAL<=1500;
    
--  emp테이블에서 사원번호가 7902,7788,7566인 사원의 사원번호,
--	이름,업무,급여,입사일자를 출력하세요.
    SELECT ENAME, JOB, SAL, HIREDATE
    FROM EMP WHERE EMPNO=7902 OR EMPNO=7788 OR EMPNO=7566;
    
    --IN(LIST)
    SELECT ENAME, JOB, SAL, HIREDATE
    FROM EMP WHERE EMPNO IN(7902, 7788, 7566);
    
--  10번 부서가 아닌 사원의 이름,업무,부서번호를 출력하세요
    SELECT ENAME, JOB, DEPTNO
    FROM EMP WHERE DEPTNO !=10 ORDER BY DEPTNO;
    
    SELECT ENAME, JOB, DEPTNO
    FROM EMP WHERE DEPTNO <> 10;
    
    SELECT ENAME, JOB, DEPTNO
    FROM EMP WHERE NOT DEPTNO IN(10);

--[문제]
--	emp테이블에서 업무가 SALESMAN 이거나 PRESIDENT인
--	사원의 사원번호,이름,업무,급여를 출력하세요.
    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP WHERE JOB='SALESMAN' OR JOB='PRESIDENT';
    

--	커미션(COMM)이 300이거나 500이거나 1400인 사원정보를 출력하세요
    SELECT *
    FROM EMP WHERE COMM IN (300, 500, 1400);

--	커미션이 300,500,1400이 아닌 사원의 정보를 출력하세요
    SELECT *
    FROM EMP WHERE COMM NOT IN (300, 500, 1400);

--	커미션이 NULL인 사원의 정보를 출력하세요
--    SELECT *
--    FROM EMP WHERE COMM=NULL;
--    ==> 데이터가 안나옴

--    NULL값 여부를 판단할 때는 등가연산자를 사용하면 안된다
--    IS NULL, IS NOT NULL을 이용해야 한다
    SELECT *
    FROM EMP WHERE COMM IS NULL;
    
    SELECT *
    FROM EMP WHERE COMM IS NOT NULL;

--    [실습]
--	- EMP테이블에서 이름이 S로 시작되는 사람의 정보를 보여주세요.

    SELECT * FROM EMP WHERE ENAME LIKE 'S%';

--	-이름 중 S자가 들어가는 사람의 정보를 보여주세요.
    SELECT * FROM EMP WHERE ENAME LIKE '%S%';
	
--	- 이름의 두번 째에 O자가 들어가는 사람의 정보를 보여주세요.
    SELECT * FROM EMP WHERE ENAME LIKE '_O%';
    
--    [문제]
--	
--	- 고객 테이블 가운데 성이 김씨인 사람의 정보를 보여주세요.
    SELECT * FROM MEMBER 
    WHERE NAME LIKE '김%';
--	
--	- 고객 테이블 가운데 '강남구'가 포함된 정보를 보여주세요.
    SELECT * FROM MEMBER 
    WHERE ADDR LIKE '%강북%';
--
--	- 카테고리 테이블 가운데 category_code가 0000로 끝나는 상품정보를 보여주세요.
    SELECT * FROM CATEGORY 
    WHERE CATEGORY_CODE LIKE '%0000';

--[문제]
--	- EMP테이블에서 급여가 1000이상 1500이하가 아닌 사원의
    SELECT * FROM EMP 
    WHERE SAL NOT BETWEEN 1000 AND 1500;
--  - EMP테이블에서 이름에 'S'자가 들어가지 않은 사람의 이름을 모두 출력하세요.
    SELECT ENAME FROM EMP
    WHERE ENAME NOT LIKE '%S%';
    
--  사원테이블에서 업무가 PRESIDENT이고 급여가 1500이상이거나
	   --업무가 SALESMAN인 사원의 사번,이름,업무,급여를 출력하세요.
       
    SELECT EMPNO, ENAME, JOB, SAL FROM EMP
    WHERE JOB = 'PRESIDENT' AND SAL >=1500 OR JOB = 'SALESMAN';


--  고객 테이블에서 이름이 홍길동이면서 직업이 학생이 정보를 모두 보여주세요.
    SELECT * FROM MEMBER
    WHERE NAME = '홍길동' AND JOB = '학생';
--  고객 테이블에서 이름이 홍길동이거나 직업이 학생이 정보를 모두 보여주세요.
    SELECT * FROM MEMBER
    WHERE NAME = '홍길동' OR JOB = '학생';
--  상품 테이블에서 제조사가 S사 또는 D사이면서 판매가가 100만원 미만의 상품 목록을 보여주세요.
    SELECT * FROM PRODUCTS
    WHERE (COMPANY = '삼성' OR COMPANY = '대우') AND OUTPUT_PRICE < 1000000;
---------------------------------------------------------------------------
/*   참고). 연산자간 우선순위
	   모든 비교 연산자 > NOT > AND > OR(오른쪽으로 갈수록 우선순위가 낮다.)*/
       
--    # ORDER BY 
--    어순 : WGHO순서 
--            WHERE > GROUP BY > HAVING > ORDER BY
--  사원테이블에서 입사일자 순으로 정렬하여 사번,이름,업무,급여,	입사일자를 출력하세요.
    SELECT EMPNO, ENAME, JOB, SAL, HIREDATE FROM EMP
    ORDER BY HIREDATE ASC;
       
--  최근 입사한 순으로 사번,이름,업무,급여,입사일자를 출력하세요.
    SELECT EMPNO, ENAME, JOB, SAL, HIREDATE FROM EMP
    ORDER BY HIREDATE DESC;
    
/*  사원 테이블에서 부서번호로 정렬한 후 부서번호가 같을 경우
	급여가 많은 순으로 정렬하여 사번,이름,업무,부서번호,급여를
	출력하세요.*/
    SELECT EMPNO, ENAME, JOB, DEPTNO, SAL FROM EMP
    ORDER BY DEPTNO, SAL DESC;
      
/*  사원 테이블에서 첫번째 정렬은 부서번호로, 두번째 정렬은
	업무로, 세번째 정렬은 급여가 많은 순으로 정렬하여
	사번,이름,입사일자,부서번호,업무,급여를 출력하세요*/
    SELECT * FROM EMP
    ORDER BY DEPTNO, JOB, SAL DESC;
    
    --#다양한 정렬 방법-------------------------------
    SELECT EMPNO, ENAME, JOB, SAL, SAL*12 ANNSAL
    FROM EMP ORDER BY SAL*12 DESC;
    SELECT EMPNO, ENAME, JOB, SAL, SAL*12 ANNSAL
    FROM EMP ORDER BY ANNSAL ASC;
    
    SELECT EMPNO, ENAME, JOB, SAL, SAL*12+NVL(COMM,0)
    FROM EMP ORDER BY 5 DESC;
    
    [문제]
	1] 상품 테이블에서 판매 가격이 저렴한 순서대로 상품을 정렬해서 
       보여주세요.
       SELECT PRODUCTS_NAME, OUTPUT_PRICE FROM PRODUCTS
       ORDER BY OUTPUT_PRICE;
		
	2] 고객 테이블의 정보를 이름의 가나다 순으로 정렬해서 보여주세요.
	   단, 이름이 같을 경우에는 나이가 많은 순서대로 보여주세요.
    	..정렬된 자료를 다시 한번 정렬하는 내용임.
       SELECT NAME, AGE FROM MEMBER
       ORDER BY NAME, AGE DESC;
        
	 3] 고객 테이블에서 직업의 종류와 각 직업에 속한 사람의 수가 
	     많은 순서대로 보여주세요.
         --GROUP BY, COUNT()함수
         SELECT JOB, COUNT(*) 
         FROM MEMBER
         GROUP BY JOB
         ORDER BY 2 DESC;

	 4]	상품테이블에서 공급업체별(EP_CODE_FK)로 평균판매가를 AVG(컬럼명) 구하되 
		평균판매가 오름차순으로 보여주세요.
        SELECT EP_CODE_FK, ROUND(AVG(OUTPUT_PRICE),2) FROM PRODUCTS
        GROUP BY EP_CODE_FK
        ORDER BY 2; -- 셀렉의 인덱스2를 타게팅함
        		
	 5] 상품 테이블에서 배송비의 내림차순으로 정렬하되, 
	    같은 배송비가 있는 경우에는
		마일리지의 내림차순으로 정렬하여 보여주세요.
        SELECT *FROM PRODUCTS ORDER BY TRANS_COST DESC, MILEAGE DESC;
    
    
    
    
    
    
    


