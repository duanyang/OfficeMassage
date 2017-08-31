<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>Department Management</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> Department Information
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<!--显示表单内容-->
<div id=MainArea>

    <s:form action="department_%{id==0 ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">Higher Department</td>
                        <td>
                        	<s:select name="parentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="Please Choose Department"
                        	/>
                        </td>
                    </tr>
                    <tr><td>Department Name</td>
                        <td><s:textfield name="name" cssClass="InputStyle required"/> *</td>
                    </tr>
                    <tr><td>Department Description</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="submit" value="Save"/>
            <a href="javascript:history.go(-1);"><front size="5" >Go Back</front></a>
        </div>
    </s:form>
</div>

</body>
</html>
