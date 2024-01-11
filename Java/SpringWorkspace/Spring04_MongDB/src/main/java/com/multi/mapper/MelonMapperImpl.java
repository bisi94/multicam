package com.multi.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.multi.domain.MelonVO;
import com.multi.domain.SumVO;

import lombok.extern.log4j.Log4j;

@Repository("melonMapper")
@Log4j
public class MelonMapperImpl implements MelonMapper {

	@Inject
	private MongoTemplate mongoTemplate;
	
	@Override
	public boolean createCollection(String collectionName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int insertSong(List<MelonVO> mList, String colName) throws Exception {
		mongoTemplate.insert(mList, colName);
		return mList.size();
	}

	@Override
	public List<MelonVO> getSongList(String colName) throws Exception {
		
		return this.mongoTemplate.findAll(MelonVO.class,colName);
	}

	
	/*---------------------------------------------
	db.Melon_231128.aggregate([
       {
           $group:{_id:'$singer', cntBySinger:{$sum:1}}
       },
       {
           $project:{singer:'$_id', cntBySinger:'$cntBySinger'}
       },
       {
           $match:{cntBySinger:{$gt:1}}
       },
       {
           $sort:{cntBySinger:-1}
       }
   ])
   -------------------------------------------------*/
	
	@Override
	public List<SumVO> getSingerSongCnt(String colName) throws Exception {
		List<? extends Bson> pipeline=Arrays.asList(
			new Document().append("$group", 
					new Document().append("_id", "$singer").append("cntBySinger", 
							new Document().append("$sum", 1))),

			new Document().append("$project", 
					new Document().append("singer", "$_id").append("cntBySinger", "$cntBySinger")),
			
			new Document().append("$match", 
					new Document().append("cntBySinger", 
							new Document().append("$gt", 1))),
			
			new Document().append("$sort", 
					new Document().append("cntBySinger", -1))
				
		);
		
		MongoCollection<Document> mongoCol=mongoTemplate.getCollection(colName);
		
		AggregateIterable<Document> cr=mongoCol.aggregate(pipeline);
		if(cr==null) return null;
		
		MongoCursor<Document> mcr=cr.cursor();
		List<SumVO> arr=new ArrayList<>();
		while(mcr.hasNext()) {
			Document doc=mcr.next();
			SumVO svo=new SumVO();
			svo.setSinger(doc.getString("singer"));
			svo.setCntBySinger(doc.getInteger("cntBySinger", 0));
			arr.add(svo);
		}
		
		return arr;
	}

}
