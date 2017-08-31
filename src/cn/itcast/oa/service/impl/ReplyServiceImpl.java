package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	@Override
	public List<Reply> findByTopic(Topic topic) {
		// TODO Auto-generated method stub
		return getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC").setParameter(0, topic).list();
	}
	
	@Override
	public void save(Reply reply) {
		// 保存到DB
		getSession().save(reply);

		// 维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 版块的文章数（主题+回复）
		topic.setReplyCount(topic.getReplyCount() + 1); // 主题的回复数
		topic.setLastReply(reply); // 主题的最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime()); // 主题的最后更新的时间（主题的发表时间或是最后回复的时间）

		getSession().update(topic);
		getSession().update(forum);
	}

	@Override
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) {
		// TODO Auto-generated method stub
		List list=getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC").setParameter(0, topic).setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).list();
		Long count=(Long) getSession().createQuery("SELECT COUNT(*) FROM Reply r WHERE r.topic=?").setParameter(0, topic).uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}

}
