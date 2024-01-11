//day03.js
//select * from member where userid like '%sh%';
[like절]=> 정규식
$regex 연산자를 이용해 정규식으로 like검색을 한다

use mydb
db

//select * from member where userid like '%sh%';
db.member.find({userid:{$regex:/sh/}}) 
db.member.find({userid:/h/}) // where userid like '%h%';

//select * from member where userid like 'sh%';
//sh로 시작하는 회원정보
db.member.find({userid:/^sh/})

//select * from member where userid like '%in';
//in으로 끝나는 회원정보
db.member.find({userid:/in$/})
db.member.find({userid:/ng$/})

/*[실습]------------------------------
<1> emp에서 이름이 S로 시작하는 사람의 정보를 보여주세요
<2>이름 중 S로 끝나는 사람의 정보를 보여주세요
<3>이름 중 E자가 들어가는 사람의 정보를 보여주세요.
----------------------------------------*/
db.emp.find({ename:{$regex:/^S/}})
db.emp.find({ename:/S$/})
db.emp.find({ename:/S/})
db.emp.find({ename:/E/})

[order by 절]
sort({정렬기준필드: 1})  오름차순:1, 내림차순: -1

//member에서 나이가 22세 이상인 회원정보 보여주되 나이 내림차순으로 보여주세요
db.member.find({age:{$gte:22}}).sort({age:-1})

//[실습]
//<1>member에서 회원의 나이를 내림차순으로 정렬하고, 
//  같은 나이일 때는 이름 가나다순으로 정렬해서 출력하세요
db.member.find().sort({age:-1, name:1})
db.member.insertOne({name:'김영희', userid:'kim', tel:'010-5444-4444', age:20, grade:'D'})

//<2> emp에서 부서번호로 정렬한 후 부서번호가 같을 경우
//	급여가 많은 순으로 정렬하여 사번,이름,부서번호,급여를 출력하세요
db.emp.find({},{empno:1,ename:1,deptno:1,sal:1}).sort({deptno:1,sal:-1})

//<3> emp에서 부서번호가 10,20인 사원의 이름,부서번호,업무를 출력하되
//    이름 순으로 정렬하시오
db.emp.find({deptno:{$in:[10,20]}}, {ename:1, deptno:1, job:1}).sort({ename:1})

[count]

전체회원수
db.member.find().count()
//select count(*) from member

//member에서 성적이 'A'인 회원이 몇명인지 보여주세요
db.member.find({grade:'A'}).count()

//member에서 성적이 없는 회원이 몇명인지 보여주세요
// $exists:true (true:1, false:0)
// 연산자 
db.member.find({grade:{$exists:0}}).count()
db.member.find({grade:{$exists:1}}).count()
//select count(grade) from member;

[limit]
//성적순으로 오름차순 정렬해서 출력하되, 상위 3명만 출력하세요
//select * from member order by grade asc limit 3
db.member.find({grade:{$exists:1}}).sort({grade:1}).limit(3)

db.member.find({grade:{$exists:1}}).sort({grade:1}).limit(1)
db.member.findOne({grade:{$exists:1}})

[distinct]
//select distinct deptno from emp
db.member.distinct("age")
db.emp.distinct("deptno")

//<1> emp에서 30번 부서의 사원수를 출력하시오.
db.emp.find({deptno:30}).count()
//<2> emp에서 보너스(comm)을 받는 사원의 수를 출력하시오
db.emp.find({comm:{$exists:true}}).count()
db.emp.find({comm:{$gt:0}}).count()
//<3> 직업이 SALESMAN이면서 보너스를 400이상 받는 사원수를 출력하시오
db.emp.find({$and:[ {job:'SALESMAN'},{comm:{$gte:400}}]}).count()
//<4> emp에서 중복되지 않는 부서번호를 출력하세요.
db.emp.find({},{deptno:1})
db.emp.distinct("deptno")
//<5> emp의 직업군을 보여주세요
db.emp.find({},{job:1})
db.emp.distinct("job")

//# 내장형 문서 조회
//{key:value, key2:value2, {key3:value} } ==>Embeded Document

db.inventory.insertMany( [     
{ item: "journal", qty: 25, size: { h: 14, w: 21, uom: "cm" }, status: "A" } , 
{ item: "notebook", qty: 50, size: { h: 8.5, w: 11, uom: "in" }, status: "A" } , 
{ item: "paper", qty: 100, size: { h: 8.5, w: 11, uom: "in" }, status: "D" } , 
{ item: "planner", qty: 75, size: { h: 22.85, w: 30, uom: "cm" }, status: "D" } , 
{ item: "postcard", qty: 45, size: { h: 10, w: 15.25, uom: "cm" }, status: "A" } 
]);

db.inventory.find()
db.inventory.find().count()

//상품 중에서 size가 높이:10, 폭:15.25. 단위:'cm'인 상품 조회
db.inventory.find({size:{h:10, w:15.25, uom:'cm'}})

//size중 높이가 10 미만인 상품을 보여주세요
db.inventory.find({size:{h:{$lt:10}}})//===> x
db.inventory.find({"size.h":{$lt:10}})//===> o

//단위(uom)가 cm인 상품들만 가져와 보여주세요
db.inventory.find({"size.uom":"cm"})
//높이가 10미만인 상품 중 상태가 'A'급인 상품만 보여주세요
db.inventory.find({"size.h":{$lt:10}, status:"A"})

//#  배열값 조회
db.product.insertMany([
	{ item: "journal", qty: 25, tags: ["blank", "red"], dim_cm: [ 14, 21 ] },
	 { item: "notebook", qty: 50, tags: ["red", "blank"], dim_cm: [ 14, 21 ] },
	 { item: "paper", qty: 100, tags: ["red", "blank", "plain"], dim_cm: [ 14, 21 ] },
	 { item: "planner", qty: 75, tags: ["blank", "red"], dim_cm: [ 22.85, 30 ] },
	 { item: "postcard", qty: 45, tags: ["blue"], dim_cm: [ 10, 15.25 ] } 
]);

show collections
db.product.find()
//tags 필드에 red,blank값을 가지고 있는 모든 상품을 조회해 출력하세요
db.product.find({tags:['red','blank']})
db.product.find({tags:['red','blank']}).count()
/*
배열 선택자
$all: 주어진 조건의 모든 요소를 포함하는 배열
$elemMatch: 주어진 조건의 모든 요소와 일치하는 배열
$size: 주어진 크기 조건과 일치하는 배열
------------------------------------------
*/
db.product.find({tags:{$all:['red','blank']}})
db.product.find({tags:{$all:['red','blank']}}).count()

//tags 의 배열크기가 2인 상품을 가져와 출력하세요
db.product.find({tags:{$size:2}})
//dim_cm에 14값을 포함하고 있는 상품을 가져와 출력하세요
db.product.find({dim_cm:{$all:[14]}})

//dim_cm필드에 25이상 값을 포함하고 있는 상품을 출력하세요
db.product.find({dim_cm:{$gte:25}})

//dim_cm필드에 14이상 값을 포함하고 있는 상품을 출력하세요
db.product.find({dim_cm:{$gte:14}})
//dim_cm 배열 인덱스 0번에 있는 값 중 14이상인 상품을 출력하세요
db.product.find({"dim_cm.0":{$gte:14}})

//$elemMatch

db.product.insertOne({
    item:"card", qty:150, tags:["red","green"], dim_cm:[9,9],
    review:[
        {no:1, content:'좋아요', userid:'aaa'},
        {no:2, content:'그저 그래요', userid:'bbb'},
        {no:3, content:'예쁘고 좋아요', userid:'ccc'},                
    ]
})

db.product.find()
//상품 중 review가 있는 상품정보를 가져와 보여주세요
db.product.find({item:{$exists:1}})
db.product.find({review:{$exists:1}})
//product 중에 review 필드에 글번호가 1번이 달려있는 상품정보를 보여주세요
db.product.find({review:{$elemMatch:{no:1}}})


var cr=db.member.find();

find()로 반환되는 객체는 cursor => 반복문 이용해서 추출 가능

cr
while(cr.hasNext()){
    var obj=cr.next();
    printjson(obj);
}

var mycr=db.emp.find({job:'SALESMAN'})


//forEach()사용
mycr.forEach(printjson);

var cr2=db.member.find({grade:'C'})
var arr=cr2.toArray();//커서가 참조하는 결과를 배열로 반환

for(i=0;i<arr.length;i++)
    printjson(arr[i])
//---------------------------------
CRUD - Update
- updateOne({})
- updateMany([])
- replaceOne({검색조건},{대체할도규먼트},{옵션}) 
//    => 도큐먼트 교체
//    옵션 : upsert 을 true 로 줄 경우 검색한 도큐먼트가 없을 경우 대체할 도규먼트로 새로 생성해서 수정한다

db.user.find()
db.user.replaceOne({userid:'go'}, {name:'홍길동',userid:'hong', passwd:'789'})
//==> _id 값은 변경되지 않는다. 교체이므로
db.user.find()

//userid가 go인 도큐먼트를 찾아서 고길동 gogildong 1234 로 대체하되 upsert옵션을 true로 주세요
db.user.replaceOne({userid:'go'},{name:'고길동',userid:'gogildong', passwd:'1234'},{upsert:true})
db.user.find()
db.user.replaceOne({userid:'happy'},{name:'김행복',userid:'happy',passwd:'5678'})
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
[1] updateOne({조건},{$set:{필드명:수정할값}})

db.member.find()

//grade 필드가 없는 회원을 선택해서 성적을 'F'로 수정하세요
db.member.updateOne({grade:{$exists:false}},  {$set:{grade:'F'}})
db.member.find()

//나이가 22세를 초과하는 회원들의  grade를 모두 A로 수정하세요
db.member.updateMany({age:{$gt:22}},{$set:{grade:'A'}})

//성적이 A인 회원들의 나이를 1씩 증가시키세요
db.member.updateMany({grade:'A'},{$inc:{age:1}})    
    
db.member.find()    
//1씩 감소
db.member.updateMany({grade:'A'},{$inc:{age:-1}})    
    
    





