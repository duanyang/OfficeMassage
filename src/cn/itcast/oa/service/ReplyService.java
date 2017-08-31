package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.QueryHelper;


public interface ReplyService extends DaoSupport<Reply> {

	List<Reply> findByTopic(Topic topic);

	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);

	
}
