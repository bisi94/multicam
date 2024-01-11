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
values(pizza_seq.nextval, '김소망', '1111-1111', '세종 시청대로 123');

insert into pizza_user(idx, name, phone, addr)
values(pizza_seq.nextval, '김소밍', '1111-2222', '세종 시청대로 456');

commit;

select * from pizza_user;