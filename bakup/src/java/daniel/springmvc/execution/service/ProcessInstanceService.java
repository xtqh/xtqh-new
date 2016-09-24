package daniel.springmvc.execution.service;

import java.util.List;

import daniel.springmvc.execution.model.ProcessInstance;
import daniel.springmvc.execution.service.impl.TaskInstanceServiceImpl;


public interface ProcessInstanceService {

	public List<ProcessInstance> getAll();

	public ProcessInstance get(String uid);

	public ProcessInstance getEagerly(String uid);

	public void saveCascadingly(ProcessInstance processInstance);

	public TaskInstanceService getTaskInstanceService();

	public void setTaskInstanceService(TaskInstanceServiceImpl taskInstanceService);
}
