package cn.itcast.oa.test;



import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.User;

@Service
public class TestService {
	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveTwoUser(){
		
		Session session=sessionFactory.getCurrentSession();
		session.save(new User());
		session.save(new User());
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
