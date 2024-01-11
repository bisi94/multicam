create table pizza_user(
	idx number(8) primary key,
	name varchar2(30),
	phone varchar2(15) not null,
	addr varchar2(100) not null
);
create sequence pizza_seq
start with 1
increment by 1
nocache;

insert into pizza_user(idx, name, phone, addr)
values(pizza_seq.nextval, '��Ҹ�', '1111-1111', '���� ��û��� 123');

insert into pizza_user(idx, name, phone, addr)
values(pizza_seq.nextval, '��ҹ�', '1111-2222', '���� ��û��� 456');

commit;

select * from pizza_user;