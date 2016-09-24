package daniel.springmvc.execution.service;

import org.springframework.stereotype.Service;

import daniel.springmvc.execution.model.StepInstance;


@Service
public interface StepInstanceService {

	public void saveCascadingly(StepInstance stepInstance);

	public OperationInstanceService getOperationInstanceService();

	public void setOperationInstanceService(OperationInstanceService operationInstanceService);
}
