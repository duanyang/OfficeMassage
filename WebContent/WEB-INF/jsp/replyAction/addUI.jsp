<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>Reply</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
	<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
	
	
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Reply
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">

	<s:form action="reply_add" cssStyle="margin: 0; padding: 0;">
		<s:hidden name="topicId"></s:hidden>
		
		<div id="PageHead"></div>
		<center>
			<div class="ItemBlock_Title1">
				<div width=85% style="float:left">
					<font class="MenuPoint"> &gt; </font>
					<s:a action="forum_list">Forum</s:a>
					<font class="MenuPoint"> &gt; </font>
					<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
					<font class="MenuPoint"> &gt;&gt; </font>
					Reply
				</div>
			</div>
			<div class="ItemBlockBorder">
				<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
					<tr>
						<td class="InputAreaBg" height="30"><div class="InputTitle">Topic</div></td>
						<td class="InputAreaBg"><div class="InputContent">${topic.title}</div></td>
					</tr>
					<tr>
						<td class="InputAreaBg" height="30"><div class="InputTitle">Header</div></td>
						<td class="InputAreaBg"><div class="InputContent">
							<s:textfield name="title" cssClass="InputStyle" cssStyle="width:100%" value="Reply：%{#topic.title}"/></div>
						</td>
					</tr>
					<!--  <tr>
						<td class="InputAreaBg" height="30"><div class="InputTitle">表情</div></td>
						<td class="InputAreaBg"><div class="InputContent">
							显示表情符号 
							现在在设计单选框(radio)和复选框(checkbox)时都会给选择文字加上label增大选择范围，以提高用户体验。
								但在给单独的图片加label时无法成功。
								解决方法是：给图片img标签加上disabled即可。
							<table cellpadding="0" border="0" cellspacing="0">
								<tr>
								<s:iterator begin="1" end="14" var="num">
									<td width="50" style="border-bottom:0 solid #ffffff">
										<input type="radio" name="faceIcon" value="${num}" id="r_${num}"/>
										<label for="r_${num}"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/${num}.gif" disabled="true" align="absmiddle"/></label>
									</td>
								</s:iterator>
								</tr>
							</table></div>
						</td>
					</tr>-->
					<tr height="240">
						<td class="InputAreaBg"><div class="InputTitle">Content</div></td>
						<td class="InputAreaBg">
							<div class="InputContent">
								<s:textarea name="content" cssStyle="width:650px;height:200px;"></s:textarea>
							<script type="text/javascript">
								CKEDITOR.replace( 'content');
							</script>
							
							</div>
						</td>
					</tr>
					<tr height="30">
						<td class="InputAreaBg" colspan="2" align="center">
							<input type="Submit" value="Reply"/>
							<a href="javascript:history.go(-1);"><font size="3">Go Back</font></a>
						</td>
					</tr>
				</table>
			</div>
		</center>
	</s:form>
</div>

</body>
</html>
