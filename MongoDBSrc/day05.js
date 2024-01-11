//day05.js
CRUD - Delete
//[1] 1개 document 삭제     : deleteOne({조건})
//[2] 여러 개 document 삭제  : deleteMany({조건})
//
//전체 삭제
deleteManay({})

use mydb
db
show collections

db.member.find()
//delete from member where grade='D';
db.member.deleteMany({grade:'D'})
//나이가 20세 미만인 회원들을 삭제하세요
//delete from member where age < 20;
db.member.deleteMany({age:{$lt:20}})


//# MongoDB 의 집계 함수
//[1]  count
//[2] distinct
//select count(*) from emp
db.emp.count()

//select count(*) from emp where deptno=20;
db.emp.find({deptno:20}).count()

db.emp.find()
//급여가 3000이상 사원의 사원수를 보여주세요
db.emp.find({sal:{$gte:3000}}).count()

//매니저가 없는 사원의 사원수를 보여주세요
db.emp.find({mgr:{$exists:true}}).count()

db.emp.find({mgr:{$exists:false}}).count()


db.user.find()
//user 컬렉션의 전체 도큐먼트 삭제
db.user.deleteMany({})

show collections

//user 컬렉션을 삭제하세요
db.user.drop()
show collections

/*
[실습]
1. 게시판의 모든 글을 삭제하시오
2. 모든 게시판 컬렉션을 삭제하시오
3. boardDB 데이터베이스를 삭제하시오.
*/
show dbs
use boardDB
//1. 게시판의 모든 글을 삭제하시오
db.article.deleteMany({})

//2. 모든 게시판 컬렉션을 삭제하시오
db.article.drop();

//3. boardDB 데이터베이스를 삭제하시오.
use boardDB
db.dropDatabase()

//emp에서 직업의 종류를 보여주세요
db.emp.find({job:{$exists:1}},{job:1,_id:0})
db.emp.distinct("job")
/*
# 몽고디비 집계 방법
파이프라인을 이용한 집계방법 ==>  aggregate([{},{},...])함수 사용
# 집계 파이프라인 명령어
$project : select절    ex)  db.컬렉션명.aggregate([{$project:{name:1, userid:1}}])
$match: where절,having절ex)  db.컬렉션명.aggregate([{$match:{userid:'hong'}])
$group: group by 절    ex)  db.컬렉션명.aggregate([{$group:{_id:'$age', count:{$sum:1}}}, {$match:{count:{$gt:1}}} ])
$sort: order by 절     ex)  db.컬렉션명.aggregate([{$group:{...}}, {$sort:{cnt:1}}])
$limit
$unwind
*/
use mydb
db

db.articles.insertMany([
    { "_id" : ObjectId("512bc95fe835e68f199c8686"), "author" : "john", "score" : 80, "views" : 100 },
    { "_id" : ObjectId("512bc962e835e68f199c8687"), "author" : "john", "score" : 85, "views" : 521 },
    { "_id" : ObjectId("55f5a192d4bede9ac365b257"), "author" : "ahn", "score" : 60, "views" : 1000 },
    { "_id" : ObjectId("55f5a192d4bede9ac365b258"), "author" : "li", "score" : 55, "views" : 5000 },
    { "_id" : ObjectId("55f5a1d3d4bede9ac365b259"), "author" : "annT", "score" : 60, "views" : 50 },
    { "_id" : ObjectId("55f5a1d3d4bede9ac365b25a"), "author" : "li", "score" : 94, "views" : 999 },
    { "_id" : ObjectId("55f5a1d3d4bede9ac365b25b"), "author" : "ty", "score" : 95, "views" : 1000 }
])
db.articles.find()

//select author, score from articles
db.articles.find({}, {author:1, score:1})
db.articles.aggregate([{$project:{author:1, score:1, _id:0}}])

//# $match
// select * from articles where author = 'john'
db.articles.aggregate([{$match:{author:'john'}}])

// select * from articles where score>=80
db.articles.aggregate([{$match:{score:{$gte:80}}}])

//select * from articles where author='li' and score>=60
db.articles.aggregate([{$match:{author:'li', score:{$gte:60}}}])

//작성자별 score의 총합계 점수를 보여주세요
//select sum(score) as total from articles group by author

db.articles.aggregate([{$group:{_id:'$author', total:{$sum:'$score'} }}])
//$group을  사용하기 위해서는  _id 값에 그룹화의 기준이 되는 값을 설정해야 한다.
//위에서는 author를 기준으로 그룹화를 진행하도록 했다.

//select sum(score) as total from articles group by author having total>100
db.articles.aggregate([{$group:{_id:'$author', total:{$sum:'$score'}}}, {$match:{total:{$gt:100}}} ])

//orders라는 컬렉션을 생성하세요
db.createCollection('orders')

show collections
db.orders.deleteMany({})
db.orders.insertOne({cust_id:"hong", ord_date: ISODate("2024-11-20"), status:'A', price:300, 
                        items:[{sku:'planner',qty:2, price:3}]})

db.orders.find()

db.orders.insertMany([
{
      cust_id: "abc123",
      ord_date: ISODate("2012-01-02T17:04:11.102Z"),
      status: 'A',
      price: 100,
      items: [ { sku: "xxx", qty: 25, price: 1 },
               { sku: "yyy", qty: 25, price: 1 } ]
    },
    {
      cust_id: "abc123",
      ord_date: ISODate("2012-01-02T17:04:11.102Z"),
      status: 'A',
      price: 500,
      items: [ { sku: "xxx", qty: 25, price: 1 },
               { sku: "yyy", qty: 25, price: 1 } ]
    },
    {
      cust_id: "abc123",
      ord_date: ISODate("2012-01-02T17:04:11.102Z"),
      status: 'B',
      price: 130,
      items: [ { sku: "jkl", qty: 35, price: 2 },
               { sku: "abv", qty: 35, price: 1 } ]
    },
    {
      cust_id: "abc123",
      ord_date: ISODate("2012-01-02T17:04:11.102Z"),
      status: 'B',
      price: 230,
      items: [ { sku: "jkl", qty: 25, price: 2 },
               { sku: "abv", qty: 25, price: 1 } ]
    },
{
      cust_id: "abc123",
      ord_date: ISODate("2012-01-02T17:04:11.102Z"),
      status: 'B',
      price: 230,
      items: [ { sku: "jkl", qty: 25, price: 2 },
               { sku: "abv", qty: 25, price: 1 } ]
    },
    {
      cust_id: "abc123",
      ord_date: ISODate("2012-01-02T17:04:11.102Z"),
      status: 'A',
      price: 130,
      items: [ { sku: "xxx", qty: 15, price: 1 },
               { sku: "yyy", qty: 15, price: 1 } ]
    },
    {
      cust_id: "abc456",
      ord_date: ISODate("2012-02-02T17:04:11.102Z"),
      status: 'C',
      price: 70,
      items: [ { sku: "jkl", qty: 45, price: 2 },
               { sku: "abv", qty: 45, price: 3 } ]
    },
{
      cust_id: "abc456",
      ord_date: ISODate("2012-02-02T17:04:11.102Z"),
      status: 'A',
      price: 150,
      items: [ { sku: "xxx", qty: 35, price: 4 },
               { sku: "yyy", qty: 35, price: 5 } ]
    },
    {
      cust_id: "abc456",
      ord_date: ISODate("2012-02-02T17:04:11.102Z"),
      status: 'B',
      price: 20,
      items: [ { sku: "jkl", qty: 45, price: 2 },
               { sku: "abv", qty: 45, price: 1 } ]
    },
    {
      cust_id: "abc456",
      ord_date: ISODate("2012-02-02T17:04:11.102Z"),
      status: 'B',
      price: 120,
      items: [ { sku: "jkl", qty: 45, price: 2 },
               { sku: "abv", qty: 45, price: 1 } ]
    },
    {
      cust_id: "abc780",
      ord_date: ISODate("2012-02-02T17:04:11.102Z"),
      status: 'B',
      price: 260,
      items: [ { sku: "jkl", qty: 50, price: 2 },
               { sku: "abv", qty: 35, price: 1 } ]
    }
])
db.orders.find()
//select count(*) cnt from orders
//주문 컬렉션에서 총 주문건수를 가져와 보여주세요
//$sum:1 ==>도큐먼트 하나당 1을 더하란 의미

db.orders.aggregate([{$group:{_id:null,cnt:{$sum:1}}}])

//고객아이디별 구매가격총액을 가져와 보여주세요
//select cust_id, sum(price) as totalPrice from orders group by cust_id order by totalPrice desc
db.orders.aggregate([
{$group:{_id:"$cust_id", totalPrice:{$sum:"$price"}}},
{$sort:{totalPrice:-1}}
])

db.orders.find({cust_id:'abc456'})

//고객별 최소 구매가와 최대 구매가를 보여주세요
//select max(price) mxPrice, min(price) mnPrice from orders group by cust_id order by cust_id asc
//$min, $max
db.orders.aggregate([
{$group:{_id:'$cust_id', mxPrice:{$max:'$price'}, mnPrice:{$min:'$price'}}},
{$sort:{cust_id:1}}
])

//고객별 평균 구매가를 보여주세요
//$avg
//select avg(price) avPrice from orders group by cust_id
db.orders.aggregate([
{$group:{_id:'$cust_id', avPrice:{$avg:"$price"}}}
])

db.orders.find({},{cust_id:1,price:1})
//고객의 주문날짜별 구매총액을 보여주세요
//select cust_id,ord_date, sum(price) totalPrice from orders
//group by cust_id,ord_date
//2개 이상의 필드로 group할때는 _id:{필드명1:'$필드명',필드명2:'$필드명'}

db.orders.aggregate([
{
    $group:{
        _id:{cust_id:'$cust_id',ord_date:'$ord_date'},
        totalPrice:{$sum:'$price'}
    }
}
])
//===> 고객의 주문날짜별 집계

db.orders.find()

//고객의 월별 주문집계

//https://www.mongodb.com/docs/manual/reference/operator/aggregation/dateToString/
//
//{ $dateToString: {
//    date: <dateExpression>,
//    format: <formatString>,
//    timezone: <tzExpression>,
//    onNull: <expression>
//} }
//
db.orders.aggregate([
    {
        $group:{
            _id:{
                    cust_id:'$cust_id', 
                    ord_date:{$dateToString:{date:'$ord_date', format:'%Y-%m-%d'}}
                },
            totalPrice:{$sum:'$price'}
        }
    }
])

//고객별 주문건수가 1개를 초과하는 데이터를 보여주세요
//select cust_id, count(*) cnt from orders group by cust_id having count(*) > 1
//having => $match사용
db.orders.aggregate([
    {$group:{_id:'$cust_id', cnt:{$sum:1}}},
    {$match:{cnt:{$gt:1}}}
])


//status별로 묶어 주문건수가 2개 이상인 데이터를 보여주세요
//select status, count(*) cnt  group by status having cnt > 1

db.orders.aggregate([
    {$group:{_id:'$status', cnt:{$sum:1}}},
    {$match:{cnt:{$gt:1}}}
])
db.orders.find()

//status별 주문총액을 가져와 출력하세요
//select sum(price) totalPrice from orders group by status
db.orders.aggregate([
    {$group:{_id:'$status', totalPrice:{$sum:'$price'}}},
    {$sort:{totalPrice:-1}}
])

//고객의 주문일자별 주문총액을 보여주되 주문총액이 250 초과하는 주문정보를 보여주세요
//select cust_id,ord_date, sum(price) totalPrice from orders
//group by cust_id,ord_date having totalPrice > 250
db.orders.aggregate([
    {
        $group:{
            _id:{
                        cust_id:'$cust_id', 
                        ord_date:{$dateToString:{date:'$ord_date', format:'%Y-%m-%d'}}
                 }, 
            totalPrice:{$sum:'$price'} 
        }
    },
    {
        $match:{
            totalPrice:{$gt:250}
        }
    }
])

//status가 'A'인 주문건수 중에서 고객별 주문 총액을 구해 보여주세요
//select cust_id, sum(price) totalPrice  from orders
//where status='A' 
//group by cust_id
//having totalPrice >250
db.orders.aggregate([
    {$match:{status:'A'}},
    {$group:{_id:'$cust_id', totalPrice:{$sum:'$price'}}},
    {$match:{totalPrice:{$gt:250}}}
])


