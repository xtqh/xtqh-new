package xtqh.framework.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class HibernateUtil {

	private EntityManagerFactory emf;

	public EntityManager getEntityManager(String persistenceUnitName) {

		return getEntityManagerFactory(persistenceUnitName).createEntityManager();
	}

	private EntityManagerFactory getEntityManagerFactory(String persistenceUnitName) {
		if (emf == null) {
			Bundle thisBundle = FrameworkUtil.getBundle(HibernateUtil.class);
			BundleContext context = thisBundle.getBundleContext();

			ServiceReference<?> serviceReference = context.getServiceReference(PersistenceProvider.class.getName());
			PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService(serviceReference);

			emf = persistenceProvider.createEntityManagerFactory(persistenceUnitName, null);
		}
		return emf;
	}
}
