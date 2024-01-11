//day04.js
/*
--도큐먼트 수정 연산자--------------------------------
$set : field값 설정
$inc : field값을 증가시키거나 감소시킴
$inc:{age:2} <= age값을 본래 값에서 2만큼 증가
$mul: 곱한 값으로 수정
$rename: 필드명을 새이름으로 변경한다
$min: 필드의 값이 주어진 값보다 클 경우 새 값으로 교체합니다. 
     만약 원래 값이 200이었고 $min의 값이 150이었다면 150으로 바뀝니다. 
     기존 기록을 경신하는 경우 사용됩니다.
$max: 필드의 값이 주어진 값보다 작을 경우 새 값으로 교체합니다. 
만약 원래 값이 800이었고 $max의 값이 950이라면, 950으로 바뀝니다. 
$currentDate: 해당 필드 값을 현재 날짜로 교체합니다. 
두 가지 타입의 현재 날짜가 있는데 하나는 기본적으로 쓰이는 Date이고 
다른 하나는 Timestamp입니다. 기본 타입을 사용하려면 그냥 true하면 되고, 
timestamp 타입을 사용하려면 $type 연산자를 사용해야 합니다.
{ $currentDate: { 필드: true } }
{ $currentDate: { 필드: { $type: 'timestamp' } } }
$unset
해당 필드를 제거합니다. 만약 배열의 요소를 $unset한 경우에는 제거하진 않고 null로 교체합니다.
$setOnInsert
$set과 비슷한데 upsert의 경우에만 작동합니다. 만약 upsert가 일어나지 않으면 아무 동작도 하지 않습니다.
--------------------------------------------------------------
*/
use mydb
db
db.dept.find()
//$set
//dept에서 40번 부서의 위치를 SEOUL로 수정하세요
db.dept.updateOne({deptno:40},{$set:{loc:'SEOUL'}})
db.dept.find()
//$inc
//dept에서 모든 부서번호를 5만큼씩 증가시키세요
db.dept.updateMany({},{$inc:{deptno:5}})
db.dept.find()
db.dept.updateMany({},{$inc:{deptno:-5}})

//$mul
//member컬렉션에서 userid에 'sh' 가 들어간 사람들의 나이를 2배로 만드세요
db.member.find()
db.member.updateMany({userid:/sh/},{$mul:{age:2}})
db.member.updateMany({userid:/sh/},{$mul:{age:1/2}})

//$rename :=>필드명 수정 {$rename:{old필드명:new필드명}}
db.member.updateMany({},{$rename:{'tel':'phone'}})
db.member.find()

//$min: 필드의 값이 주어진 값보다 클 경우 새 값으로 교체합니다. 
//     만약 원래 값이 200이었고 $min의 값이 150이었다면 150으로 바뀝니다. 
//     기존 기록을 경신하는 경우 사용됩니다.

//$max: 필드의 값이 주어진 값보다 작을 경우 새 값으로 교체합니다. 
//만약 원래 값이 800이었고 $max의 값이 950이라면, 950으로 바뀝니다. 

//아이디가 'hong'회원의 나이가 20세보다 크면 20세로 수정하세요 => $min
db.member.updateMany({},{$min:{age:20}})
db.member.find()
//아이디가 hong인 회원의 나이가 30세보다 작으면 30세로 수정하세요=> $max

db.member.update({userid:'hong'},{$max:{age:30}})

db.member.update({userid:'shin'},{$max:{age:18}})

db.member.find()

//$currentDate: 해당 필드 값을 현재 날짜로 교체합니다
//{ $currentDate: { 필드: true } }
//{ $currentDate: { 필드: { $type: 'timestamp' } } }

//모든 회원에 indate라는 필드를 추가하고 현재날짜값이 들어가도록 하세요

db.member.updateMany({},{$currentDate:{indate:true}})
db.member.find()

db.member.updateMany({},{$currentDate:{joindate:{$type:'timestamp'}}})

//timestamp는  1970년 1월1일 기준으로 현재 날짜까지 계산해서 정수값으로 반환한다
//=>Timestamp(1700183266, 2)
//date는 =>ISODate("2023-11-17T01:05:53.097+0000"),


//김신입 회원정보를 insert하세요
db.member.insertOne({name:'김신입',userid:'newMember',age:18, grade:'B', phone:'010-4545-7878',indate:new Date()})
db.member.insertOne({name:'최신입',userid:'newMember',age:18, grade:'B', phone:'010-4545-7878',indate:new Date(),joindate:new Timestamp()})
db.member.find()

//member에서 joindate라는 필드를 삭제하세요
//=> $unset : 지정된 필드를 제거
db.member.updateMany({},{$unset:{joindate:""}})
//----------------------------------------------------
db.product.find()
/*
[
	{ item: "journal", qty: 25, tags: ["blank", "red"], dim_cm: [ 14, 21 ] },
	 { item: "notebook", qty: 50, tags: ["red", "blank"], dim_cm: [ 14, 21 ] },
	 { item: "paper", qty: 100, tags: ["red", "blank", "plain"], dim_cm: [ 14, 21 ] },
	 { item: "planner", qty: 75, tags: ["blank", "red"], dim_cm: [ 22.85, 30 ] },
	 { item: "postcard", qty: 45, tags: ["blue"], dim_cm: [ 10, 15.25 ] } 
	 ]
*/
//# 배열 요소값 수정

/*
db.collection.updateMany({선택조건},
		{<update연산자>:{"<array>.$[<identifier>]":value}},
		{arrayFilters:[{<identifier>:<condition>}]}
		);
*/
db.product.find()
//item이 journal인 상품의 tags 요소값 중 blank를 blue로 변경하세요
db.product.updateMany({item:'journal'},
{$set:{"tags.$[elem]":"blue"}},{arrayFilters:[{elem:'blank'}]})
//elem : =>변수명 (알아서 지정)

db.product.find()

/*
# ---배열수정 연산자------------
$addToSet: 배열필드에 해당 요소가 없으면 추가하고 있으면 아무것도 하지 않습니다. 
	몽고DB에서 자체적으로 배열에 해당 요소가 있는지 검사해주기 때문에 편합니다.
	{ $addToSet: { 필드1: 값, 필드2: 값, ... } }

$pop
배열 메소드처럼 몽고DB 배열에서 맨 앞 또는 맨 뒤 요소를 꺼내는 겁니다. 
shift와 pop을 합쳐놓은 연산자입니다. -1 값은 shift 기능, 1 값은 pop 기능을 합니다.
	{ $pop: { 필드1: ±1, 필드2: ±1, ... } }

$pull
배열에서 조건을 만족하는 특정한 요소 하나를 제거한다. 꺼내는 조건은 쿼리 연산자와 같습니다.
{ $pull: { 조건1, 조건2, ... } }
$pullAll
$pull 연산자와는 달리 $pullAll은 조건이 아니라 그냥 일치하는 값을 배열에서 꺼냅니다.
{ $pullAll: { 필드: [값1, 값2, ...] } }
$push
배열 필드에 값을 push합니다. 
{ $push: { 필드1: 값, 필드2: 값, ... } }
조심해야할 것은 값이 배열일 경우 한 번에 push해버립니다. 
만약 원래 [1, 2]라는 배열이 있다면 [3, 4, 5]를 push할 경우 [1, 2, [3, 4, 5]]가 되어버립니다. 
3, 4, 5를 따로따로 push하고 싶다면
{ $push: { 필드: { $each: 배열 } } }
해야 합니다.

$each
방금 위에서도 사용되었습니다. 다른 용례로 $addToSet과 같이 사용하는 경우가 있습니다. 
$addToSet도 $push처럼 한 번에 배열을 집어넣기 때문에 따로따로 넣고 싶다면
{ $addToSet: { 필드: { $each: 배열 } } }
해야 합니다.
-----------------------------------------
*/
//#배열 수정 연산자
$addToSet
{$addToSet:{필드명:값}}
//notebook상품의 tags 에 yellow를 추가하세요

db.product.updateMany({item:'notebook'},{$addToSet:{tags:'yellow'}})
db.product.find({item:'notebook'})
db.product.updateMany({item:'notebook'},{$addToSet:{tags:'red'}})


$pop
//배열의 뒤(1) 또는 앞(-1)에 있는 요소를 삭제
//$pop : 1이면 뒤를 삭제(pop), -1이면 앞을 삭제(shift)
//planner아이템의 tags 중 제일 뒤의 요소를 삭제하세요
db.product.find({item:'planner'})

db.product.updateMany({item:'planner'},{$pop:{tags:1}});

$push
//item이 planner인 상품에 tags 중 red를 추가하세요
db.product.updateMany({item:'planner'},{$push:{tags:'red'}})
db.product.find({item:'planner'})

//item이 planner인 상품에 tags 중 [blue, yellow, pink]를 추가하세요
db.product.updateMany({item:'planner'},{$push:{tags:['blue','yellow','pink']}})
/*=>결과
tags:[blank,red,['blue','yellow','pink']]

=>해결하려면 $each 를 사용해야 한다
{$push:{필드명:$each:[요소1, 요소2]}}
*/

db.product.find({item:'planner'})
db.product.updateMany({item:'planner'},{$pop:{tags:1}})
db.product.updateMany({item:'planner'},{$push:{tags:{$each:['blue','yellow','pink']}}})

//$pull : 조건을 이용해서 하나 제거
//$pullAll:값으로 여러 개를 제거할 때 사용

//planner에서 tags 중에 yellow 값을 제거하세요
db.product.updateMany({item:'planner'},{$pull:{tags:'yellow'}})
db.product.find({item:'planner'})
//planner에서 tags 값 중에 [blue,pink]를 제거하세요

db.product.updateMany({item:'planner'},{$pullAll:{tags:['blue','pink']}})

//planner 에 $addToSet이용해서 'orange','green','red' 값을 tags에 넣으세요
db.product.updateMany({item:'planner'},{$addToSet:{tags:{$each:['orange','green','red']}}})
db.product.updateMany({item:'planner'},{$push:{tags:{$each:['orange','green','red']}}})
db.product.find({item:'planner'})



/*--------------------------------------------------------------------------------------------------------
[실습]
use boardDB
1. boardDB에서 작업한다
2. 자유게시판에 아무 글이나 2~3개 작성한다. 특히 그 중에 글 하나에는 댓글 하나가 달린 상태로 생성해본다.
3. 비밀게시판을 생성한다.
4. 비밀게시판에 작성자가 'noname'값을 가지는 글을 하나 작성한다.
5. 모든 글에 추천수 필드(upvote)를 추가하고 값을 0으로 설정한다
6. 비밀게시판 글에 추천수를 1 증가시킨다
7. 이미 댓글이 달린 자유게시판 글에 upvote 필드 없이 댓글을 추가한다.
8. 이미 댓글이 달린 글에 방금 달은 댓글에(특징을 기억해서 수정하자) upvote 필드 값을 0으로 추가한다
==>배열요소값 수정 arraysFilters 파라미터 사용해보기.
--------------------------------------------------------------------------------------------------------
 55p, 77p*/





