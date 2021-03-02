/**
 * 
 */
package integracion.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import integracion.transactions.manager.TransactionManager;

public class TransactionMySQL implements Transaction {

	private Connection connection;
	public static final String url = "jdbc:mysql://localhost:3306/ucmcomponentes";
	public static final String user = "root";
	public static final String password = "root";
	//	public static final String password = "1234";

	public void start() {

		try {
			this.connection = DriverManager.getConnection(url, user, password);
			//	this.connection = DriverManager.getConnection(url + "?serverTimezone=UTC" + "&useSSL=false", user, password); //Lo uso yo (pablo) que sino no me funciona, no me lo borreis
			this.connection.setAutoCommit(false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void commit() {
		try {
			if (connection != null) {
				this.connection.commit();
				this.connection.close();
			}
			TransactionManager.getInstancia().eliminarTransaccion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			if (connection != null) {
				this.connection.rollback();
				this.connection.close();
			}
			TransactionManager.getInstancia().eliminarTransaccion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Object getResource() {
		return connection;
	}
}