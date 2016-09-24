package xtqh.framework.base;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 */
public abstract class BaseService {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Object obj) {
		sessionFactory.getCurrentSession().save(obj);
	}

	public void saveList(List objList) {
		Session session = sessionFactory.getCurrentSession();
		for (Object obj : objList) {
			session.save(obj);
		}
	}

	public <T> List<T> getAll(Class<T> entityType) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(entityType);
		return (List<T>) criteria.list();
	}

	public <T> T get(Class<T> entityType, String uid) {
		return (T) sessionFactory.getCurrentSession().get(entityType, uid);

	}

	public void delete(Object obj) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(obj);
	}

	public void beginTransaction() {
		sessionFactory.getCurrentSession().beginTransaction();
	}

	public void commitTransaction() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

}
