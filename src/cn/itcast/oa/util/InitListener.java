package cn.itcast.oa.util;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;


public class InitListener implements ServletContextListener {
	
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		
		List<Privilege> topPrivilegeList=privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);

		System.out.println("--------->已经准备数据<--------");
		
		
		Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("-- 已准备好所有权限的URL数据 --");
		

	}

}
