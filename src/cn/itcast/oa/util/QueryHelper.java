package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.PageBean;

import com.opensymphony.xwork2.ActionContext;

public class QueryHelper {

	private String fromClause;
	private String whereClause="";
	private String orderByClause="";

	private List<Object> parameters = new ArrayList<Object>();

	public QueryHelper(Class clazz, String alias) {
		this.fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	public QueryHelper addCondition(String condition, Object... params) {
		if (whereClause.length()==0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		if (params != null) {
			for (Object p : params) {
				parameters.add(p);
			}

		}
		return this;
	}
	
	public QueryHelper addCondition(boolean append, String condition, Object... params) {
		if(append){
			addCondition(condition, params);
		}
		return this;
		
	}
	
	public QueryHelper addOrderProperty(String propertyName, boolean asc){
		if(orderByClause.length()==0){
			orderByClause+=" ORDER BY "+propertyName + (asc ? " ASC": " DESC");
		}
		else{
			orderByClause+=", "+ propertyName + (asc ? " ASC": " DESC");
		}
		return this;
	}
	
	public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc){
		if(append){
			addOrderProperty(propertyName, asc);
		}
		return this;
	}
	
	public String getListQueryHql(){
		return fromClause+whereClause+orderByClause;
	}
	
	public String getCountQueryHql(){
		return "SELECT COUNT(*) "+fromClause+whereClause;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public String getFromClause() {
		return fromClause;
	}

	public void setFromClause(String fromClause) {
		this.fromClause = fromClause;
	}

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	
	public void preparePageBean(DaoSupport<?> service, int pageNum, int pageSize){
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}

}
