select * from member;

탈퇴회원을 제외한 view를 만들자
뷰이름: memberView

create view 권한을 가져야 생성가능

CREATE OR REPLACE VIEW memberView
as
select member.*, decode(mstate,0,'활동회원',-1,'정지회원',-2,'탈퇴회원',9,'관리자') mstateStr
from member
where mstate>-2;

select * from memberView;

======================================================================

select count(*) from tab;

select * from tab;

자유게시판 - 단순형 게시판
drop table board;

create table board{
    no number(8) constraint board_no_pk primary key, --글번호
    name varchar2(30) not null, --작성자
    pwd varchar2(100) not null, --글비밀번호
    subject varchar2(200), --제목
    content varchar2(2000), --글내용
    wdate timestamp default systimestamp, --작성일
    readnum number(8) default 0, --조회수
    filename varchar2(300), --첨부파일명
    filesize number(8) --첨부파일 크기
};

alter table member modify userpwd varchar2(100);






