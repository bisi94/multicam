use mydb
db.post.find()
db.post.deleteMany({})

show collections

db.createCollection("sequence")

db.sequence.insertOne({collectionName:'post', count:1})
db.sequence.find()

db.post.find()
