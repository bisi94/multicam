package com.multi.domain;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="post") //컬렉션명 지정
public class PostVO {
	
	@Id
	private String id;
	
	@BsonProperty
	private int no;
	
	@BsonProperty
	private String name;
	
	@BsonProperty
	private String msg;
	
	@BsonProperty
	private String wdate;
}
