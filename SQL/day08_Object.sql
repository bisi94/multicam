--day08_Object.sql
--# ORACLE�� ��ü
--[1] SEQUENCE
--[2] VIEW
--[3] INDEX
--[4] SYNONYM
--
-- #[1] ��������? 
--
--�� ����(UNIQUE)�� ���� �������ִ� ����Ŭ ��ü��. 
--�� �������� �����ϸ� �⺻Ű�� ���� ���������� 
--   �����ϴ� �÷��� 
--   �ڵ������� �����Ҽ� �ִ�. 
--�� ���� primary key ���� �����ϱ� ���� ����Ѵ�. 
--�� �޸𸮿� Cache�Ǿ��� �� Sequence ���� 
--   �׼��� ȿ���� ���� �Ѵ�. 
--�� Sequence�� ���̺���� ���������� ����ǰ� �����˴ϴ�. 
--    ���� �ϳ��� sequence��  ���� ���̺��� �� �� �ִ�
--����
--CREATE SEQUENCE ��������
--[INCREMENT BY n] -- ����ġ
--[START WITH n] -- ���۰�
--[{MAXVALUE n | NOMAXVALE}] - ������ �ִ밪
--[{MINVALUE n | NOMINVALUE}] - ������ �ּҰ�
--[{CYCLE | NOCYCLE}] -- �ݺ�����
--[{CACHE | NOCACHE}] -- ĳ�� ��� ����

SELECT * FROM DEPT2;

CREATE SEQUENCE DEPT2_SEQ
START WITH 60
INCREMENT BY 10
MAXVALUE 99
NOCACHE
NOCYCLE;

--PRIMARY KEY ���� CYCLE �ɼ� ��� �Ұ�

--  ������ �������� ��ȸ
--  - USER_OBJECTS
--  - USER_SEQUENCES

SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='SEQUENCE';

SELECT * FROM USER_SEQUENCES;

--DEPT2�� 'SALES2' �μ��� �����ϼ��� ��ġ : '�λ�'
INSERT INTO DEPT2 ( DEPTNO, DNAME, LOC)
VALUES(DEPT2_SEQ.NEXTVAL, 'SALES2', 'BUSAN');

SELECT * FROM DEPT2 ORDER BY DEPTNO;

--'RESEARCH2' 'SUWON' �����ϼ���
INSERT INTO DEPT2
VALUES(DEPT2_SEQ.NEXTVAL, 'RESEARCH2', 'SUWON');

ROLLBACK;

--- ������ �Ӽ�
--[1] NEXTVAL : NEXT VALUE (������)
--[2] CURRVAL : CURRENT VALUE (���簪)
--
--# SEQUENCE ����
--
--- NEXTVAL, CURRVAL�� ����Ͽ� ������ ���� �����Ѵ�.
--- NEXTVAL�� ���� ��밡���� ������ ���� ��ȯ�Ѵ�.
--- �������� ������ ������ �ٸ� ����ڿ��� ������ ������ ���� ��ȯ�Ѵ�.
--- CURRVAL�� ���� SEQUENCE���� ��´�.
--- CURRVAL�� �����Ǳ� ���� NEXTVAL�� ���Ǿ�� �Ѵ�.
--
--**����]
--� ���ǿ��� NEXTVAL �� ���� ���� ä CURRVAL �� �䱸�ϰ� �Ǹ� ������ 
--����. �װ��� CURRVAL �� �ٷ� �� ������ ���� ���� ������ �ִ� ���� ������ ���� �ǹ� 
--�ϹǷ� �ѹ��� NEXTVAL �� �䱸�� ���� ���ٸ� �����ϰ� �ִ� CURRVAL ���� ���� 
--�����̴�.

SELECT DEPT2_SEQ.CURRVAL FROM DUAL;

--��������:  TEMP_SEQ
--���۰�: 100
--����ġ: -5�� ����
--�ּҰ�: 0
--CYCLE�ɼ��ֱ�
--CACHE����ϱ�

CREATE SEQUENCE TEMP_SEQ
START WITH 100
INCREMENT BY -5
MAXVALUE 100
MINVALUE 0
CYCLE
CACHE 20;

SELECT TEMP_SEQ.CURRVAL FROM DUAL;

SELECT TEMP_SEQ.NEXTVAL FROM DUAL;

-- # ������ ����
-- ALTER SEQUENCE ��������
-- INCREMENT BY N
-- MINVALUE
-- MAXVALUE
-- CYCLE|NOCYCLE
-- CACHE|NOCACHE

-- # ������ ����
--DROP SEQUENCE ��������;

--TEMP_SEQ�� ������ ���� �����ϼ���
--����ġ: 2
--MINVALUE : 100
--MAXVALUE: 150
--NOCYCLE
--NOCACHE
--�� �����ϼ���

ALTER SEQUENCE TEMP_SEQ
INCREMENT BY 2
MINVALUE 80
MAXVALUE 100
NOCYCLE
NOCACHE;

SELECT * FROM USER_SEQUENCES WHERE SEQUENCE_NAME='TEMP_SEQ';

--# TEMP_SEQ �������� �����ϼ���

DROP SEQUENCE TEMP_SEQ;

----------------------------------------------------------

--# [2] VIEW
--
--- ������ ���̺�
--- ������ �������� �ܼ�ȭ��ų �� �ִ�.
--- �����͸� �پ��� �������� �� �� �ִ�
--
--
-- # �並 ����� ��Ģ
--	CREATE VIEW ���̸�
--	AS
--	SELECT �÷���1, �÷���2...
--	FROM �信 ����� ���̺��
--	WHERE ����
--
--** ���������� ����,�׷� �� ���� SELECT���� ������ �� �ְ� ORDER BY����
--	    ������ �� ����.
--	    VIEW�� �����ϰų� ����� ���� �ʰ�VIEW�� ���Ǹ� �����Ϸ���
--	    OR REPLACE�ɼ��� ����Ѵ�.
--	...����] view�� ����� ���ؼ��� ������ �ʿ�[dba�������� CREATE VIEW������ ����]

conn system/oracle

show user
--USER��(��) "SYSTEM"�Դϴ�.
grant create view to scott;
--Grant��(��) �����߽��ϴ�.

conn scott/tiger
show user
-----------------------------------------------------------------------

--[�ǽ�] 
--    EMP���̺��� 20�� �μ��� ��� �÷��� �����ϴ� EMP20_VIEW�� �����϶�.

CREATE VIEW EMP20_VIEW
AS
SELECT * FROM EMP WHERE DEPTNO=20;

SELECT * FROM EMP20_VIEW;

-- EMP20_VIEW�� ���, �μ���ȣ, �̸�, ������ ���Եǵ��� �����ϼ���
--# VIEW�� ������ OR REPLACE �ɼ��� �ش�

CREATE OR REPLACE VIEW EMP20_VIEW  --�����ϴ��� ��ġ��
AS SELECT EMPNO, ENAME, JOB, DEPTNO 
FROM EMP WHERE DEPTNO=20;

SELECT * FROM EMP20_VIEW;

-- [1] EMP���̺��� 30�� �μ��� EMPNO�� EMP_NO�� ENAME�� NAME����
--	SAL�� SALARY�� �ٲپ� EMP30_VIEW�� �����Ͽ���.
    CREATE OR REPLACE VIEW EMP30_VIEW
--  CREATE OR REPLACE VIEW EMP30_VIEW(EMP_NO, NAME, SALARY) >> �̰͵� ����
    AS SELECT EMPNO "EMP_NO", ENAME "NAME", SAL "SALARY"
    FROM EMP WHERE DEPTNO=30;
    
    SELECT * FROM EMP30_VIEW;
    
-- [2] �����̺��� �� ���� �� ���̰� 19�� �̻���
--	���� ������ Ȯ���ϴ� �並 ��������.
--	�� ���� �̸��� MEMBER_19�� �ϼ���.

    CREATE OR REPLACE VIEW MEMBER_19
    AS
    SELECT USERID ID, NAME, AGE, JOB, ADDR ADDRESS 
    FROM MEMBER WHERE AGE>=19;
    
    SELECT * FROM MEMBER_19;
    
--  # ������ �������� ��ȸ : USER_VIEWS

SELECT * FROM USER_VIEWS WHERE VIEW_NAME = UPPER('MEMBER_19');

-- # �� ����
--DROP VIEW ���̸�
DROP VIEW MEMBER_19;

SELECT * FROM java_MEMBER;

--# view���� dml����

CREATE OR REPLACE VIEW SALES_VW
AS
SELECT * FROM EMP2 WHERE JOB='SALESMAN';

SELECT * FROM SALES_VW;
--#���ο� ���Ի�� ���
INSERT INTO EMP2 VALUES(8021,'SUSAN', 'SALESMAN', 7698, SYSDATE, 2000, 200, 30);
INSERT INTO SALES_VW VALUES(8022,'ANN', 'SALESMAN', 7698, SYSDATE, 1000, 500, 30);

SELECT * FROM EMP2;
COMMIT;

-- EMP2���� 7844���� JOB�� 'MANAGER'�� �����ϼ���
UPDATE EMP2 SET JOB='MANAGER', COMM = NULL WHERE EMPNO='7844';

SELECT * FROM EMP2;
SELECT * FROM SALES_VW;

--#DML ���� ����=> WITH READ ONLY �ɼ��� �־� �����Ѵ�

--JOB�� 'ANALYST'�� ����� �並 �����, �б� �������� ���弼��
--���̸� : ANALYST_VW

CREATE OR REPLACE VIEW ANALYST_VW
AS
SELECT * FROM EMP2 WHERE JOB='ANALYST' WITH READ ONLY;
SELECT * FROM ANALYST_VW;

UPDATE ANALYST_VW SET DEPTNO=10 WHERE EMPNO=7788;
--�б� ���� ��� DML���� �Ұ�

UPDATE EMP2 SET DEPTNO=10 WHERE EMPNO=7788;
-- �̷��� �������� ���� ����
COMMIT;
-----------------------------------------------
--# WITH CHECK OPTION ��
--: WHERE �������� ���յ��� �ʴ� �������� ����,������ ������� �ʴ´�.

-- EMP20VW �並 �����ϵ� WITH CHECK OPTION �� �ּ���

CREATE OR REPLACE VIEW EMP20VW
AS
SELECT * FROM EMP2 WHERE DEPTNO=20
WITH CHECK OPTION CONSTRAINT EMP20_CK;

SELECT * FROM EMP20VW;

--�信�� Ư�� ��� 1���� ��� �μ���ȣ�� 30�� �μ��� �����ϼ���
    
UPDATE EMP20VW SET JOB='MANAGER' WHERE EMPNO=7369;

UPDATE EMP20VW SET DEPTNO=30 WHERE EMPNO=7566; -- �������� �ȿ����� ���氡��
    
UPDATE EMP2 SET DEPTNO=30 WHERE EMPNO=7369;

SELECT * FROM EMP20VW;
    
ROLLBACK;

SELECT * FROM USER_VIEWS WHERE VIEW_NAME='EMP20VW';

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='EMP20VW';

--# JOIN ���� �̿��� �並 �����ϼ���
--
--��ǰ ������ �����ֵ� ī�װ����, ��ǰ��, �ǸŰ�, ���޾�ü��, ���ް���
--�Բ� �����ִ� �並 �����ϼ���
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

-- JOIN���� ���� ������ ��� DML���� �Ұ�
UPDATE PRODUCTS_VIEW SET OUTPUT_PRICE=35000 WHERE PRODUCTS_NAME='������ø���';

--# INLINE VIEW
--- FROM ���� ���� SUBQUERY �� �ζ��� ���� �Ѵ�

SELECT ROWNUM, EMP2.* FROM EMP ORDER BY HIREDATE;

SELECT ROWNUM, EMP2VW.* FROM (
(SELECT * FROM EMP2 ORDER BY HIREDATE ASC) EMP2VW) WHERE ROWNUM <6;

--EMP2VW=>�ζ��κ�
--------------------------------------------------------------
--# [3] INDEX
--- ������ �˻��� �ż��ϰ� �� �� �ֵ��� �ϴ� ��ü

--PRIMARY KEY, UNIQUE  ���������� �ָ� �ڵ�����  UNIQUE �ε����� �����ȴ�

--CREATE INDEX �ε����� ON ���̺��(�÷���[,�÷���]...)

--**����: �ε����� NOT NULL�� �÷����� ����� �� �ִ�.
--	  NULL�� ��쿡�� �ε����� ������ �� ���� ������ ��� �Ұ�.

--EMP2���� ENAME�� INDEX�� �����ϼ���
--�ε�����: EMP2_ENAME_INDX

CREATE INDEX EMP2_ENAME_INDX ON EMP2 (ENAME);

SELECT * FROM EMP2 WHERE ENAME LIKE '%S%';

--������ �������� ��ȸ
SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='INDEX';

SELECT * FROM USER_INDEXES WHERE INDEX_NAME='EMP2_ENAME_INDX';

SELECT * FROM USER_INDEXES WHERE TABLE_NAME='EMP2';

--������ �ε��� �÷����� ��ȸ: USER_IND_COLUMNS
--SELECT * FROM USER_IND_COLUMNS WHERE TABLE_NAME='EMP2';

--# �ε��� ����=> �Ұ���
--�����Ϸ��� �����ϰ� �ٽ� �����Ѵ�
--
--# �ε��� ����
--DROP INDEX �ε�����;

--[����1 ] ��ǰ ���̺��� �ε����� �ɾ�θ� ���� �÷�(FK���)�� ã�� �ε����� ���弼��.
--        2�� �÷��� �ε��� �����ϱ�
    CREATE INDEX PRODUCTS_CATEGORY_IDX ON PRODUCTS (CATEGORY_FK);
    CREATE INDEX PRODUCTS_EP_CODE_IDX ON PRODUCTS (EP_CODE_FK);
    
--        ������ �������� ��ȸ�ϱ�
    SELECT * FROM USER_INDEXES WHERE TABLE_NAME='PRODUCTS';
--        
--[����2] 2�� �ε��� �� 1���� ��� �����ϼ���
--        ������ �������� ��ȸ�ϱ�
    DROP INDEX PRODUCTS_EP_CODE_IDX;
    
    ------------------------------------------------------------
--# [4] SYNONYM (���Ǿ�)
--    
--    - ����
--  CREATE [PUBLIC] SYNONYM �ó�Ը�
--  FOR object_name;
--
--  PUBLIC: ��� ����ڰ� ���� ������ �ó���� ����
--          �Ѵ�. PUBLIC�ó���� ����,������ DBA����
--	  �� �� �ִ�.

