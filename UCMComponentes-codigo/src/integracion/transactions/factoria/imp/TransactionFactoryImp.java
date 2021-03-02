/**
 * 
 */
package integracion.transactions.factoria.imp;

import integracion.transactions.Transaction;
import integracion.transactions.TransactionMySQL;
import integracion.transactions.factoria.TransactionFactory;

public class TransactionFactoryImp extends TransactionFactory {

	public Transaction generaTransaction() {
		return new TransactionMySQL();
	}
}