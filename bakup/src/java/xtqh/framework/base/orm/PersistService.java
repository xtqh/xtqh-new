/**
 * 
 */
package xtqh.framework.base.orm;

import java.util.List;
import java.util.Map;

/**
 * 
 * <code>PersistService.java</code>
 * <p>
 * 
 * @function:JPA持久化接口类
 * 
 * @version 1.0 </br>
 */
public interface PersistService {
	/**
	 * 持久化一个实体类
	 * 
	 * @param toSaveObj
	 */
	public void persist(Object toSaveObj);

	/**
	 * 根据主键获取实体类
	 * 
	 * @param objCls
	 * @param id
	 */
	public <T> T find(Class<T> objCls, Integer id);

	/**
	 * 根据主键获取实体类
	 * 
	 * @param objCls
	 * @param id
	 */
	public <T> T find(Class<T> objCls, Long id);

	/**
	 * 根据主键(主键为字符串)获取实体类
	 * 
	 * @param objCls
	 * @param id
	 */
	public <T> T find(Class<T> objCls, String id);

	public <T> T find(Class<T> objCls, final Object id);

	/**
	 * 删除一条记录
	 * 
	 * @param object
	 */
	public void remove(Object object);

	/**
	 * merge
	 * 
	 * @param entity
	 * @return
	 */
	public <T> T merge(T entity);

	/**
	 * 
	 * @function:批量插入
	 */
	public void batchInsert(List<?> entityList);

	/**
	 * 
	 * @function:批量更新
	 * @param entityList
	 */
	public void batchUpdate(List<?> entityList);

	/**
	 * @function:
	 * @author LVJIANLIU
	 * @param entityList
	 */
	public void batchDelete(List<?> entityList);

	/**
	 * 根据某个字段查询获取多条记录
	 * 
	 * @param entityClass
	 * @param field
	 * @param value
	 * @return
	 */
	public <T> List<T> findListByField(Class<T> objClass);

	/**
	 * 根据某个字段查询获取多条记录
	 * 
	 * @param entityClass
	 * @param field
	 * @param value
	 * @return
	 */
	public <T> List<T> findListByField(Class<T> objClass, String sidx, String sord);

	/**
	 * 根据某个字段查询获取多条记录
	 * 
	 * @param entityClass
	 * @param field
	 * @param value
	 * @return
	 */
	public <T> List<T> findListByField(Class<T> objClass, String field, Object value);

	/**
	 * @function:根据查询条件获取多条记录
	 * @param entityClass
	 * @param params
	 * @return
	 */
	public <T> List<T> findListByFields(Class<T> entityClass, Map<String, ?> params, boolean... isOr);

	/**
	 * @function:根据查询条件获取多条记录
	 * @param entityClass
	 * @param params
	 * @return
	 */
	public <T> List<T> findListByFields(Class<T> entityClass, Map<String, ?> params, String sidx, String sord,
			boolean... isOr);

	/**
	 * 根据某个字段获取值
	 * 
	 * @param entityClass
	 * @param field
	 * @param value
	 * @return
	 */
	public <T> T findObjectByField(Class<T> entityClass, String field, Object value);

	/**
	 * 根据原生脚本查询列表
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public <T> List<T> findListByQuery(Class<T> entityClass, String queryString, Object... values);

}
