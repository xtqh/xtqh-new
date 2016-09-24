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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Xenon Boostrap Admin Panel" />
<meta name="author" content="" />
<script type="text/javascript" src="${ctx}/pages/resource/resourceList.js?var=9"></script>

<title>资源管理</title>
</head>
<body>
	<!-- 顶部导航栏 -->
	<ol class="breadcrumb">
		<li><a>资源管理</a></li>
	</ol>
	<!-- 	//////////////////////////// -->
	<div class="detail" style="margin: 1px 10px">
		<h3 class="title">资源列表</h3>
		<i class="ico-1"></i>
		<div class="detail-content back-fff bbor">
			<div class="detail-intro pl0">
				<div class="input-type col-5-1 l">
					<span>资源类型：</span> <select class="select-185" id="type" name="type"
						onchange="queryResource(1,10);">
						<option value="">全部资源类型</option>
					</select>
				</div>
				<div class="input-type col-5-1 l">
					<span>资源地点：</span> <select class="select-185" id="location"
						name="location" onchange="queryResource(1,10);">
						<option value="">全部资源地点</option>
					</select>
				</div>
				<div class="input-type col-5-1 l">
					<span>关联应用系统：</span> <select id="appNameInCmdb"
						name="appNameInCmdb" class="selectpicker form-control"
						data-live-search="true" onchange="queryResource(1, 10)"
						data-live-search-style="begins">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="input-type col-xs-3 r">
					<input type="text" class="input-text l" id="searchKey"
						name="searchKey" placeholder="通过资源名称、hostname、控制IP、序列号、等关键字来模糊搜索已有的资源">
					<input type="button" class="btn search" value="搜索"
						onclick="searchResourceList();">
				</div>
			</div>
			<div class="hg20"></div>
			<hr />
			<div class="hg10"></div>
			<!-- ///////////////////////////////////// -->
			<div>
				<div id="pageDiv">
					<s:if test="#session.roleList.roleCodel.indexOf('005')!=-1  ">
						<input id="newresource" type="button"
							class="btn btn-danger fr mr-15"
							style="cursor: pointer; border-radius: 6px;" value="新建" />
						<input id="btnBatchCopy" type="button"
							class="btn btn-danger fr mr-15"
							style="cursor: pointer; border-radius: 6px;" value="批量复制cmdb资源" />
						<input id="btnDelMany" type="button"
							class="btn btn-danger fr mr-15"
							style="cursor: pointer; border-radius: 6px;" value="删除" />
					</s:if>
					<p class="r">
						当前可达资源 <span id="ans_resource1"></span>个，不可达资源 <span
							id="ans_resource0" style="cursor: pointer"></span>个。
					</p>
					<div class="hg10"></div>
					<table id="resourceList">
					</table>
					<div id="pageBar_resourceList"></div>
				</div>
				<div style="height: 15px"></div>
			</div>
		</div>
	</div>
</body>

</html>
