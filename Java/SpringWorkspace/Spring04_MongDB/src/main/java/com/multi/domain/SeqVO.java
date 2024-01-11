package com.multi.domain;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
/*	
db.createCollection("sequence")
db.sequence.insertOne({collectionName:'post', count:1})
db.sequence.find()
*/
@Data
@Document(collection="sequence")
public class SeqVO {
	
	@Id
	private String id;
	
	@BsonProperty
	private String collectionName;
	
	@BsonProperty
	private int count;
	
}
