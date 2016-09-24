package xtqh.framework.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import xtqh.framework.base.audit.PersistenceAuditListener;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(PersistenceAuditListener.class)
public abstract class BaseEntity {

	// @Id
	// @GeneratedValue(generator = "uuid2")
	// @GenericGenerator(name = "uuid2", strategy = "uuid2")
	// @Column(name = "ID", columnDefinition = "CHAR(36)")
	// private String id = null;

	@Column(name = "CREATOR", length = 50, insertable = true, updatable = false)
	private String creator;

	@Column(name = "CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;

	@Column(name = "UPDATOR", length = 50, insertable = false, updatable = true)
	private String updator;

	@Column(name = "UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	// public String getId() {
	// return id;
	// }
	//
	// public void setId(String id) {
	// this.id = id;
	// }

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((id == null) ? 0 : id.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// BaseEntity other = (BaseEntity) obj;
	// if (id != null && other.id != null)
	// return (id == other.id);
	// else return false;
	// }
	//
	// @Override
	// public String toString() {
	// return "[id=" + id;
	// }
}
