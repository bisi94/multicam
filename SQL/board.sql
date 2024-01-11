select count(*) from tab;

select * from tab;

--�����Խ��� - �ܼ��� �Խ���
drop table board;

create table board(
    no number(8) constraint board_no_pk primary key, --�۹�ȣ
    name varchar2(30) not null, --�ۼ���
    pwd varchar2(20) not null, --�ۺ�й�ȣ
    subject varchar2(200), --����
    content varchar2(2000), --�۳���
    wdate timestamp default systimestamp, --�ۼ���
    readnum number(8) default 0, --��ȸ��
    filename varchar2(300), --÷�����ϸ�
    filesize number(8) --÷������ ũ��
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





