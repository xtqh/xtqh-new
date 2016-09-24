<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="./resource/CommonTag.jsp" %>

<title>Process Instance Detail</title>
</head>
<body>
<table >
	<tr>
		<th nowrap="nowrap">流程实例名称</th>
		<th nowrap="nowrap">流程实例状态</th>
		<th nowrap="nowrap">超时</th>
		<th nowrap="nowrap">出错</th>
		<th nowrap="nowrap">任务序号</th>
		<th nowrap="nowrap">任务名称</th>
		<th nowrap="nowrap">任务执行状态</th>
		<th nowrap="nowrap">超时</th>
		<th nowrap="nowrap">出错</th>
		<th nowrap="nowrap">前置任务</th>
		<th nowrap="nowrap">后置任务</th>
		<th nowrap="nowrap">步骤序号</th>
		<th nowrap="nowrap">步骤名称</th>
		<th nowrap="nowrap">步骤执行状态</th>
		<th nowrap="nowrap">超时</th>
		<th nowrap="nowrap">出错</th>
		<th nowrap="nowrap">步骤类型</th>
		<th nowrap="nowrap">步骤实际类型</th>
		<th nowrap="nowrap">并行/串行</th>
		<th nowrap="nowrap">前置步骤</th>
		<th nowrap="nowrap">后置步骤</th>
		<th nowrap="nowrap">操作序号</th>
		<th nowrap="nowrap">操作</th>
		<th nowrap="nowrap">串行顺序</th>
		<th nowrap="nowrap">操作状态</th>
		<th nowrap="nowrap">超时</th>
		<th nowrap="nowrap">出错</th>
	</tr>
	<tr>
		<td nowrap="nowrap"><c:out value="${processInstance.name}"></c:out></td>
		<td nowrap="nowrap"><c:out value="${processInstance.status.getValue()}"></c:out></td>
		<td nowrap="nowrap"><c:if test="${processInstance.timedOut == true}">是</c:if></td>
		<td nowrap="nowrap"><c:if test="${processInstance.error == true}">是</c:if></td>
	</tr>
	<c:forEach var="taskInstanceIndex" begin="0" end="${processInstance.taskInstances.size() -1}">
		<c:set var="taskInstance" value="${processInstance.taskInstances.get(taskInstanceIndex)}" />
		<tr>
			<td colspan="4"></td>
			<td nowrap="nowrap"><c:out value="${taskInstanceIndex}"></c:out></td>
			<td nowrap="nowrap"><c:out value="${taskInstance.name}"></c:out></td>
			<td nowrap="nowrap"><c:out value="${taskInstance.status}"></c:out></td>
			<td nowrap="nowrap"><c:if test="${taskInstance.timedOut == true}">是</c:if></td>
			<td nowrap="nowrap"><c:if test="${taskInstance.error == true}">是</c:if></td>
			<td nowrap="nowrap">
				<c:set var="isFirstItem" value="true" />
				<c:forEach var="predecessorTaskInstance" items="${taskInstance.predecessors}">
					<c:if test="${isFirstItem == false}">, </c:if>
					<a href="javascript:void(0);" title="${predecessorTaskInstance.name}">${processInstance.taskInstances.indexOf(predecessorTaskInstance)}</a>
					<c:set var="isFirstItem" value="false" />
				</c:forEach>
			</td>
			<td nowrap="nowrap">
				<c:set var="isFirstItem" value="true" />
				<c:forEach var="successorTaskInstance" items="${taskInstance.successors}">
					<c:if test="${isFirstItem == false}">, </c:if>
					<a href="javascript:void(0);" title="${successorTaskInstance.name}">${processInstance.taskInstances.indexOf(successorTaskInstance)}</a>
					<c:set var="isFirstItem" value="false" />
				</c:forEach>
			</td>
		</tr>		
		<c:forEach var="stepInstanceIndex" begin="0" end="${taskInstance.stepInstances.size() -1}">
			<c:set var="stepInstance" value="${taskInstance.stepInstances.get(stepInstanceIndex)}" />
			<tr>
				<td colspan="11"></td>
				<td nowrap="nowrap"><c:out value="${stepInstanceIndex}"></c:out></td>
				<td nowrap="nowrap"><c:out value="${stepInstance.name}"></c:out></td>
				<td nowrap="nowrap"><c:out value="${stepInstance.status}"></c:out></td>
				<td nowrap="nowrap"><c:if test="${stepInstance.timedOut == true}">是</c:if></td>
				<td nowrap="nowrap"><c:if test="${stepInstance.error == true}">是</c:if></td>
				<td nowrap="nowrap"><c:if test="${stepInstance.automatic == true}">自动</c:if><c:if test="${stepInstance.automatic == false}">手动</c:if></td>
				<td nowrap="nowrap"><c:if test="${stepInstance.currentlyAutomatic == true}">自动</c:if><c:if test="${stepInstance.currentlyAutomatic == false}">手动</c:if></td>
				<td nowrap="nowrap"><c:if test="${stepInstance.operationExecutionOrder == 'PARALLE'}">并行</c:if><c:if test="${stepInstance.operationExecutionOrder == 'SERIAL'}">串行</c:if></td>
				<td nowrap="nowrap">
					<c:set var="isFirstItem" value="true" />
					<c:forEach var="predecessorStepInstance" items="${stepInstance.predecessors}">
						<c:if test="${isFirstItem == false}">, </c:if>
						<a href="javascript:void(0);" title="${predecessorStepInstance.name}">${taskInstance.stepInstances.indexOf(predecessorStepInstance)}</a>
						<c:set var="isFirstItem" value="false" />
					</c:forEach>
				</td>
				<td nowrap="nowrap">
					<c:set var="isFirstItem" value="true" />
					<c:forEach var="successorStepInstance" items="${stepInstance.successors}">
						<c:if test="${isFirstItem == false}">, </c:if>
						<a href="javascript:void(0);" title="${successorStepInstance.name}">${taskInstance.stepInstances.indexOf(successorStepInstance)}</a>
						<c:set var="isFirstItem" value="false" />
					</c:forEach>
				</td>
			</tr>		
			<c:forEach var="operationInstanceIndex" begin="0" end="${stepInstance.operationInstances.size() -1}">
				<c:set var="operationInstance" value="${stepInstance.operationInstances.get(operationInstanceIndex)}" />
				<tr>
					<td colspan="21"></td>
					<td nowrap="nowrap"><c:out value="${operationInstanceIndex}"></c:out></td>
					<td nowrap="nowrap"><c:out value="${operationInstance.name}"></c:out></td>
					<td nowrap="nowrap"><c:if test="${stepInstance.operationExecutionOrder == 'SERIAL'}"><c:out value="${operationInstanceIndex + 1}" /></c:if></td>
					<td nowrap="nowrap"><c:out value="${operationInstance.status.getValue()}"></c:out></td>
					<td nowrap="nowrap"><c:if test="${operationInstance.timedOut == true}">是</c:if></td>
					<td nowrap="nowrap"><c:if test="${operationInstance.error == true}">是</c:if></td>
				</tr>
			</c:forEach>
		</c:forEach>
	</c:forEach>


</table>
</body>
</html>