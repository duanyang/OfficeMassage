package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position ASC")//
				.list();
	}

	@Override
	public void save(Forum forum) {
		// 保存到DB，会生成Id的值
		getSession().save(forum);

		// 指定position的值为最大 // SELECT MAX(f.position) FROM Forum f
		forum.setPosition(forum.getId().intValue());

		// 因为是持久化状态，所以不需要调用update()方法。
	}


	@Override
	public void moveUp(Long id) {
		// TODO Auto-generated method stub
		Forum forum=getById(id);
		Forum other = (Forum) getSession().createQuery(// 我上面的那个Forum
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		if(other==null)
			return;
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
	}

	@Override
	public void moveDown(Long id) {
		// TODO Auto-generated method stub
		Forum forum = getById(id); // 当前操作的Forum
		Forum other = (Forum) getSession().createQuery(// 我下面的那个Forum
				"FROM Forum f WHERE f.position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();

		// 最下面的不能下移
		if (other == null) {
			return;
		}

		// 交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
	}

}
