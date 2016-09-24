
package xtqh.resource.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

//import xtqh.execution.model.AnsibleServer;
import xtqh.dao.entity.Resource;
import xtqh.framework.base.BaseEntity;

/**
 * 资源对ansible server可达情况
 * @author Daniel
 * @version 1.0
 * @created 02-九月-2016 20:57:37
 */
@Entity
@Table(name="TB_RESOURCECONNECTIONSTATUS")
public class ResourceConnectionStatus extends BaseEntity {

	private Resource resource;
	
//	private AnsibleServer ansibleServer;
	/**
	 * 该ansible server对该资源是否可达
	 */
	private boolean isReachable;
	/**
	 * 最后可达时间点
	 */
	private Date lastReachableTime;
	/**
	 * 最后一次连接测试时间
	 */
	private Date lastConnectionTestTime;

}