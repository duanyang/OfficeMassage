package cn.itcast.oa.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements java.io.Serializable{
	private long id;
	private Department department;
	private Set<Role> roles=new HashSet<Role>();
	private String loginName;
	private String password;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;
	
	public boolean hasPrivilegeByName(String name){
		if (isAdmin()) {
			return true;
		}

		// 其他用户要是有权限才返回true
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if (privilege.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean hasPrivilegeByUrl(String privUrl){
		if (isAdmin()) {
			return true;
		}
		
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		if(privUrl.endsWith("UI")){
			privUrl=privUrl.substring(0,privUrl.length()-2);
		}
		
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			// 如果是不需要控制的功能，则所有用户都可以使用
			return true;
		} else {
			// 如果是要控制的功能，则有权限才能使用
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
