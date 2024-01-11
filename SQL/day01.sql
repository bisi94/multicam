select * from tab;
-- ���� Ŀ���� sql�� ����: Ctlr+Enter
/*  �л� ���� ���α׷�
    �л� (Entity) ==> ������ (Table)   
*/
--���̺� ���� ���� (DDL - Date Definition Language)
--DDL����: CREATE, DROP, RENAME TO,...
--CREATE TABLE ���̺��(
--�÷���1 �ڷ���(ũ��) ��������,
--�÷���2 �ڷ���(ũ��)...,
--...
--);
-----------------------------------
CREATE TABLE STUDENT(
    NUM NUMBER(4) PRIMARY KEY, -- UNIQUE + NON NULL
    NAME VARCHAR2(30) NOT NULL, -- �л��� //CHAR : ����ũ��, VARCHAR2 : ũ�⺯������
    TEL VARCHAR2(15) NOT NULL, -- ����ó
    ADDR VARCHAR2(50), --�ּ� 
    INDATE DATE DEFAULT SYSDATE, -- ����� //��ϵ� ���� ��¥������ �ڵ����� �־��ش�
    SNAME VARCHAR2(30), -- �б޸�
    SROOM NUMBER(3) -- ���ǹ�ȣ
);

DESC STUDENT;

SELECT * FROM TAB;

--���̺� ����
--DROP TABLE ���̺��

DROP TABLE STUDENT;
------------------------------------------------------------
--������ ���� - INSERT ��
--
--INSERT INTO ���̺��(�÷���1, �÷���2,...�÷���N)
--            VALUES (��1, ��2,...��N) -- 1:1 ��ġ�� �Ǿ�� ��
------------------------------------------------------------
INSERT INTO STUDENT(NUM, NAME, TEL, ADDR, SNAME, SROOM)
            VALUES(1, 'GILDOND HONG', '010-345-6678', '����','�ڹٹ�', 201);
            
-- ������ ��ȸ--------------------------------
--SELECT �÷���1, �÷���2,... FROM ���̺��;
--SELECT * FROM ���̺��;
--------------------------------------------

SELECT NUM, SNAME, SROOM FROM STUDENT;
SELECT * FROM STUDENT;

2�� ��ö��  ��õ �ڹٹ� �����͸� ��������

INSERT INTO STUDENT(NUM, NAME, TEL, ADDR, SNAME, SROOM)
            VALUES(2, 'CHEOLSU CHOI', '0103123123', '��õ', '�ڹٹ�', 201);

SELECT * FROM STUDENT;


--DML (INSERT, DELETE, UPDATE��) ������ COMMIT �Ǵ� ROLLBACK ��ɹ��� �̿��ؼ�
--Ʈ������� ������ �ؾ� �Ѵ�
--- COMMIT : �����ͺ��̽��� �����͸� ������ ����
--- ROLLBACK : ��� �ߴ� DML�۾��� ���

COMMIT;

3 �̹α� ����ó, ����, ������, 301ȣ
INSERT INTO STUDENT(NUM, NAME, TEL, ADDR, SNAME, SROOM)
VALUES(3, 'MINKI LEE', '010-123-1234', '����', '������', 301);

SELECT *FROM STUDENT;

ROLLBACK;
COMMIT;

--�����͸� ��� ������ �÷����� �������� �ʾƵ� �ȴ�
--�ٸ� ���̺��� �������� �� ����� ��� ���� �÷� ������� �����ϸ� �ȴ�.
INSERT INTO  STUDENT
VALUES(4, 'BISI MIN', '01010101010', '��õ', SYSDATE, '������','301');

COMMIT;

INSERT INTO  STUDENT
VALUES(5, 'BISI MIN', '01010101234', '����', '23/09/17', NULL, NULL);

SELECT * FROM STUDENT;
COMMIT;
----------------------------------------------------------------
--5�� �л��� �б޸��� '�ڹٹ�'���� �����ϼ���

--UPDATE �� :
--UPDATE ���̺�� SET �÷���1=��1, �÷���2=��2
--WHERE ������;

UPDATE STUDENT SET SNAME='�ڹٹ�';
--=>��� ���ڵ尡 ������. WHERE���� ����ϴ°��� ����

UPDATE STUDENT SET SNAME='�ڹٹ�' WHERE NUM=5;

--5�� �л��� �б޹�ȣ���� '201'���� �����ϼ���
UPDATE STUDENT SET SROOM=201, TEL='010-2222-3333', SNAME='�ڹٹ�' WHERE NUM=5;

SELECT * FROM STUDENT;
ROLLBACK;
COMMIT;
-------------------------------------------------------------------
--������ ����: DELETE ��
--DELETE FROM ���̺��
--WHERE ������
-------------------------------------------------------------------
--2�� ��ö�� �л��� �����͸� �����ϼ���
DELETE FROM STUDENT
WHERE NUM=2;
SELECT * FROM STUDENT;
COMMIT;
--------------------------------------------
����SW�� �⺻������ ���� ���
CRUD
C: CREATE ==> INSERT��
R: READ ==> SELECT��
U: UPDATE ==> UADATE��
D: DELETE ==> DELETE��
--------------------------------------------

--���̺� ����
--DROP TABLE ���̺��;
-- DDL������ �ﰢ������ ������ ��ģ��

DROP TABLE STUDENT;
SELECT * FROM TAB;

�б����̺� �����ϱ�
���̺��: SCLASS
�÷�: 
�б޹�ȣ: SNUM NUMBER(4) PK
�б޸�: SNAME VATCHAR2(30) NOT NULL
���ǹ�ȣ: SROOM NUMBER(3)

CREATE TABLE SCLASS(
SNUM NUMBER(4) PRIMARY KEY,
SNAME VARCHAR2(30) NOT NULL,
SROOM NUMBER(3)
);

SELECT * FROM TAB;

DESC SCLASS;

-- �л����̺� �����
-- �б������� �б����̺��� �б޹�ȣ�� �����ϴ� �ܷ�Ű�� ����� ����

CREATE TABLE STUDENT(
    NUM NUMBER(4) PRIMARY KEY,
    NAME VARCHAR2(30) NOT NULL,
    TEL VARCHAR2(15) NOT NULL,
    ADDR VARCHAR(50),
    INDATE DATE DEFAULT SYSDATE,
    --�б޹�ȣ�� �ܷ�Ű��
    SNUM_FK NUMBER(4) REFERENCES SCLASS (SNUM)
);

--�ܷ�Ű(fk)�� �θ� ���̺�(SCLASS)�� pk�� �����ϰų�, UNIQUE ���������� ���� �÷��� �����ؾ� �Ѵ�
--�θ� ���̺� ��ϵ��� ���� ���� �ڽ� ���̺� ��ϵǰų� �����Ǵ� ���� ������� �ʴ´�

--�б����̺� 
--10 �ڹٹ� 201
--20 �����͹� 301
--30 �ΰ����ɹ� 401
--�����͸� �����ÿ�

INSERT INTO SCLASS(SNUM, SNAME, SROOM)
        VALUES(10, '�ڹٹ�', 201);
        
INSERT INTO SCLASS
        VALUES(20, '�����͹�', 301);
        
INSERT INTO SCLASS
        VALUES(30, '�ΰ����ɹ�', 401);
        
SELECT * FROM SCLASS;
COMMIT;
        
--�л����� �ֱ�
--�ڹٹ� 3��
--1 ȫ�浿 ����ó ���� ����� �б޹�ȣ
--�����͹� 2��
--�ΰ����ɹ� 1��

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
--���Ἲ �������ǿ� ����Ǿ��� - �θ�Ű�� �߰ߵ��� �ʾ���

SELECT * FROM STUDENT;

---------------------------------------------------------------
--JOIN �� : 2�� �̻��� ���̺��� �ϳ��� ���ļ� �����ش�
--�� �� PK�� FK�� ���� �������� ������ �ϴ� ��찡 ���� => EQUI JOIN

SELECT NUM, NAME, SNUM_FK, SNAME, SROOM, TEL, ADDR, INDATE
FROM SCLASS JOIN STUDENT
ON SCLASS.SNUM = STUDENT.SNUM_FK; -- '=' �񱳿�������

�ڹٹ� ������ 201ȣ���� 501ȣ�� ����Ǿ����ϴ�

UPDATE SCLASS SET SROOM=501 WHERE SNAME='�ڹٹ�';

------------------------------------------------------------

SELECT * FROM SCLASS;

--�ΰ����ɹ��� ������ ��ҵǾ����ϴ�
--�б����̺��� �ΰ����ɹ�(30��)�� �����ϼ���

DELETE FROM SCLASS WHERE SNUM=30;
--ORA-02292: integrity constraint (SCOTT.SYS_C007010) violated - child record found

--�ڽ� ���ڵ尡 �����ϰ� �ִ� ���¿��� �θ� ���̺��� �����ִ� �����͸� ������ �� ����
--�ڽ� ���ڵ带 ���� �����, �θ� ���ڵ带 �����غ���

DELETE FROM STUDENT WHERE NUM=6;
SELECT * FROM STUDENT;
DELETE FROM SCLASS WHERE SNUM=30;
SELECT * FROM SCLASS;

SELECT NUM, NAME, SNUM_FK, SNAME, SROOM, TEL, ADDR, INDATE
FROM SCLASS JOIN STUDENT
ON SCLASS.SNUM = STUDENT.SNUM_FK; -- '=' �񱳿�������


SELECT * FROM TAB;

SELECT * FROM MEMBER;
SELECT * FROM CATEGORY;
SELECT * FROM PRODUCTS;
SELECT * FROM SUPPLY_COMP;

-- �λ���� ���� �����ͺ��̽�
SELECT * FROM DEPT; --�μ�
SELECT * FROM EMP; --���
SELECT * FROM SALGRADE; --�޿���� ���̺�
------------------------------------------------
#������ ��ȸ
SELECT * FROM EMP;

������̺��� ���, �����, �μ���ȣ�� ������ �����ּ���

SELECT EMPNO, ENAME, DEPTNO FROM EMP;

���ǥ������ ����� ���� �ִ�

�����, �޿�, �޿��� 10%�λ���� ������ �����ּ���

SELECT ENAME, SAL, SAL*1.1 AS SAL_UP FROM EMP;
-- ALIAS (��Ī)�� �����Ҷ� AS"��Ī(����������)"
-- AS��� "��Ī"�� ��밡�� ""���� ���鰡��

���, �����, �޿�, ���ʽ�(COMM), ����(�����ؼ�)�� ������ ����ϼ���
SELECT EMPNO, ENAME, JOB, SAL, COMM, SAL*12+COMM "����", SAL*12+NVL(COMM,0) "1YEAR SALARY" FROM EMP;
#NVL�Լ�: NULL���� ������ ������ ��ȯ�Ͽ� �����ϵ��� ��
NVL(�÷�, ��ȯ�� ��)

#NVL2�Լ�
NVL2(�÷�, ��1, ��2): �÷����� NULL�� �ƴ� ��쿡�� ��1�� ��ȯ�ϰ�
                    NULL�� ��쿡�� ��2�� ��ȯ�ϴ� �Լ�

--��� ���̺��� ������(MGR)�� �ִ� ���� 1, ������0�� ����Ͻÿ� / ��Ī�� "���ӻ������"
SELECT EMPNO, ENAME, MGR, NVL2(MGR, 1,0)"���ӻ������" FROM EMP;

------------------------------------------------------
# ���ڿ� ���� ������: ||
SELECT ENAME||' IS A '||JOB FROM EMP;

--����] EMP���̺��� �̸��� ������ "KING: 1 YEAR SALARY = 60000"
--	�������� ����϶�.

SELECT ENAME||': 1 YEAR SALAEY = '||(SAL*12+NVL(COMM,0)) FROM EMP;











