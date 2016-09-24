package xtqh.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import xtqh.framework.base.BaseEntity;


/**
 * 

  * @ClassName: ResourceConnectionStatus

  * @Description: TODO

  * @author Comsys-Yan Fugen

  * @date Sep 13, 2016 3:29:51 PM

  *
 */

@Entity
@Table(name = "RESOURCECONNECTIONSTATUS")
public class ResourceConnectionStatus extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String resourceConnectionStatus_id;

	@Column(name = "ansibleServer", columnDefinition = "VARCHAR(100)")
	private String ansibleServer;

	@Column(name = "isReachable")
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean isReachable;

	@Column(name = "lastConnectionTestTime", columnDefinition = "TIMESTAMP")
	private Date lastConnectionTestTime;

	@Column(name = "lastReachableTime", columnDefinition = "TIMESTAMP")
	private Date lastReachableTime;

}
