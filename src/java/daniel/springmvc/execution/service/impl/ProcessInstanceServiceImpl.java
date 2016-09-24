package daniel.springmvc.execution.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daniel.springmvc.execution.model.ProcessInstance;
import daniel.springmvc.execution.model.TaskInstance;
import daniel.springmvc.execution.service.ProcessInstanceService;
import daniel.springmvc.execution.service.TaskInstanceService;
import xtqh.util.HibernateTools;

@Service("ProcessInstanceService")
public class ProcessInstanceServiceImpl extends HibernateTools implements ProcessInstanceService {

	@Resource(name = "HibernateTools")
	private HibernateTools hibernate;

	@Autowired
	private TaskInstanceService taskInstanceService;

	@Override
	public List<ProcessInstance> getAll() {
		return super.getAll(ProcessInstance.class);
	}

	@Override
	public ProcessInstance get(String uid) {
		return super.get(ProcessInstance.class, uid);
	}

	@Override
	public ProcessInstance getEagerly(String uid) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("SELECT pi FROM ProcessInstance pi " + "LEFT JOIN FETCH pi.taskInstances ti "
				+ "LEFT JOIN FETCH ti.stepInstances si " + "LEFT JOIN FETCH si.operationInstances "
				+ "WHERE pi.id = :uid");
		query.setString("uid", uid);
		ProcessInstance processInstance = (ProcessInstance) query.uniqueResult();
		return processInstance;
	}

	@Override
	public void saveCascadingly(ProcessInstance processInstance) {
		for (TaskInstance taskInstance : processInstance.getTaskInstances()) {
			taskInstanceService.saveCascadingly(taskInstance);
		}
		this.save(processInstance);
	}

	@Override
	public TaskInstanceService getTaskInstanceService() {
		return taskInstanceService;
	}

	@Override
	public void setTaskInstanceService(TaskInstanceServiceImpl taskInstanceService) {
		this.taskInstanceService = taskInstanceService;
	}
}
