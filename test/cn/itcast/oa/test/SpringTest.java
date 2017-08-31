package cn.itcast.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	private ApplicationContext sc=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void test() throws Exception{
		TestAction testAction=(TestAction) sc.getBean("testAction");
		System.out.println(testAction);
	}
	
	@Test
	public void testSessionFactory() throws Exception{
		SessionFactory sessionFactory=(SessionFactory) sc.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	@Test
	public void testTransaction() throws Exception{
		TestService testService=(TestService) sc.getBean("testService");
		testService.saveTwoUser();
	}
}
