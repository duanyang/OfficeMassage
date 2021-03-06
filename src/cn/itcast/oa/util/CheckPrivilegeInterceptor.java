package cn.itcast.oa.util;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		User user = (User) ActionContext.getContext().getSession().get("user");

		// 获取当前访问的URL，并去掉当前应用程序的前缀（也就是 namespaceName + actionName ）
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privilegeUrl = namespace+actionName;
		

		// 如果未登录用户
		if (user == null) {
			if (privilegeUrl.startsWith("/user_login")) { // userAction_login, userAction_loginUI
				// 如果是正在使用登录功能，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录，就转到登录页面
				return "loginUI";
			}
		}
		// 如果已登录用户（就判断权限）
		else {
			if (user.hasPrivilegeByUrl(privilegeUrl)) {
				// 如果有权限，就放行
				return invocation.invoke();
			} else {
				// 如果没有权限，就转到提示页面
				return "noPrivilegeError";
		}
			}
	
	}

}
