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
	
	//servlet-context.xml�� MongoTemplate�� ����Ѱ��� ���⼭ ����
	@Inject //by type inject
	private MongoTemplate mongoTemplate;
	
	@Override
	public int insertPost(PostVO vo) {
		//vo�� name, msg�� �������. �۹�ȣ => sequence �÷��ǿ��� ������ 1�� ��������
		//sequence�÷��ǿ��� collectionName�� post�� ��ť��Ʈ�� count�ʵ尪�� ��������,
		//count���� �����µ� 1�� ������Ų�� ==> post�� �� ��ȣ(no)�� ����ϱ� ����
		Update update=new Update().inc("count",1);
		// ==> {$inc:{count:1}) ==> count���� 1�� ������Ų��
		
		//Query query=Query.query(Criteria.where("collectionName").is("post"));
		
		Query query=query(where("collectionName").is("post"));
		
		//where collectionName='post'
		//...find({collectionName:'post'})
		
		SeqVO svo=mongoTemplate.findAndModify(query, update, SeqVO.class);
		
		vo.setNo(svo.getCount());//sequence���� ������ count������ ����
		
		vo.setWdate(CommonUtil.getDateTime()); //�� �� ��¥ ����
		PostVO resVo=mongoTemplate.insert(vo);
		System.out.println("resVo: "+resVo+", no: "+resVo.getNo());
		return resVo.getNo();
	}//-----------------------------------

	@Override
	public List<PostVO> listPost() {
		
		//List<PostVO> postArr=mongoTemplate.findAll(PostVO.class, "post");
		
		//�ֱ� �� ���� ���� �ö������ �����ؼ� ��������
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
