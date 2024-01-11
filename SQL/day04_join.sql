--day04_join.sql
SELECT * FROM TAB;

--  # TABLE JOIN
--  �μ� ���̺�� ��� ���̺��� �ϳ��� ���ĺ���
--  CARTESIEN PROJECT

    select d.*, e.* from dept d, emp e;
    
    select * from emp;
    
--  �������� ������ ==> ����Ʈ�� ��ҵ��� ��� �������� ����� ������
    ------------------------------------------------------
--  # inner join(��������), equi join
--  '=' �� �̿��ؼ� ���� ������ �ɾ��ش�
--  �ַ� pk, fk ���� ���� ������ ��츦 ���� �����

--  [1] old version
    SELECT E.*, D.*
    FROM DEPT D, EMP E
    WHERE D.DEPTNO = E.DEPTNO;

--  [2] ����� join�� ��� ==> ǥ�� sql
    SELECT E.*, D.*
    FROM DEPT D JOIN EMP E
    ON D.DEPTNO = E.DEPTNO;
    
    SELECT ENAME, JOB, E.DEPTNO, DNAME, LOC
    FROM DEPT D JOIN EMP E
    ON D.DEPTNO = E.DEPTNO;
    
--[�ǽ�]
--	 SALESMAN�� �����ȣ,�̸�,�޿�,�μ���,�ٹ����� ����Ͽ���.

    SELECT EMPNO, ENAME, JOB, SAL, DNAME, LOC
    FROM EMP E JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO AND JOB ='SALESMAN';
    
--    [����]
--
--	[1] ���� ������ �ִ� ī�װ� ���̺�� ��ǰ ���̺��� �̿��Ͽ� �� ��ǰ���� ī�װ�
--	      �̸��� ��ǰ �̸��� �Բ� �����ּ���.    

--	..�� �������� �� ���� ���̺��� ���� �����ϴ� Ű�� ���� ������ �ϴ� �÷���
--	  ī�װ� ���̺��� category_code�� ��ǰ ���̺��� category_fk �̴�.
--	  �̿� ���� �� ���� ���̺��� ���ε� �� �ִ� ���� ���θ� ������ �ִ� Ű ����
--	  �ֱ� �����̸�, �� Ű ���� �̿��ϸ� ���� ���� ���̺��� ���� ȿ�������� ������
--	  �� �ְ� �ȴ�.

    SELECT CATEGORY_CODE, CATEGORY_NAME, PRODUCTS_NAME
    FROM CATEGORY C , PRODUCTS P
    WHERE C.CATEGORY_CODE = P.CATEGORY_FK; --������

    SELECT CATEGORY_CODE, CATEGORY_NAME, PRODUCTS_NAME
    FROM CATEGORY C JOIN PRODUCTS P
    ON C.CATEGORY_CODE = P.CATEGORY_FK; --ǥ��
  
--	[2] ī�װ� ���̺�� ��ǰ ���̺��� �����Ͽ� ȭ�鿡 ����ϵ� ��ǰ�� ���� ��
--	      ������ü�� �Ｚ�� ��ǰ�� ������ �����Ͽ� ī�װ� �̸��� ��ǰ�̸�, ��ǰ����
--	      ������ ���� ������ ȭ�鿡 �����ּ���.
    SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE, COMPANY
    FROM CATEGORY C JOIN PRODUCTS P
    ON C.CATEGORY_CODE = P.CATEGORY_FK AND COMPANY = '�Ｚ';

--	[3] �� ��ǰ���� ī�װ� �� ��ǰ��, ������ ����ϼ���. �� ī�װ��� 'TV'�� ���� 
--	      �����ϰ� ������ ������ ��ǰ�� ������ ������ ������ �����ϼ���.
    SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE
    FROM CATEGORY C , PRODUCTS P
    WHERE C.CATEGORY_CODE = P.CATEGORY_FK AND CATEGORY_NAME <> 'TV'
    ORDER BY 3 ASC; --������
        
    SELECT CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE
    FROM CATEGORY C JOIN PRODUCTS P
    ON C.CATEGORY_CODE = P.CATEGORY_FK AND CATEGORY_NAME != 'TV'
    ORDER BY OUTPUT_PRICE ASC; --ǥ��
    
--  # NON-EQUI JOIN
--  EQUAL('=')�� �ƴ� �����ȣ�� �̿��� �����ϴ� ���

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
--  <1> LEFT OUTER JOIN : ���� ���� ���ʿ� ��ġ�� ���̺� ������ �����͸� ������
--  ���� ���̺� �����ʹ� ��� ��� + ���������̺�(���ؿ� �´� �͸�)
    SELECT D.*, ENAME, JOB
    FROM DEPT D LEFT OUTER JOIN EMP E
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
--  <2> RIGHT OUTER JOIN : ���� ���� �����ʿ� ��ġ�� ���̺� ������ �����͸� ������   
--  ������ ���̺� �����ʹ� ��� ��� + �������̺� (���ؿ� �´� �͸� )
    SELECT D.*, ENAME, JOB, D.DEPTNO
    FROM DEPT D RIGHT OUTER JOIN EMP E
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
    SELECT D.*, ENAME, JOB, D.DEPTNO
    FROM EMP E RIGHT OUTER JOIN DEPT D
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
--  <3> FULL OUTER JOIN : ���� ���̺� �� outer join�� �Ŵ°��� TWO-WAY OUTER JOIN 
--	�Ǵ� FULL OUTER JOIN�̶� ��    
    SELECT D.*, ENAME, JOB, D.DEPTNO
    FROM EMP E FULL OUTER JOIN DEPT D
    ON D.DEPTNO = E.DEPTNO 
    ORDER BY D.DEPTNO ASC;
    
    SELECT DISTINCT(E.DEPTNO), D.DEPTNO
    FROM EMP E FULL OUTER JOIN DEPT D
    ON E.DEPTNO=D.DEPTNO 
    ORDER BY 1;
    
--  ����98] ��ǰ���̺��� ��� ��ǰ�� ���޾�ü(SUPPLY_COMP), ���޾�ü�ڵ�, ��ǰ�̸�, 
--          ��ǰ���ް�, ��ǰ �ǸŰ� ������ ����ϵ� ���޾�ü�� ����
--          ��ǰ�� ����ϼ���(��ǰ�� ��������).
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

--	����99] ��ǰ���̺��� ��� ��ǰ�� ���޾�ü, ī�װ���, ��ǰ��, ��ǰ�ǸŰ�
--		������ ����ϼ���. ��, ���޾�ü�� ��ǰ ī�װ��� ���� ��ǰ��
--		����մϴ�.
    
    SELECT EP_CODE, EP_NAME, CATEGORY_NAME, PRODUCTS_NAME, OUTPUT_PRICE
    FROM SUPPLY_COMP S RIGHT OUTER JOIN PRODUCTS P
    ON S.EP_CODE = P.EP_CODE_FK
    LEFT OUTER JOIN CATEGORY C
    ON C.CATEGORY_CODE = P.CATEGORY_FK
    ORDER BY 1;
    
--  # SELF JOIN : �ڱ�  ���̺�� ���� �ϴ� ���
--
--  - self join�� ����Ͽ� �� ���̺��� ����� ���� ���̺��� ���� �����Ѵ�.
--	- ���� ���̺� ���� �ΰ��� alias�� �ۼ��Ͽ� ���̺��� ����������
--	   from ���� �� ���� ���̺��� ����ϴ� �� ���� �Ѵ�.
--	- �÷��� ���ؼ��� � ���̺��� �Դ��� �ݵ�� ��Ī�� ����ؾ� �Ѵ�.
--	- ���̺� �ϳ��� �� �� �Ǵ� �� �̻����� self join�� �� �ִ�.
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
    
--  [����] emp���̺��� "������ �����ڴ� �����̴�"�� ������ ����ϼ���.
    SELECT E.ENAME||'�� �����ڴ� '||M.ENAME||'�̴�'
    FROM EMP E JOIN EMP M
    ON E.MGR = M.EMPNO;
    
    SELECT E.ENAME,'�� �����ڴ� ',M.ENAME,'�̴�'
    FROM EMP E JOIN EMP M
    ON E.MGR = M.EMPNO;
    
--  # SET OPERATOR - ���� ������
--  UNION, UNION ALL : ������
--  INTERSECT : ������
--  MINUS : ������

    SELECT DEPTNO FROM DEPT
    UNION
    SELECT DEPTNO FROM EMP;
    -- �ߺ��Ǵ� �����ʹ� �ѹ��� ���
        
    SELECT DEPTNO FROM DEPT
    UNION ALL
    SELECT DEPTNO FROM EMP;    
    -- �� ����� ������ (�ߺ������� ���)
    
    SELECT DEPTNO FROM DEPT
    INTERSECT
    SELECT DEPTNO FROM EMP;
    -- ������
    
    SELECT DEPTNO FROM DEPT
    MINUS
    SELECT DEPTNO FROM EMP;
    -- ������
    
--    1. emp���̺��� ��� ����� ���� �̸�,�μ���ȣ,�μ����� ����ϴ� ������ �ۼ�
        SELECT ENAME, E.DEPTNO, DNAME
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO;
        
        SELECT ENAME, D.DEPTNO, DNAME
        FROM EMP E RIGHT OUTER JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO;
                        
--    2. emp���̺��� NEW YORK���� �ٹ��ϰ� �ִ� ����� ���Ͽ� �̸�,����,�޿�,
--        �μ����� ����ϴ� SELECT���� �ۼ��ϼ���.
        SELECT ENAME, JOB, SAL, DNAME, LOC
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND D.LOC='NEW YORK';
        
--    3. EMP���̺��� ���ʽ��� �޴� ����� ���Ͽ� �̸�,�μ���,��ġ�� ����ϴ�
--        SELECT���� �ۼ��ϼ���.
        SELECT ENAME, DNAME, LOC, COMM
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND COMM IS NOT NULL;
        
        SELECT ENAME, DNAME, LOC, COMM
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND COMM > 0;
    
--    4. EMP���̺��� �̸� �� L�ڰ� �ִ� ����� ���� �̸�,����,�μ���,��ġ�� 
--       ����ϴ� ������ �ۼ��ϼ���.
        SELECT ENAME, JOB, DNAME, LOC
        FROM EMP E JOIN DEPT D
        ON E.DEPTNO = D.DEPTNO AND ENAME LIKE '%L%';
--      
--    5. �Ʒ��� ����� ����ϴ� ������ �ۼ��Ͽ���(�����ڰ� ���� King�� �����Ͽ�
--        ��� ����� ���)
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
        
    
    
    
    
    
    
    