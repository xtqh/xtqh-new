package xtqh.framework.log.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_USER_OPERATION_LOG")
public class OperationLog {
	@Id
	@Column(name="ID",length=50)
	private String id;
	@Column(name="USER_ID",length=200)
	private String userId;
	@Column(name="LOGIN_IP")
	private String loginIp;
	@Column(name="TABLE_NAME")
	private String tableName;
	@Column(name="OPERATION",length=50)
	private String operation;
	@Column(name="OPERATION_CONTENT")
	private String operationContent;
	@Column(name="MODULE",length=50)
	private String module;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATION_TIME",length=50)
	private Date operationTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperationContent() {
		return operationContent;
	}
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
}
