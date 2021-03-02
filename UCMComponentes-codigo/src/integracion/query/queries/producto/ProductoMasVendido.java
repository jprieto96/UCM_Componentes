/**
 * 
 */
package integracion.query.queries.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import integracion.query.Query;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;

import negocio.producto.TProducto;

public class ProductoMasVendido implements Query {

	public Object execute(Object param) {
		TProducto producto = null;
		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();
			String query = "SELECT * FROM producto WHERE id =(SELECT id_producto FROM linea_factura  GROUP BY id_producto ORDER BY sum(cantidad) DESC LIMIT 1) FOR UPDATE;";

			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				producto = new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getInt("existencias"), rs.getInt("id_proveedor"), rs.getBoolean("activo"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return producto;
	}

}