<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>Topic：${topic.title}</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				Topic
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--内容显示-->
	<div id="MainArea">
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1" style="width: 98%">
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_list">Forum</s:a>
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
				<font class="MenuPoint"> &gt;&gt; </font> Topic <span
					style="margin-left: 30px;"> <s:a
						action="topic_addUI?forumId=%{#topic.forum.id}">
						<img align="absmiddle"
							src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png" />
					</s:a>
				</span>
			</div>

			<div class="ForumPageTableBorder dataContainer" datakey="replyList">

				<!--显示主题标题等-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr valign="bottom">
						<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
						<td class="ForumPageTableTitle"><b>Topic：${topic.title}</b></td>
						<td class="ForumPageTableTitle" align="right"
							style="padding-right: 12px;"><s:a cssClass="detail"
								action="reply_addUI?topicId=%{#topic.id}">
								<img border="0"
									src="${pageContext.request.contextPath}/style/images/reply.gif" />
							Reply
						</s:a> 
						</td>
						<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
					</tr>
					<tr height="1" class="ForumPageTableTitleLine">
						<td colspan="4"></td>
					</tr>
				</table>

				<!-- ~~~~~~~~~~~~~~~ 显示主帖（主帖只在第1页显示） ~~~~~~~~~~~~~~~ -->
				<s:if test="currentPage == 1">
					<div class="ListArea">
						<table border="0" cellpadding="0" cellspacing="1" width="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center"
									valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110"
											src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
									</div> <!--作者名称-->
									<div class="AuthorName">${topic.author.name}</div>
								</td>
								<td align="center">
									<ul class="TopicFunc">
									</ul>
								</td>
							</tr>
							<tr>
								<!-- 文章内容 -->
								<td valign="top" align="center">
									<div class="Content">${topic.content}</div>
								</td>
							</tr>
							<tr>
								<!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height: 18px;"><font
											color=#C30000>[Author]</font> <s:date name="%{#topic.postTime}"
												format="yyyy-MM-dd HH:mm:ss" /></li>
										<li style="float: right;"><a
											href="javascript:scroll(0,0)"> <img border="0"
												src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
										</li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</s:if>
				<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


				<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
				<s:iterator value="recordList" status="status">
					<div class="ListArea template">
						<table border="0" cellpadding="0" cellspacing="1" width="100%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center"
									valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110"
											src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
									</div> <!--作者名称-->
									<div class="AuthorName">${author.name}</div>
								</td>
								<td align="center">
								</td>
							</tr>
							<tr>
								<!-- 文章内容 -->
								<td valign="top" align="center">
									<div class="Content">${content}</div>
								</td>
							</tr>
							<tr>
								<!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height: 18px;"><font
											color=#C30000>[#${(currentPage - 1) * pageSize + status.count} Reply]</font>
											<s:date name="%{postTime}" format="yyyy-MM-dd HH:mm:ss" /></li>
										<li style="float: right;"><a
											href="javascript:scroll(0,0)"> <img border="0"
												src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
										</li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</s:iterator>
				<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
			</div>

			<!--分页信息-->
			<s:form action="topic_show?id=%{id}">
			</s:form>
			<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
		</center>
	</div>
</body>
</html>
