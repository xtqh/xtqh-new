package xtqh.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Yan Fugen
 *
 */

@Entity
@Table(name = "SYSMENU")
public class SysMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MANAGEMENT")
	@SequenceGenerator(sequenceName = "SEQ_MANAGEMENT", name = "SEQ_MANAGEMENT")
	private String id;

	@Column(name = "PARENT_ID", columnDefinition = "VARCHAR(50)")
	private String parentId;

	@Column(name = "NAME", columnDefinition = "VARCHAR(50)")
	private String name;

	@Column(name = "DISPLAY_NAME", columnDefinition = "VARCHAR(500)")
	private String displayName;

	@Column(name = "DISPLAY Order", columnDefinition = "VARCHAR(25)")
	private String displayOrder;

	@Column(name = "URL", columnDefinition = "VARCHAR(1000)")
	private String url;

	@Column(name = "ICO", columnDefinition = "VARCHAR(50)")
	private String ico;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

}
