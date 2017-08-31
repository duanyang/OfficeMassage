package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.QueryHelper;

@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {
	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public DaoSupportImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		System.out.println(clazz);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);

	}

	@Override
	public void delete(long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}

	}

	@Override
	public void update(T entity) {
		getSession().update(entity);

	}

	@Override
	public T getById(long id) {
		// TODO Auto-generated method stub
		if (id != 0) {
			return (T) getSession().get(clazz, id);
		} else {
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		// TODO Auto-generated method stub
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession()
					.createQuery(
							"FROM " + clazz.getSimpleName()
									+ " where id in (:ids)")
					.setParameterList("ids", ids).list();
		}

	}
	
	public PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper){
		
		List<Object> parameters=queryHelper.getParameters();
		
		Query query = getSession().createQuery(queryHelper.getListQueryHql());
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}

		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list();

		Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}

		Long count = (Long) countQuery.uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}

}
