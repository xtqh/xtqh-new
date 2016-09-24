package daniel.springmvc.execution.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import daniel.springmvc.execution.model.enump.OperationExecutionOrderEnum;
import daniel.springmvc.execution.model.enump.StepInstanceStatusEnum;
import xtqh.framework.base.BaseEntity;

/**
 * @author Daniel
 *
 */
@Entity
@Table(name = "TB_STEPINSTANCE")
public class StepInstance extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1948974296877251332L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String id;
	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(1023)")
	private String description;

	/**
	 * 前置实例步骤
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_STEPINSTANCE_STEPINSTANCE", joinColumns = @JoinColumn(name = "SUCCESSOR_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "STEP_INSTANCE_ID", referencedColumnName = "ID"))
	private List<StepInstance> predecessors = new ArrayList<StepInstance>();

	/**
	 * 后置实例步骤
	 */
	@ManyToMany(mappedBy = "predecessors", fetch = FetchType.EAGER)
	private List<StepInstance> successors = new ArrayList<StepInstance>();

	/**
	 * 所在实例任务
	 */
	@ManyToOne
	@JoinColumn(name = "TASK_INSTANCE_ID")
	private TaskInstance taskInstance;

	@OneToMany(mappedBy = "stepInstance", cascade = { CascadeType.REMOVE })
	@OrderColumn(name = "OPERATION_INSTANCE_INDEX")
	private List<OperationInstance> operationInstances = new ArrayList<OperationInstance>();

	/**
	 * 实例步骤运行状态，初始设为“未开始”
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StepInstanceStatusEnum status = StepInstanceStatusEnum.NOT_STARTED;

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

	/**
	 * 该步骤在流程定义中是否为自动方式执行
	 */
	@Column(name = "IS_AUTOMATIC")
	private boolean automatic;

	/**
	 * 当前是否为自动方式执行<br>
	 * true: 自动方式<br>
	 * false: 人工方式
	 * 
	 */
	@Column(name = "IS_CURRENTLY_AUTOMATIC")
	private boolean currentlyAutomatic;

	@Enumerated(EnumType.STRING)
	private OperationExecutionOrderEnum operationExecutionOrder;

	public void addOperationInstance(OperationInstance operationInstance) {
		this.getOperationInstances().add(operationInstance);
		operationInstance.setStepInstance(this);
	}

	public void addPredecessor(StepInstance predecessor) {
		this.getPredecessors().add(predecessor);
		predecessor.getSuccessors().add(this);
	}

	public void addSuccessor(StepInstance successor) {
		this.getSuccessors().add(successor);
		successor.getPredecessors().add(this);
	}

	public OperationExecutionOrderEnum getOperationExecutionOrder() {
		return operationExecutionOrder;
	}

	public void setOperationExecutionOrder(OperationExecutionOrderEnum operationExecutionOrder) {
		this.operationExecutionOrder = operationExecutionOrder;
	}

	public StepInstance(String name) {
		this.name = name;
	}

	public StepInstance() {
	}

	public List<OperationInstance> getOperationInstances() {
		return operationInstances;
	}

	public void setOperationInstances(List<OperationInstance> operationInstances) {
		this.operationInstances = operationInstances;
	}

	public boolean isCurrentlyAutomatic() {
		return currentlyAutomatic;
	}

	public void setCurrentlyAutomatic(boolean CurrentlyAutomatic) {
		this.currentlyAutomatic = CurrentlyAutomatic;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}

	@Override
	public String toString() {
		return "StepInstance [toString()=" + super.toString() + ", name=" + name + ", description=" + description
				+ ", status=" + status + ", error=" + error + ", timedOut=" + timedOut + ", automatic=" + automatic
				+ ", currentlyAutomatic=" + currentlyAutomatic + ", operationExecutionOrder=" + operationExecutionOrder
				+ "]";
	}

	public List<StepInstance> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(List<StepInstance> predecessors) {
		this.predecessors = predecessors;
	}

	public List<StepInstance> getSuccessors() {
		return successors;
	}

	public void setSuccessors(List<StepInstance> successors) {
		this.successors = successors;
	}

	public TaskInstance getTaskInstance() {
		return taskInstance;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		this.taskInstance = taskInstance;
	}

	public StepInstanceStatusEnum getStatus() {
		return status;
	}

	public void setStatus(StepInstanceStatusEnum status) {
		this.status = status;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isAutomatic() {
		return automatic;
	}

	public void setAutomatic(boolean Automatic) {
		this.automatic = Automatic;
		if (!Automatic)
			this.setOperationExecutionOrder(OperationExecutionOrderEnum.NOT_APPLICABLE);
	}

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

}
