package daniel.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import daniel.springmvc.execution.model.TaskInstance;
import daniel.springmvc.execution.service.OperationInstanceService;
import daniel.springmvc.execution.service.ProcessInstanceService;
import daniel.springmvc.execution.service.StepInstanceService;
import daniel.springmvc.execution.service.TaskInstanceService;


@Controller
@RequestMapping("/TaskInstance")
public class TaskInstanceController {

	@Autowired
	private ProcessInstanceService processInstanceService;

	@Autowired
	private TaskInstanceService taskInstanceService;

	@Autowired
	private StepInstanceService stepInstanceService;

	@Autowired
	private OperationInstanceService operationInstanceService;

	@RequestMapping("/Create")
	public String saveTaskInstance(Model model) {
		TaskInstance taskInstance1 = new TaskInstance();
		TaskInstance taskInstance2 = new TaskInstance();
		taskInstance1.setName("task1");
		taskInstance2.setName("task2");
		taskInstance1.getSuccessors().add(taskInstance2);
//		taskInstanceService.beginTransaction();
//		taskInstanceService.save(taskInstance1);
//		taskInstanceService.save(taskInstance2);
//		taskInstanceService.commitTransaction();

		TaskInstance taskInstance3 = new TaskInstance();
		TaskInstance taskInstance4 = new TaskInstance();
		taskInstance3.setName("task3");
		taskInstance4.setName("task4");
		taskInstance4.getPredecessors().add(taskInstance3);
		List<TaskInstance> taskList = new ArrayList<TaskInstance>();
		taskList.add(taskInstance3);
		taskList.add(taskInstance4);
//		taskInstanceService.beginTransaction();
//		taskInstanceService.saveList(taskList);
//		taskInstanceService.commitTransaction();

		model.addAttribute("msg", "4 tasks saved");
		return "Success";
	}

	@RequestMapping("/Create2")
	public String saveTaskInstance2(Model model) {
		TaskInstance taskInstance1 = new TaskInstance();
		taskInstance1.setName("task new");
//		taskInstanceService.beginTransaction();
//		taskInstanceService.save(taskInstance1);
		model.addAttribute("task", taskInstance1);
//		taskInstanceService.commitTransaction();
		return "Success";
	}

	public TaskInstanceService getUserService() {
		return taskInstanceService;
	}

	public void setUserService(TaskInstanceService taskInstanceService) {
		this.taskInstanceService = taskInstanceService;
	}
}
