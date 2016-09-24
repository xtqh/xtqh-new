package daniel.springmvc.execution.model.enump;
/**
 * @author Daniel
 * StepInstanceStatus 实例步骤状态，包括：<br>
 * NOT_STARTED: 未开始 <br>
 * READY: 可执行 <br>
 * PAUSED: 已暂停 <br>
 * CANCELLED: 已取消<br>
 * TO_BE_ACCEPTED: 待执行人接受<br>
 * EXECUTING: 执行中<br>
 * FINISHED: 已完成<br>
 * 
 */
public enum StepInstanceStatusEnum {
	NOT_STARTED, 
	READY, 
	PAUSED,
	CANCELLED,
	TO_BE_ACCEPTED,
	EXECUTING,
	FINISHED
}