package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDrivenBaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends ModelDrivenBaseAction<Department> {

	private long parentId;

	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == 0) {
			departmentList = departmentService.findTopList();
		} else {
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
			departmentList = departmentService.findChildren(parentId);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);

		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	public String add() throws Exception {
		System.out.println(parentId);
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}

	public String editUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	public String edit() throws Exception {
		Department department = departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		departmentService.update(department);
		return "toList";
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

}
