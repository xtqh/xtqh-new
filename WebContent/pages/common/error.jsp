<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/common/taglibs.jsp"%>
<%@ include file="/pages/common/commonJS.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>error page</title>
</head>
<body>
	<div class="errCot">
		<div class="errTit">
			<table border="0">
				<tr>
					<td class="errIcon404"></td>
					<td class="errTitTxt">Exception</td>
				</tr>
			</table>
		</div>

		<div class="centLine"></div>

		<div class="detailCon">
			<div class="explainTxt">异常问题</div>
			<div class="detailTxt">
				<ul>
					<li>${errorMsg }</li>
					<li><%=request.getParameter("errorMsg")%></li>
				</ul>
			</div>
			<p class="btn_box">
				<input class="btn_back" type="button"
					onclick="javascript:history.back(-1);" />
			</p>
		</div>

	</div>
</body>
</html>