package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDrivenBaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class ForumAction extends ModelDrivenBaseAction<Forum>{
	
	private int viewType=0;
	private int orderBy=0;
	private boolean asc=false;
	
	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	private int pageNum=1;
	private int pageSize=10;
	public String list() throws Exception{
		List<Forum> forumList=forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	public String show() throws Exception{
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		//List<Topic> topicList=topicService.findByForum(forum);
		//ActionContext.getContext().put("topicList", topicList);
		
		//PageBean pageBean=topicService.getPageBeanByForum(pageNum, pageSize, forum);
		//ActionContext.getContext().getValueStack().push(pageBean);
		
		 //String hql = "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
		 //List<Object> parameters = new ArrayList<Object>();
		 //parameters.add(forum);
		//PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql, parameters);
		//ActionContext.getContext().getValueStack().push(pageBean);
		
		String hql="FROM Topic t WHERE t.forum=?";
		
		new QueryHelper(Topic.class, "t")
		.addCondition("t.forum=?", forum)
		.addCondition((viewType==1), "t.type=?", Topic.TYPE_BEST)
		.addOrderProperty((orderBy==1),"t.lastUpdateTime", asc)
		.addOrderProperty((orderBy==2),"t.postTime", asc)
		.addOrderProperty((orderBy==3),"t.replyCount", asc)
		.addOrderProperty((orderBy==0),"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)
		.addOrderProperty((orderBy==0),"t.lastUpdateTime", false).preparePageBean(replyService, pageNum, pageSize);
		return "show";
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	

}
