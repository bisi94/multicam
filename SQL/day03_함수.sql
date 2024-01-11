--  day03_�Լ�.sql
------------------------------------------------------------------
--  DDL(DATA DEFINITION LANGUAGE) : CREATE, DROP, ALTER, TRUNCATE
--  DML(DATA MANIPULATION LANGUAGE) : INSERT, UPDATE, DELETE
--  DQL(DATA QUERY LANGUAGE) : SELECT
--  DCL(DATA CONTROL LANGUAGE) : GRANT TO, REVOKE FROM
--  TCL(TRANSACTION CONTROL LANGUAGE):COMMIT, ROLLBACK
------------------------------------------------------------------

--# �Լ�
--[1] ������ �Լ�
--[2] �׷� �Լ�
--[3] ��Ÿ �Լ�
-------------------------------------------
--#[1] ������ �Լ�
--    <1> ������ �Լ�
--    <2> ������ �Լ�
--    <3> ��¥�� �Լ�
--    <4> ��ȯ �Լ�
--    <5> ��Ÿ
--    
--# <1> ������ �Լ� 
--(1) LOWER() / UPPER() : �ҹ���/�빮�ڷ� ��ȯ
SELECT LOWER('Happy Birthday'), UPPER('Happy Birthday') FROM DUAL;

SELECT * FROM DUAL;

SELECT 2*7 FROM DUAL;

--(2) initCap() : ù���ڸ� �빮��, �������� �ҹ���
SELECT DEPTNO, DNAME, INITCAP(DNAME), LOC, INITCAP(LOC) FROM DEPT;

--(3) CONCAT(�÷�1, �÷�2) : �� �÷��� �������ش�
SELECT CONCAT('ABC', '1234') FROM DUAL;
SELECT EMPNO, ENAME, CONCAT(CONCAT(ENAME,' '),JOB) E_JOB FROM EMP;

--  [����] ��� ���̺��� SCOTT�� ���,�̸�,������(�ҹ��ڷ�),�μ���ȣ�� ����ϼ���.
SELECT EMPNO, ENAME, LOWER(JOB), DEPTNO FROM EMP WHERE ENAME=UPPER('SCOTT');

--  ��ǰ ���̺��� �ǸŰ��� ȭ�鿡 ������ �� �ݾ��� ������ �Բ� �ٿ��� ����ϼ���.
SELECT PRODUCTS_NAME, CONCAT(OUTPUT_PRICE, 'WON') OUT_PRICE
FROM PRODUCTS;

--(4) SUBSTR(�÷�, START, LEN) : ���ڿ��� �Ϻθ� �����ϴ� �Լ�
SELECT SUBSTR('ABCDEFG',3,4) FROM DUAL; --CDEF
SELECT '050130-3012345' FROM DUAL;

--�ֹι�ȣ ���ڸ��� �����غ�����
SELECT SUBSTR('050130-3012345',8,7) FROM DUAL;
SELECT SUBSTR('050130-3012345',-7) FROM DUAL;
SELECT SUBSTR('050130-3012345',8) FROM DUAL;
--�������� ������ ���� ���������� ī����

--05��01��30�� �� ����غ�����
SELECT SUBSTR('050130-3012345',1,2)||'��'||SUBSTR('050130-3012345',3,2)||'��'||SUBSTR('050130-3012345',5,2)||'��' FROM DUAL;

--(5) LECGTH() : ���ڿ� ���� ��ȯ
--[����]
--	  ��� ���̺��� ù���ڰ� 'K'���� ũ�� 'Y'���� ���� �����
--	  ���,�̸�,����,�޿��� ����ϼ���. �� �̸������� �����ϼ���.
    SELECT ENAME FROM EMP 
    WHERE SUBSTR(ENAME,1,1) > 'K' AND SUBSTR(ENAME,1,1)<'Y'
    ORDER BY ENAME ASC; 

--      ������̺��� �μ��� 20���� ����� ���,�̸�,�̸��ڸ���,
--	�޿�,�޿��� �ڸ����� ����ϼ���.
	SELECT DEPTNO, EMPNO, ENAME, LENGTH(ENAME), SAL, LENGTH(SAL) FROM EMP 
    WHERE DEPTNO ='20';
    
	
--	������̺��� ����̸� �� 6�ڸ� �̻��� �����ϴ� ������̸��� 
--	�̸��ڸ����� �����ּ���.
    SELECT ENAME, LENGTH(ENAME)
    FROM EMP WHERE LENGTH(ENAME)>=6;
    
--  (6) LPAD(�÷�, LEN, CH) / RPAD()
    SELECT ENAME, LPAD(ENAME,15,'*'), LPAD(SAL,10,'#')
    FROM EMP;
    SELECT RPAD(DNAME,15,'*') FROM DEPT;

--  (7) LTRIM(�÷�, CH) / RTRIM()
    SELECT LTRIM('tttHello test','t'), RTRIM('tttHello test','t') FROM DUAL;
    SELECT LENGTH(LTRIM(' HI ORACLE ',' ')) FROM DUAL; --���� ���ſ��� Ȯ��
    
--  (8) REPLACE(�÷�, ����1, ����2): �÷����� ���ڤ�1�� ã�Ƽ� ����2�� ��ġ
    SELECT REPLACE('ORACLE TEST','TEST','BYE') FROM DUAL;

--[����]
--	������̺��� 10�� �μ��� ����� ���� ������ �� ������'T'��
--	�����ϰ� �޿��� ������ 0�� �����Ͽ� ����ϼ���.
    SELECT JOB, RTRIM(JOB,'T'),SAL, RTRIM(SAL,0) FROM EMP 
    WHERE DEPTNO = '10';

--  ������̺� JOB���� 'A'�� '$'�� �ٲپ� ����ϼ���.
    SELECT JOB, REPLACE(JOB,'A','$') FROM EMP;

--  �� ���̺��� ������ �ش��ϴ� �÷����� ���� ������ �л��� ������ ���
--	 ���л����� ������ ��µǰ� �ϼ���.
    SELECT NAME, JOB, REPLACE(JOB,'�л�','���л�') FROM MEMBER;
    
--  �� ���̺� �ּҿ��� ����ø� ����Ư���÷� �����ϼ���.
    UPDATE MEMBER SET ADDR = REPLACE(ADDR,'�����','����Ư����') 
    WHERE ADDR LIKE '%�����%';
    
    SELECT * FROM MEMBER;
    ROLLBACK;
----------------------------------------------------------------
--# <2> ������ �Լ�

--(1) ROUND(��), ROUND(��,N)
--N�� ����� ��� �Ҽ����� �������� ���������� N ��ġ�� ���� �ݿø���. �� N+1 �ڸ����� ���� 5�̻��̸� �ø��� �̸��̸� ������
--N�� ������ ���� �Ҽ����� �������� �������� N ��ġ���� �ݿø� ���θ� ���� ==> ����
SELECT ROUND(4567.678), ROUND(4567.678,0), ROUND(4567.678,2), ROUND(4567.678,-2) FROM DUAL;

--(2) TRUNK(��), TRUNK(��,N) : ����
SELECT TRUNC(4567.678), TRUNC(4567.678,0), TRUNC(4567.678,2), TRUNC(4567.678,-2) FROM DUAL;

--(3) MOD(��1,��2) : ��1�� ��2�� ���� ���������� ��ȯ
SELECT MOD(10,3) FROM DUAL;

--(4) ABS(��) : ���밪�� ��ȯ
SELECT ABS(8), ABS(-8) FROM DUAL;

--(5) CEIL(��) : �ø�
--(6) FLOOR(��) : ����

SELECT CEIL(500.001), FLOOR(500.001) FROM DUAL;

--  [����]
--	��ǰ ���̺��� ��ǰ ������� õ���������� ���� ��ۺ� ���Ͽ� ����ϼ���.
    SELECT TRANS_COST, TRUNC(TRANS_COST,-4) FROM PRODUCTS;
--  ������̺��� �μ���ȣ�� 10�� ����� �޿��� 30���� ���� �������� ����ϼ���.
    SELECT SAL, MOD(SAL,30), DEPTNO FROM EMP 
    WHERE DEPTNO = '10';

--  MEMBER���̺��� �� ȸ������ ���̰� 40���� ������̰� ������ �̸�,����, ������ ������
--  ����ϼ���
    SELECT NAME, AGE, ABS(AGE-40)"��������" FROM MEMBER ORDER BY 2 DESC;
----------------------------------------------------
--# <3> ��¥�Լ�
--
--��¥ ����
--DATE + ���� : �ϼ��� ����
--DATE - ���� : �ϼ��� ��
--DATE - DATE : �ϼ�
--DATE +/- ����/24 : �ð��� ���ϰų� ����

SELECT SYSDATE "����", SYSDATE+3 "3�� ��", SYSDATE-3 "3�� ��" FROM DUAL;

SELECT CEIL(TO_DATE('23/09/30','YY/MM/DD') - SYSDATE) FROM DUAL;

SELECT TO_CHAR(SYSDATE +3/24, 'YY/MM/DD HH:MI:SS') FROM DUAL;

--2�ð� ���� �ð�����
SELECT TO_CHAR(SYSDATE -2/24, 'YY/MM/DD HH:MI:SS') FROM DUAL;

--[�ǽ�]
--	������̺��� ��������� �ٹ� �ϼ��� �� �� �����ΰ��� ����ϼ���.
--	�� �ٹ��ϼ��� ���� ��������� ����ϼ���.
    SELECT ENAME, SYSDATE, HIREDATE, TRUNC((SYSDATE-HIREDATE)/7) "WEEKS",
    TRUNC(MOD(SYSDATE-HIREDATE,7)) DAYS
    FROM EMP;
    
--  (1) MONTHS_BETWEEN(DATE1, DATE2)    : �� ��¥ ������ ������ ��ȯ
    SELECT MONTHS_BETWEEN(SYSDATE, TO_DATE('23/03/01','YY/MM/DD')) FROM DUAL;
        
--  (2) ADD_MONTHS(DATE, N) : ��¥�� N�� ��ŭ ���� ��¥�� ��ȯ
--  ���÷κ��� 5���� ��, 2���� �� ��¥�� ���� ����ϼ���
    SELECT SYSDATE, ADD_MONTHS(SYSDATE,5)"5����", ADD_MONTHS(SYSDATE,-2)"2����" FROM DUAL;
    
--  (3) LAST_DAY(DATE) : ���� ������ ��¥�� ���� �� ���
    SELECT LAST_DAY('23/02/01'), LAST_DAY('22/02/01'), LAST_DAY('24/02/01') FROM DUAL;
    
--  (4) SYSDATE : ���� ��¥�� �ð����� ��ȯ
--  (5) SYSTIMESTAMP : ���� ��¥�� �ð����� ��ȯ
    SELECT SYSDATE, TO_CHAR(SYSDATE,'YYYY-MM-DD HH:MI:SS') FROM DUAL;
    SELECT SYSTIMESTAMP FROM DUAL;

    SELECT TO_CHAR(SYSDATE,'CC YEAR-MONTH-DDD DAY') FROM DUAL;
    
--# <4> ��ȯ�Լ�
--    (1) TO_CHAR(��¥)
--        TO_CHAR(����)
--        : ��¥ �Ǵ� ���ڸ� ���ڿ�(VARCHAR2)�� ��ȯ�ϴ� �Լ�
        
        SELECT TO_CHAR(SYSDATE), TO_CHAR(SYSDATE,'YYY-MON-DD HH12:MI:SS') FROM DUAL;

--�����̺��� ������ڸ� 0000-00-00 �� ���·� ����ϼ���.
--	 
--	 �����̺� �ִ� �� ���� �� ��Ͽ����� 2013���� 
--	 ���� ������ �����ּ���.
    SELECT NAME, AGE, REG_DATE FROM MEMBER 
    WHERE TO_CHAR(REG_DATE,'YYYY')='2013';
    
--	 �����̺� �ִ� �� ���� �� ������ڰ� 2013�� 5��1�Ϻ��� 
--	 ���� ������ ����ϼ���. 
    SELECT REG_DATE FROM MEMBER WHERE REG_DATE > '13/05/01'; 
    
--	 ��, ����� ������ ��, ���� ���̵��� �մϴ�.
    SELECT NAME, TO_CHAR(REG_DATE,'YYYY-MM')"��� ����" FROM MEMBER 
    WHERE REG_DATE > '13/05/01'; 

    SELECT TO_CHAR(1000000,'9,999,999') FROM DUAL;
    SELECT TO_CHAR(25000,'$99G999') FROM DUAL;
    SELECT TO_CHAR(25000,'L99,999') FROM DUAL;
    SELECT TO_CHAR(-23,'S999'), TO_CHAR(-23,'999S') FROM DUAL;
    SELECT TO_CHAR(23,'99.99'), TO_CHAR(23,'99D99') FROM DUAL;
    SELECT TO_CHAR(23,'99.99EEEE') FROM DUAL;
    
-- (2)TO_NUMBER(���ڿ�, �������) : ���ڿ�(VARCHAR2, CHAR)�� ����(NUMBER)�� ��ȯ
    SELECT TO_NUMBER('125,300', '999,999') + 100000 FROM DUAL;
    
-- '$25,000' ���� 2�� ���� ���� ����ϼ���
    SELECT TO_CHAR(TO_NUMBER('$25,000','$99,999')*2,'$99,999') FROM DUAL;
    --���ڿ��� ���� ���·� ���� �� ��� �� �ٽ� ���ڿ��� ����
    
--    ��ǰ ���̺��� ��ǰ�� ���� �ݾ��� ���� ǥ�� ������� ǥ���ϼ���.
--	  õ�ڸ� ���� , �� ǥ���մϴ�.
    SELECT PRODUCTS_NAME, INPUT_PRICE, TO_CHAR(INPUT_PRICE,'999,999,999')"���ް���" FROM PRODUCTS;
--	
--	 ��ǰ ���̺��� ��ǰ�� �ǸŰ��� ����ϵ� ��ȭ�� ǥ���� �� ����ϴ� �����
--	  ����Ͽ� ����ϼ���.[��:\10,000]
    SELECT PRODUCTS_NAME, OUTPUT_PRICE, TO_CHAR(OUTPUT_PRICE,'L999,999,999')"�ǸŰ���" FROM PRODUCTS;
    
--    (3) TO_DATE(���ڿ�, �������) : ���� �����͸� ��¥ �������� ��ȯ
    
    SELECT ADD_MONTHS('20230512',5) FROM DUAL;
    SELECT TRUNC(TO_DATE('20230512','YYYYMMDD') - SYSDATE) FROM DUAL;
    
--    EMP���� �Ի� �⵵�� �Ի��� ������� ���� ����ϼ���
--    GROUP BY, COUNT(*)
    SELECT HIREDATE,COUNT(*) FROM EMP
    GROUP BY HIREDATE;
    
    SELECT TO_CHAR(HIREDATE,'YY'),COUNT(*) FROM EMP
    GROUP BY TO_CHAR(HIREDATE,'YY')
    ORDER BY 1;
    -- GROUP BY �� ����� �� ����Ʈ ���� ���� ��ü�� ���ؼ��� �׷�ȭ �� �� ����

-- # ������ �Լ�- �׷��Լ�, �����Լ�
--  (1) COUNT([DISTINCT|ALL] �÷���|*)
        SELECT COUNT(EMPNO) FROM EMP; --��ü �����
        SELECT COUNT(MGR) FROM EMP;   --�����޴� �����
        SELECT COUNT(DISTINCT MGR) FROM EMP; -- ������ ��
        
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
        --> �÷����� �����ϸ� NULL���� ī�������� ���� 
        
        SELECT COUNT(*) FROM TEST;
        -->NULL���� ī����
        
--    COUNT(pk�÷�) => ����
--    PK(NOT NULL + UNIQUE)

--    (2) AVG(): ��հ�
--        MAX(): �ִ밪
--        MIN(): �ּҰ�
--        SUM(): �հ谪
        
        
--        [�ǽ�]
--		 emp���̺��� ��� SALESMAN�� ���Ͽ� �޿��� ���,
--		 �ְ��,������,�հ踦 ���Ͽ� ����ϼ���
        
        SELECT AVG(SAL), MAX(SAL), MIN(SAL), SUM(SAL) 
        FROM EMP WHERE JOB='SALESMAN';
        
        SELECT ENAME, SAL FROM EMP WHERE JOB='SALESMAN';
        
        SELECT MIN(ENAME), MAX(ENAME), MIN(HIREDATE), MAX(HIREDATE), MIN(SAL), MAX(SAL)
        FROM EMP;
        
--        [����]
--		EMP���̺� ��ϵǾ� �ִ� �ο���, ���ʽ��� NULL�� �ƴ� �ο���,
--		���ʽ��� ���,��ϵǾ� �ִ� �μ��� ���� ���Ͽ� ����ϼ���.
        SELECT COUNT(EMPNO), COUNT(COMM), AVG(COMM), COUNT(DEPTNO), COUNT(DISTINCT DEPTNO)
        FROM EMP;
        
--  # �׷��Լ����� ����ϴ� GROUP BY��
    
--    16] �� ���̺��� ������ ������ �� ������ ���� ����� ���� �����ֽÿ�.
        SELECT JOB, COUNT(*) FROM MEMBER
        GROUP BY JOB;
--    17] �� ���̺��� ������ ����, �� ������ ���� �ִ� ���ϸ��� ������ �����ּ���.
        SELECT JOB, MAX(MILEAGE) FROM MEMBER
        GROUP BY JOB;
--    18] ��ǰ ���̺��� �� ��ǰī�װ����� �� �� ���� ��ǰ�� �ִ��� �����ּ���.
        SELECT CATEGORY_FK, COUNT(*), MAX(OUTPUT_PRICE), MIN(OUTPUT_PRICE) FROM PRODUCTS
        GROUP BY CATEGORY_FK
        ORDER BY CATEGORY_FK;

--    19] ��ǰ ���̺��� �� ���޾�ü �ڵ庰�� ������ ��ǰ�� ����԰��� �����ּ���.
        SELECT EP_CODE_FK, ROUND(AVG(INPUT_PRICE),1) FROM PRODUCTS
        GROUP BY EP_CODE_FK
        ORDER BY 2 DESC;

--	    ����1] ��� ���̺��� �Ի��� �⵵���� ��� ���� �����ּ���.
        SELECT TO_CHAR(HIREDATE,'YYYY'), COUNT(*) FROM EMP
        GROUP BY TO_CHAR(HIREDATE,'YYYY')
        ORDER BY TO_CHAR(HIREDATE,'YYYY');
        
--	    ����2] ��� ���̺��� �ش�⵵ �� ������ �Ի��� ������� �����ּ���.
        SELECT TO_CHAR(HIREDATE,'YYYY-MM'), COUNT(*) FROM EMP 
        GROUP BY TO_CHAR(HIREDATE,'YYYY-MM')
        ORDER BY TO_CHAR(HIREDATE,'YYYY-MM');
--	    ����3] ��� ���̺��� ������ �ִ� ����, �ּ� ������ ����ϼ���
        SELECT JOB, MIN(SAL), MAX(SAL) FROM EMP
        GROUP BY JOB;

--    # HAVING �� ���
--        GROUP BY�� ������ �־� �����ϰ��� �� �� ���
        
        SELECT JOB, COUNT(*) FROM MEMBER
        GROUP BY JOB HAVING COUNT(*) > 1;
        
--        21] �� ���̺��� ������ ������ �� ������ ���� �ִ� ���ϸ��� ������ �����ּ���.
--	      ��, �������� �ִ� ���ϸ����� 0�� ���� ���ܽ�ŵ�ô�.
        SELECT JOB, MAX(MILEAGE) FROM MEMBER 
        GROUP BY JOB HAVING MAX(MILEAGE) != 0;
        
        
--        ����1] ��ǰ ���̺��� �� ī�װ����� ��ǰ�� ���� ���, �ش� ī�װ��� ��ǰ�� 2���� 
--              ��ǰ���� ������ �����ּ���.
        SELECT CATEGORY_FK, COUNT(*) 
        FROM PRODUCTS
        GROUP BY CATEGORY_FK 
        HAVING COUNT(*) = 2
        ORDER BY 2 DESC;
--    
--        ����2] ��ǰ ���̺��� �� ���޾�ü �ڵ庰�� ��ǰ �ǸŰ��� ��հ� �� ������ 100������ ����
--              ���� �׸��� ������ �����ּ���.
        SELECT EP_CODE_FK, AVG(OUTPUT_PRICE)
        FROM PRODUCTS
        GROUP BY EP_CODE_FK
        HAVING MOD(AVG(OUTPUT_PRICE),100)=0;
        
        SELECT EP_CODE_FK, AVG(OUTPUT_PRICE)
        FROM PRODUCTS
        GROUP BY EP_CODE_FK
        HAVING AVG(OUTPUT_PRICE) LIKE '%00';
        
--# ��Ÿ �Լ�
--    (1) NVL(), NVL2()
--    (2) DECODE()
--    (3) CASE
--    (4) RANK() OVER()
--    (4) ROW_NUMBER() OVER()

--# DECODE() �Լ�
--     DECODE(�÷�, ����1, �����1, ����2, �����2,..., �׿� ��������)

    SELECT * FROM DEPT;
    SELECT * FROM EMP;
    
--    EMP���� �μ���ȣ�� 10���̸� 'ȸ��μ�', 20���̸� '�����μ�' 
--    30���̸� '�����μ�' 40���̸� '��μ�' �μ����� �Բ� ���
    
    SELECT EMPNO, ENAME, DEPTNO,
    DECODE(DEPTNO, 10, 'ȸ��μ�', 20, '�����μ�', 30, '�����μ�', 40, '��μ�','��Ÿ�μ�') "�μ���"
    FROM EMP;
    
--  JOB�� 'CLERK'�̸� �޿��� 15%, 'SALESMAN'�̸� 10%, 'MANAGER' 5%, 'ANALYST' 3%�� �λ��Ѱ� �����, ����, �޿���, �λ���� �Բ� �����ּ���
    SELECT ENAME, JOB, SAL,
    DECODE(JOB, 'CLERK', SAL*1.15, 'SALESMAN', SAL*1.1, 'MANAGER', SAL*1.05, 'ANALYST', SAL*1.03, SAL) "�λ�� �޿�"
    FROM EMP;
    
-- # CASE �Լ�
--    CASE �÷���
--        WHEN ����1 THEN ǥ����1
--        WHEN ����2 THEN ǥ����2
--        ..
--        ELSE ǥ����
--    END
-----------------------------------
    SELECT ENAME, JOB, SAL,
    CASE JOB
        WHEN 'CLERK' THEN SAL*1.15
        WHEN 'SALESMAN' THEN SAL*1.1
        WHEN 'MANAGER' THEN SAL*1.05
        WHEN 'ANALYST' THEN SAL*1.03
        ELSE SAL
    END "�λ�� �޿�"
    FROM EMP;
    
--    EMP ���� ���, �����, ����, ���ʽ��� �Բ� ����ϵ� 
--    ���ʽ�(COMM)�� NULL�� ���� ���� '�ش���� ����', 
--    COMM�� 0�ΰ��� '���ʽ� ����', 
--    COMM�� 0���� ū ���� '���ʽ� ����'�� �Բ� ���
--    �÷����� "���ʽ� ���� ����"

    SELECT EMPNO, ENAME, JOB, COMM,
    CASE 
        WHEN COMM IS NULL THEN '�ش���� ����'
        WHEN COMM=0 THEN '���ʽ� ����'
        WHEN COMM>0 THEN '���ʽ� ����: ���ɾ�=>'||COMM
        ELSE '???'
    END AS "���ʽ� ���� ����"
    FROM EMP;
        
--# RANK() OVER() : Ư�� �������� ��ŷ�� �Ű� �����ش�
--  RANK() OVER(�м���)

-- EMP���� �޿��� ���� ������� ������ �ű⼼��
    SELECT *
    FROM EMP ORDER BY SAL DESC;
    
    SELECT RANK() OVER(ORDER BY SAL DESC) RNK, EMP.*
    FROM EMP;

--  �޿��� ���� ���� TOP3�� ������ �����ּ���
--    MYSQL : LIMIT 3
--    MS SQLSERVER : TOP 3
--    ORACLE : SUBQUERY
    SELECT * FROM (
        SELECT RANK() OVER(ORDER BY SAL DESC) RNK, E.*
        FROM EMP E
    )
    WHERE RNK <4;
    
--    FROM�� �ڿ� ���� ALIAS(��Ī)�� ��𼭵� ��� ����
--    SELECT �ȿ��� ���� ALIAS�� �� �ۿ��� ����� �� ����
--------------------------------------------------------
--    # ROW_NUMBER() OVER(�м���)
--    : �м����� �������� ���ȣ�� �Ű��ش�
--    �Խ��ǿ��� ����¡ ó���� �� ���� ����Ѵ�

    SELECT *
    FROM EMP
    ORDER BY HIREDATE DESC;
    
    SELECT * FROM(
        SELECT ROW_NUMBER() OVER(ORDER BY HIREDATE DESC) RNUM, EMP.*
        FROM EMP)    
    WHERE RNUM BETWEEN 6 AND 10;
    ---------------------------------------------------
--  ROWNUM �ǻ翭�� ���

    SELECT ROWNUM RN, EMP.*
    FROM EMP
    ORDER BY HIREDATE DESC;
    
--  ==> ORDER BY�� �� �ڿ� ROWNUM�� ���̴� ���� �ƴ϶� 
--  ���� �����Ϳ� ROWNUM�� ���� ���̰�, �� �ڿ� ORDER BY�� �Ѵ�

--    =>�ذ���
--  [1] ORDER BY�� ���� �Ѵ�
--  [2] �׷� �� ROWNUM�� ������
    
    SELECT * FROM (
        SELECT ROWNUM RN, A.*
        FROM
        (SELECT * FROM EMP ORDER BY HIREDATE DESC) A
    )
    WHERE RN BETWEEN 6 AND 10;

