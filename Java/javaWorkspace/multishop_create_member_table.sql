DROP INDEX PK_Member;

/* ȸ�� */
DROP TABLE Member 
	CASCADE CONSTRAINTS;

/* ȸ�� */
CREATE TABLE Member (
	userid VARCHAR2(20) NOT NULL, /* ���̵� */
	name VARCHAR2(30) NOT NULL, /* �̸� */
	userpwd VARCHAR2(16) NOT NULL, /* ��й�ȣ */
	hp1 CHAR(3) NOT NULL, /* ����ó1 */
	hp2 CHAR(4) NOT NULL, /* ����ó2 */
	hp3 CHAR(4) NOT NULL, /* ����ó3 */
	post CHAR(5), /* �����ȣ */
	addr1 VARCHAR2(200), /* �ּ�1 */
	addr2 VARCHAR2(200), /* �ּ�2 */
	indate DATE, /* ������ */
	mileage NUMBER(8), /* ������ */
	mstate NUMBER(1) /* ȸ������ */
);

CREATE UNIQUE INDEX PK_Member
	ON Member (
		userid ASC
	);

ALTER TABLE Member
	ADD
		CONSTRAINT PK_Member
		PRIMARY KEY (
			userid
		);
        
        
select * from member;

desc member;

--�� �����
CREATE OR REPLACE VIEW MEMBERVIEW AS
select member."USERID",member."NAME",member."USERPWD",member."HP1",member."HP2",member."HP3",member."POST",member."ADDR1",member."ADDR2",member."INDATE",member."MILEAGE",member."MSTATE", decode(mstate,0,'Ȱ��ȸ��',-1,'����ȸ��',-2,'Ż��ȸ��',9,'������') mstateStr
from member
where mstate>-2;

select * from memberview;

--������ ����
UPDATE member
SET "MSTATE" = 9
WHERE "MSTATE" = 0;
commit;
        