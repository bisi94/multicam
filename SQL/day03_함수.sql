--  day03_함수.sql
------------------------------------------------------------------
--  DDL(DATA DEFINITION LANGUAGE) : CREATE, DROP, ALTER, TRUNCATE
--  DML(DATA MANIPULATION LANGUAGE) : INSERT, UPDATE, DELETE
--  DQL(DATA QUERY LANGUAGE) : SELECT
--  DCL(DATA CONTROL LANGUAGE) : GRANT TO, REVOKE FROM
--  TCL(TRANSACTION CONTROL LANGUAGE):COMMIT, ROLLBACK
------------------------------------------------------------------

--# 함수
--[1] 단일행 함수
--[2] 그룹 함수
--[3] 기타 함수
-------------------------------------------
--#[1] 단일행 함수
--    <1> 문자형 함수
--    <2> 숫자형 함수
--    <3> 날짜형 함수
--    <4> 변환 함수
--    <5> 기타
--    
--# <1> 문자형 함수 
--(1) LOWER() / UPPER() : 소문자/대문자로 변환
SELECT LOWER('Happy Birthday'), UPPER('Happy Birthday') FROM DUAL;

SELECT * FROM DUAL;

SELECT 2*7 FROM DUAL;

--(2) initCap() : 첫글자를 대문자, 나머지를 소문자
SELECT DEPTNO, DNAME, INITCAP(DNAME), LOC, INITCAP(LOC) FROM DEPT;

--(3) CONCAT(컬럼1, 컬럼2) : 두 컬럼을 연결해준다
SELECT CONCAT('ABC', '1234') FROM DUAL;
SELECT EMPNO, ENAME, CONCAT(CONCAT(ENAME,' '),JOB) E_JOB FROM EMP;

--  [문제] 사원 테이블에서 SCOTT의 사번,이름,담당업무(소문자로),부서번호를 출력하세요.
SELECT EMPNO, ENAME, LOWER(JOB), DEPTNO FROM EMP WHERE ENAME=UPPER('SCOTT');

--  상품 테이블에서 판매가를 화면에 보여줄 때 금액의 단위를 함께 붙여서 출력하세요.
SELECT PRODUCTS_NAME, CONCAT(OUTPUT_PRICE, 'WON') OUT_PRICE
FROM PRODUCTS;

--(4) SUBSTR(컬럼, START, LEN) : 문자열의 일부를 추출하는 함수
SELECT SUBSTR('ABCDEFG',3,4) FROM DUAL; --CDEF
SELECT '050130-3012345' FROM DUAL;

--주민번호 뒷자리를 추출해보세요
SELECT SUBSTR('050130-3012345',8,7) FROM DUAL;
SELECT SUBSTR('050130-3012345',-7) FROM DUAL;
SELECT SUBSTR('050130-3012345',8) FROM DUAL;
--음수값을 넣으면 문자 끝에서부터 카운팅

--05년01월30일 로 출력해보세요
SELECT SUBSTR('050130-3012345',1,2)||'년'||SUBSTR('050130-3012345',3,2)||'월'||SUBSTR('050130-3012345',5,2)||'일' FROM DUAL;

--(5) LECGTH() : 문자열 길이 반환
--[문제]
--	  사원 테이블에서 첫글자가 'K'보다 크고 'Y'보다 작은 사원의
--	  사번,이름,업무,급여를 출력하세요. 단 이름순으로 정렬하세요.
    SELECT ENAME FROM EMP 
    WHERE SUBSTR(ENAME,1,1) > 'K' AND SUBSTR(ENAME,1,1)<'Y'
    ORDER BY ENAME ASC; 

--      사원테이블에서 부서가 20번인 사원의 사번,이름,이름자릿수,
--	급여,급여의 자릿수를 출력하세요.
	SELECT DEPTNO, EMPNO, ENAME, LENGTH(ENAME), SAL, LENGTH(SAL) FROM EMP 
    WHERE DEPTNO ='20';
    
	
--	사원테이블의 사원이름 중 6자리 이상을 차지하는 사원의이름과 
--	이름자릿수를 보여주세요.
    SELECT ENAME, LENGTH(ENAME)
    FROM EMP WHERE LENGTH(ENAME)>=6;
    
--  (6) LPAD(컬럼, LEN, CH) / RPAD()
    SELECT ENAME, LPAD(ENAME,15,'*'), LPAD(SAL,10,'#')
    FROM EMP;
    SELECT RPAD(DNAME,15,'*') FROM DEPT;

--  (7) LTRIM(컬럼, CH) / RTRIM()
    SELECT LTRIM('tttHello test','t'), RTRIM('tttHello test','t') FROM DUAL;
    SELECT LENGTH(LTRIM(' HI ORACLE ',' ')) FROM DUAL; --공백 제거여부 확인
    
--  (8) REPLACE(컬럼, 문자1, 문자2): 컬럼에서 문자ㅏ1을 찾아서 문자2로 대치
    SELECT REPLACE('ORACLE TEST','TEST','BYE') FROM DUAL;

--[문제]
--	사원테이블에서 10번 부서의 사원에 대해 담당업무 중 우측에'T'를
--	삭제하고 급여중 우측의 0을 삭제하여 출력하세요.
    SELECT JOB, RTRIM(JOB,'T'),SAL, RTRIM(SAL,0) FROM EMP 
    WHERE DEPTNO = '10';

--  사원테이블 JOB에서 'A'를 '$'로 바꾸어 출력하세요.
    SELECT JOB, REPLACE(JOB,'A','$') FROM EMP;

--  고객 테이블의 직업에 해당하는 컬럼에서 직업 정보가 학생인 정보를 모두
--	 대학생으로 변경해 출력되게 하세요.
    SELECT NAME, JOB, REPLACE(JOB,'학생','대학생') FROM MEMBER;
    
--  고객 테이블 주소에서 서울시를 서울특별시로 수정하세요.
    UPDATE MEMBER SET ADDR = REPLACE(ADDR,'서울시','서울특별시') 
    WHERE ADDR LIKE '%서울시%';
    
    SELECT * FROM MEMBER;
    ROLLBACK;
----------------------------------------------------------------
--# <2> 숫자형 함수

--(1) ROUND(값), ROUND(값,N)
--N이 양수인 경우 소수점을 기준으로 오른쪽으로 N 위치의 값을 반올림함. 즉 N+1 자리값을 보고 5이상이면 올리고 미만이면 버린다
--N이 음수인 경우는 소수점을 기준으로 왼쪽으로 N 위치에서 반올림 여부를 정함 ==> 정수
SELECT ROUND(4567.678), ROUND(4567.678,0), ROUND(4567.678,2), ROUND(4567.678,-2) FROM DUAL;

--(2) TRUNK(값), TRUNK(값,N) : 버림
SELECT TRUNC(4567.678), TRUNC(4567.678,0), TRUNC(4567.678,2), TRUNC(4567.678,-2) FROM DUAL;

--(3) MOD(값1,값2) : 값1을 값2로 나눈 나머지값을 반환
SELECT MOD(10,3) FROM DUAL;

--(4) ABS(값) : 절대값을 반환
SELECT ABS(8), ABS(-8) FROM DUAL;

--(5) CEIL(값) : 올림
--(6) FLOOR(값) : 내림

SELECT CEIL(500.001), FLOOR(500.001) FROM DUAL;

--  [문제]
--	상품 테이블의 상품 정보가운데 천원단위까지 버린 배송비를 비교하여 출력하세요.
    SELECT TRANS_COST, TRUNC(TRANS_COST,-4) FROM PRODUCTS;
--  사원테이블에서 부서번호가 10인 사원의 급여를 30으로 나눈 나머지를 출력하세요.
    SELECT SAL, MOD(SAL,30), DEPTNO FROM EMP 
    WHERE DEPTNO = '10';

--  MEMBER테이블에서 각 회원들의 나이가 40세와 몇살차이가 나는지 이름,나이, 나이차 순으로
--  출력하세요
    SELECT NAME, AGE, ABS(AGE-40)"나이차이" FROM MEMBER ORDER BY 2 DESC;
----------------------------------------------------
--# <3> 날짜함수
--
--날짜 연산
--DATE + 숫자 : 일수를 더함
--DATE - 숫자 : 일수를 뺌
--DATE - DATE : 일수
--DATE +/- 숫자/24 : 시간을 더하거나 뺀다

SELECT SYSDATE "오늘", SYSDATE+3 "3일 후", SYSDATE-3 "3일 전" FROM DUAL;

SELECT CEIL(TO_DATE('23/09/30','YY/MM/DD') - SYSDATE) FROM DUAL;

SELECT TO_CHAR(SYSDATE +3/24, 'YY/MM/DD HH:MI:SS') FROM DUAL;

--2시간 전의 시간정보
SELECT TO_CHAR(SYSDATE -2/24, 'YY/MM/DD HH:MI:SS') FROM DUAL;

--[실습]
--	사원테이블에서 현재까지의 근무 일수가 몇 주 몇일인가를 출력하세요.
--	단 근무일수가 많은 사람순으로 출려하세요.
    SELECT ENAME, SYSDATE, HIREDATE, TRUNC((SYSDATE-HIREDATE)/7) "WEEKS",
    TRUNC(MOD(SYSDATE-HIREDATE,7)) DAYS
    FROM EMP;
    
--  (1) MONTHS_BETWEEN(DATE1, DATE2)    : 두 날짜 사이의 월수를 반환
    SELECT MONTHS_BETWEEN(SYSDATE, TO_DATE('23/03/01','YY/MM/DD')) FROM DUAL;
        
--  (2) ADD_MONTHS(DATE, N) : 날짜에 N월 만큼 더한 날짜를 반환
--  오늘로부터 5개월 뒤, 2개월 전 날짜를 구해 출력하세요
    SELECT SYSDATE, ADD_MONTHS(SYSDATE,5)"5달후", ADD_MONTHS(SYSDATE,-2)"2달전" FROM DUAL;
    
--  (3) LAST_DAY(DATE) : 월의 마지막 날짜를 구할 때 사용
    SELECT LAST_DAY('23/02/01'), LAST_DAY('22/02/01'), LAST_DAY('24/02/01') FROM DUAL;
    
--  (4) SYSDATE : 현재 날짜와 시간값을 반환
--  (5) SYSTIMESTAMP : 현재 날짜와 시간값을 반환
    SELECT SYSDATE, TO_CHAR(SYSDATE,'YYYY-MM-DD HH:MI:SS') FROM DUAL;
    SELECT SYSTIMESTAMP FROM DUAL;

    SELECT TO_CHAR(SYSDATE,'CC YEAR-MONTH-DDD DAY') FROM DUAL;
    
--# <4> 변환함수
--    (1) TO_CHAR(날짜)
--        TO_CHAR(숫자)
--        : 날짜 또는 숫자를 문자열(VARCHAR2)로 변환하는 함수
        
        SELECT TO_CHAR(SYSDATE), TO_CHAR(SYSDATE,'YYY-MON-DD HH12:MI:SS') FROM DUAL;

--고객테이블의 등록일자를 0000-00-00 의 형태로 출력하세요.
--	 
--	 고객테이블에 있는 고객 정보 중 등록연도가 2013년인 
--	 고객의 정보를 보여주세요.
    SELECT NAME, AGE, REG_DATE FROM MEMBER 
    WHERE TO_CHAR(REG_DATE,'YYYY')='2013';
    
--	 고객테이블에 있는 고객 정보 중 등록일자가 2013년 5월1일보다 
--	 늦은 정보를 출력하세요. 
    SELECT REG_DATE FROM MEMBER WHERE REG_DATE > '13/05/01'; 
    
--	 단, 고객등록 정보는 년, 월만 보이도록 합니다.
    SELECT NAME, TO_CHAR(REG_DATE,'YYYY-MM')"등록 일자" FROM MEMBER 
    WHERE REG_DATE > '13/05/01'; 

    SELECT TO_CHAR(1000000,'9,999,999') FROM DUAL;
    SELECT TO_CHAR(25000,'$99G999') FROM DUAL;
    SELECT TO_CHAR(25000,'L99,999') FROM DUAL;
    SELECT TO_CHAR(-23,'S999'), TO_CHAR(-23,'999S') FROM DUAL;
    SELECT TO_CHAR(23,'99.99'), TO_CHAR(23,'99D99') FROM DUAL;
    SELECT TO_CHAR(23,'99.99EEEE') FROM DUAL;
    
-- (2)TO_NUMBER(문자열, 출력포맷) : 문자열(VARCHAR2, CHAR)을 숫자(NUMBER)로 변환
    SELECT TO_NUMBER('125,300', '999,999') + 100000 FROM DUAL;
    
-- '$25,000' 값의 2배 값을 구해 출력하세요
    SELECT TO_CHAR(TO_NUMBER('$25,000','$99,999')*2,'$99,999') FROM DUAL;
    --문자열을 숫자 형태로 변경 후 계산 뒤 다시 문자열로 변경
    
--    상품 테이블에서 상품의 공급 금액을 가격 표시 방법으로 표시하세요.
--	  천자리 마다 , 를 표시합니다.
    SELECT PRODUCTS_NAME, INPUT_PRICE, TO_CHAR(INPUT_PRICE,'999,999,999')"공급가격" FROM PRODUCTS;
--	
--	 상품 테이블에서 상품의 판매가를 출력하되 주화를 표시할 때 사용하는 방법을
--	  사용하여 출력하세요.[예:\10,000]
    SELECT PRODUCTS_NAME, OUTPUT_PRICE, TO_CHAR(OUTPUT_PRICE,'L999,999,999')"판매가격" FROM PRODUCTS;
    
--    (3) TO_DATE(문자열, 출력형식) : 문자 데이터를 날짜 유형으로 변환
    
    SELECT ADD_MONTHS('20230512',5) FROM DUAL;
    SELECT TRUNC(TO_DATE('20230512','YYYYMMDD') - SYSDATE) FROM DUAL;
    
--    EMP에서 입사 년도별 입사한 사원수를 구해 출력하세요
--    GROUP BY, COUNT(*)
    SELECT HIREDATE,COUNT(*) FROM EMP
    GROUP BY HIREDATE;
    
    SELECT TO_CHAR(HIREDATE,'YY'),COUNT(*) FROM EMP
    GROUP BY TO_CHAR(HIREDATE,'YY')
    ORDER BY 1;
    -- GROUP BY 를 사용할 때 셀렉트 하지 않은 객체에 대해서는 그룹화 할 수 없다

-- # 다중행 함수- 그룹함수, 집계함수
--  (1) COUNT([DISTINCT|ALL] 컬럼명|*)
        SELECT COUNT(EMPNO) FROM EMP; --전체 사원수
        SELECT COUNT(MGR) FROM EMP;   --관리받는 사원수
        SELECT COUNT(DISTINCT MGR) FROM EMP; -- 관리자 수
        
        CREATE TABLE TEST(
        A NUMBER(2),
        B NUMBER(2),
        C NUMBER(2)
        );
        
        DESC TEST;
        
        SELECT * FROM TEST;
        SELECT COUNT(*) FROM TEST;
        INSERT INTO TEST(A,B,C) VALUES(NULL,NULL,NULL);
        COMMIT;
        
        SELECT COUNT(A) FROM TEST;
        --> 컬럼명을 지정하면 NULL값은 카운팅하지 않음 
        
        SELECT COUNT(*) FROM TEST;
        -->NULL값도 카운팅
        
--    COUNT(pk컬럼) => 권장
--    PK(NOT NULL + UNIQUE)

--    (2) AVG(): 평균값
--        MAX(): 최대값
--        MIN(): 최소값
--        SUM(): 합계값
        
        
--        [실습]
--		 emp테이블에서 모든 SALESMAN에 대하여 급여의 평균,
--		 최고액,최저액,합계를 구하여 출력하세요
        
        SELECT AVG(SAL), MAX(SAL), MIN(SAL), SUM(SAL) 
        FROM EMP WHERE JOB='SALESMAN';
        
        SELECT ENAME, SAL FROM EMP WHERE JOB='SALESMAN';
        
        SELECT MIN(ENAME), MAX(ENAME), MIN(HIREDATE), MAX(HIREDATE), MIN(SAL), MAX(SAL)
        FROM EMP;
        
--        [문제]
--		EMP테이블에 등록되어 있는 인원수, 보너스에 NULL이 아닌 인원수,
--		보너스의 평균,등록되어 있는 부서의 수를 구하여 출력하세요.
        SELECT COUNT(EMPNO), COUNT(COMM), AVG(COMM), COUNT(DEPTNO), COUNT(DISTINCT DEPTNO)
        FROM EMP;
        
--  # 그룹함수같이 사용하는 GROUP BY절
    
--    16] 고객 테이블에서 직업의 종류와 각 직업에 속한 사람의 수를 보여주시오.
        SELECT JOB, COUNT(*) FROM MEMBER
        GROUP BY JOB;
--    17] 고객 테이블에서 직업의 종류, 각 직업에 속한 최대 마일리지 정보를 보여주세요.
        SELECT JOB, MAX(MILEAGE) FROM MEMBER
        GROUP BY JOB;
--    18] 상품 테이블에서 각 상품카테고리별로 총 몇 개의 상품이 있는지 보여주세요.
        SELECT CATEGORY_FK, COUNT(*), MAX(OUTPUT_PRICE), MIN(OUTPUT_PRICE) FROM PRODUCTS
        GROUP BY CATEGORY_FK
        ORDER BY CATEGORY_FK;

--    19] 상품 테이블에서 각 공급업체 코드별로 공급한 상품의 평균입고가를 보여주세요.
        SELECT EP_CODE_FK, ROUND(AVG(INPUT_PRICE),1) FROM PRODUCTS
        GROUP BY EP_CODE_FK
        ORDER BY 2 DESC;

--	    문제1] 사원 테이블에서 입사한 년도별로 사원 수를 보여주세요.
        SELECT TO_CHAR(HIREDATE,'YYYY'), COUNT(*) FROM EMP
        GROUP BY TO_CHAR(HIREDATE,'YYYY')
        ORDER BY TO_CHAR(HIREDATE,'YYYY');
        
--	    문제2] 사원 테이블에서 해당년도 각 월별로 입사한 사원수를 보여주세요.
        SELECT TO_CHAR(HIREDATE,'YYYY-MM'), COUNT(*) FROM EMP 
        GROUP BY TO_CHAR(HIREDATE,'YYYY-MM')
        ORDER BY TO_CHAR(HIREDATE,'YYYY-MM');
--	    문제3] 사원 테이블에서 업무별 최대 연봉, 최소 연봉을 출력하세요
        SELECT JOB, MIN(SAL), MAX(SAL) FROM EMP
        GROUP BY JOB;

--    # HAVING 절 사용
--        GROUP BY에 조건을 주어 제한하고자 할 때 사용
        
        SELECT JOB, COUNT(*) FROM MEMBER
        GROUP BY JOB HAVING COUNT(*) > 1;
        
--        21] 고객 테이블에서 직업의 종류와 각 직업에 속한 최대 마일리지 정보를 보여주세요.
--	      단, 직업군의 최대 마일리지가 0인 경우는 제외시킵시다.
        SELECT JOB, MAX(MILEAGE) FROM MEMBER 
        GROUP BY JOB HAVING MAX(MILEAGE) != 0;
        
        
--        문제1] 상품 테이블에서 각 카테고리별로 상품을 묶은 경우, 해당 카테고리의 상품이 2개인 
--              상품군의 정보를 보여주세요.
        SELECT CATEGORY_FK, COUNT(*) 
        FROM PRODUCTS
        GROUP BY CATEGORY_FK 
        HAVING COUNT(*) = 2
        ORDER BY 2 DESC;
--    
--        문제2] 상품 테이블에서 각 공급업체 코드별로 상품 판매가의 평균값 중 단위가 100단위로 떨어
--              지는 항목의 정보를 보여주세요.
        SELECT EP_CODE_FK, AVG(OUTPUT_PRICE)
        FROM PRODUCTS
        GROUP BY EP_CODE_FK
        HAVING MOD(AVG(OUTPUT_PRICE),100)=0;
        
        SELECT EP_CODE_FK, AVG(OUTPUT_PRICE)
        FROM PRODUCTS
        GROUP BY EP_CODE_FK
        HAVING AVG(OUTPUT_PRICE) LIKE '%00';
        
--# 기타 함수
--    (1) NVL(), NVL2()
--    (2) DECODE()
--    (3) CASE
--    (4) RANK() OVER()
--    (4) ROW_NUMBER() OVER()

--# DECODE() 함수
--     DECODE(컬럼, 조건1, 결과값1, 조건2, 결과값2,..., 그외 나머지값)

    SELECT * FROM DEPT;
    SELECT * FROM EMP;
    
--    EMP에서 부서번호가 10번이면 '회계부서', 20번이면 '연구부서' 
--    30번이면 '영업부서' 40번이면 '운영부서' 부서명을 함께 출력
    
    SELECT EMPNO, ENAME, DEPTNO,
    DECODE(DEPTNO, 10, '회계부서', 20, '연구부서', 30, '영업부서', 40, '운영부서','기타부서') "부서명"
    FROM EMP;
    
--  JOB이 'CLERK'이면 급여를 15%, 'SALESMAN'이면 10%, 'MANAGER' 5%, 'ANALYST' 3%를 인상한고 사원명, 업무, 급여액, 인상액을 함께 보여주세요
    SELECT ENAME, JOB, SAL,
    DECODE(JOB, 'CLERK', SAL*1.15, 'SALESMAN', SAL*1.1, 'MANAGER', SAL*1.05, 'ANALYST', SAL*1.03, SAL) "인상된 급여"
    FROM EMP;
    
-- # CASE 함수
--    CASE 컬럼명
--        WHEN 조건1 THEN 표현식1
--        WHEN 조건2 THEN 표현식2
--        ..
--        ELSE 표현식
--    END
-----------------------------------
    SELECT ENAME, JOB, SAL,
    CASE JOB
        WHEN 'CLERK' THEN SAL*1.15
        WHEN 'SALESMAN' THEN SAL*1.1
        WHEN 'MANAGER' THEN SAL*1.05
        WHEN 'ANALYST' THEN SAL*1.03
        ELSE SAL
    END "인상된 급여"
    FROM EMP;
    
--    EMP 에서 사번, 사원명, 업무, 보너스를 함께 출력하되 
--    보너스(COMM)이 NULL일 경우는 옆에 '해당사항 없음', 
--    COMM이 0인경우는 '보너스 없음', 
--    COMM이 0보다 큰 경우는 '보너스 있음'을 함께 출력
--    컬럼명은 "보너스 수령 여부"

    SELECT EMPNO, ENAME, JOB, COMM,
    CASE 
        WHEN COMM IS NULL THEN '해당사항 없음'
        WHEN COMM=0 THEN '보너스 없음'
        WHEN COMM>0 THEN '보너스 있음: 수령액=>'||COMM
        ELSE '???'
    END AS "보너스 수령 여부"
    FROM EMP;
        
--# RANK() OVER() : 특정 기준으로 랭킹을 매겨 보여준다
--  RANK() OVER(분석절)

-- EMP에서 급여가 많은 순서대로 순위를 매기세요
    SELECT *
    FROM EMP ORDER BY SAL DESC;
    
    SELECT RANK() OVER(ORDER BY SAL DESC) RNK, EMP.*
    FROM EMP;

--  급여가 제일 많은 TOP3만 가져와 보여주세요
--    MYSQL : LIMIT 3
--    MS SQLSERVER : TOP 3
--    ORACLE : SUBQUERY
    SELECT * FROM (
        SELECT RANK() OVER(ORDER BY SAL DESC) RNK, E.*
        FROM EMP E
    )
    WHERE RNK <4;
    
--    FROM절 뒤에 붙인 ALIAS(별칭)는 어디서든 사용 가능
--    SELECT 안에서 붙인 ALIAS는 그 밖에선 사용할 수 없다
--------------------------------------------------------
--    # ROW_NUMBER() OVER(분석절)
--    : 분석절을 기준으로 행번호를 매겨준다
--    게시판에서 페이징 처리할 때 많이 사용한다

    SELECT *
    FROM EMP
    ORDER BY HIREDATE DESC;
    
    SELECT * FROM(
        SELECT ROW_NUMBER() OVER(ORDER BY HIREDATE DESC) RNUM, EMP.*
        FROM EMP)    
    WHERE RNUM BETWEEN 6 AND 10;
    ---------------------------------------------------
--  ROWNUM 의사열을 사용

    SELECT ROWNUM RN, EMP.*
    FROM EMP
    ORDER BY HIREDATE DESC;
    
--  ==> ORDER BY를 한 뒤에 ROWNUM을 붙이는 것이 아니라 
--  기존 데이터에 ROWNUM을 먼저 붙이고, 그 뒤에 ORDER BY를 한다

--    =>해결방법
--  [1] ORDER BY를 먼저 한다
--  [2] 그런 뒤 ROWNUM을 구힌다
    
    SELECT * FROM (
        SELECT ROWNUM RN, A.*
        FROM
        (SELECT * FROM EMP ORDER BY HIREDATE DESC) A
    )
    WHERE RN BETWEEN 6 AND 10;

