package cn.itcast.oa.base;

import java.util.List;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.QueryHelper;

public interface DaoSupport<T> {
	void save (T entity);
	void delete(long id);
	void update (T entity);
	T getById (long id);
	List<T> findAll();
	List<T> getByIds(Long[] roleIds);
	
	
	PageBean getPageBean(int pageNum, int pageSize,
			QueryHelper queryHelper);

}
