<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>User Information</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> User Information
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<!--显示表单内容-->
<div id=MainArea>

    <s:form action="user_%{id == 0 ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
    
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> User Information </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">Department</td>
                        <td>
                            <s:select name="departmentId" cssClass="SelectStyle"
                            	list="#departmentList" listKey="id" listValue="name"
                            	headerKey="" headerValue="Please Select Department">
                            </s:select>
                        </td>
                    </tr>
                   
                    <tr><td>Username</td>
                        <td><s:textfield name="loginName" cssClass="InputStyle"/> *
							（Username needs to be unique）
						</td>
                    </tr>
                    <tr><td>Name</td>
                        <td><s:textfield name="name" cssClass="InputStyle"/> *</td>
                    </tr>
					<tr><td>Sex</td>
                        <td>
                        	<%-- 
								<s:radio name="gender" list="#{'男':'男', '女':'女'}"></s:radio>
                        	--%>
							<s:radio name="gender" list="%{ {'Male', 'Female'} }"></s:radio>
						</td>
                    </tr>
					<tr><td>Phone</td>
                        <td><s:textfield name="phoneNumber" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>Note</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> Select Position </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">Position</td>
                        <td>
                        	<s:select name="roleIds" cssClass="SelectStyle"
                        		multiple="true" size="10" 
                        		list="#roleList" listKey="id" listValue="name">
                            </s:select>
							Use Ctrl To Select Multiple Position
                        </td>
                    </tr>
                </table>
            </div>
        </div>		
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="submit" value="Save"/>
            <a href="javascript:history.go(-1);"><font size="2">Go Back</font></a>
        </div>
    </s:form>
</div>

</body>
</html>
