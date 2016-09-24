<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/pages/common/taglibs.jsp"%>
<%@ include file="/pages/common/commonJS.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
</head>
<body class="light_bg2">
	<div class="errCot">
		<div class="errTit">
			<table border="0">
				<tr>
					<td class="errIcon500"></td>
					<td class="errTitTxt">Error 500</td>
				</tr>
			</table>
		</div>

		<div class="centLine"></div>

		<div class="detailCon">
			<div class="explainTxt">系统发生的内部错误！</div>
			<div class="detailTxt">
				<ul>
					<li>
						<%
							Throwable ex = null;
							if (exception != null)
								ex = exception;
							if (request.getAttribute("javax.servlet.error.exception") != null)
								ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
							String errorMsg = ex.getMessage();
							if (errorMsg != null && errorMsg.length() > 1000) {
								errorMsg = errorMsg.substring(0, 995) + "...";
							}
						%><%=(ex == null ? "" : ex.getMessage())%>
					</li>
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
