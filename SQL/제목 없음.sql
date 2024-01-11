select * from tab;

select * from java_member;

-- view, table, sequence, index, synonym ...  => Object

--  SEQUENCE 객체를 생성해보자
--  => 일련번호를 만들어서 특정 방향으로 증가 또는 감소시켜주는 객체
--  주요 속성: NEXTVAL, CURRVAL
--  
--  CREATE SEQUENCE 시퀀스명
--  START WITH 시작값
--  INCREMENT BY 증가치;
-----------------------------------------------------
CREATE SEQUENCE JAVA_MEMBER_SEQ
START WITH 3
INCREMENT BY 1
NOCACHE;

INSERT INTO JAVA_MEMBER(NO, NAME, USERID, USERPW, TEL)
VALUES(JAVA_MEMBER_SEQ.NEXTVAL, '이민수','LEE3', 'ASDF','010-1234-1234');

ROLLBACK;
SELECT * FROM JAVA_MEMBER;
COMMIT;

SELECT JAVA_MEMBER_SEQ.CURRVAL FROM DUAL;


--MYSQL은 시퀀스X, 컬럼명 AUTOINCREMENT 








