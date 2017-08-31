package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {
	
	
	
	@Resource
	private SessionFactory sessionFactory;
	
	/**
	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentDao.finAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		departmentDao.delete(id);
		
	}

	@Override
	public void save(Department model) {
		// TODO Auto-generated method stub
		departmentDao.save(model);
	}

	@Override
	public Department getById(long id) {
		// TODO Auto-generated method stub
		return departmentDao.getById(id);
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		departmentDao.update(department);
	}
	**/

	@Override
	public List<Department> findTopList() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("FROM Department d WHERE d.parent IS NULL").list();
	}

	@Override
	public List<Department> findChildren(long parentId) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("FROM Department d WHERE d.parent.id=?").setParameter(0, parentId).list();
	}

}
