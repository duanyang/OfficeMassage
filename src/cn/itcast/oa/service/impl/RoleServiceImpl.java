package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;







import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {
	
	/**
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.finAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		roleDao.delete(id);
		
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
		
	}

	@Override
	public Role getById(long id) {
		// TODO Auto-generated method stub
		return roleDao.getById(id);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
		
	}
	**/


	
	
}
