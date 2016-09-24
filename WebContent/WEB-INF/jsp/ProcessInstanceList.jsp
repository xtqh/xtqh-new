<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="./resource/CommonTag.jsp" %>
<title>Process Instances</title>
</head>
<body>

<c:if test="${msg != null}">${msg }</c:if>
<c:choose>
	<c:when test="${fn:length(processInstanceList) == 0}">
		<c:out value="There's no process instances." /><br>
		<a href="CreateSampleProcess">新建测试流程</a>
	</c:when>
	<c:otherwise>
		<p>List of process instances:</p>
		<ul>
		<c:forEach var="processInstance" items="${processInstanceList}">
			<li><a href="./Show/${processInstance.id}"><c:out value="${processInstance.name}"/></a>   <a href="./Remove/${processInstance.id}">删除</a></li>
		</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>
</body>
</html>