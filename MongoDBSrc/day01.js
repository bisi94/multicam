use testDB
show dbs

use testDB

//컬렉션 생성 : employee
db.createCollection('employee')
show collections
show dbs

//컬렉션 이름 변경: db.컬렉션명.renameCollection('변경할이름')
db.employee.renameCollection('emp')
show collections

//컬렉션 삭제: db.컬렉션명.drop()
db.emp.drop()
show collections
show dbs
//컬렉션명: cappedCol
//컬렉션 생성시 옵션=> capped:true, size:10000
//==> 저장공간이 차면 기존공간을 재사용하여 새로운 데이터를 저장

db.createCollection('cappedCol', {capped:true, size:10000})
show collections

show dbs

db.cappedCol.stats()

//db.컬렉션명.insertOne({key:value})
//db.컬렉션명.insertMany([{key:value},{key:value},...  ])
for(i=0;i<1000;i++){
    db.cappedCol.insertOne({x:i})
}
//db.컬렉션명.find()
db.cappedCol.find()

db.cappedCol.find().count()
//=>353개
//처음 넣었던 데이터들이 사라진 것을 확인할 수 있다.
//==>capped:true

