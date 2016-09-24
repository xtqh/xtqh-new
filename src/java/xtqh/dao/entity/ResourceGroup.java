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
 * 
 * @ClassName: ResourceGroup
 * 
 * @Description: TODO
 * 
 * @author Comsys-Yan Fugen
 * 
 * @date Sep 13, 2016 3:29:57 PM
 *
 * 
 */
@Entity
@Table(name = "RESOURCEGROUP")
public class ResourceGroup extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String id = null;
}
