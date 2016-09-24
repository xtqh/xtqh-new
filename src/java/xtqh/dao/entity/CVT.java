package xtqh.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import xtqh.framework.base.BaseEntity;


/**
 * 
 * 
 * @ClassName: CVT
 * 
 * @Description: TODO
 * 
 * @author Comsys-Yan Fugen
 * 
 * @date Sep 13, 2016 3:18:34 PM
 *
 * 
 */
@Entity
@Table(name = "CVT")
public class CVT extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MANAGEMENT")
	@SequenceGenerator(sequenceName = "SEQ_MANAGEMENT", name = "SEQ_MANAGEMENT")
	private String id;

	@Column(name = "SYS_ID", columnDefinition = "VARCHAR(50)")
	private String sys_id;

	@Column(name = "SYS_DESC", columnDefinition = "VARCHAR(100)")
	private String sys_desc;

	@Column(name = "FUNC_ID", columnDefinition = "VARCHAR(50)")
	private String func_id;

	@Column(name = "FUNC_DESC", columnDefinition = "VARCHAR(100)")
	private String func_desc;

	@Column(name = "SHORT_DESC", columnDefinition = "VARCHAR(100)")
	private String short_desc;

	@Column(name = "LONG_DESC", columnDefinition = "VARCHAR(1000)")
	private String long_desc;

	@Column(name = "code", columnDefinition = "VARCHAR(50)")
	private String code;

	@Column(name = "INT_VAL", columnDefinition = "INT")
	private int int_val;

	@Column(name = "DOUBLE_VAL", columnDefinition = "FLOAT")
	private double double_val;

	@Column(name = "STR_VAL", columnDefinition = "VARCHAR(200)")
	private String str_val;

	@Column(name = "BOOLEAN_VAL")
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean boolean_val;

}
