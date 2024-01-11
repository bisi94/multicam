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
	cartNum NUMBER(8) NOT NULL, /* 장바구니번호 */
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
		) on delete cascade ;

ALTER TABLE Cart
	ADD
		CONSTRAINT FK_Products_TO_Cart
		FOREIGN KEY (
			pnum_fk
		)
		REFERENCES Products (
			pnum
		) on delete cascade ;

ALTER TABLE Cart
    ADD
        CONSTRAINT CK_PRODUCTS_PQTY
        CHECK (PQTY>0 AND PQTY<51);
------------------------------------------------------------
DROP SEQUENCE upCategory_seq;
create sequence upCategory_seq nocache;

DROP SEQUENCE downCategory_seq;
create sequence downCategory_seq nocache;

DROP SEQUENCE products_seq;
create sequence products_seq nocache;

DROP SEQUENCE cart_seq;
create sequence cart_seq nocache;

insert into upCategory values(upCategory_seq.nextval,'전자제품');
insert into upCategory values(upCategory_seq.nextval,'생활용품');
insert into upCategory values(upCategory_seq.nextval,'의류');

commit;

select * from upCategory;


insert into downCategory(upCg_code, downCg_code, downCg_name)
values(1, downCategory_seq.nextval, '가전제품');
insert into downCategory(upCg_code, downCg_code, downCg_name)
values(1, downCategory_seq.nextval, '컴퓨터');

commit;

select * from downCategory;

insert into downCategory(upCg_code, downCg_code, downCg_name)
values(2, downCategory_seq.nextval, '주방세제');
insert into downCategory(upCg_code, downCg_code, downCg_name)
values(2, downCategory_seq.nextval, '휴지');

commit;

desc products;
desc cart;

select * from cart;

select c.*,pname, pimage1, price, saleprice, point
, (c.pqty * saleprice) totalPrice
, (c.pqty * point) totalPoint
from cart c join products p
on c.pnum_fk = p.pnum;

--CartView를 만들자
CREATE OR REPLACE VIEW CARTVIEW
AS
select c.*,pname, pimage1, price, saleprice, point
, (c.pqty * saleprice) totalPrice
, (c.pqty * point) totalPoint
from cart c join products p
on c.pnum_fk = p.pnum;

SELECT * FROM CARTVIEW WHERE USERID_FK='kim';

SELECT SUM(TOTALPRICE) CARTTOTALPRICE, SUM(TOTALPOINT) CARTTOTALPOINT
FROM CARTVIEW
WHERE USERID_FK='kim';







select * from member;
desc member;
alter table member modify userpwd varchar2(100);

desc member;

select * from member;


select * from memberView;

select * from member where mstate=9;

update member set mstate=9 where userid='admin2';
commit;


drop table spring_board;

drop table spring_board;

--답변형 게시판

create table spring_board(
	num number(8) primary key, --글번호
	userid varchar2(20) not null references member (userid) on delete cascade, --작성자 아이디(FK)
	passwd varchar2(100) not null, --비번
	subject varchar2(300) not null,--제목
	content varchar2(4000), --글내용
	wdate date default sysdate, --작성일
	readnum number(8) default 0,
	filename varchar2(500), --첨부파일명[uuid_a.txt] ==> 물리적 파일명
	originFilename varchar2(500), --원본파일명 [a.txt] ==> 논리적 파일명
	filesize number(8),--첨부파일크기
	
	refer number(8), -- 글그룹번호 [답변형 게시판에서 필요한 컬럼]
	lev number(8),--답변 레벨  [답변형 게시판에서 필요한 컬럼]
	sunbun number(8)--같은 글 그룹내에 순서 정렬시 필요  [답변형 게시판에서 필요한 컬럼]
);

drop sequence spring_board_seq;

create sequence spring_board_seq nocache;

select * from spring_board;

select * from spring_board order by refer desc, sunbun asc;

select * from (
select row_number() over(order by refer desc, sunbun asc) rn, spring_board.*
from spring_board) where rn>0 and rn<6;

cpage       pageSize        start       end
1               5           0           6
2               5           5           11
3               5           10          16

start = (cpage-1) * pageSize
end = start + (pageSize+1)







