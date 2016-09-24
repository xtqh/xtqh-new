package daniel.springmvc.execution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import daniel.springmvc.execution.model.enump.OperationInstanceStatusEnum;
import xtqh.framework.base.BaseEntity;

/**
 * @author Daniel 实例步骤中的操作
 */
@Entity
@Table(name = "TB_OPERATIONINSTANCE")
public class OperationInstance extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String id;

	private static final long serialVersionUID = 4207378569575528516L;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(1023)")
	private String description;

	/**
	 * 所在实例步骤
	 */
	@ManyToOne
	@JoinColumn(name = "STEP_INSTANCE_ID")
	private StepInstance stepInstance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 实例步骤操作运行状态，初始设为“未开始”
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private OperationInstanceStatusEnum status = OperationInstanceStatusEnum.NOT_STARTED;

	/**
	 * 自动执行过程中是否有出错，初始设为“否”
	 */
	@Column(name = "HAS_ERROR")
	private boolean error = false;

	/**
	 * 是否运行超时，初始设为“否”
	 */
	@Column(name = "TIMED_OUT")
	private boolean timedOut = false;

	public OperationInstance(String name) {
		this.name = name;
	}

	public OperationInstance() {
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public StepInstance getStepInstance() {
		return stepInstance;
	}

	public void setStepInstance(StepInstance stepInstance) {
		this.stepInstance = stepInstance;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}

	@Override
	public String toString() {
		return "OperationInstance [toString()=" + super.toString() + ", name=" + name + ", description=" + description
				+ ", stepInstance=" + stepInstance + ", status=" + status + ", error=" + error + ", timedOut="
				+ timedOut + "]";
	}

	public OperationInstanceStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OperationInstanceStatusEnum status) {
		this.status = status;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
