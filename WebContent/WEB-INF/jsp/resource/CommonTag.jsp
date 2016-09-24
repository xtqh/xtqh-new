<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="<%=path%>/resource/css/mystyle.css" type="text/css"/>
<link rel="shortcut icon" href="<%=path%>/resource/pix/logo.ico">
<link rel="Bookmark" href="<%=path%>/resource/pix/logo.ico">
