package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDrivenBaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {

	private Long topicId;

	public String addUI() throws Exception {
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);

		return "addUI";
	}

	public String add() throws Exception {
		// 1，封装（已经封装了title, content, faceIcon）
		model.setTopic(topicService.getById(topicId));

		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());

		// 2，保存
		replyService.save(model);
		return "toTopicShow";
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
