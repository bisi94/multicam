select * from member;

Ż��ȸ���� ������ view�� ������
���̸�: memberView

create view ������ ������ ��������

CREATE OR REPLACE VIEW memberView
as
select member.*, decode(mstate,0,'Ȱ��ȸ��',-1,'����ȸ��',-2,'Ż��ȸ��',9,'������') mstateStr
from member
where mstate>-2;

select * from memberView;

======================================================================

select count(*) from tab;

select * from tab;

�����Խ��� - �ܼ��� �Խ���
drop table board;

create table board{
    no number(8) constraint board_no_pk primary key, --�۹�ȣ
    name varchar2(30) not null, --�ۼ���
    pwd varchar2(100) not null, --�ۺ�й�ȣ
    subject varchar2(200), --����
    content varchar2(2000), --�۳���
    wdate timestamp default systimestamp, --�ۼ���
    readnum number(8) default 0, --��ȸ��
    filename varchar2(300), --÷�����ϸ�
    filesize number(8) --÷������ ũ��
};

alter table member modify userpwd varchar2(100);






