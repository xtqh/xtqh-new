/**
 * 
 */
package xtqh.framework.base.orm;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service("JpaPersistence")
public class JpaPersistence implements PersistService {
	// 批量操作间隔数
	public static final Integer BATCH_INTERVAL = 100;

	private static Logger logger = Logger.getLogger(JpaPersistence.class);

	@PersistenceContext
	protected EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.portal.systemManager.dao.PersistService#find(java.lang.Class,
	 * java.lang.Integer)
	 */
	public <T> T find(Class<T> objCls, final Integer id) {
		try {
			return entityManager.find(objCls, id);
		} catch (Exception e) {
			logger.error("失败了", e);
			e.printStackTrace();
		}
		return null;
	}

	public <T> T find(Class<T> objCls, final Long id) {
		try {
			return entityManager.find(objCls, id);
		} catch (Exception e) {
			logger.error("失败了", e);
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.framework.orm.PersistService#find(java.lang.Class,
	 * java.lang.String)
	 */
	public <T> T find(Class<T> objCls, final String id) {
		try {
			return entityManager.find(objCls, id);
		} catch (Exception e) {
			logger.error("失败了", e);
			e.printStackTrace();
		}
		return null;
	}

	public <T> T find(Class<T> objCls, final Object id) {
		try {
			return entityManager.find(objCls, id);
		} catch (Exception e) {
			logger.error("失败了", e);
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.portal.systemManager.dao.PersistService#persist(java.lang.Object)
	 */
	public void persist(Object toSaveObj) {
		entityManager.persist(toSaveObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.portal.systemManager.dao.PersistService#remove(java.lang.Object)
	 */
	public void remove(Object entity) {
		entityManager.remove(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.portal.systemManager.dao.PersistService#merge(java.lang.Object)
	 */
	public <T> T merge(T entity) {
		// String content = getbeforeUpdateContent(entity);
		T t = entityManager.merge(entity);
		// this.getTableNameByEntity(t,"update",content);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.portal.systemManager.dao.PersistService#findListByField(java.
	 * lang.Class, java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByField(Class<T> objClass, String field, Object value) {
		String queryString = produceQueryString(objClass, field);
		Query query = entityManager.createQuery(queryString);
		query.setParameter(1, value);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findListByField(final Class<T> objClass, final String field, final Object value, final int start,
			final int maxRows) {
		String queryString = produceQueryString(objClass, field);
		Query query = entityManager.createQuery(queryString);
		query.setParameter(1, value);
		if (maxRows >= 0) {
			query.setMaxResults(maxRows);
		}
		if (start >= 0) {
			query.setFirstResult(start);
		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findListByFields(final Class<T> objClass, final Map<String, ?> params, final int start,
			final int maxRows, boolean... isOr) {
		String queryString = produceQueryStringByNamedParams(objClass, params, isOr);
		Query query = entityManager.createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if (maxRows >= 0) {
			query.setMaxResults(maxRows);
		}
		if (start >= 0) {
			query.setFirstResult(start);
		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findListOrderbyByFields(final Class<T> objClass, final Map<String, ?> params, final int start,
			final int maxRows, String sidx, String sord, boolean isOr) {
		String queryString = produceQueryStringByNamedParams(objClass, params, sidx, sord, isOr);
		Query query = entityManager.createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if (maxRows >= 0) {
			query.setMaxResults(maxRows);
		}
		if (start >= 0) {
			query.setFirstResult(start);
		}
		return query.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.portal.systemManager.dao.PersistService#findListByFields(java
	 * .lang.Class, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByFields(final Class<T> entityClass, final Map<String, ?> params, boolean... isOr) {
		String queryString = produceQueryStringByNamedParams(entityClass, params, isOr).toString();
		Query query = entityManager.createQuery(queryString);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findListByFields(final Class<T> entityClass, final Map<String, ?> params, String sidx,
			String sord, boolean... isOr) {
		String queryString = produceQueryStringByNamedParams(entityClass, params, sidx, sord, isOr).toString();
		Query query = entityManager.createQuery(queryString);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public <T> T findObjectByField(final Class<T> entityClass, final String field, final Object value) {
		String queryString = produceQueryString(entityClass, field).toString();
		Query query = entityManager.createQuery(queryString, entityClass);
		query.setParameter(1, value);
		try {
			return (T) query.getSingleResult();
		}
		// 忽略结果集
		catch (EmptyResultDataAccessException e) {
			logger.warn("No entity found for query. But be ignored.", e);
			return null;
		} catch (EntityNotFoundException e) {
			logger.warn("No entity found for query. But be ignored.", e);
			return null;
		} catch (NoResultException e) {
			logger.warn("No entity found for query. But be ignored.", e);
			return null;
		} catch (NonUniqueResultException e) {
			logger.warn("No entity found for query. But be ignored.", e);
			return null;
		}

	}

	/**
	 * 构建查询语句
	 * 
	 * @param entityClass
	 * @param fields
	 * @return
	 */
	private String produceQueryString(final Class<?> entityClass, final String... fields) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from ").append(entityClass.getName());

		if (fields != null && fields.length > 0) {
			queryBuilder.append(" where ");
			for (String field : fields) {
				queryBuilder.append(field).append(" = ? and ");
			}

			if (queryBuilder.lastIndexOf(" and ") == (queryBuilder.length() - 5)) {
				queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
			}
		}

		return queryBuilder.toString();
	}

	/**
	 * 生成多个查询字段的查询语句
	 * 
	 * @param entityClass
	 * @param params
	 * @return
	 */
	private String produceQueryStringByNamedParams(final Class<?> entityClass, final Map<String, ?> params,
			boolean... isOr) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from ").append(entityClass.getName());

		if (params != null && !params.isEmpty()) {
			queryBuilder.append(" where ");

			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryBuilder.append(entry.getKey()).append(" = :").append(entry.getKey()).append(" and ");
			}
			if (queryBuilder.lastIndexOf(" and ") == (queryBuilder.length() - 5)) {
				queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
			}
		}

		String query = queryBuilder.toString();

		if (isOr != null && isOr.length != 0) {
			if (isOr[0]) {
				query = query.replaceAll(" and ", " or ");
			}
		}

		return query;

	}

	/**
	 * 生成多个查询字段的查询语句
	 * 
	 * @param entityClass
	 * @param params
	 * @return
	 */
	private String produceQueryStringByNamedParams(final Class<?> entityClass, final Map<String, ?> params, String sidx,
			String sord, boolean... isOr) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from ").append(entityClass.getName());

		if (params != null && !params.isEmpty()) {
			queryBuilder.append(" where ");

			for (Map.Entry<String, ?> entry : params.entrySet()) {
				queryBuilder.append(entry.getKey()).append(" = :").append(entry.getKey()).append(" and ");
			}
			if (queryBuilder.lastIndexOf(" and ") == (queryBuilder.length() - 5)) {
				queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
			}
		}
		if (sidx != null && sidx.length() > 0 && sord != null && sord.length() > 0) {
			queryBuilder.append(" order by " + sidx + " " + sord);
		}

		String query = queryBuilder.toString();

		if (isOr != null && isOr.length != 0) {
			if (isOr[0]) {
				query = query.replaceAll(" and ", " or ");
			}
		}
		return query;

	}

	public <T> List<T> findListByQuery(final Class<T> entityClass, final String queryString, final Object... values) {
		Query query = entityManager.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i + 1, values[i]);
			}

		}
		return query.getResultList();

	}

	/**
	 * 
	 * @param entityclass
	 * @param queryString
	 * @param start
	 * @param maxRows
	 * @param values
	 * @return
	 */
	public <T> List<T> findListByQuery(final Class<T> entityclass, final String queryString, final int start,
			final int maxRows, final Object... values) {
		Query query = entityManager.createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
		if (maxRows >= 0) {
			query.setMaxResults(maxRows);
		}
		if (start >= 0) {
			query.setFirstResult(start);
		}
		return query.getResultList();
	}

	@Override
	public void batchInsert(List<?> entityList) {
		if (entityList != null) {
			int index = 1;
			for (Object obj : entityList) {
				entityManager.persist(obj);
				// 整体提交一次
				if (index % BATCH_INTERVAL == 0) {
					entityManager.flush();
					entityManager.clear();
				}

			}

		}

	}

	@Override
	public void batchUpdate(List<?> entityList) {
		if (entityList != null) {
			int index = 1;
			for (Object obj : entityList) {
				entityManager.merge(obj);
				// 整体提交一次
				if (index % BATCH_INTERVAL == 0) {
					entityManager.flush();
					entityManager.clear();
				}

			}

		}

	}

	@Override
	public void batchDelete(List<?> entityList) {
		if (entityList != null) {
			int index = 1;
			for (Object obj : entityList) {
				entityManager.remove(obj);
				// 整体提交一次
				if (index % BATCH_INTERVAL == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
		}
	}

	@Override
	public <T> List<T> findListByField(Class<T> entityClass) {
		// TODO Auto-generated method stub
		String queryString = produceQueryStringByNamedParams(entityClass, null, false).toString();
		Query query = entityManager.createQuery(queryString);
		return query.getResultList();
	}

	@Override
	public <T> List<T> findListByField(Class<T> entityClass, String sidx, String sord) {
		// TODO Auto-generated method stub
		String queryString = produceQueryStringByNamedParams(entityClass, null, sidx, sord, false).toString();
		Query query = entityManager.createQuery(queryString);
		return query.getResultList();
	}

}
