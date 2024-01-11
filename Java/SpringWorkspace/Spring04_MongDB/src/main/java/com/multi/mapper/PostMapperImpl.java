package com.multi.mapper;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.multi.common.CommonUtil;
import com.multi.domain.PostVO;
import com.multi.domain.SeqVO;

@Repository("postMapper")
public class PostMapperImpl implements PostMapper {
	
	//servlet-context.xml에 MongoTemplate빈 등록한것을 여기서 주입
	@Inject //by type inject
	private MongoTemplate mongoTemplate;
	
	@Override
	public int insertPost(PostVO vo) {
		//vo에 name, msg는 담겨있음. 글번호 => sequence 컬렉션에서 가져와 1씩 증가예정
		//sequence컬렌션에서 collectionName이 post인 도큐먼트의 count필드값을 가져오되,
		//count값을 가져온뒤 1씩 증가시킨다 ==> post의 글 번호(no)로 사용하기 위함
		Update update=new Update().inc("count",1);
		// ==> {$inc:{count:1}) ==> count값을 1씩 증가시킨다
		
		//Query query=Query.query(Criteria.where("collectionName").is("post"));
		
		Query query=query(where("collectionName").is("post"));
		
		//where collectionName='post'
		//...find({collectionName:'post'})
		
		SeqVO svo=mongoTemplate.findAndModify(query, update, SeqVO.class);
		
		vo.setNo(svo.getCount());//sequence에서 가져온 count값으로 설정
		
		vo.setWdate(CommonUtil.getDateTime()); //글 쓴 날짜 삽입
		PostVO resVo=mongoTemplate.insert(vo);
		System.out.println("resVo: "+resVo+", no: "+resVo.getNo());
		return resVo.getNo();
	}//-----------------------------------

	@Override
	public List<PostVO> listPost() {
		
		//List<PostVO> postArr=mongoTemplate.findAll(PostVO.class, "post");
		
		//최근 쓴 글이 위로 올라오도록 정렬해서 가져오기
		List<PostVO> postArr=mongoTemplate.find(new Query().with(Sort.by(Sort.Direction.DESC,"no")), PostVO.class, "post");
		
		return postArr;
	}//------------------------

	@Override
	public int deletePost(String id) {
		DeleteResult res=mongoTemplate.remove(query(where("_id").is(id)),PostVO.class,"post");
		long cnt=res.getDeletedCount();
		return (int)cnt;
	}//--------------------------------

	@Override
	public int udatePost(PostVO vo) {
		/*
		Update update=new Update();
		update.set("msg", vo.getMsg()).set("name", vo.getName());
		UpdateResult res=mongoTemplate.updateFirst(query(where("_id").is(vo.getId())), update, PostVO.class, "post");
		long cnt=res.getModifiedCount();
		return (int)cnt;
		*/
		vo.setWdate(CommonUtil.getDateTime("yy-MM-dd"));
		PostVO resVo=this.mongoTemplate.findAndReplace(
				query(where("_id").is(vo.getId())), vo);
		return resVo.getNo();
	}

	@Override
	public PostVO getPost(String id) {
		PostVO vo=mongoTemplate.findOne(query(where("_id").is(id)), PostVO.class, "post");
		return vo;
	}//------------------------

}
