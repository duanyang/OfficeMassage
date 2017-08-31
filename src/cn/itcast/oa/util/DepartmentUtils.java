package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {
	
	public static List<Department> getAllDepartments(List<Department> topList){
		List<Department> list=new ArrayList<Department>();
		walkDepartmentTreeList(topList, "|-", list);
		return list;
	}
	
	private static void walkDepartmentTreeList(Collection<Department> topList, String prefix, List<Department> list){
		for (Department top: topList){
			Department copy=new Department();
			copy.setId(top.getId());
			copy.setName(prefix+ top.getName());
			list.add(copy);
			walkDepartmentTreeList(top.getChildren(),"　"+prefix, list);
		}
	}
	
}
