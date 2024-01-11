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