package daniel.springmvc.execution.model.enump;
/**
 * 
 * TaskInstanceStatus 实例任务状态，包括：<br>
 * NOT_STARTED: 未开始 <br>
 * READY: 可执行 <br>
 * PARTIALLY_PAUSED: 部分暂停<br>
 * PAUSED: 已暂停 <br>
 * PARTIALLY_CANCELLED: 部分取消<br>
 * CANCELLED: 已取消<br>
 * EXECUTING: 执行中<br>
 * FINISHED: 已完成<br>
 * 
 */
public enum TaskInstanceStatusEnum {
	NOT_STARTED, 
	READY, 
	PARTIALLY_PAUSED,
	PAUSED,
	PARTIALLY_CANCELLED,
	CANCELLED,
	EXECUTING,
	FINISHED
}
