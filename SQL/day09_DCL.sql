--day09_DCL.sql
---------------------------------------------------
--DDL 문장: CREATE, ALTER, DROP, RENAME
--DML 문장: UPDATE,INSERT,DELETE
--DCL 문장: GRANT,REVOKE
--DATA RETRIEVAL : SELECT
--TRANSACTION CONTROL : COMMIT, ROLLBACK, SAVEPOINT
---------------------------------------------------
--# DCL 문장 : GRANT, REVOKE
--
--시스템 권한 부여 문법(Syntax)
--
--GRANT [system_privilege| role] TO [user|role|PUBLIC]
--[WITH ADMIN OPTION]
--
-- - system_privilege : 부여할 시스템 권한의 이름
-- - role : 부여할 데이터베이스 역할의 이름
-- - user, role : 부여할 사용자 이름과 다른 데이터 베이스 역할 이름
-- - PUBLIC : 시스템 권한, 또는 데이터베이스
--         역할을 모든 사용자에게 부여할 수 있습니다.
-- - WITH ADMIN OPTION : 권한을 부여 받은 사용자도 
--        부여 받은 권한을 다른 사용자 또는 역할로 
--           부여할 수 있게 되며, 
--	   만약 사용자가 WITH ADMIN OPTION과 
--	   같이 역할을 부여 받는다면
--           부여된 역할은 그 사용자에 의해 변경 
--	   또는 삭제 될 수 있습니다.
------------------------------------------------------
--시나리오.
-- 1. DBA권한을 가진 SYSTEM으로 접속
    conn system/oracle
    show user;
    
-- 2. 새로운 사용자를 생성 MILLER USER를 생성하고 비번 지정
    create user miller
    identified by miller;
    
-- 3. MILLER로 접속 시도  -> 권한 부족
    conn miller/miller --=> 에러 발생
    
-- 4. SYSTEM으로 접속하여 MILLER에게 CREATE SESSION 권한 부여
    conn system/oracle
    grant create session to miller;
    
-- 5. MILLER로 접속 시도 --> 성공
    conn miller/miller
    
-- 6. TEST 테이블 생성하기 --> 권한 부족
    CREATE TABLE TEST(
    no number);
    
-- 7. SYSTEM으로 접속 후 테이블 생성 권한 부여
    conn system/oracle
    grant create table to miller
    
-- 8. 다시 테이블 생성후 레코드 insert
    conn miller/miller
    create table test(
    no number);
        
-- 9. tablespace에 대한 권한 부족이라고 나옴
    alter user miller quota 2M on system;
        
-- 10. miller로 접속하여 다시 test 테이블을 생성하고 데이터를 insert한다
    create table test(
    no number);
    insert into test values(100);
    commit;

-- 11. system으로 접속후 MILLER의 테이블 스페이스 확인
--	SELECT USERNAME, DEFAULT_TABLESPACE FROM DBA_USERS
--	WHERE USERNAME='MILLER'

--	테이블 스페이스는 테이블과 뷰 등 데이터베이스 객체들이 저장되는 장소.
--	USERS는 사용자 데이터를 저장하기 위한 공간.

--	CREATE TABLE권한을 줬음에도 테이블을 생성하지 못하거나 INSERT
--	하지 못한 것은 사용자 생성 당시 해당 디폴트 테이블스페이스인 USERS
--	에 대한 QUOTA를 설정하지 않았기 때문.

--12. SYSTEM 접속 후 테이블스페이스 영역 할당하기
--	ALTER USER MILLER QUOTA 2M ON USERS

--	QUOTA절을 이용하여 MILLER사용자가 사용할 테이블스페이스 영역을
--	할당했으므로 다시 MILLER로 접속하여 시도해보자.
-- ----------------------------------------------------------------------------------
--  # 권한 회수
--  REVOKE [system_privilege| role] FROM [user|role|PUBLIC]

--시나리오
--0. conn system/oracle

    revoke connect, resource from scott;
    conn scott/tiger==>에러
    conn system/oracle
    
-- create session권한을 scott에게 주세요
    grant create session to scott;

-- storm user도 생성하세요
    create user storm
    identified by storm;

-- 1. DBA가 STORM에게 WITH ADMIN OPTION을 사용하여 
-- CREATE TABLE 시스템 권한을 부여 합니다.  : 오직 테이블 만드는것에 대한 권한부여 권한임
    grant create table to storm with admin option;

--2. STORM이 테이블을 생성 합니다. 
    conn storm/storm    
    create table msg(no number); -->tablespace error

--STORM에게 2할당
    alter user storm quota 2M on system;
    conn storm/storm
    create table msg(no number);
    
--3. STORM이 CREATE TABLE 시스템 권한을 
--   SCOTT에게 부여 합니다. 
    grant create table to scott;
        
--4. SCOTT가 테이블을 생성 합니다. 
    create table msg(no number);

--5. DBA가 STORM에게 부여한 CREATE TABLE 시스템 권한을 취소 합니다.
    conn system/oracle
    revoke create table from storm;
    conn storm/storm
    create table dummy(no number); -->error 발생
    
--6. scott의 테이블 생성권한은? (남아있는지 같이 사라졌는지)
    conn scott/tiger
    create table mytable(no number); --> 생성됨.. 권한 잔류
    ----------------------------------------------------------------
    
-- # [2] 객체 권한
---구문(Syntax)
--  GRANT object_privilege [column] ON object
--  TO {user[,user] | role |PUBLIC]
--  [WITH GRANT OPTION]


-- - object_privilege : 부여할 객체 권한의 이름

-- - object : 객체명

-- - user, role : 부여할 사용자 이름과 다른 데이터 베이스 역할 이름

-- - PUBLIC : 오브젝 권한, 또는 데이터베이스 역할을 모든 사용자에게 부여할 수 있습니다.

-- - WITH GRANT OPTION : 권한을 부여 받은 사용자도 부여 받은 권한을 다른 사용자 또는 역할로 
--                      부여할 수 있게 됩니다. Object 권한 부여 문법
---------------------------------------------------------------------------
-- # ROLE
   
--   [1] ROLE 생성        : CREATE ROLE 롤이름
--   [2] ROLE에 권한부여   : GRANT 권한1, 권한2  TO 롤이름
--   [3] ROLE을 다른 사용자 또는 다른 ROLE에게 부여 GRANT 롤이름 TO 유저|다른롤
    
--    ROLE생성하기
--   롤이름: MGR
--   권한유형: CREATE SESSION, CREATE TABLE, CREATE VIEW, CREATE SYNONYM
--   MGR롤을 STORM에게 부여하세요
    
    CREATE ROLE MGR;
    GRANT CREATE SESSION, CREATE TABLE, CREATE VIEW, CREATE SYNONYM TO MGR;
    GRANT MGR TO STORM;
    
--    # CONNECT, RESOURCE => ROLE
    SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE=UPPER('CONNECT');
    SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE=UPPER('RESOURCE');
    SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE=UPPER('DBA');
    
--    # SCOTT에게 DBA 롤을 부여하세요
    GRANT DBA TO SCOTT;
    
    
    














