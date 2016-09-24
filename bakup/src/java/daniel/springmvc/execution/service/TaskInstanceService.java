package daniel.springmvc.execution.service;

import org.springframework.stereotype.Service;

import daniel.springmvc.execution.model.TaskInstance;
import daniel.springmvc.execution.service.impl.StepInstanceServiceImpl;


@Service
public interface TaskInstanceService {

	public void saveCascadingly(TaskInstance taskInstance);

	public StepInstanceServiceImpl getStepInstanceService();

	public void setStepInstanceService(StepInstanceServiceImpl stepInstanceService);
}
