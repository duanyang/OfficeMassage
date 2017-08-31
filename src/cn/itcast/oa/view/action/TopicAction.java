package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDrivenBaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.QueryHelper;

@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {

	private Long forumId;
	private int pageNum=1;
	private int pageSize=10;

	public String show() throws Exception {
		Topic topic=topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		//List<Reply> replyList=replyService.findByTopic(topic);
		//ActionContext.getContext().put("replyList", replyList);
		
		//PageBean pageBean=replyService.getPageBeanByTopic(pageNum, pageSize, topic);
		//ActionContext.getContext().getValueStack().push(pageBean);
		new QueryHelper(Reply.class, "r")
		.addCondition("r.topic=?", topic)
		.addOrderProperty("r.postTime", true).preparePageBean(replyService, pageNum, pageSize);
		return "show";
	}

	public String addUI() throws Exception {

		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);

		return "addUI";
	}
	
	

	public String add() throws Exception {

		// 封装
		// >> 表单中的字段, 已经封装了 title, content, faceIcon
		model.setForum(forumService.getById(forumId));

		// >> 当前可以直接获取的信息
		model.setAuthor(getCurrentUser()); // 作者，当前登录的用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // IP地址，当前请求中的IP信息
		model.setPostTime(new Date()); // 发表时间，当前时间

		// >> 应放到业务方法中的一个其他设置
		// model.setType(type);
		// model.setReplyCount(replyCount);
		// model.setLastReply(lastReply);
		// model.setLastUpdateTime(lastUpdateTime);

		// 保存
		topicService.save(model);
		return "toShow";
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
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
