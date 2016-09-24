
package xtqh.resource.model;

import java.util.List;
import java.util.Map;

//import xtqh.process.model.ProcessVersion;
import xtqh.dao.entity.Property;
import xtqh.dao.entity.Resource;
import xtqh.framework.base.BaseEntity;

/**
 * 资源组
 * @author Daniel
 * @version 1.0
 * @created 02-九月-2016 20:57:54
 */
public class ResourceGroup extends BaseEntity {

	private String name;
	/**
	 * 包含资源（含标签）
	 */
	private Map<Resource,List<ResourceTag>> taggedResources;
	/**
	 * 资源组属性值
	 */
	private List<Property> properties;
//	public ProcessVersion m_ProcessVersion;

}