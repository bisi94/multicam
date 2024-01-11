-- ��ǰ ���� �Խ���
-- products���̺��� pnum(PK)�� member���̺��� userid(PK)�� �ܷ�Ű�� �����ؾ� ��
-- �ϴ� ajax�� �����ϰ� �׽�Ʈ�ϱ� ���� ���踦 ���� �ʰ� ���̺��� �����غ���

DROP TABLE REVIEW;

CREATE TABLE REVIEW(
	NUM NUMBER(8) PRIMARY KEY, --���� �۹�ȣ
	USERID VARCHAR2(20) NOT NULL, --ȸ�� ���̵� => �Ŀ� FK�������� �߰� ����
	PNUM NUMBER(8) NOT NULL, --��ǰ��ȣ =>�Ŀ� FK�������� �߰� ����
	TITLE VARCHAR2(200), --����
	CONTENT VARCHAR2(500), --���䳻��
	SCORE NUMBER(1) CONSTRAINT SCORE_CK CHECK(SCORE>0 AND SCORE <=5),
	FILENAME VARCHAR2(300), --÷������
	WDATE DATE DEFAULT SYSDATE
	
);

DROP SEQUENCE REVIEW_SEQ;

CREATE SEQUENCE REVIEW_SEQ NOCACHE;