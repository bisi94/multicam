--day05_subquery.sql
-- # SUBQUERY
--  #[1] ������ ��������

-- ������̺��� scott�� �޿����� ���� ����� �����ȣ,�̸�,����.�޿��� ����ϼ���.

    SELECT SAL FROM EMP
    WHERE ENAME='SCOTT';
    
    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP
    WHERE SAL > 3000;
    
--  ==> SUBQUERY�� �����غ���

    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP
    WHERE SAL> (SELECT SAL FROM EMP WHERE ENAME='SCOTT');
    
--  ����2]������̺��� �޿��� ��պ��� ���� ����� ���,�̸�
--	����,�޿�,�μ���ȣ�� ����ϼ���.  
    SELECT EMPNO, ENAME, JOB, SAL, DEPTNO
    FROM EMP
    WHERE SAL < (SELECT AVG(SAL) FROM EMP) ORDER BY SAL;
    
--    ������̺��� ����� �޿��� 20�� �μ��� �ּұ޿�
--		���� ���� �μ��� �μ���ȣ�� �� �μ��� �ּұ޿��� ����ϼ���.
    
    SELECT  DEPTNO, MIN(SAL)
    FROM EMP
    GROUP BY DEPTNO 
    HAVING MIN(SAL) > (SELECT MIN(SAL) FROM EMP WHERE DEPTNO=20);
    
--  #[2] ������ ��������    
  
--    �ϳ� �̻��� ���� ��ȯ�ϴ� ���������� ������ ����������� ��
--		������ ���������� ���� �� ������ ��� ������ �����ڸ� ����Ѵ�.
--		������ �����ڴ� �ϳ� �̻��� ���� �䱸�Ѵ�.

--		������ SUBQUERY ������.
--		* IN ������
--		* ANY ������
--		* ALL ������
--		* EXISTS ������

--   �������� �ִ� �޿��� �޴� ����� 
--	 �����ȣ�� �̸��� ����ϼ���.

    SELECT EMPNO, ENAME, JOB, MAX(SAL)
    FROM EMP
    GROUP BY JOB; --[X]
    
--  ������ ���߿� �������� ����    
    SELECT EMPNO, ENAME, JOB, SAL
    FROM EMP
    WHERE (JOB, SAL)
    IN
    (SELECT JOB, MAX(SAL)
    FROM EMP
    GROUP BY JOB);
    
--    #ANY ������
--     : ANY�����ڴ� ���������� ������� ��� �ϳ��� ���̶� ������ �Ǹ� 
--	    ������� ��ȯ �Ѵ�
    
    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL > ANY (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN�� �ּұ޿����� ū �޿��� �޴� ��������� ��������� �ǹ�

    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL < ANY (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN�� �ִ�޿����� ���� �޿��� �޴� ��������� ��������� �ǹ�

--     #ALL ������
--     : ALL�����ڴ� ���������� ������� ��� ��� ���� ���� �Ǿ߸� 
--	    ������� ��ȯ �Ѵ�.
    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL > ALL (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN�� �ִ�޿����� ū �޿��� �޴� ��������� ��������� �ǹ�

    SELECT ENAME, SAL
    FROM EMP
    WHERE SAL < ALL (SELECT SAL FROM EMP WHERE JOB = 'SALESMAN');
--  ==> SALESMAN�� �ּұ޿����� ���� �޿��� �޴� ��������� ��������� �ǹ�
    
--    # EXISTS ������
--     - EXISTS �����ڸ� ����ϸ� ���������� �����Ͱ� �����ϴ°��� 
--	   ���θ� ���� ���� �����ϴ� ���鸸�� ����� ��ȯ�� �ش�. 
--	 - SUBQUERY���� ��� 1���� ���� RETURN�ϸ� ������ ���̰� 
--	    �׷��� ������ ���� �Դϴ�

    SELECT E.EMPNO, ENAME, JOB
    FROM EMP E
    WHERE EXISTS (SELECT EMPNO FROM EMP WHERE E.EMPNO = MGR);
    
--  #[3] ���߿� ��������
--  ���� �� ���������� ���������� ������� �ΰ� �̻��� �÷��� ��ȯ�ϴ� ���������� �ǹ�. 	
--	- ���������� �ѹ� ����Ǹ鼭 ��� ������ �˻��ؼ� �� ������ �Ѱ� �ش�.

--	�ǽ�] �μ����� �ּ� �޿��� �޴� ����� ���,�̸�,����,�μ���ȣ�� ����ϼ���. �� �������� �����ϼ���.
    
    SELECT EMPNO, ENAME, JOB, DEPTNO, SAL
    FROM EMP
    WHERE (DEPTNO, SAL) IN 
    (SELECT DEPTNO, MIN(SAL)
    FROM EMP
    GROUP BY DEPTNO)
    ORDER BY 4 ASC;
    
--    *SELECT������ �������� ���.
--	
--	84] �� ���̺� �ִ� �� ���� �� ���ϸ����� ���� ���� �ݾ��� �� ������ �����ּ���.
    SELECT *
    FROM MEMBER
    WHERE MILEAGE IN 
    (SELECT MAX(MILEAGE)
    FROM MEMBER);
    
--	85] ��ǰ ���̺� �ִ� ��ü ��ǰ ���� �� ��ǰ�� �ǸŰ����� 
--	    �ǸŰ����� ��պ��� ū  ��ǰ�� ����� �����ּ���. 
--	    ��, ����� ���� ���� ����� ������ ���� �Ǹ� ������
--	    50������ �Ѿ�� ��ǰ�� ���ܽ�Ű����.
    SELECT *
    FROM PRODUCTS
    WHERE OUTPUT_PRICE <= 500000 AND OUTPUT_PRICE > 
    (SELECT AVG(OUTPUT_PRICE)
    FROM PRODUCTS
    WHERE OUTPUT_PRICE <= 500000);
    

--	86] ��ǰ ���̺� �ִ� �ǸŰ��ݿ��� ��հ��� �̻��� ��ǰ ����� ���ϵ� �����
--	    ���� �� �ǸŰ����� �ִ��� ��ǰ�� �����ϰ� ����� ���ϼ���.
    SELECT AVG(OUTPUT_PRICE)
    FROM PRODUCTS
    WHERE OUTPUT_PRICE > 
    (SELECT AVG(OUTPUT_PRICE)
    FROM PRODUCTS
    WHERE OUTPUT_PRICE != 
    (SELECT MAX(OUTPUT_PRICE)
    FROM PRODUCTS)
    );
    
--  87] ��ǰ ī�װ� ���̺��� ī�װ� �̸��� ��ǻ�Ͷ�� �ܾ ���Ե� ī�װ���
--	    ���ϴ� ��ǰ ����� �����ּ���.
    SELECT * FROM PRODUCTS WHERE CATEGORY_FK IN
    (SELECT CATEGORY_CODE FROM CATEGORY WHERE CATEGORY_NAME LIKE '%��ǻ��%');
    
--	88] �� ���̺� �ִ� ������ �� ������ �������� ���� ���̰� ���� ����� ������
--	    ȭ�鿡 �����ּ���.
    SELECT * FROM MEMBER
    WHERE (JOB, AGE) IN
    (SELECT JOB, MAX(AGE)
    FROM MEMBER
    GROUP BY JOB)
    ORDER BY AGE DESC;
--------------------------------------------------------------
--    * UPDATE ������ ��������
--    UPDATE ���̺�� SET �÷���1 = ��1, �÷���2 = ��2... N TH
--    WHERE ������;

--  89] �� ���̺� �ִ� �� ���� �� ���ϸ����� ���� ���� �ݾ���
--	     ������ ������ ���ʽ� ���ϸ��� 5000���� �� �ִ� SQL�� �ۼ��ϼ���.
    UPDATE MEMBER SET MILEAGE = MILEAGE + 5000
    WHERE MILEAGE IN (SELECT MAX(MILEAGE) FROM MEMBER);
    ROLLBACK;
    SELECT * FROM MEMBER;

--	90] �� ���̺��� ���ϸ����� ���� ���� ������ڸ� �� ���̺��� 
--	      ������� �� ���� �ڿ� ����� ��¥�� ���ϴ� ������ �����ϼ���
    UPDATE MEMBER SET REG_DATE = (SELECT MAX(REG_DATE) FROM MEMBER)
    WHERE MILEAGE = 0;
    ROLLBACK;
    SELECT * FROM MEMBER;

-- DELETE������ ���
--    DELETE FROM ���̺�� WHERE ������
--
--	91] ��ǰ ���̺� �ִ� ��ǰ ���� �� ���ް��� ���� ū ��ǰ�� ���� ��Ű�� 
--	      SQL���� �ۼ��ϼ���.
    DELETE FROM PRODUCTS WHERE INPUT_PRICE IN (SELECT MAX(INPUT_PRICE) FROM PRODUCTS);
    ROLLBACK;
    SELECT * FROM PRODUCTS;

--	92] ��ǰ ���̺��� ��ǰ ����� ���� ��ü���� ������ ��,
--	     �� ���޾�ü���� �ּ� �Ǹ� ������ ���� ��ǰ�� �����ϼ���.

    DELETE FROM PRODUCTS
    WHERE (EP_CODE_FK, OUTPUT_PRICE) 
    IN (SELECT EP_CODE_FK, MIN(OUTPUT_PRICE) FROM PRODUCTS GROUP BY EP_CODE_FK);
    ROLLBACK;
    SELECT * FROM PRODUCTS ORDER BY EP_CODE_FK;
----------------------------------------------------------------    
--  * INSERT ������ SUBQUERY���
--    - INSERT INTO ���̺��(�÷���1, �÷���2,...)
--                  VALUES(��1, ��2,...)
--    - INSERT INTO ���̺��(�÷���1, �÷���2,...)
--                  SUBQUERY

--    EMP ���̺��� ������ �����ؼ� EMP_20 ���̺��� ������
    CREATE TABLE EMP_10
    AS SELECT * FROM EMP WHERE DEPTNO=10;
    
    SELECT * FROM EMP_10;
    
    CREATE TABLE EMP_20
    AS SELECT * FROM EMP WHERE DEPTNO=100;
    
    SELECT * FROM EMP_20;

    CREATE TABLE EMP_30
    AS SELECT * FROM EMP WHERE 1=2;
    
    SELECT * FROM EMP_30;
    
--   EMP_20 ���̺� EMP���� 20�� �μ� ��������� ������ �����ϼ���
    INSERT INTO EMP_20
    SELECT * FROM EMP WHERE DEPTNO=20;
    
    SELECT * FROM EMP_20;
    ROLLBACK;
    
    COMMIT;
    
--    # FROM�������� ��������(INLINE VIEW)
--	
--	SUBQUERY�� FROM�������� ��� �����ϴ�.
--	�ϳ��� ���̺��� �ڷ��� ���� ���� ��� FROM���� ���̺� ��ü��
--	����Ͽ� ����ϸ� ȿ���� ������ �� �ִ�.
--	�̷� ��� �ʿ��� ��� ���� �����Ͽ� FROM���� ����ϸ� ����Ŭ
--	������ ����ȭ �ܰ迡�� ȿ������ �˻��� �� �� �ִ�.
--	��ó�� FROM���� ����� ���������� ��ġ VIEW�� ���� ������ �Ѵ�.
--	�̷� ���� INLINE VIEW��� �Ѵ�.
--
--	[�ǽ�]
--	EMP�� DEPT ���̺��� ������ MANAGER�� ����� �̸�, ����,�μ���,
--	�ٹ����� ����ϼ���.

--    - JOIN ��
    SELECT ENAME, JOB, DNAME, LOC
    FROM EMP E JOIN DEPT D
    ON E.DEPTNO = D.DEPTNO AND JOB = 'MANAGER';
    
--    - SUBQUERY �̿�
    SELECT ENAME, JOB, DNAME, LOC 
    FROM(SELECT * FROM EMP WHERE JOB = 'MANAGER') A JOIN DEPT D
    ON A.DEPTNO = D.DEPTNO;
    
    
--  EMP TABLE���� �޿��� ���� ������� 5���� ��� ����� �����ϼ���
    SELECT * FROM(
    SELECT RANK() OVER(ORDER BY SAL ASC) RNK, EMP.*
    FROM EMP
    )WHERE RNK > 0 AND RNK < 6;
------------------------------------------------------------
--  �̸��� ���� �並 �����غ��� (�ٸ� �並 ������� CREATE VIEW ������ DBA�κ��� �ο� �޾ƾ� �Ѵ�)
    CREATE VIEW SAL_ASC_VIEW
    AS 
    SELECT RANK() OVER(ORDER BY SAL ASC) RNK, EMP.* FROM EMP;
    
--  => insufficient privilleges
    
--------------------SCOTT�� CREATE VIEW���Ѻο�-------------------
--    SYSTEM / oracle ==> window
--    SYSTEM / master1234 ==> mac
--    ���� �� ��
--    show user
--    grant create view to scott;
--    
--    �ٽ� scott/tiger�� ������ �ڿ�
--    
--    CREATE VIEW SAL_ASC_VIEW
--    AS 
--    SELECT RANK() OVER(ORDER BY SAL ASC) RNK, EMP.* FROM EMP;
--    
--    �����ϱ�
---------------------------------------------------------------
    SELECT * FROM SAL_ASC_VIEW WHERE RNK BETWEEN 1 AND 5;
    
    
    
    
    
    