drop table memo;

create table memo(
    idx number(8), --글번호
    name varchar2(30) not null, --작성자
    msg varchar2(200), --메모내용
    wdate date default sysdate, --작성일
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

-- #row_number() over() 함수 사용
select * from (
select row_number() over(order by idx desc) rn, memo.*
from memo
) where rn between 1 and 5;

select * from (
select row_number() over(order by idx desc) rn, memo.*
from memo
) where rn between 6 and 10;


DROP INDEX PK_upCategory;

/* 상위카테고리 */
DROP TABLE upCategory 
	CASCADE CONSTRAINTS;

/* 상위카테고리 */
CREATE TABLE upCategory (
	upcg_code NUMBER(8) NOT NULL, /* 상위카테고리 코드 */
	upcg_name  VARCHAR2(30) NOT NULL /* 카테고리명 */
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

/* 하위카테고리 */
DROP TABLE downCategory 
	CASCADE CONSTRAINTS;

/* 하위카테고리 */
CREATE TABLE downCategory (
	upcg_code NUMBER(8) NOT NULL, /* 상위카테고리 코드 */
	downcg_code NUMBER(8) NOT NULL, /* 하위카테고리 코드 */
	downcg_name  VARCHAR2(30) NOT NULL /* 하위카테고리명 */
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

/* 상품 */
DROP TABLE Products 
	CASCADE CONSTRAINTS;

/* 상품 */
CREATE TABLE Products (
	pnum NUMBER(8) NOT NULL, /* 상품번호 */
	pname  VARCHAR2(50) NOT NULL, /* 상품명 */
	pimage1  VARCHAR2(100), /* 이미지1 */
	pimage2  VARCHAR2(100), /* 이미지2 */
	pimage3  VARCHAR2(100), /* 이미지3 */
	price NUMBER(8) NOT NULL, /* 상품 정가 */
	saleprice NUMBER(8), /* 상품 판매가 */
	pqty NUMBER(8), /* 상품 수량 */
	point NUMBER(8), /* 포인트 */
	pspec  VARCHAR2(20), /* 스펙 */
	pcontent  VARCHAR2(1000), /* 상품설명 */
	pcompany  VARCHAR2(50), /* 제조사 */
	pindate DATE, /* 등록일 */
	upcg_code NUMBER(8), /* 상위카테고리 코드 */
	downcg_code NUMBER(8) /* 하위카테고리 코드 */
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

/* 장바구니 */
DROP TABLE Cart 
	CASCADE CONSTRAINTS;

/* 장바구니 */
CREATE TABLE Cart (
	cartNum <지정 되지 않음> NOT NULL, /* 장바구니번호 */
	userid_fk VARCHAR2(20) NOT NULL, /* 아이디 */
	pnum_fk NUMBER(8) NOT NULL, /* 상품번호 */
	pqty NUMBER(8), /* 수량 */
	indate DATE /* 등록일 */
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


