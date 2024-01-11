--day04_join.sql
SELECT * FROM TAB;

--  # TABLE JOIN
--  부서 테이블과 사원 테이블을 하나로 합쳐보자
--  CARTESIEN PROJECT

    select d.*, e.* from dept d, emp e;
    
    select * from emp;
    
--  쓸데없는 데이터 ==> 셀렉트한 요소들의 모든 조합으로 행들을 결합함
    ------------------------------------------------------
--  # inner join(내부조인), equi join
--  '=' 를 이용해서 조인 조건을 걸어준다
--  주로 pk, fk 값이 같은 조건인 경우를 많이 사용함

--  [1] old version
    SELECT E.*, D.*
    FROM DEPT D, EMP E
    WHERE D.DEPTNO = E.DEPTNO;

--  [2] 명시적 join절 사용 ==> 표준 sql
    SELECT E.*, D.*
    FROM DEPT D JOIN EMP E
    ON D.DEPTNO = E.DEPTNO;
    
    SELECT ENAME, JOB, E.DEPTNO, DNAME, LOC
    FROM DEPT D JOIN EMP E
    ON D.DEPTNO = E.DEPTNO;
    
--[실습]
--	 SALESMAN의 사원번호,이름,급여,부서명,근무지를 출력하여라.

    SELECT EMPNO, ENAME, JOB, SAL, DNAME, LOC
    FROM EMP E JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO AND JOB ='SALESMAN';
    
--    [문제]
--
--	[1] 서로 연관이 있는 카테고리 테이블과 상품 테이블을 이용하여 각 상품별로 카테고리
--	      이름과 상품 이름을 함께 보여주세요.    

--	..이 예제에서 두 개의 테이블을 서로 연결하는 키와 같은 역할을 하는 컬럼은
--	  카테고리 테이블의 category_code와 상품 테이블의 category_fk 이다.
--	  이와 같이 두 개의 테이블이 조인될 수 있는 것은 서로를 연결해 주는 키 값이
--	  있기 때문이며, 이 키 값을 이용하면 여러 개의 테이블을 보다 효율적으로 관리할
--	  수 있게 된다.

    SELECT CATEGORY_CODE, CATEGORY_NAME, PRODUCTS_NAME
    FROM CATEGORY C , PRODUCTS P
    WHERE C.CATEGORY_CODE = P.CATEGORY_FK; --구버전

    SELECT CATEGORY_CODE, CATEGORY_NAME, PRODUCTS_NAME
    FROM CATEGORY C JOIN PRODUCTS P
    ON C.CATEGORY_CODE = P.CATEGORY_FK; --표준
  
--	[2] 카테고리 테이블과 상품 테이블을 조인하여 화면에 출력하되 상품의 정보 중
--	      제조업체가 삼성인 상품의 정보만 추출하여 카테고리 이름과 상품이름, 상품가격
--	      제조사 등의 정보를 화면에 보여주세요.
    SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE, COMPANY
    FROM CATEGORY C JOIN PRODUCTS P
    ON C.CATEGORY_CODE = P.CATEGORY_FK AND COMPANY = '삼성';

--	[3] 각 상품별로 카테고리 및 상품명, 가격을 출력하세요. 단 카테고리가 'TV'인 것은 
--	      제외하고 나머지 정보는 상품의 가격이 저렴한 순으로 정렬하세요.
    SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE
    FROM CATEGORY C , PRODUCTS P
    WHERE C.CATEGORY_CODE = P.CATEGORY_FK AND CATEGORY_NAME <> 'TV'
    ORDER BY 3 ASC; --구버전
        
    SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE
    FROM CATEGORY C JOIN PRODUCTS P
    ON C.CATEGORY_CODE = P.CATEGORY_FK AND CATEGORY_NAME != 'TV'
    ORDER BY OUTPUT_PRICE ASC; --표준
    
--  # NON-EQUI JOIN
--  EQUAL('=')이 아닌 연산기호를 이용해 조인하는 경우

    SELECT * FROM SALGRADE;
    
    SELECT EMPNO, ENAME, SAL, GRADE, LOSAL, HISAL
    FROM EMP E JOIN SALGRADE S
    ON E.SAL BETWEEN S.LOSAL AND S.HISAL;
    
--  # OUTER JOIN
--[1] OLD VERSION
    SELECT EMPNO, ENAME, E.DEPTNO, D.DEPTNO, DNAME
    FROM DEPT D, EMP E
    WHERE D.DEPTNO = E.DEPTNO(+) 
    ORDER BY 3;

--[2] LEFT OUTER JOIN/ RIGHT OUTER JOIN/ FULL OUTER JOIN
--  <1> LEFT OUTER JOIN : 조인 조건 왼쪽에 위치한 테이블 관점의 데이터를 보여줌
--  왼쪽 테이블 데이터는 모두 출력 + 오른쪽테이블(기준에 맞는 것만)
    SELECT D.*, ENAME, JOB
    FROM DEPT D LEFT OUTER JOIN EMP E
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
--  <2> RIGHT OUTER JOIN : 조인 조건 오른쪽에 위치한 테이블 관점의 데이터를 보여줌   
--  오른쪽 테이블 데이터는 모두 출력 + 왼쪽테이블 (기준에 맞는 것만 )
    SELECT D.*, ENAME, JOB, D.DEPTNO
    FROM DEPT D RIGHT OUTER JOIN EMP E
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
    SELECT D.*, ENAME, JOB, D.DEPTNO
    FROM EMP E RIGHT OUTER JOIN DEPT D
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
--  <3> FULL OUTER JOIN : 양쪽 테이블에 다 outer join을 거는것을 TWO-WAY OUTER JOIN 
--	또는 FULL OUTER JOIN이라 함    
    SELECT D.*, ENAME, JOB, D.DEPTNO
    FROM EMP E FULL OUTER JOIN DEPT D
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
    SELECT DISTINCT(E.DEPTNO), D.DEPTNO
    FROM EMP E FULL OUTER JOIN DEPT D
    ON E.DEPTNO=D.DEPTNO 
    ORDER BY 1;
    
--  문제98] 상품테이블의 모든 상품을 공급업체(SUPPLY_COMP), 공급업체코드, 상품이름, 
--          상품공급가, 상품 판매가 순서로 출력하되 공급업체가 없는
--          상품도 출력하세요(상품을 기준으로).
    SELECT PRODUCTS_NAME, EP_CODE, EP_NAME, INPUT_PRICE, OUTPUT_PRICE
    FROM PRODUCTS P, SUPPLY_COMP S
    WHERE P.EP_CODE_FK = S.EP_CODE(+) 
    ORDER BY 2;

    SELECT EP_NAME, S.EP_CODE, PRODUCTS_NAME, INPUT_PRICE, OUTPUT_PRICE
    FROM SUPPLY_COMP S LEFT OUTER JOIN PRODUCTS P
    ON S.EP_CODE = P.EP_CODE_FK
    ORDER BY 2;
    
    SELECT EP_NAME, S.EP_CODE, PRODUCTS_NAME, INPUT_PRICE, OUTPUT_PRICE
    FROM SUPPLY_COMP S RIGHT OUTER JOIN PRODUCTS P
    ON S.EP_CODE = P.EP_CODE_FK
    ORDER BY 2;
    
    SELECT EP_NAME, S.EP_CODE, PRODUCTS_NAME, INPUT_PRICE, OUTPUT_PRICE
    FROM SUPPLY_COMP S FULL OUTER JOIN PRODUCTS P
    ON S.EP_CODE = P.EP_CODE_FK
    ORDER BY 2;

--	문제99] 상품테이블의 모든 상품을 공급업체, 카테고리명, 상품명, 상품판매가
--		순서로 출력하세요. 단, 공급업체나 상품 카테고리가 없는 상품도
--		출력합니다.
    
    SELECT EP_CODE, EP_NAME, CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE
    FROM SUPPLY_COMP S RIGHT OUTER JOIN PRODUCTS P
    ON S.EP_CODE = P.EP_CODE_FK
    LEFT OUTER JOIN CATEGORY C
    ON C.CATEGORY_CODE = P.CATEGORY_FK
    ORDER BY 1;
    
--  # SELF JOIN : 자기  테이블과 조인 하는 경우
--
--  - self join을 사용하여 한 테이블의 행들을 같은 테이블의 행들과 조인한다.
--	- 같은 테이블에 대해 두개의 alias를 작성하여 테이블을 구분함으로
--	   from 절에 두 개의 테이블을 사용하는 것 같이 한다.
--	- 컬럼에 대해서도 어떤 테이블에서 왔는지 반드시 별칭을 기술해야 한다.
--	- 테이블 하나를 두 개 또는 그 이상으로 self join할 수 있다.
        --INNER SELF JOIN
    SELECT E.EMPNO, E.ENAME, E.MGR, M.EMPNO, M.ENAME
    FROM EMP E JOIN EMP M
    ON E.MGR = M.EMPNO;
        --OUTER SELF JOIN
    SELECT E.EMPNO, E.ENAME, E.MGR, M.EMPNO, M.ENAME
    FROM EMP E LEFT OUTER JOIN EMP M
    ON E.MGR = M.EMPNO;
    
    SELECT E.EMPNO, E.ENAME, E.MGR, M.EMPNO, M.ENAME
    FROM EMP E RIGHT OUTER JOIN EMP M
    ON E.MGR = M.EMPNO;
    
--  [문제] emp테이블에서 "누구의 관리자는 누구이다"는 내용을 출력하세요.
    SELECT E.ENAME||'의 관리자는 '||M.ENAME||'이다'
    FROM EMP E JOIN EMP M
    ON E.MGR = M.EMPNO;
    
    SELECT E.ENAME,'의 관리자는 ',M.ENAME,'이다'
    FROM EMP E JOIN EMP M
    ON E.MGR = M.EMPNO;
    
--  # SET OPERATOR - 집합 연산자
--  UNION, UNION ALL : 합집합
--  INTERSECT : 교집합
--  MINUS : 차집합

    SELECT DEPTNO FROM DEPT
    UNION
    SELECT DEPTNO FROM EMP;
    -- 중복되는 데이터는 한번만 출력
        
    SELECT DEPTNO FROM DEPT
    UNION ALL
    SELECT DEPTNO FROM EMP;    
    -- 각 결과의 합집합 (중복데이터 출력)
    
    SELECT DEPTNO FROM DEPT
    INTERSECT
    SELECT DEPTNO FROM EMP;
    -- 교집합
    
    SELECT DEPTNO FROM DEPT
    MINUS
    SELECT DEPTNO FROM EMP;
    -- 차집합
    
--    1. emp테이블에서 모든 사원에 대한 이름,부서번호,부서명을 출력하는 문장을 작성
        SELECT ENAME, E.DEPTNO, DNAME
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO;
        
        SELECT ENAME, D.DEPTNO, DNAME
        FROM EMP E RIGHT OUTER JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO;
                        
--    2. emp테이블에서 NEW YORK에서 근무하고 있는 사원에 대하여 이름,업무,급여,
--        부서명을 출력하는 SELECT문을 작성하세요.
        SELECT ENAME, JOB, SAL, DNAME, LOC
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND D.LOC='NEW YORK';
        
--    3. EMP테이블에서 보너스를 받는 사원에 대하여 이름,부서명,위치를 출력하는
--        SELECT문을 작성하세요.
        SELECT ENAME, DNAME, LOC, COMM
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND COMM IS NOT NULL;
        
        SELECT ENAME, DNAME, LOC, COMM
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND COMM > 0;
    
--    4. EMP테이블에서 이름 중 L자가 있는 사원에 대해 이름,업무,부서명,위치를 
--       출력하는 문장을 작성하세요.
        SELECT ENAME, JOB, DNAME, LOC
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND ENAME LIKE '%L%';
--      
--    5. 아래의 결과를 출력하는 문장을 작성하에요(관리자가 없는 King을 포함하여
--        모든 사원을 출력)
--    
--        ---------------------------------------------
--        Emplyee		Emp#		Manager	Mgr#
--        ---------------------------------------------
--        KING		7839
--        BLAKE		7698		KING		7839
--        CKARK		7782		KING		7839
--        .....
--        ---------------------------------------------
--        14ROWS SELECTED.
    
        SELECT E.ENAME Employee, E.EMPNO "EMP#", M.ENAME MANAGER, M.EMPNO "MGR#"
        FROM EMP E LEFT OUTER JOIN EMP M
        ON E.MGR = M.EMPNO;
        
--        SELECT E.ENAME Employee, E.EMPNO "EMP#", M.ENAME MANAGER, M.EMPNO "MGR#"
--        FROM EMP E RIGHT OUTER JOIN EMP M
--        ON E.MGR = M.EMPNO;
        
    
    
    
    
    
    
    