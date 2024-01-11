--day06_DDL.sql
show user
-------------------------------------------------
DDL ����: CREATE, ALTER, DROP, RENAME,TRUNCATE
DML ����: UPDATE,INSERT,DELETE,MERGE
DCL ����: GRANT,REVOKE
DQL : SELECT
TCL(TRANSACTION CONTROL) : COMMIT, ROLLBACK, SAVEPOINT
-------------------------------------------------
# table ��������
create table ���̺��(
    �÷���1 �ڷ���(ũ��) [default �⺻��],
    �÷���2 �ڷ��� ��������,
    �÷���3 �ڷ���,
    ....,
    ���̺������ ��������1,
    ���̺������ ��������2
);

# ��������

-----------------------------------------------------------------------
  <1> PRIMARY KEY (PK) : �����ϰ� ���̺��� �� ���� �ĺ�
				  NOT NULL�� UNIQUE ������ ����
  <2> FOREIGN KEY (FK) : �÷��� ������ �÷� ������ �ܷ�Ű ���踦 �����ϰ� ����
  <3> UNIQUE KEY (UK)  : ���̺��� ��� ���� �����ϰ� �ϴ� ���� ���� ��
				  NULL�� ���
  <4> NOT NULL (NN)	: �÷��� NULL�� ������ �� ����.
  <5> CHECK (CK)		: ���̾�� �ϴ� ������ ������
				  ��κ� ���� ��Ģ�� ����
-----------------------------------------------------------------------

[1] primary key : not null + unique ������ ����
  <1> �÷� ������ ����
  
  CREATE TABLE TEST_TAB1(
        ID NUMBER(2) CONSTRAINT TEST_TAB1_ID_PK PRIMARY KEY,
        NAME VARCHAR2(20)
  );
  
  DESC TEST_TAB1;
  
  ������ �������� ��ȸ
  
  SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB1';
  
  INSERT INTO TEST_TAB1(ID,NAME)
  VALUES(1,NULL);
  
  SELECT * FROM TEST_TAB1;
  
  INSERT INTO TEST_TAB1(ID,NAME)
  VALUES(2,'ȫ�浿');
  COMMIT;
  
  <2> pk-���̺� ������ ����

    CREATE TABLE TEST_TAB2(
      ID NUMBER(2),
      NAME VARCHAR2(20),
      TEL VARCHAR2(15),
      -- ���̺� ������ ����
      CONSTRAINT TEST_TAB2_ID_PK PRIMARY KEY (ID)    
    );
    
    DESC TEST_TAB2;
    
    ������ �������� ��ȸ�ϼ���
    
    SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE,INDEX_NAME
    FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
    
    
    # ���� ���� ����
    ALTER TABLE ���̺�� DROP CONSTRAINT �������Ǹ�;
    
    # ���� ���� �߰�
    ALTER TABLE ���̺�� ADD CONSTRAINT �������Ǹ� ������������ (�÷���)
    
    # ���� ������ �̸� ����
    ALTER TABLE ���̺�� RENAME CONSTRAINT OLD_NAME TO NEW_NAME;
    
   [�ǽ�] 
    [1] TEST_TAB2 ���̺��� �������� TEST_TAB2_ID_PK �� �����ϼ���
        
        ALTER TABLE TEST_TAB2 DROP CONSTRAINT TEST_TAB2_ID_PK;
        
    ������ �������� Ȯ���ϱ�
        SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
        
    [2] TEST_TAB2 ���̺� ���ο� ���������� �߰��ϵ�
        �������Ǹ��� TEST_TAB2_PK�� �ְ� PRIMARY KEY ���������� ID�� �ּ���
        
        ALTER TABLE TEST_TAB2 ADD CONSTRAINT TEST_TAB2_PK PRIMARY KEY (ID);
        
    ������ �������� Ȯ���ϱ�    
        SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
        
    [3] TEST_TAB2_PK �������� �̸��� �����ϼ��� TEST_TAB2_PRIMARY_KEY
    
        ALTER TABLE TEST_TAB2 RENAME CONSTRAINT TEST_TAB2_PK   TO TEST_TAB2_PRIMARY_KEY;
    
    ������ �������� Ȯ���ϱ�    
        SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='TEST_TAB2';
--------------------------------------------------------------
    [2] FOREIGN KEY (�ܷ�Ű)
        - PK OR UNIQUE �÷����� �ܷ�Ű�� ������ �� �ִ�.
        - PK�� �÷��� �ڷ���,ũ�� ���� ��ġ�ؾ� �Ѵ�
        - �ܷ�Ű�� ���� �����ǰ� �ִ� �⺻Ű(PK)�� ������ �� ����
        - ON DELETE CASCADE �����ڿ� �Բ� ���ǵ� �ܷ�Ű �����ʹ�
          �� �⺻Ű�� ������ �� ���� �����ȴ�
        
     <1>FK-�÷� ������ ����
     
     --MASTER TABLE ���� (�θ� ���̺�)
     DROP TABLE DEPT_TAB;
     
     CREATE TABLE DEPT_TAB(
        DEPTNO NUMBER(2),
        DNAME CHAR(14),
        LOC CHAR(13),
        CONSTRAINT DEPT_TAB_DEPTNO_PK PRIMARY KEY (DEPTNO)
     );
     
     -- DETAIL TABLE (�ڽ� ���̺�)
     CREATE TABLE EMP_TAB(
        EMPNO NUMBER(4),
        ENAME VARCHAR2(20),
        JOB VARCHAR2(20),
        -- �÷������� ����
        MGR NUMBER(4) CONSTRAINT EMP_TAB_MGR_FK REFERENCES EMP_TAB (EMPNO),
        HIREDATE DATE,
        SAL NUMBER(7,2),
        COMM NUMBER(7,2),
        DEPTNO NUMBER(2),
        -- ���̺� ���� ����
        CONSTRAINT EMP_TAB_DEPTNO_FK FOREIGN KEY (DEPTNO)
        REFERENCES DEPT_TAB (DEPTNO),
        PRIMARY KEY (EMPNO)
     );
     
     SELECT * FROM USER_CONSTRAINTS
     WHERE TABLE_NAME IN ('DEPT_TAB','EMP_TAB');
     
     ������ ����
     DEPT_TAB��
     10 �λ�� ����
     20 �濵�� �λ�
     �����͸� INSERT �ϱ�
     
     INSERT INTO DEPT_TAB VALUES(10,'�λ��','����');
     INSERT INTO DEPT_TAB VALUES(20,'�濵��','�λ�');
     
     SELECT * FROM DEPT_TAB;
     COMMIT;
     
     EMP_TAB�� ������ �ֱ�
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1001,'����','�������',NULL,SYSDATE,NULL);
     
     SELECT * FROM EMP_TAB;
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1002,'�ֻ��','�������',1001,SYSDATE,20);
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1003,'�̻��','�������',1003,SYSDATE,20);
     
      INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1004,'�̻��','�������',1005,SYSDATE,20); [X] FK�� ����
     
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1004,'�̻��','�������',1002,SYSDATE,10);
     
     INSERT INTO EMP_TAB (EMPNO, ENAME,JOB,MGR,HIREDATE,DEPTNO)
     VALUES(1005,'����','�������',1003,SYSDATE,30); [X] FK�� ���� 
     
     SELECT * FROM EMP_TAB;
     COMMIT;
     
     10�� �μ��� DEPT_TAB���� �����ϼ���
     
     DELETE FROM DEPT_TAB WHERE DEPTNO=10; [X]
   =>  integrity constraint (SCOTT.EMP_TAB_DEPTNO_FK) violated - child record found
     �ڽ� ���ڵ尡 �ִٸ� ���õ� �θ� ���ڵ� ���� �Ұ�
     
     DELETE FROM EMP_TAB WHERE DEPTNO=10;
     
     SELECT * FROM EMP_TAB;
     
     DELETE FROM DEPT_TAB WHERE DEPTNO=10;
     
     SELECT * FROM DEPT_TAB;
     COMMIT;
     
     ON DELETE CASCADE ������ �̿�
     => �ڽķ��ڵ尡 �־ ���õ� �θ� ���ڵ带 ������ �� ������,
        �̶� �ڽ� ���ڵ嵵 ���� �����ȴ�
     
-----------------------------------------------------------
�Խ���: BBS (�θ����̺�) �۹�ȣ(PK)
���: REPLY (�ڽ����̺�) <=�θ���� �۹�ȣ�� �ܷ�Ű�� ����(FK) 
                    �̶� ON DELETE CASCADE �ɼ��� �־��

CREATE TABLE BBS(
   NO NUMBER(4) PRIMARY KEY,
   TITLE VARCHAR2(200) NOT NULL,
   WRITER VARCHAR2(30) NOT NULL,
   CONTENT VARCHAR2(2000),
   WDATE DATE DEFAULT SYSDATE,
   -- WRITER �÷��� �ܷ�Ű�� �ּ���(���̺� ���ؿ���) => JAVA_MEMBER ���̺��� USERID�� �����ϵ���
   CONSTRAINT BBS_WRITER_FK FOREIGN KEY (WRITER)
   REFERENCES JAVA_MEMBER (USERID) ON DELETE CASCADE
);
DESC BBS;

SELECT * FROM JAVA_MEMBER;
     
BBS�� ���� �ϳ� �����ϼ���     

INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(1,'�ݰ����ϴ�','hong','���� ó�� ���� ���');

select * from bbs;

INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(2,'���� �ݰ����ϴ�','kim','���� ���� ó�� ���� ���');

INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(3,'�ݰ����ϴ�','noname','���� ó�� ���� ���');    [x] 
    
INSERT INTO BBS(NO,TITLE,WRITER,CONTENT)
VALUES(3,'�ȳ��ϼ���','hong','���õ� ���� ���ϴ�');     
       
select * from bbs;     
commit;
   
java_member ���̺��� userid�� 'kim'�� ȸ���� �����غ�����

select * from java_member;

delete from java_member where userid='kim';
     
select * from java_member;
select * from bbs;

rollback;
     
on delete cascade �� �ڽ����̺��� �ܷ�Ű�� �����ϸ鼭 �����Ѵ�

------------------------------------------------
���̺��: reply
�۹�ȣ: rno number(4) pk
�۳���: rcontent varchar2(500)
�ۼ���: rdate date
�ۼ���: rwriter => �ܷ�Ű (java_member�� userid)  on delete cascade
bbs�Ǳ۹�ȣ: no_fk => �ܷ�Ű (bbs�� no)  on delete cascade
------------------------------------------------

create table reply(
  rno number(4) primary key,
  rcontent varchar2(500),
  rdate date default sysdate,
  rwriter varchar2(20) references java_member (userid) on delete cascade,
  no_fk number(4) references bbs (no) on delete cascade
);

select * from reply;

select * from bbs;

insert into reply(rno,rcontent,rwriter,no_fk)
values(3,'���� ��������','kim',2);

select * from reply;

bbs�� reply�� join�ؼ� �Բ� �����ּ���

select b.*, r.*
from bbs b join reply r
on b.no = r.no_fk;

bbs���� 2������ �����ϼ���

delete from bbs where no=2;

select * from bbs;

rollback;
-----------------------------------------------
# [3]  unique ��������

 <1> uk - �÷������� ����
    
    create table uni_tab(
        deptno number(2) constraint uni_tab_deptno_uk unique,
        dname char(10),
        loc char(10)
    );
    
    select * from user_constraints where table_name=upper('uni_tab');
    
    insert into uni_tab values(1,'accounting','LA');
    insert into uni_tab values(2,'sales','LA');
    insert into uni_tab values(null,'operation','LA');
    
    select * from uni_tab;
    commit;
 
 
 <2> ���̺� ���� ����
 
 create table uni_tab2(
    deptno number(2),
    dname char(10),
    loc char(10),
    constraint uni_tab2_deptno_uk unique (deptno)
 );
 
 # [4] NOT NULL ��������
    - �÷� ���ؿ����� ������ �� �ִ�
 
CREATE TABLE NN_TAB(
   DEPTNO NUMBER(2) CONSTRAINT NN_TAB_DEPTNO_NN NOT NULL,
   DNAME CHAR(10),
   LOC CHAR(10)
);

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME='NN_TAB';
     
INSERT INTO NN_TAB VALUES(10,'ACCOUNTING','LA');     
INSERT INTO NN_TAB VALUES(NULL,'ACCOUNTING','LA');
     
CREATE TABLE NN_TAB2(
   DEPTNO NUMBER(2) CONSTRAINT NN_TAB2_DEPTNO_NN NOT NULL,
   DNAME CHAR(10),
   LOC CHAR(10),
   CONSTRAINT DNAME_NN NOT NULL (DNAME) -- [X] NOT NULL�� ���̺���ؿ����� ���� �Ұ�
);

DESC NN_TAB2;
     
     
# CHECK ��������
<1> �÷� ����
    CREATE TABLE CK_TAB(
        DEPTNO NUMBER(2) CONSTRAINT CK_TAB_DEPTNO_CK CHECK (DEPTNO >= 10 AND DEPTNO <=20 ),
        DNAME CHAR(10)
    );
    
    INSERT INTO CK_TAB VALUES(20,'SALES');
    INSERT INTO CK_TAB VALUES(30,'SALES');
    INSERT INTO CK_TAB VALUES(13,'SALES');
    SELECT * FROM CK_TAB;

<2> ���̺� ����
     SCORE ���̺��� �����
     NAME VARCHAR2(20)
     KOR �÷��� NUMBER(3) ���� ������ 0 ~100�����̷� �����ϼ���
     
     CREATE TABLE SCORE(
      NAME VARCHAR2(20) NOT NULL,
      KOR NUMBER(3), 
      -- ���̺� ���� ����
      CONSTRAINT SCORE_KOR_CK CHECK (KOR >=0 AND KOR <=100)
     );
     
     INSERT INTO SCORE VALUES('ȫ�浿',0);
     INSERT INTO SCORE VALUES('ȫ�浿',100);
     INSERT INTO SCORE VALUES('ȫ�浿',88);
     INSERT INTO SCORE VALUES('ȫ�浿',-20);
     INSERT INTO SCORE VALUES('ȫ�浿',120);
     SELECT * FROM SCORE;
     
-- ================================================================================     --
# SUBQUERY�� �̿��� ���̺� ����
CREATE TABLE ���̺��
AS SELECT �÷���1,�÷���2,... FROM ���̺��2;
     
     
     [�ǽ�] ��� ���̺��� 30�� �μ��� �ٹ��ϴ� ����� ������ �����Ͽ�
	          EMP30 ���̺��� �����Ͽ���. �� ���� ���,�̸�,����,�Ի�����,
		  �޿�,���ʽ��� �����Ѵ�.
         
         CREATE TABLE EMP30
         AS SELECT EMPNO, ENAME, JOB, HIREDATE, SAL, COMM FROM EMP
            WHERE DEPTNO=30;
            
            SELECT * FROM EMP30;
            
         CREATE TABLE EMP20(���,�����,����,�Ի���,�޿�,���ʽ�)
         AS
         SELECT EMPNO, ENAME, JOB, HIREDATE, SAL, COMM FROM EMP
         WHERE DEPTNO=20;
            
            SELECT * FROM EMP20;
     
     [����1]
		EMP���̺��� �μ����� �ο���,��� �޿�, �޿��� ��, �ּ� �޿�,
		�ִ� �޿��� �����ϴ� EMP_DEPTNO ���̺��� �����϶�.
        
        CREATE TABLE EMP_DEPTNO
        AS
        SELECT DEPTNO, COUNT(EMPNO) CNT, AVG(SAL) AVG_SAL, SUM(SAL) SUM_SAL, MIN(SAL) MIN_SAL, MAX(SAL) MAX_SAL
        FROM EMP GROUP BY DEPTNO;
        
        SELECT * FROM EMP_DEPTNO;
        
		
	[����2]	EMP���̺��� ���,�̸�,����,�Ի�����,�μ���ȣ�� �����ϴ�
		EMP_TEMP ���̺��� �����ϴµ� �ڷ�� �������� �ʰ� ������
		�����Ͽ���.
     
        CREATE TABLE EMP_TEMP
        AS 
        SELECT EMPNO,ENAME,JOB,HIREDATE,DEPTNO FROM EMP
        WHERE 1=2;
        
        SELECT * FROM EMP_TEMP;
===================================================================
# �÷� �߰�, ����, ����...

[1]  �÷� �߰�
     ALTER TABLE ���̺�� ADD �߰��� �÷��� �ڷ��� [DEFAULT �⺻��];
[2]  �÷� ����
    ALTER TABLE ���̺�� DROP COLUMN ������ �÷���;
[3] �÷� �ڷ��� ����
    ALTER TABLE ���̺�� MODIFY ������ �÷�����
[4] �÷��� ����
    ALTER TABLE ���̺�� RENAME COLUMN OLD�÷��� TO NEW�÷���;
    
     [�ǽ�]    		
		
	      ��ǰ ���̺� ��ǰ�� �����ϴ� ���ο� Į���� �߰��ϼ���. 
	      �÷� �̸��� prod_desc VARCHAR2(1000)
          
         ALTER TABLE PRODUCTS ADD PROD_DESC VARCHAR2(1000);  

        SELECT * FROM PRODUCTS;
        
        - PROD_DESC�� �ڷ����� NUMBER(8)�� �����غ�����
        
        ALTER TABLE PRODUCTS MODIFY PROD_DESC NUMBER(8);
        
        DESC PRODUCTS;

      - PROD_DESC �÷����� PROD_NUM ���� �����ϼ���
      
        ALTER TABLE PRODUCTS RENAME COLUMN PROD_DESC TO PROD_NUM;
      
        SELECT * FROM PRODUCTS;
      
      - PRODUCTS ���̺��� RPOD_NUM �÷��� �����ϼ���
      
        ALTER TABLE PRODUCTS DROP COLUMN PROD_NUM;
        
        SELECT * FROM PRODUCTS;
      
#     ���̺�� ����(��ü�� ����)
    - RENAME OLD_NAME TO NEW_NAME;
      
      EMP30 ���̺� �̸��� EMP_TEMP30���� �����ϼ���
      RENAME EMP30 TO EMP_TEMP30;
      
    SELECT * FROM TAB;
    SELECT * FROM EMP_TEMP30;

# ���̺� ���� 
    DROP TABLE ���̺�� [CASCADE CONSTRAINT]
    -   DROP TABLE ������ ����Ŭ ���̺� ���Ǹ� �����Ѵ�.
	  ���̺��� ������ �� �����ͺ��̽��� ���̺� �ִ� ��� �ڷ��
	  �׿� ������ ��� INDEX�� DROP �ϰ� ����ϰ� �ִ�
	  ������ �����ش�.

   EMP_TEMP30  ���̺��� �����ϼ���
   
   DROP TABLE EMP_TEMP30;
    
   SELECT * FROM TAB; 

   -- �����뿡 ���ִ� ���� ���̺����� ������ ����
   DROP TABLE EMP20 PURGE;
   -- ������ ����
   PURGE RECYCLEBIN;
   
   SELECT * FROM DEPT_TAB;
   SELECT * FROM EMP_TAB;
   
   DEPT_TAB ���̺��� �����ϼ���
   
   DROP TABLE DEPT_TAB CASCADE CONSTRAINT;
   
   [1] �ڽ� ���̺��� ���� DROP�ϰ� �θ� ���̺��� DROP�Ѵ�
   [2] DROP�Ҷ�  CASCADE CONSTRAINT �ɼ��� �־� �����Ѵ�
   
   
# TRUNCATE
- �����͸� �����ϴ� DDL����
- �����͵� �����ϰ�, ����ϰ� �ִ� ��������� �ݳ��Ѵ�
- ���̺� INDEX �� �����Ѵ�

TRUNCATE TABLE ���̺��

CREATE TABLE EMP_COPY
AS SELECT * FROM EMP;

SELECT * FROM EMP_COPY;

EMP_COPY�� �����͸� �����ϼ��� (TRUNCATE)

TRUNCATE TABLE EMP_COPY;