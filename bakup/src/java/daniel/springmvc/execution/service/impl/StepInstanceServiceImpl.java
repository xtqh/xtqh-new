package daniel.springmvc.execution.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daniel.springmvc.execution.model.OperationInstance;
import daniel.springmvc.execution.model.StepInstance;
import daniel.springmvc.execution.service.OperationInstanceService;
import daniel.springmvc.execution.service.StepInstanceService;
import xtqh.util.HibernateTools;

@Service("StepInstanceService")
public class StepInstanceServiceImpl extends HibernateTools implements StepInstanceService {

	@Autowired
	private OperationInstanceService operationInstanceService;

	@Override
	public void saveCascadingly(StepInstance stepInstance) {
		for (OperationInstance operationInstance : stepInstance.getOperationInstances()) {
//			operationInstanceService.save(operationInstance);
		}
		this.save(stepInstance);
	}

	@Override
	public OperationInstanceService getOperationInstanceService() {
		return operationInstanceService;
	}

	@Override
	public void setOperationInstanceService(OperationInstanceService operationInstanceService) {
		this.operationInstanceService = operationInstanceService;
	}
}
