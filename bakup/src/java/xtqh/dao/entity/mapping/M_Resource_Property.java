package xtqh.dao.entity.mapping;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import xtqh.dao.entity.Property;
import xtqh.dao.entity.Resource;
import xtqh.framework.base.BaseEntity;

/**
 * 
 * 
 * @ClassName: M_Resource_Property
 * 
 * @Description: TODO
 * 
 * @author Comsys-Yan Fugen
 * 
 * @date Sep 13, 2016 3:31:26 PM
 *
 * 
 */
@Entity
@Table(name = "M_Resource_Property")
public class M_Resource_Property extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MANAGEMENT")
	@SequenceGenerator(sequenceName = "SEQ_MANAGEMENT", name = "SEQ_MANAGEMENT")
	private String id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "resource_id")
	private Resource resource;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "property_id")
	private Property property;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
