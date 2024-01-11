package com.multi.ex;
//static import===================================
import static java.lang.System.out;//static import(static�޼ҵ� static���� �տ� Ŭ�������� �����ϰ� ����� �� �ִ�)
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
		String db="mydb";//db��
		String collectionName="posts";//�÷��Ǹ�
		
		MongoClient mongoClient=null;//MongoClients.create(DB����url) ==> ��ȯ�� �Ǹ� Ŀ�ؼ��� �� ��
		MongoDatabase mongodb;
		MongoCollection<Document> collection=null;
		
		//1. ������ Ŀ�ؼ�
		mongoClient=MongoClients.create("mongodb://localhost:27017");
		//System.out.println("mongoClient: "+mongoClient);
		
		//2. �����ͺ��̽� ���� - use mydb
		mongodb=mongoClient.getDatabase(db);
		
		//3. �÷��� ��� - db.createCollection('posts')
		collection=mongodb.getCollection(collectionName);
		//System.out.println("posts �÷��� : "+collection);
		
		//4. CRUD�۾� ���� => �޼ҵ庰 ����
		//insertOne(collection);
		selectAll(collection);
		//findAuthor(collection);
		//findTitle(collection);
		//deleteMany(collection);
		updateMany(collection);
		
		//5. DB �����ڿ� �ݳ�
		mongoClient.close();
		
	}//main()-----------------------

	//db.posts.updateMany({author:'ȫ�浿'}),{$set:{title}:'�ȳ�', kind:'�����Խ���'}})
	private static void updateMany(MongoCollection<Document> col) {
		out.println("������ ���� �ۼ���(author): ");
		String author=sc.nextLine();
		
		out.println("������ ���� ����(kind): ");
		String kind=sc.nextLine();
		
		out.println("������ ���� ����(title)");
		String title=sc.nextLine();
		out.printf("%s, %s, %s\n", author, kind, title);
		out.println("------------------------------------");
		
		Bson filter=eq("author", author);//where��
		//Bson edit=Updates.combine(Updates.set("title",  title),Updates.set("kind", kind)); //set��
		Bson edit=combine(set("title",  title), set("kind", kind)); //���� �ڵ带 static import�� ����ȭ
		
		UpdateResult res=col.updateMany(filter, edit);
		long cnt=res.getModifiedCount();
		out.println(cnt+"���� ��ť��Ʈ�� �����߽��ϴ�");
		
		selectAll(col);
		
	}

	//static �ʵ�, �޼ҵ� => Ŭ������.�ʵ� ==> Ŭ�������� �����ؼ� ����Ϸ��� static import�ϸ� �ȴ�
	//import static java.lang.System.*;
	//db.posts.deleteMany({����})
	private static void deleteMany(MongoCollection<Document> col) {
		out.println("������ ���� �ۼ���(author) �Է�: ");
		String author=sc.nextLine();
		out.println("author: "+author);
		DeleteResult res=col.deleteOne(eq("author",author));
		//DeleteResult res=col.deleteMany(eq("author",author));
		long cnt=res.getDeletedCount();
		System.out.println(cnt+"���� documents�� posts�÷��ǿ��� �����߽��ϴ�");
		
		
	}//-----------------------------

	//db.posts.find({title:/�ȳ�/}) ==> Filters.regex(field, pattern)
	private static void findTitle(MongoCollection<Document> col) {
		System.out.println("�˻��� �˻���(title)�� �Է��ϼ���: ");
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
			System.out.println(keyword+"�� �� ���� �����ϴ�");
		}
	}//-----------------------------
	
	//db.posts.find({author:'ȫ�浿'})
	public static void findAuthor(MongoCollection<Document> col) {
		System.out.println("�˻��� �ۼ���(author)�� �Է��ϼ���: ");
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
			System.out.println(author+"���� ���� �����ϴ�");
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
		System.out.println("===������ posts�÷��� ��ȸ ����=======");
		
	}//-----------------------------

	public static void insertOne(MongoCollection<Document> col) {
		System.out.println("Kind : ");
		String kind=sc.nextLine();

		System.out.println("Author : ");
		String author=sc.nextLine();
		
		System.out.println("Title : ");
		String title=sc.nextLine();
		
		//db.posts.insertOne({kind:'IT���͵�', title:',title: '������ ������� �ڹٿ��� �غ��ƿ�', author:'ȫ�浿'})
		Document doc=new Document("kind",kind) //key(�ʵ��), value(������)
					.append("author:",author)
					.append("title", title);
		InsertOneResult result=col.insertOne(doc);

		//insertManyResult insertMany(List<Document>)

		
		//System.out.println(result);
		ObjectId oid=result.getInsertedId().asObjectId().getValue();
		
		System.out.println(result.getInsertedId()+" id�� posts �÷��ǿ� ��ť��Ʈ ���� �Ϸ�!");
		
		System.out.println("_id: "+oid); //��Ʈ��Ÿ���� ���ϸ� oid.toString()
	}//-----------------------------

}////////////////////////////////////////////////




