package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDrivenBaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role> {
	
	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		if (!roleList.isEmpty()) {
			ActionContext.getContext().put("roleList", roleList);
		}

		return "list";

	}

	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";

	}

	public String addUI() throws Exception {
		return "saveUI";
	}

	public String add() throws Exception {

		roleService.save(model);
		return "toList";

	}

	public String editUI() throws Exception {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";

	}

	public String edit() throws Exception {
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);

		return "toList";
	}
	

	public String setPrivilegeUI() throws Exception {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);

		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);

		// 准备回显的数据
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege privilege : role.getPrivileges()) {
			privilegeIds[index++] = privilege.getId();
		}
		return "setPrivilegeUI";

	}

	public String setPrivilege() throws Exception {
		Role role = roleService.getById(model.getId());
		List<Privilege> privilegeList=privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		roleService.update(role);

		return "toList";
	}

}
