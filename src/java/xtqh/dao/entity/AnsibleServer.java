package xtqh.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import xtqh.framework.base.BaseEntity;


/**
 * 
 * @author Yan Fugen
 *
 */
@Entity
@Table(name = "AnsibleServer")
public class AnsibleServer extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String ansibleServer_id;

	private String name;

	private String ip_address;

	private String controlling_ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getControlling_ip() {
		return controlling_ip;
	}

	public void setControlling_ip(String controlling_ip) {
		this.controlling_ip = controlling_ip;
	}

	public String getAnsibleServer_id() {
		return ansibleServer_id;
	}

	public void setAnsibleServer_id(String ansibleServer_id) {
		this.ansibleServer_id = ansibleServer_id;
	}

}
