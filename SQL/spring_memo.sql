drop table memo;

create table memo(
    idx number(8), --�۹�ȣ
    name varchar2(30) not null, --�ۼ���
    msg varchar2(200), --�޸𳻿�
    wdate date default sysdate, --�ۼ���
    constraint memo_pk primary key(idx) 
);

drop sequence memo_seq;

create sequence memo_seq nocache;

select * from memo;

















-----------------------------------------------------------

select * from (
select rownum rn, a.* from
(select * from memo order by idx desc) a
)
where rn between 1 and 5;

-- #row_number() over() �Լ� ���
select * from (
select row_number() over(order by idx desc) rn, memo.*
from memo
) where rn between 1 and 5;

select * from (
select row_number() over(order by idx desc) rn, memo.*
from memo
) where rn between 6 and 10;


DROP INDEX PK_upCategory;

/* ����ī�װ� */
DROP TABLE upCategory 
	CASCADE CONSTRAINTS;

/* ����ī�װ� */
CREATE TABLE upCategory (
	upcg_code NUMBER(8) NOT NULL, /* ����ī�װ� �ڵ� */
	upcg_name  VARCHAR2(30) NOT NULL /* ī�װ��� */
);

CREATE UNIQUE INDEX PK_upCategory
	ON upCategory (
		upcg_code ASC
	);

ALTER TABLE upCategory
	ADD
		CONSTRAINT PK_upCategory
		PRIMARY KEY (
			upcg_code
		);
-----------------------------------------------------------
DROP INDEX PK_downCategory;

/* ����ī�װ� */
DROP TABLE downCategory 
	CASCADE CONSTRAINTS;

/* ����ī�װ� */
CREATE TABLE downCategory (
	upcg_code NUMBER(8) NOT NULL, /* ����ī�װ� �ڵ� */
	downcg_code NUMBER(8) NOT NULL, /* ����ī�װ� �ڵ� */
	downcg_name  VARCHAR2(30) NOT NULL /* ����ī�װ��� */
);

CREATE UNIQUE INDEX PK_downCategory
	ON downCategory (
		upcg_code ASC,
		downcg_code ASC
	);

ALTER TABLE downCategory
	ADD
		CONSTRAINT PK_downCategory
		PRIMARY KEY (
			upcg_code,
			downcg_code
		);

ALTER TABLE downCategory
	ADD
		CONSTRAINT FK_upCategory_TO_downCategory
		FOREIGN KEY (
			upcg_code
		)
		REFERENCES upCategory (
			upcg_code
		);
-----------------------------------------------------------
DROP INDEX PK_Products;

/* ��ǰ */
DROP TABLE Products 
	CASCADE CONSTRAINTS;

/* ��ǰ */
CREATE TABLE Products (
	pnum NUMBER(8) NOT NULL, /* ��ǰ��ȣ */
	pname  VARCHAR2(50) NOT NULL, /* ��ǰ�� */
	pimage1  VARCHAR2(100), /* �̹���1 */
	pimage2  VARCHAR2(100), /* �̹���2 */
	pimage3  VARCHAR2(100), /* �̹���3 */
	price NUMBER(8) NOT NULL, /* ��ǰ ���� */
	saleprice NUMBER(8), /* ��ǰ �ǸŰ� */
	pqty NUMBER(8), /* ��ǰ ���� */
	point NUMBER(8), /* ����Ʈ */
	pspec  VARCHAR2(20), /* ���� */
	pcontent  VARCHAR2(1000), /* ��ǰ���� */
	pcompany  VARCHAR2(50), /* ������ */
	pindate DATE, /* ����� */
	upcg_code NUMBER(8), /* ����ī�װ� �ڵ� */
	downcg_code NUMBER(8) /* ����ī�װ� �ڵ� */
);

CREATE UNIQUE INDEX PK_Products
	ON Products (
		pnum ASC
	);

ALTER TABLE Products
	ADD
		CONSTRAINT PK_Products
		PRIMARY KEY (
			pnum
		);

ALTER TABLE Products
	ADD
		CONSTRAINT FK_downCategory_TO_Products
		FOREIGN KEY (
			upcg_code,
			downcg_code
		)
		REFERENCES downCategory (
			upcg_code,
			downcg_code
		);

ALTER TABLE Products
	ADD
		CONSTRAINT FK_upCategory_TO_Products
		FOREIGN KEY (
			upcg_code
		)
		REFERENCES upCategory (
			upcg_code
		);
-----------------------------------------------------------
DROP INDEX PK_Cart;

/* ��ٱ��� */
DROP TABLE Cart 
	CASCADE CONSTRAINTS;

/* ��ٱ��� */
CREATE TABLE Cart (
	cartNum <���� ���� ����> NOT NULL, /* ��ٱ��Ϲ�ȣ */
	userid_fk VARCHAR2(20) NOT NULL, /* ���̵� */
	pnum_fk NUMBER(8) NOT NULL, /* ��ǰ��ȣ */
	pqty NUMBER(8), /* ���� */
	indate DATE /* ����� */
);

CREATE UNIQUE INDEX PK_Cart
	ON Cart (
		cartNum ASC
	);

ALTER TABLE Cart
	ADD
		CONSTRAINT PK_Cart
		PRIMARY KEY (
			cartNum
		);

ALTER TABLE Cart
	ADD
		CONSTRAINT FK_Member_TO_Cart
		FOREIGN KEY (
			userid_fk
		)
		REFERENCES Member (
			userid
		);

ALTER TABLE Cart
	ADD
		CONSTRAINT FK_Products_TO_Cart
		FOREIGN KEY (
			pnum_fk
		)
		REFERENCES Products (
			pnum
		);


