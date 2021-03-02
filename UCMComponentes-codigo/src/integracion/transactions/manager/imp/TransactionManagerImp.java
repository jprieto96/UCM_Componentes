/**
 * 
 */
package integracion.transactions.manager.imp;

import integracion.transactions.Transaction;
import integracion.transactions.factoria.TransactionFactory;
import integracion.transactions.manager.TransactionManager;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	private ConcurrentHashMap<Thread, Transaction> transacciones;

	public TransactionManagerImp() {
		transacciones = new ConcurrentHashMap<>();
	}

	public Transaction nuevaTransaccion() {
		Transaction transaction = transacciones.get(Thread.currentThread());
		if (transaction == null) {
			transaction = TransactionFactory.getInstancia().generaTransaction();
			transacciones.put(Thread.currentThread(), transaction);
		}
		return transaction;
	}

	public Transaction getTransaccion() {
		return transacciones.get(Thread.currentThread());
	}

	public boolean eliminarTransaccion() {
		try {
			transacciones.remove(Thread.currentThread());
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}
}