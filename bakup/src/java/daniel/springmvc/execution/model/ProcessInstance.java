package daniel.springmvc.execution.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import daniel.springmvc.execution.model.enump.ProcessInstanceStatusEnum;
import xtqh.framework.base.BaseEntity;

@Entity
@Table(name = "TB_PROCESSINSTANCE")
public class ProcessInstance extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3322244446610834890L;

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

	@OneToMany(mappedBy = "processInstance", cascade = { CascadeType.REMOVE })
	@OrderColumn(name = "TASK_INSTANCE_INDEX")
	private List<TaskInstance> taskInstances = new ArrayList<TaskInstance>();

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private ProcessInstanceStatusEnum status = ProcessInstanceStatusEnum.NOT_STARTED;

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

	public ProcessInstance() {
		super();
	}

	public ProcessInstance(String name) {
		this.name = name;
	}

	public void addTaskInstance(TaskInstance taskInstance) {
		this.getTaskInstances().add(taskInstance);
		taskInstance.setProcessInstance(this);
	}

	@Override
	public String toString() {
		return "ProcessInstance [toString()=" + super.toString() + ", name=" + name + ", description=" + description
				+ ", status=" + status + ", error=" + error + ", timedOut=" + timedOut + "]";
	}

	public List<TaskInstance> getTaskInstances() {
		return taskInstances;
	}

	public void setTaskInstances(List<TaskInstance> taskInstances) {
		this.taskInstances = taskInstances;
	}

	public ProcessInstanceStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProcessInstanceStatusEnum status) {
		this.status = status;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
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
