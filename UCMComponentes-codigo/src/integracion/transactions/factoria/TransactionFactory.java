/**
 * 
 */
package integracion.transactions.factoria;

import integracion.transactions.Transaction;
import integracion.transactions.factoria.imp.TransactionFactoryImp;

public abstract class TransactionFactory {

	private static TransactionFactory instancia;

	public synchronized static TransactionFactory getInstancia() {
		if (instancia == null)
			instancia = new TransactionFactoryImp();
		return instancia;
	}

	public abstract Transaction generaTransaction();
}