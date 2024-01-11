//day02.js
use testDB
db
show dbs
use admin
db

1. 데이터베이스 생성
use 데이터베이스명
//=>이미 데이터베이스가 있을 때는 해당 DB를 사용함

2. 데이터베이스 조회
db : 현재 사용중인 데이터베이스를 확인
show dbs : 전체 데이터베이스 목록 
db.stats(): 데이터베이스 상태 확인

3. 데이터베이스 삭제
db.dropDatabase()
//=> use 로 데이터베이스를 선택한 뒤 실행해야 한다

use testDB
db
db.stats()
db
use testDB
db.dropDatabase()

show dbs

use mydb
db
show dbs

//-------------------------------
//# 컬렉션 
[1] 컬렉션 생성
db.createCollection('컬렉션명', 옵션들)

db.createCollection('posts',{capped:true, size:10000, max:1000})
//max: 컬렉션에 추가할 수 있는 최대 document 갯수를 지정
[2] 컬렉션 조회
show collections

[3] 컬렉션 이름 변경
db.컬렉션명.renameCollection('새컬렉션명')
//posts를 bbs로 수정해보세요
db.posts.renameCollection('bbs')
show collections

[4] 컬렉션 삭제
db.컬렉션명.drop()

db.bbs.drop()

show collections
//---------------------------------
db.createCollection('member')
show collections
//# Document

[1] 삽입 : insertOne({ }), insertMany([ {},{},...])

db.member.insertOne({
    name:'홍길동',
    userid:'hong',
    tel:'010-2222-3333',
    age: 20
})
[2] 조회: db.컬렉션명.find({조회조건})

db.member.find({})

db.member.insertMany([
    {name:'김수지',userid:'kim', tel:'011-3333-4444',age:22, grade:'A'},
    {name:'신철민',userid:'shin', tel:'010-4333-4444',age:25, grade:'C'},
    {name:'김유신',userid:'youshin', tel:'010-5333-4444',age:32, grade:'B'},
    {name:'최영희',userid:'choi', tel:'010-6333-4444',age:19, grade:'C'},
])
db.user.insert([
    {_id:1, name:'김길동', userid:'gildong', passwd:'123'},
    {_id:2, name:'고영남', userid:'go', passwd:'456'},
])
db.user.find()

db.user.insertOne({_id:2,name:'신혜선',userid:'noname', passwd:'good'})

==> error발생 duplicate key error 
/*
[실습1]---------------------------------------------------------------------
1. boardDB생성
2. board 컬렉션 생성
3. board 컬렉션에 name 필드값으로 "자유게시판"을 넣어본다
4. article 컬렉션을 만들어 document들을 삽입하되,
   bid필드에 3에서 만든 board컬렉션 자유게시판의 _id값이 참조되도록 처리해보자.

5. 똑 같은 방법으로 "공지사항게시판"을 만들고 그 안에 공지사항 글을 작성하자.
--------------------------------------------------------------------------
*/
use boardDB
db

db.article.drop()
db.board.drop()

freeboard_res=db.board.insertOne({name:'자유게시판'})
db.board.find()
freeboard_res

article에 title, content,writer,bid

freeboardId=freeboard_res.insertedId
freeboardId

db.article.insertMany([
    {bid:freeboardId, title:'처음 쓰는 글입니다',content:'안녕하세요?',writer:'kim'},
    {bid:freeboardId, title:'자유게시판에 쓰는 두번째 글',content:'반가워요',writer:'lee'},
    {bid:freeboardId, title:'저도 글을 씁니다',content:'Hello?',writer:'choi'},
])

db.article.find()
/*
실습
1. board 컬렉션에 name 이 '공지사항게시판'을 갖는 도큐먼트를 삽입하세요
2. 1번에서 삽입할때 그 결과를 변수에 저장하세요

3. article 컬렉션에 공지사항 글을 2개 쓰세요=> bid 필드값에  공지사항게시판의 _id값을 넣어주세요
*/

notice_res=db.board.insertOne({name:'공지사항 게시판'})

notice_id=notice_res.insertedId


db.article.insertMany([
    {bid:notice_id,title:'시스템을 정비 공지',content:'오늘 10시 이후 시스템이 중단됩니다',writer:'admin'},
    {bid:notice_id,title:'특별 이벤트',content:'이벤트가 있습니다',writer:'admin'},
])

db.article.find()
//자유게시판 글만 가져오기
db.article.find({bid:freeboardId})
//공지사항 글만 가져오기

db.article.find({bid:notice_id})

/*
--------------------------------------------------------------------------
[실습2]
1. employees Collection 생성 {capped:true, size:100000} Capped Collection, size는 100000 으로 생성
2. 다음 Document 데이터 넣기 
  => insertOne()으로 3개 문서 삽입, 
     insertMany로 4개 문서 삽입해보기
- empno가 7369, ename:'SMITH', sal:800, deptno: 20, job:'CLERK'
- empno가 7499, ename:'ALLEN', sal:1600, deptno: 30, job:'SALESMAN', comm:300
- empno가 7521, ename:'WARD', sal:1250, deptno: 30,job:'SALESMAN', comm:0
- empno가 7566, ename:'JONES', sal:2975, deptno: 20, job:'MANAGER'
- empno가 7654, ename:'MARTIN', sal:1250, deptno: 30, job:'SALESMAN', comm:800
- empno가 7782, ename:'CLARK', sal:2450, deptno: 10, job:'MANAGER'
- empno가 7934, ename:'MILLER', sal:1300, deptno: 10, job:'CLERK'

*/
db
db.createCollection('employees',{capped:true,size:100000})
show collections
db.employees.insertOne({empno:7369, ename:'SMITH',sal:800,deptno:20, job:'CLERK'})

db.employees.insertOne({empno:7499, ename:'ALLEN',sal:1600,deptno:30, job:'SALESMAN', comm:300})

db.employees.insertOne({empno:7521, ename:'WARD',sal:1250,deptno:30, job:'SALESMAN', comm:0})
db.employees.find()


db.employees.insertMany([
{empno:7566, ename:'JONES', sal:2975, deptno:20, job:'MANAGER'},
{empno:7654, ename:'MARTIN', sal:1200, deptno:30, job:'SALESMAN', comm:800},
{empno:7782, ename:'CLARK', sal:2450, deptno:10, job:'MANAGER'},
{empno:7934, ename:'MILLER', sal:1300, deptno:10, job:'CLERK'}
])

db.employees.stats()

use mydb
db.emp.drop()
var empArr=[
        {
                "empno" : 7499,
                "ename" : "ALLEN",
                "job" : "SALESMAN",
                "mgr" : 7698,
                "hiredate" : "1981-02-20",
                "sal" : 1600.00,
                "comm" : 300.00,
                "deptno" : "30"
        },
        {
                "empno" : 7521,
                "ename" : "WARD",
                "job" : "SALESMAN",
                "mgr" : 7698,
                "hiredate" : "1981-02-22",
                "sal" : 1250.00,
                "comm" : 500.00,
                "deptno" : "30"
        },
        {
                "empno" : 7654,
                "ename" : "MARTIN",
                "job" : "SALESMAN",
                "mgr" : 7698,
                "hiredate" : "1981-09-28",
                "sal" : 1250.00,
                "comm" : 1400.00,
                "deptno" : 30
        },
        {
                "empno" : 7844,
                "ename" : "TURNER",
                "job" : "SALESMAN",
                "mgr" : 7698,
                "hiredate" : "1981-09-08",
                "sal" : 1500.00,
                "comm" : 0.00,
                "deptno" : 30
              },

{"empno":7369, "ename":"SMITH","job":"CLERK",mgr:7902,"hiredate" : "1980-12-17","sal":800.0, "comm" : 0.00,"deptno":20},
{"empno":7566, "ename":"JONES","job":"MANAGER",mgr:7839,"hiredate" : "1981-04-02","sal":2975.0, "comm" : 0.00,"deptno":20.0},
{"empno":7782,"ename":"CLARK","job":"MANAGER",mgr:7839,"hiredate" : "1981-09-08","sal":2450.0, "comm" : 0.00,"deptno":10.0},
{"empno":7934,"ename":"MILLER","job":"CLERK",mgr:7782,"hiredate" : "1981-09-08","sal":1300.0, "comm" : 0.00,"deptno":10.0},
{"empno":7788,"ename":"SCOTT","job":"ANALYST",mgr:7566,"hiredate" : "1982-12-09","sal":3000.0, "comm" : 0.00,"deptno":10.0},
{"empno":7839,"ename":"KING","job":"PRESIDENT","hiredate" : "1981-11-17","sal":5000.0, "comm" : 0.00,"deptno":10.0},
{"empno":7876,"ename":"ADAMS","job":"CLERK",mgr:7788,"hiredate" : "1983-01-12","sal":1100.0, "comm" : 0.00,"deptno":20.0},
{"empno":7902,"ename":"FORD","job":"ANALYST",mgr:7566,"hiredate" : "1981-12-03","sal":3000.0, "comm" : 0.00,"deptno":20.0},
{"empno":7934,"ename":"MILLER","job":"CLERK",mgr:7782,"hiredate" : "1982-01-23","sal":1300.0, "comm" : 0.00,"deptno":10.0}
]

db.emp.insertMany(empArr)
db.emp.find()

var deptArr=[{
                "deptno" : 10,
                "dname" : "ACCOUNTING",
                "loc" : "NEW YORK"
        },
        {
                "deptno" : 20,
                "dname" : "RESEARCH",
                "loc" : "DALLAS"
        },
        {
                "deptno" : 30,
                "dname" : "SALES",
                "loc" : "CHICAGO"
        },
        {
                "deptno" : 40,
                "dname" : "OPERATIONS",
                "loc" : "BOSTON"
        }
  ]


db.dept.insertMany(deptArr)
db.dept.find()
db
show collections
/*
 # 컬렉션에서 데이터 조회 - Read
 
 - findOne() : 1개의 document조회
 - find() : 여러 개의 document조회
 
 find({조건들},{필드들})
 */
 //select * from member
 db.member.find({})

// select name,userid from member
 
db.member.find({},{name:1,userid:true}) 

db.member.find({},{_id:0, name:1, age:1}) 
 
// select * from member where age=20
db.member.find({age:20},{})

//select * from member where age=19 and userid='choi'
db.member.find({age:19,userid:'choi'})
db.member.find({$and:[{age:19},{userid:'choi'}]})
 
//$and, $or : 비교연산자 
//select * from member where age=22 or userid='choi' 
db.member.find({$or:[{age:22},{userid:'choi'}]}) 
 
/*------------------------------------------------
비교 문법
$eq     =    
$gt     >    
$gte    >=   
$in          목록 중의 어느 하나라도 있는지 여부를 체크
$lt     <    
$lte    <=   
$ne     !=   not equal
$nin         $in의 반대. not in

논리연산
$and : 배열안 두개 이상의 조건이 모두 참인 경우를 반환 
$or  : 배열안 두개 이상의 조건 중 하나라도 참인 경우를 반환 
$nor : $or의 반대. 배열안 두개 이상의 조건이 모두 아닌 경우 를 반환
------------------------------------------------------------------------------------------
*/
db
use mydb
db
show collections


db.member.find()



//[1] userid shin 인 member의 name, userid, tel, grade를 가져오세요
db.member.find({userid:'shin'},{name:1,userid:1,tel:1,grade:1, _id:0})

//[2] age가 25세 이상인 회원의 모든 정보를 가져오세요
db.member.find({age:{$gte:25}})

//[3] 나이가 22세 미만인 회원의 이름,나이,연락처를 가져오세요
db.member.find({age:{$lt:22}},{name:1,age:1,tel:1})

//[4] 성적이 A 또는 B 학점인 회원의 모든 정보를 가져오세요
db.member.find({$or:[{grade:'A'}, {grade:'B'}]})

//[5] emp에서 사원의 이름과 급여를 가져와 보여주세요
db.emp.find({},{ename:1,sal:1})

//[6] emp에서 담당 업무가 'MANAGER'인 사원의 사번,이름,업무를 보여주세요
db.emp.find({job:'MANAGER'},{empno:1, ename:1, job:1})
//[7] emp에서 급여가 3000 이상인 사원의 사원번호,이름,	담당업무,급여를 출력하세요
db.emp.find({sal:{$gte:3000}},{empno:1, ename:1, job:1, sal:1})

//[8] emp에서 급여가 1300에서 2000사이의 사원의 이름,업무,급여,
//	부서번호를 출력하세요
db.emp.find({$and:[{sal:{$gte:1300}}, {sal:{$lte:2000}}]}, {ename:1, job:1, sal:1, deptno:1})

//[9]	emp에서 사원번호가 7369,7654,7934인 사원의 사원번호,
//	이름,업무,급여,입사일자를 출력하세요.
db.emp.find({$or:[{empno:7369},{empno:7654}, {empno:7934} ]},{empno:1,ename:1,job:1, sal:1,hiredate:1})

db.emp.find({empno:{$in:[7369,7654,7934]}},{empno:1,ename:1,job:1, sal:1,hiredate:1})

//[10]	emp에서부서번호가 20번 부서인 사원의 이름,업무,부서번호를 출력하세요
db.emp.find({deptno:20},{ename:1,job:1,deptno:1})

//[11] emp에서	부서번호가 20번 부서가 아닌 사원의 이름,업무,부서번호를 출력하세요
db.emp.find({deptno:{$ne:20}},{ename:1,job:1,deptno:1}).sort({deptno:-1})//order by deptno desc 
//[12] emp에서 업무가 CLERK이거나 MANAGER인 사원의 모든 정보를 출력하세요
db.emp.find({$or:[{job:'CLERK'}, {job:'MANAGER'}]}).sort({job:1})
db.emp.find({job:{$in:['CLERK','MANAGER']}}).sort({job:-1})

//[13] emp에서 업무가 CLERK이거나 MANAGER가 아닌 사원의 모든 정보를 출력하세요
db.emp.find({$nor:[{job:'CLERK'}, {job:'MANAGER'}]}).sort({job:1})
db.emp.find({job:{$nin:['CLERK','MANAGER']}}).sort({job:-1})




 
 
 
 
 
 
 
 
























