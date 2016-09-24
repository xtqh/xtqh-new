package xtqh.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import xtqh.framework.base.BaseEntity;

/**
 * 

  * @ClassName: Property

  * @Description: TODO

  * @author Comsys-Yan Fugen

  * @date Sep 13, 2016 3:29:35 PM

  *
 */
@Entity
@Table(name = "PROPERTY")
public class Property extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)")
	private String propertyId;

	@Transient
	private List<StandardProperty> scriptArguments;

	@Column(name = "NAME", columnDefinition = "VARCHAR(50)")
	private String value;

	public List<StandardProperty> getScriptArguments() {
		return scriptArguments;
	}

	public void setScriptArguments(List<StandardProperty> scriptArguments) {
		this.scriptArguments = scriptArguments;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}


}
