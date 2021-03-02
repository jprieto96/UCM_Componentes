package integracion.entityManagerFactory.imp;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import integracion.entityManagerFactory.SingletonEntityManagerFactory;

public class SingletonEntityManagerFactoryImp extends SingletonEntityManagerFactory {

	private static EntityManagerFactory emf;

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("UCMComponentes-codigo");
		else if (!emf.isOpen())
			emf = Persistence.createEntityManagerFactory("UCMComponentes-codigo");
		return emf;
	}

	public void finalize() {
		emf.close();
	}

}
