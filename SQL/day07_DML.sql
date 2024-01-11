--day07_DML.sql

CREATE TABLE EMP2
AS 
SELECT * FROM EMP;

SELECT * FROM EMP2;

CREATE TABLE DEPT2
AS
SELECT * FROM DEPT;

SELECT * FROM DEPT2;

--DEPT2�� DEPTNO �÷��� PRIMARY KEY �������� �߰��ϱ�
    ALTER TABLE DEPT2
    ADD CONSTRAINT DEPT2_DEPTNO PRIMARY KEY (DEPTNO);
    
    SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'DEPT2';
    SELECT * FROM DEPT2;
    
--EMP2�� EMPNO�÷��� PRIMARY KEY �������� �߰��ϱ�
    ALTER TABLE EMP2
    ADD CONSTRAINT EMP2_EMPNO PRIMARY KEY (EMPNO);
    
    SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMP2';

--EMP2�� DEPTNO �÷��� FOREIGN KEY �������� �߰��ϱ�
    ALTER TABLE EMP2
    ADD CONSTRAINT EMP2_DEPTNO FOREIGN KEY (DEPTNO)
    REFERENCES DEPT2 (DEPTNO);
    
    SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMP2';

--# INSERT ��
--INSERT INTO ���̺��(�÷���1,�÷���2,...)
--            VALUES(��1, ��2,...)
--=> �÷��� ���������� �Է���

--INSERT INTO ���̺��
--VALUES(��1, ��2,...)
--=> ��� �÷����� �־�� ��. ���̺� ������ ���� �÷� ������� ���� ����

--EMP2�� ���, �̸�, �Ի���, �μ���ȣ�� INSERT ����

    INSERT INTO EMP2(EMPNO, ENAME, HIREDATE, DEPTNO)
    VALUES(8001, 'TOM', SYSDATE, 10);
    
    SELECT * FROM EMP2;

    INSERT INTO EMP2
    VALUES(8002, 'PETER', 'MANAGER', 7788, SYSDATE, 2500, NULL, 20);
    
    
--DEPT2�� 50���μ� 'EDUCATION','SEOUL'  �����͸� �߰��ϼ���

    INSERT INTO DEPT2
    VALUES(50, 'EDUCATION', 'SEOUL');

    COMMIT;
    
--    PRODUCTS ���̺��� ī���Ͽ� PRODUCTS_TEMP ���̺��� �����ϵ�,
--    ���̺� ������ �����ϼ���

    CREATE TABLE PRODUCTS_TEMP
    AS SELECT * FROM PRODUCTS WHERE 1=0;
    
    SELECT * FROM PRODUCTS_TEMP;
    
--PRODUCTS_TEMP ���̺� �����͸� �����ϵ�,
--PRODUCTS�� �ִ� ������ǰ���� ���ϴ� �����͸� �����ϼ���
--'0001xxxx'    
    INSERT INTO PRODUCTS_TEMP
    SELECT * FROM PRODUCTS WHERE CATEGORY_FK LIKE '0001%';
    
    SELECT * FROM PRODUCTS_TEMP;
    COMMIT;
    
--# UPDATE ��
--UPDATE ���̺�� SET �÷���1=��1, �÷���2=��2,...;

--UPDATE ���̺�� SET �÷���1=��1, �÷���2=��2,... WHERE ������;


--[1] EMP2���� ����� 7788�� ����� �μ���ȣ�� 10������ �����ϼ���
    UPDATE EMP2 SET DEPTNO = 10 WHERE EMPNO=7788;
    
    SELECT * FROM EMP2;
    
--[2] emp2 ���̺��� ����� 7788�� ����� �μ��� 20,
--	�޿��� 3500���� �����Ͽ���.
    UPDATE EMP2 SET DEPTNO = 20, SAL = 3500 WHERE EMPNO = 7788;
    
    SELECT * FROM EMP2;
    
    ROLLBACK;
    
--[3] emp2���̺��� �μ��� ��� 10���� �����Ͽ���.
    
    UPDATE EMP2 SET DEPTNO = 10;
    
    SELECT * FROM EMP2;
    
    ROLLBACK;
    
--[4] ��(MEMBER) ���̺� �� �̸��� '��浿'�� ����� �̸���  �ڱ浿���� �����ϼ���.
    UPDATE MEMBER SET NAME = '�ڱ浿' WHERE NAME='ȫ�浿';
    
    SELECT * FROM MEMBER;
    ROLLBACK;
    
--[5] �� �� 13/09/01���� ����� ������ ���ϸ����� 350���� �÷��ּ���.   
    SELECT * FROM MEMBER;
    
    UPDATE MEMBER SET MILEAGE = MILEAGE+350
    WHERE REG_DATE >= '13/09/01';
    ROLLBACK;
    
--[6] ��ϵǾ� �ִ� �� ���� �� �̸��� '��'�ڰ� ����ִ� ��� �̸��� '��' ���
--	     '��'�� �����ϼ���.
    SELECT * FROM MEMBER;
    
    UPDATE MEMBER SET NAME=REPLACE(NAME, '��','��')
    WHERE NAME LIKE '��%';
    
    ROLLBACK;
    
--    # UPDATE������ SUBQUERY
--
--    UPDATE ���̺�� SET (�÷���1, �÷���2) = (SUBQUERY ) WHERE ������

--   [1] EMP2���̺��� SCOTT�� ������ �޿��� ��ġ�ϵ���
--		JONES�� ������ �޿��� �����Ͽ���.
    SELECT * FROM EMP2;
    
    UPDATE EMP2 SET (JOB, SAL) = 
    (SELECT JOB, SAL FROM EMP2 WHERE ENAME = 'SCOTT')
    WHERE ENAME='JONES';
    
    ROLLBACK;
    
--    # UPDATE �� ���Ἲ �������� ����
--[2] EMP2���� �μ���ȣ�� 10���� ������� �μ���ȣ�� 90������ �����ϼ���

    UPDATE EMP2 SET DEPTNO=90 WHERE DEPTNO=10;
--  ORA-02291: integrity constraint (SCOTT.EMP2_DEPTNO) violated - parent key not found

    SELECT * FROM EMP2;
    
    UPDATE EMP2 SET DEPTNO=40 WHERE DEPTNO=10;
    
    ROLLBACK;
    
--    #DELETE ����-�ڷ� �����ϱ�

--	- ���� ����� �÷��� ������ �� �ʿ信 ���� �ش� �����͸� �����ϴ� ����.
--	- UPDATE���� ���������� WHERE ������ �̿��Ͽ� ���ϴ� �����͵鸸 ���� ����
--	- ��� �����͸� �����Ϸ��� WHERE ������ ���� ����ϸ� �ȴ�.

--	-DELETE FROM ���̺��  WHERE ����(�÷�)='���ǰ�'
--	-DELETE FROM ���̺��

--	[�ǽ�]
--	- EMP2���̺��� �����ȣ�� 7499�� ����� ������ �����϶�.

    DELETE FROM EMP2 WHERE EMPNO=7499;
    
    SELECT * FROM EMP2;
    
    ROLLBACK;

--  - EMP2���̺��� �Ի����ڰ� 83���� ����� ������ �����϶�.
    DELETE FROM EMP2 WHERE HIREDATE LIKE '83%';
    
    DELETE FROM EMP2 WHERE TO_CHAR(HIREDATE, 'YY') ='23';
    
    SELECT * FROM EMP2;
    
    ROLLBACK;
    
    ------------------------------------------------------
--# DELETE ������ SUBQUERY

--DELETE FROM ���̺�� WHERE �÷���=(SUBQUERY);

--[1] EMP2���̺��� �ڷ� �� �μ����� 'SALES'�� ����� ������ �����϶�.
    SELECT * FROM DEPT2;
    
    DELETE FROM EMP2 WHERE DEPTNO = (SELECT DEPTNO FROM DEPT2 WHERE DNAME='SALES');
    
    SELECT * FROM EMP2;
    ROLLBACK;
    
--[2] ��ǰ(PRODUCTS) ���̺� �ִ� ��ǰ �� ��ǰ�� ��з��� ������ ��ǰ�� �����ϼ���.
    DELETE FROM PRODUCTS WHERE CATEGORY_FK IN 
    (SELECT CATEGORY_CODE FROM CATEGORY WHERE CATEGORY_NAME LIKE '%����%');
    
    SELECT * FROM CATEGORY;
    SELECT * FROM PRODUCTS;
    ROLLBACK;
    
--    # DELETE�� ���� ���Ἲ �������� ����
--[1] DEPT2 ���̺��� 'SALES' �μ��� �����ϼ��� ==> ERROR �߻�
    DELETE FROM DEPT2 WHERE DNAME='SALES';
--    ORA-02292: integrity constraint (SCOTT.EMP2_DEPTNO) violated - child record found
    SELECT * FROM EMP2 WHERE DEPTNO = (SELECT DEPTNO FROM DEPT2 WHERE DNAME='SALES');
    
--    �ڽ����̺��� FK�� �ٶ� ON DELETE CASCADE �ɼ����ָ� �ڽ� ���ڵ尡 �־
--  �θ� ���ڵ带 ������ �� �ִ�. �� �� �ڽ� ���ڵ嵵 ���� �����ȴ�.

--[2] DEPT2 ���̺��� 'OPERATIONS' �μ��� �����ϼ���
    
    SELECT * FROM DEPT2;
    SELECT * FROM EMP2 WHERE DEPTNO=(SELECT DEPTNO FROM DEPT2 WHERE DNAME = 'OPERAIONS');
    
    DELETE FROM DEPT2 WHERE DNAME='OPERATIONS';
    
    ROLLBACK;
    
-- # TCL (Transaction Control Language)
-- commit
-- rollback
-- savepoint

    UPDATE EMP2 SET ENAME = 'CHARSE', DEPTNO = 30 WHERE EMPNO=7788;

    SELECT * FROM EMP2;
    
    ROLLBACK;
    
    UPDATE EMP2 SET ENAME = 'CHARSE', DEPTNO = 30 WHERE EMPNO=7788;
    
    SAVEPOINT POINT_1;
    
    UPDATE EMP2 SET JOB='MANAGER';
    
    SELECT * FROM EMP2;
    
    ROLLBACK TO SAVEPOINT POINT_1;
    COMMIT;


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    