<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>Forum List</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<style type="text/css">
    	.disabledA{
    		color: gray; 
    		/** text-decoration: underline; */ 
    		cursor: pointer;
    	}
    </style>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Forum Management
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px">Forum</td>
                <td width="300px">Forum Description</td>
                <td>Action</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
        
        <s:iterator value="#forumList" status="status">
			<tr class="TableDetail1 template">
				<td>${name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="forumManage_delete?id=%{id}" onclick="return delConfirm()">Modify</s:a>&nbsp;
					<s:a action="forumManage_editUI?id=%{id}">Delete</s:a>&nbsp;
					
					<!-- 最上面的不能用上移-->
					<s:if test="#status.first">
						<span class="disabledA">Move Up</span>
					</s:if> 
					
					<s:else>
						<s:a action="forumManage_moveUp?id=%{id}">Move Up</s:a>
					</s:else>
					
					<!-- 最下面的不能用下移-->
					<s:if test="#status.last">
						<span class="disabledA">Move Down</span>
					</s:if> 
					<s:else>
						<s:a action="forumManage_moveDown?id=%{id}">Move Down</s:a>
					</s:else>
					
				</td>
			</tr>
		</s:iterator>	
		
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="forumManage_addUI"><font size="3">New Forum</font></s:a>
        </div>
    </div>
</div>

</body>
</html>
