<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Position List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">Position</td>
                <td width="300px">Position Description</td>
                <td>Action</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="#roleList">
			<tr class="TableDetail1 template">
				<td>${name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="role_delete?id=%{id}" onclick="return delConfirm()">Delete</s:a>&nbsp;
					<s:a action="role_editUI?id=%{id}">Modify</s:a>&nbsp;
					<s:a action="role_setPrivilegeUI?id=%{id}">Define Position Privileges </s:a>
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="role_addUI"><front size="4">Create New</front></s:a>
        </div>
    </div>
</div>
</body>
</html>
