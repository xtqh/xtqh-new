package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

	@PersistenceContext
	protected EntityManager entityManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager entityManager = emf.createEntityManager();

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-Hibernate.xml", "SpringMVC-Dispatcher.xml" });

		// LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean)
		// context.getBean("sessionFactory");

		// Person person = new Person();
		// testMain.entityManager.persist(person);
		//
		// Phone phone = new Phone("123-456-7890");
		// phone.setPerson(person);
		// testMain.entityManager.persist(phone);
		//
		// testMain.entityManager.flush();
		//
		// phone.setPerson(null);

	}

}
