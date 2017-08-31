<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>Forum</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Forum </div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
	<center>
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
				<!--表头-->
				<tr align="center" valign="middle">
					<td colspan="3" class="ForumPageTableTitleLeft">Forum</td>
					<td width="80" class="ForumPageTableTitle">Topic</td>
					<td width="80" class="ForumPageTableTitle">Total Submission</td>
					<td width="270" class="ForumPageTableTitle">Last Published Topic</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
				<tr height="3"><td colspan="9"></td></tr>
			
				<!--版面列表-->
				<tbody class="dataContainer" datakey="forumList">
				
				<s:iterator value="#forumList">
					<tr height="78" align="center" class="template">
						<td width="3"></td>
						<td width="75" class="ForumPageTableDataLine">
							<img src="${pageContext.request.contextPath}/style/images/forumpage3.gif" />
						</td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li class="ForumPageTopic">
									<s:a action="forum_show?id=%{id}" cssClass="ForumPageTopic">${name}</s:a>
								</li>
								<li class="ForumPageTopicMemo">${description}</li>
							</ul>
						</td>
						<td class="ForumPageTableDataLine"><b>${topicCount}</b></td>
						<td class="ForumPageTableDataLine"><b>${articleCount}</b></td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li><font color="#444444">┌ Topic：</font> 
									<s:a cssClass="ForumTitle" action="topic_show?id=%{lastTopic.id}">${lastTopic.title}</s:a>
								</li>
								<li><font color="#444444">├ Author：</font> ${lastTopic.author.name}</li>
								<li><font color="#444444">└ Date and Time：</font> <s:date name="%{lastTopic.postTime}" format="yyyy-MM-dd HH:mm:ss"/></li>
							</ul>
						</td>
						<td width="3"></td>
					</tr>
				</s:iterator>	
					
				</tbody>
				<!-- 版面列表结束 -->
				
				<tr height="3"><td colspan="9"></td></tr>
			</table>
		</div>
	</center>
</div>
</body>
</html>
