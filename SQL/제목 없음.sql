select * from tab;

select * from java_member;

-- view, table, sequence, index, synonym ...  => Object

--  SEQUENCE ��ü�� �����غ���
--  => �Ϸù�ȣ�� ���� Ư�� �������� ���� �Ǵ� ���ҽ����ִ� ��ü
--  �ֿ� �Ӽ�: NEXTVAL, CURRVAL
--  
--  CREATE SEQUENCE ��������
--  START WITH ���۰�
--  INCREMENT BY ����ġ;
-----------------------------------------------------
CREATE SEQUENCE JAVA_MEMBER_SEQ
START WITH 3
INCREMENT BY 1
NOCACHE;

INSERT INTO JAVA_MEMBER(NO, NAME, USERID, USERPW, TEL)
VALUES(JAVA_MEMBER_SEQ.NEXTVAL, '�̹μ�','LEE3', 'ASDF','010-1234-1234');

ROLLBACK;
SELECT * FROM JAVA_MEMBER;
COMMIT;

SELECT JAVA_MEMBER_SEQ.CURRVAL FROM DUAL;


--MYSQL�� ������X, �÷��� AUTOINCREMENT 








