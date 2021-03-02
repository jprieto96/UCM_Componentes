/**
 * 
 */
package integracion.transactions.manager;

import integracion.transactions.Transaction;
import integracion.transactions.manager.imp.TransactionManagerImp;

public abstract class TransactionManager {

	private static TransactionManager instancia;

	public synchronized static TransactionManager getInstancia() {
		if (instancia == null)
			instancia = new TransactionManagerImp();
		return instancia;
	}

	public abstract Transaction nuevaTransaccion();

	public abstract Transaction getTransaccion();

	public abstract boolean eliminarTransaccion();
}