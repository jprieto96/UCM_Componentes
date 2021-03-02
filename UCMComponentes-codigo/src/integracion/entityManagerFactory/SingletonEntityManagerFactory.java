package integracion.entityManagerFactory;

import javax.persistence.EntityManagerFactory;

import integracion.entityManagerFactory.imp.SingletonEntityManagerFactoryImp;

public abstract class SingletonEntityManagerFactory {

	private static SingletonEntityManagerFactory instancia;

	public synchronized static SingletonEntityManagerFactory getInstancia() {
		if (instancia == null)
			instancia = new SingletonEntityManagerFactoryImp();
		return instancia;
	}

	public abstract EntityManagerFactory getEntityManagerFactory();
}
