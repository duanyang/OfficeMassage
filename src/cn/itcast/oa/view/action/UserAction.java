package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDrivenBaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;


@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {

	private long departmentId;
	private Long[] roleIds;

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";

	}

	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";

	}

	public String addUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	public String add() throws Exception {
		model.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		String md5Digest=DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);

		userService.save(model);
		return "toList";

	}

	public String editUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if(user.getRoles()!=null){
			roleIds=new Long[user.getRoles().size()];
			int index=0;
			for(Role role: user.getRoles()){
				roleIds[index++]=role.getId();
			}
		}
		return "saveUI";

	}

	public String edit() throws Exception {
		User user=userService.getById(model.getId());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		user.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		userService.update(user);
		return "toList";
	}

	public String initPassword() throws Exception {
		User user=userService.getById(model.getId());
		String md5Digest=DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		userService.update(user);
		return "toList";
	}
	
	public String loginUI()throws Exception{
		return "loginUI";
	}
	
	public String login()throws Exception{
		User user=userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		if(user==null){
			addFieldError("login", "Not Correct Username!");
			return "loginUI";
		}
		else{
			ActionContext.getContext().getSession().put("user", user);
			System.out.println(ActionContext.getContext().getSession());
			return "toIndex";
		}
	}
	
	public String logout()throws Exception{
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	
}
