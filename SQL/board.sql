select count(*) from tab;

select * from tab;

--자유게시판 - 단순형 게시판
drop table board;

create table board(
    no number(8) constraint board_no_pk primary key, --글번호
    name varchar2(30) not null, --작성자
    pwd varchar2(20) not null, --글비밀번호
    subject varchar2(200), --제목
    content varchar2(2000), --글내용
    wdate timestamp default systimestamp, --작성일
    readnum number(8) default 0, --조회수
    filename varchar2(300), --첨부파일명
    filesize number(8) --첨부파일 크기
);

drop sequence board_seq;

create sequence board_seq
start with 1
increment by 1
nocache;

select * from board;

select * from board order by no;

select rownum rn, board.* from board order by no desc;
select * from (
select rownum rn, a.* from
(select * from board order by no desc) a
) where rn between 1 and 5;

cpage       pagesize        start       end
1           5               1           5
2           5               6           10
3           5               11          15
4           5               16          20

end=cpage*pageSize
start= end -(pageSize-1);





