package daniel.springmvc.execution.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daniel.springmvc.execution.model.StepInstance;
import daniel.springmvc.execution.model.TaskInstance;
import daniel.springmvc.execution.service.TaskInstanceService;
import xtqh.util.HibernateTools;

@Service("TaskInstanceService")
public class TaskInstanceServiceImpl extends HibernateTools implements TaskInstanceService {

	@Autowired
	private StepInstanceServiceImpl stepInstanceService;

	@Override
	public void saveCascadingly(TaskInstance taskInstance) {
		for (StepInstance stepInstance : taskInstance.getStepInstances()) {
			stepInstanceService.saveCascadingly(stepInstance);
		}
		this.save(taskInstance);
	}

	@Override
	public StepInstanceServiceImpl getStepInstanceService() {
		return stepInstanceService;
	}

	@Override
	public void setStepInstanceService(StepInstanceServiceImpl stepInstanceService) {
		this.stepInstanceService = stepInstanceService;
	}

}
