<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>部门列表</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Department Management
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">Department</td>
				<td width="150px">Higher Department</td>
				<td width="200px">Description</td>
				<td>Action</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        
        <s:iterator value="#departmentList">
			<tr class="TableDetail1 template">
				<td><s:a action="department_list?parentId=%{id}">${name}</s:a> &nbsp;</td>
				<td>${parent.name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('This will delete all sub-department. Are you sure you want to do that？')">Delete</s:a>&nbsp;
					<s:a action="department_editUI?id=%{id}">Modify</s:a>
					&nbsp;
					
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="department_addUI?parentId=%{parentId}">Create New Department</s:a>
            <s:if test="#parent != null">
				<s:a action="department_list?parentId=%{#parent.parent.id}">Go Back</s:a>
            </s:if>
        </div>
    </div>
</div>

<!--说明-->	

</body>
</html>
