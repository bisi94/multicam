package com.multi.ex;
//static import===================================
import static java.lang.System.out;//static import(static메소드 static변수 앞에 클래스명을 생략하고 사용할 수 있다)
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
//================================================
public class MongoJavaTest {

	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		String db="mydb";//db명
		String collectionName="posts";//컬렉션명
		
		MongoClient mongoClient=null;//MongoClients.create(DB접속url) ==> 반환이 되면 커넥션이 된 것
		MongoDatabase mongodb;
		MongoCollection<Document> collection=null;
		
		//1. 몽고디비에 커넥션
		mongoClient=MongoClients.create("mongodb://localhost:27017");
		//System.out.println("mongoClient: "+mongoClient);
		
		//2. 데이터베이스 선택 - use mydb
		mongodb=mongoClient.getDatabase(db);
		
		//3. 컬렉션 얻기 - db.createCollection('posts')
		collection=mongodb.getCollection(collectionName);
		//System.out.println("posts 컬렉션 : "+collection);
		
		//4. CRUD작업 수행 => 메소드별 수행
		//insertOne(collection);
		selectAll(collection);
		//findAuthor(collection);
		//findTitle(collection);
		//deleteMany(collection);
		updateMany(collection);
		
		//5. DB 연결자원 반납
		mongoClient.close();
		
	}//main()-----------------------

	//db.posts.updateMany({author:'홍길동'}),{$set:{title}:'안녕', kind:'자유게시판'}})
	private static void updateMany(MongoCollection<Document> col) {
		out.println("수정할 글의 작성자(author): ");
		String author=sc.nextLine();
		
		out.println("수정할 글의 종류(kind): ");
		String kind=sc.nextLine();
		
		out.println("수정할 글의 제목(title)");
		String title=sc.nextLine();
		out.printf("%s, %s, %s\n", author, kind, title);
		out.println("------------------------------------");
		
		Bson filter=eq("author", author);//where절
		//Bson edit=Updates.combine(Updates.set("title",  title),Updates.set("kind", kind)); //set절
		Bson edit=combine(set("title",  title), set("kind", kind)); //위에 코드를 static import로 간소화
		
		UpdateResult res=col.updateMany(filter, edit);
		long cnt=res.getModifiedCount();
		out.println(cnt+"개의 도큐먼트를 수정했습니다");
		
		selectAll(col);
		
	}

	//static 필드, 메소드 => 클래스명.필드 ==> 클래스명을 생략해서 사용하려면 static import하면 된다
	//import static java.lang.System.*;
	//db.posts.deleteMany({조건})
	private static void deleteMany(MongoCollection<Document> col) {
		out.println("삭제할 글의 작성자(author) 입력: ");
		String author=sc.nextLine();
		out.println("author: "+author);
		DeleteResult res=col.deleteOne(eq("author",author));
		//DeleteResult res=col.deleteMany(eq("author",author));
		long cnt=res.getDeletedCount();
		System.out.println(cnt+"개의 documents를 posts컬렉션에서 삭제했습니다");
		
		
	}//-----------------------------

	//db.posts.find({title:/안녕/}) ==> Filters.regex(field, pattern)
	private static void findTitle(MongoCollection<Document> col) {
		System.out.println("검색할 검색어(title)를 입력하세요: ");
		String keyword=sc.nextLine();
		Pattern pattern=Pattern.compile(keyword,Pattern.CASE_INSENSITIVE);
		
		Bson filter=Filters.regex("title", pattern);
		
		FindIterable<Document> cursor=col.find(filter);
		int cnt=0;
		if(cursor!=null) {
			for(Document doc:cursor) {
				cnt++;
				String kind=doc.getString("kind");
				String title=doc.getString("title");
				String author2=doc.getString("author");
				ObjectId oid=doc.getObjectId("_id");
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n",kind, title, author2, oid.toString());
			}//for-------------
		}//if------------------
		if(cnt==0) {
			System.out.println(keyword+"가 들어간 글은 없습니다");
		}
	}//-----------------------------
	
	//db.posts.find({author:'홍길동'})
	public static void findAuthor(MongoCollection<Document> col) {
		System.out.println("검색할 작성자(author)를 입력하세요: ");
		String author=sc.nextLine();
		Bson filter=Filters.eq("author",author);
		
		FindIterable<Document> cursor=col.find(filter);
		int cnt=0;
		if(cursor!=null) {
			for(Document doc:cursor) {
				cnt++;
				String kind=doc.getString("kind");
				String title=doc.getString("title");
				String author2=doc.getString("author");
				ObjectId oid=doc.getObjectId("_id");
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n",kind, title, author2, oid.toString());
			}//for-------------
		}//if------------------
		if(cnt==0) {
			System.out.println(author+"님의 글은 없습니다");
		}
	}//--------------------------------------------------
	
	/* cr=db.post.find({})
	 * while(cr.hasNext()){
	 * 	printJson(cr.next())
	 * }
	 * */
	public static void selectAll(MongoCollection<Document> col) {
		FindIterable<Document> cursor=col.find();
		for(Document doc:cursor) {
			System.out.println(doc.toJson());
		}
		/*
		MongoCursor<Document> mcr=cursor.iterator();
		while(mcr.hasNext()) {
			Document doc=mcr.next();
			String kind=doc.getString("kind");
			String author=doc.getString("author");
			String title=doc.getString("title");
			ObjectId oid=doc.getObjectId("_id");
			System.out.println(kind+"\t\t"+author+"\t\t"+title+"\t\t+"+oid);
		}//while()------------------
		mcr.close();
		*/
		System.out.println("===몽고디비 posts컬렉션 조회 성공=======");
		
	}//-----------------------------

	public static void insertOne(MongoCollection<Document> col) {
		System.out.println("Kind : ");
		String kind=sc.nextLine();

		System.out.println("Author : ");
		String author=sc.nextLine();
		
		System.out.println("Title : ");
		String title=sc.nextLine();
		
		//db.posts.insertOne({kind:'IT스터디', title:',title: '오늘은 몽고디비와 자바연결 해보아요', author:'홍길동'})
		Document doc=new Document("kind",kind) //key(필등명), value(데이터)
					.append("author:",author)
					.append("title", title);
		InsertOneResult result=col.insertOne(doc);

		//insertManyResult insertMany(List<Document>)

		
		//System.out.println(result);
		ObjectId oid=result.getInsertedId().asObjectId().getValue();
		
		System.out.println(result.getInsertedId()+" id로 posts 컬렉션에 도큐먼트 저장 완료!");
		
		System.out.println("_id: "+oid); //스트링타입을 원하면 oid.toString()
	}//-----------------------------

}////////////////////////////////////////////////




