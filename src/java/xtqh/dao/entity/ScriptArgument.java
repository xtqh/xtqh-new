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
 * @ClassName: ScriptArgument
 * 
 * @Description: TODO
 * 
 * @author Comsys-Yan Fugen
 * 
 * @date Sep 13, 2016 3:30:48 PM
 *
 * 
 */
@Entity
@Table(name = "SCRIPTARGUMENT")
public class ScriptArgument extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String id = null;
}
