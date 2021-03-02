/**
 * 
 */
package integracion.query.queries.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import integracion.query.Query;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;

public class ClienteQueMasGasta implements Query {

	public Object execute(Object param) {
		TCliente cliente = null;
		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentenciaPar = "SELECT c.*, p.id AS id_particular, p.fiel " + "FROM cliente AS c "
					+ "JOIN particular AS p ON c.id = p.id_cliente "
					+ "WHERE c.id = (SELECT id_cliente FROM factura GROUP BY id_cliente ORDER BY sum(precio_total) DESC LIMIT 1) GROUP BY c.id FOR UPDATE";
			String sentenciaEmp = "SELECT c.*, e.id AS id_empresa, e.direccion_social " + "FROM cliente AS c "
					+ "JOIN empresa AS e ON c.id = e.id_cliente "
					+ "WHERE c.id = (SELECT id_cliente FROM factura GROUP BY id_cliente ORDER BY sum(precio_total) DESC LIMIT 1) GROUP BY c.id FOR UPDATE";

			PreparedStatement psPar = conexion.prepareStatement(sentenciaPar);
			PreparedStatement psEmp = conexion.prepareStatement(sentenciaEmp);

			ResultSet rsPar = psPar.executeQuery();
			while (rsPar.next()) {
				cliente = new TParticular(rsPar.getInt("id"), rsPar.getString("nombre"),
						rsPar.getString("cuenta_bancaria"), rsPar.getBoolean("activo"), rsPar.getString("nif"),
						rsPar.getInt("id_particular"), rsPar.getBoolean("fiel"));
			}

			ResultSet rsEmp = psEmp.executeQuery();
			while (rsEmp.next()) {
				cliente = new TEmpresa(rsEmp.getInt("id"), rsEmp.getString("nombre"),
						rsEmp.getString("cuenta_bancaria"), rsEmp.getBoolean("activo"), rsEmp.getString("nif"),
						rsEmp.getInt("id_empresa"), rsEmp.getString("direccion_social"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return cliente;
	}

}