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

import daniel.springmvc.execution.model.enump.TaskInstanceStatusEnum;
import xtqh.framework.base.BaseEntity;

@Entity
@Table(name = "TB_TASKINSTANCE")
public class TaskInstance extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4231152958123748541L;

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_TASKINSTANCE_TASKINSTANCE", joinColumns = @JoinColumn(name = "SUCCESSOR_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TASK_INSTANCE_ID", referencedColumnName = "ID"))
	private List<TaskInstance> predecessors = new ArrayList<TaskInstance>();

	@ManyToMany(mappedBy = "predecessors", fetch = FetchType.EAGER)
	private List<TaskInstance> successors = new ArrayList<TaskInstance>();

	@OneToMany(mappedBy = "taskInstance", cascade = { CascadeType.REMOVE })
	@OrderColumn(name = "STEP_INSTANCE_INDEX")
	private List<StepInstance> stepInstances = new ArrayList<StepInstance>();

	@ManyToOne
	@JoinColumn(name = "PROCESS_INSTANCE_ID")
	private ProcessInstance processInstance;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private TaskInstanceStatusEnum status = TaskInstanceStatusEnum.NOT_STARTED;

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

	@Override
	public String toString() {
		return "TaskInstance [toString()=" + super.toString() + ", name=" + name + ", description=" + description
				+ ", status=" + status + ", error=" + error + ", timedOut=" + timedOut + "]";
	}

	public TaskInstance(String name) {
		this.name = name;
	}

	public TaskInstance() {
	}

	public void addStepInstance(StepInstance stepInstance) {
		this.getStepInstances().add(stepInstance);
		stepInstance.setTaskInstance(this);
	}

	public void addPredecessor(TaskInstance predecessor) {
		this.getPredecessors().add(predecessor);
		predecessor.getSuccessors().add(this);
	}

	public void addSuccessor(TaskInstance successor) {
		this.getSuccessors().add(successor);
		successor.getPredecessors().add(this);
	}

	public List<StepInstance> getStepInstances() {
		return stepInstances;
	}

	public void setStepInstances(List<StepInstance> stepInstances) {
		this.stepInstances = stepInstances;
	}

	public List<TaskInstance> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(List<TaskInstance> predecessors) {
		this.predecessors = predecessors;
	}

	public List<TaskInstance> getSuccessors() {
		return successors;
	}

	public void setSuccessors(List<TaskInstance> successors) {
		this.successors = successors;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}

	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public TaskInstanceStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TaskInstanceStatusEnum status) {
		this.status = status;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
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
