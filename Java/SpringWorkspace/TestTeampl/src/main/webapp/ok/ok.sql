create table ok_user(
	idx number(8) primary key,
	name varchar2(30),
	phone varchar2(15) not null,
	addr varchar2(100) not null
);
create sequence ok_seq
start with 1
increment by 1
nocache;

insert into ok_user(idx, name, phone, addr)
values(ok_seq.nextval, 'ok', '1111-1111', '세종 시청대로 123');

insert into ok_user(idx, name, phone, addr)
values(ok_seq.nextval, 'ookk', '1111-2222', '세종 시청대로 456');

commit;