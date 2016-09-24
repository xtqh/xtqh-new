package daniel.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import daniel.springmvc.execution.model.OperationInstance;
import daniel.springmvc.execution.model.ProcessInstance;
import daniel.springmvc.execution.model.StepInstance;
import daniel.springmvc.execution.model.TaskInstance;
import daniel.springmvc.execution.service.OperationInstanceService;
import daniel.springmvc.execution.service.ProcessInstanceService;
import daniel.springmvc.execution.service.StepInstanceService;
import daniel.springmvc.execution.service.TaskInstanceService;
import daniel.springmvc.execution.service.impl.ProcessInstanceServiceImpl;


@Controller
@RequestMapping("/ProcessInstances")
public class ProcessInstanceController {
	
	@Autowired
	private ProcessInstanceService processInstanceService;
	
	@Autowired
	private TaskInstanceService taskInstanceService;

	@Autowired
	private StepInstanceService stepInstanceService;

	@Autowired
	private OperationInstanceService operationInstanceService;

	/**
	 * Lists all the process instances.
	 */
	@RequestMapping("/List")
	public String showProcessInstanceList(Model model) {
//		processInstanceService.beginTransaction();
		List<ProcessInstance> processInstanceList = processInstanceService.getAll();
//		processInstanceService.commitTransaction();
		model.addAttribute("processInstanceList", processInstanceList);
		return "ProcessInstanceList";
	}
	
	@RequestMapping("/Show/{uid}")
	public String showProcessInstanceDetail(Model model, @PathVariable String uid){
//		processInstanceService.beginTransaction();
		ProcessInstance processInstance = processInstanceService.getEagerly(uid);
		model.addAttribute("processInstance", processInstance);
//		processInstanceService.commitTransaction();
		return "ProcessInstanceDetail";
	}
	
	/**
	 * Creates a sample process instance
	 */
	@RequestMapping("/CreateSampleProcess")
	public String saveTaskInstance(Model model){
		ProcessInstance pi = new ProcessInstance("测试流程");
		
		TaskInstance ti1 = new TaskInstance("停止应用程序");
		pi.addTaskInstance(ti1);
		
		StepInstance si1 = new StepInstance("停应用程序");
		si1.setAutomatic(true);
//		si1.setOperationExecutionOrder(OperationExecutionOrderEnum.PARALLE);
		ti1.addStepInstance(si1);
		OperationInstance oi1= new OperationInstance("停app1应用程序");
		OperationInstance oi2= new OperationInstance("停app2应用程序");
		si1.addOperationInstance(oi1);
		si1.addOperationInstance(oi2);
		
		StepInstance si2 = new StepInstance("检查应用程序停止情况");
		si2.setAutomatic(true);
//		si2.setOperationExecutionOrder(OperationExecutionOrderEnum.PARALLE);
		si1.addSuccessor(si2);
		ti1.addStepInstance(si2);
		OperationInstance oi3= new OperationInstance("检查app1应用停止情况");
		OperationInstance oi4= new OperationInstance("检查app2应用停止情况");
		si2.addOperationInstance(oi3);
		si2.addOperationInstance(oi4);
		
		TaskInstance ti2 = new TaskInstance("停止数据库");
		pi.addTaskInstance(ti2);
		ti1.addSuccessor(ti2);
		
		StepInstance si3 = new StepInstance("停数据库");
		si3.setAutomatic(true);
//		si3.setOperationExecutionOrder(OperationExecutionOrderEnum.SERIAL);
		ti2.addStepInstance(si3);
		OperationInstance oi5= new OperationInstance("停db1的数据库");
		OperationInstance oi6= new OperationInstance("停db2的数据库");
		si3.addOperationInstance(oi5);
		si3.addOperationInstance(oi6);
		
		StepInstance si4 = new StepInstance("检查数据库状态");
		si4.setAutomatic(true);
//		si4.setOperationExecutionOrder(OperationExecutionOrderEnum.PARALLE);
		ti2.addStepInstance(si4);
		si3.addSuccessor(si4);
		OperationInstance oi7= new OperationInstance("检查db1的数据库状态");
		OperationInstance oi8= new OperationInstance("检查db2的数据库状态");
		si4.addOperationInstance(oi7);
		si4.addOperationInstance(oi8);
			
//		processInstanceService.beginTransaction();
		processInstanceService.saveCascadingly(pi);
//		processInstanceService.commitTransaction();
		
		return "redirect:./List";
	}
	
	@RequestMapping("/Remove/{uid}")
	public String removeProcessInstance(Model model, @PathVariable String uid){
//		processInstanceService.beginTransaction();
		ProcessInstance processInstance = processInstanceService.get(uid);
//		processInstanceService.delete(processInstance);
//		processInstanceService.commitTransaction();
		return "redirect:../List";
	}


	public ProcessInstanceService getProcessInstanceService() {
		return processInstanceService;
	}



	public void setProcessInstanceService(ProcessInstanceServiceImpl processInstanceService) {
		this.processInstanceService = processInstanceService;
	}



	public TaskInstanceService getTaskInstanceService() {
		return taskInstanceService;
	}



	public void setTaskInstanceService(TaskInstanceService taskInstanceService) {
		this.taskInstanceService = taskInstanceService;
	}



	public StepInstanceService getStepInstanceService() {
		return stepInstanceService;
	}



	public void setStepInstanceService(StepInstanceService stepInstanceService) {
		this.stepInstanceService = stepInstanceService;
	}



	public OperationInstanceService getOperationInstanceService() {
		return operationInstanceService;
	}



	public void setOperationInstanceService(OperationInstanceService operationInstanceService) {
		this.operationInstanceService = operationInstanceService;
	}



	public TaskInstanceService getUserService() {
		return taskInstanceService;
	}

	public void setUserService(TaskInstanceService taskInstanceService) {
		this.taskInstanceService = taskInstanceService;
	}
}
