<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>Topic List Of Forum 【${forum.name}】</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
		function onSortByChange( selectedValue ){
			if(selectedValue == 0){
				$("select[name=asc]").attr("disabled", "disabled");	
			}else{
				$("select[name=asc]").removeAttr("disabled");	
			}
		}
		$(function(){
			if($("select[name=orderBy]").val() == '0'){
				$("select[name=asc]").attr("disabled", "disabled");		
			}
		});
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Topic List Of Forum 【${forum.name}】
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<s:form action="forum_show?id=%{id}">
<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_list">Forum</s:a>
			<font class="MenuPoint"> &gt; </font>
			${forum.name}
			<span style="margin-left:30px;">
				<s:a action="topic_addUI?forumId=%{#forum.id}">
					<front size="6">New Topic</front>
				</s:a>
			</span>
		</div>
		
		<div class="ForumPageTableBorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="3" class="ForumPageTableTitleLeft">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
					<td width="50" class="ForumPageTableTitle"><!--状态/图标-->&nbsp;</td>
					<td class="ForumPageTableTitle">Topic</td>
					<td width="130" class="ForumPageTableTitle">Author</td>
					<td width="100" class="ForumPageTableTitle">Total Reply</td>
					<td width="130" class="ForumPageTableTitle">Last Reply</td>
					<td width="3" class="ForumPageTableTitleRight">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
				<tr height=3><td colspan=8></td></tr>
					
				<!--主题列表-->
				<tbody class="dataContainer" datakey="topicList">
				
				<s:iterator value="recordList">
					<tr height="35" id="d0" class="template">
						<td></td>
						<td class="ForumTopicPageDataLine" align="center"></td>
						<td class="Topic">
							<s:a cssClass="Default" action="topic_show?id=%{id}">${title}</s:a>
						</td>
						<td class="ForumTopicPageDataLine">
							<ul class="ForumPageTopicUl">
								<li class="Author">${author.name}</li>
								<li class="CreateTime"><s:date name="postTime" format="yyyy-MM-dd HH:mm:ss"/> </li>
							</ul>
						</td>
						<td class="ForumTopicPageDataLine Reply" align="center"><b>${replyCount}</b></td>
						<td class="ForumTopicPageDataLine">
							<ul class="ForumPageTopicUl">
								<li class="Author">${lastReply.author.name}</li>
								<li class="CreateTime"><s:date name="lastReply.postTime" format="yyyy-MM-dd HH:mm:ss"/></li>
							</ul>
						</td>
						<td></td>
					</tr>
				</s:iterator>	
					
				</tbody>
				<!--主题列表结束-->	
					
				<tr height="3"><td colspan="9"></td></tr>
				
			</table>
			
			<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td>
								<s:select name="viewType" list="#{0:'All Topics'}"/>
								<s:select name="orderBy" onchange="onSortByChange(this.value)"
									list="#{0:'Default', 1:'Sort By When Topic Was Last Upldated ', 2:'Sort of Date of Topic Publication', 3:'Sort By Rely Number'}"
								/>
								<s:select name="asc" list="#{false:'Descend', true:'Ascend'}"/>
								<input type="Submit" value="Sort"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
	</center>
</div>
</s:form>



<!--分页信息-->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>

</body>
</html>
