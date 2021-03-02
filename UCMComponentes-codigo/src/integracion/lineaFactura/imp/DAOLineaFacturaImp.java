/**
 * 
 */
package integracion.lineaFactura.imp;

import integracion.lineaFactura.DAOLineaFactura;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.factura.TLineaFactura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOLineaFacturaImp implements DAOLineaFactura {

	public int create(TLineaFactura lineaFactura) {
		int id = -1;

		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();

			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO linea_factura (cantidad, precio_venta, id_factura, id_producto) VALUES (?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, lineaFactura.getCantidad());
			ps.setDouble(2, lineaFactura.getPrecio());
			ps.setInt(3, lineaFactura.getIdFactura());
			ps.setInt(4, lineaFactura.getIdProducto());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next())
				id = rs.getInt(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return id;
	}

	public boolean update(TLineaFactura lineaFactura) {
		boolean isUpdate = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE linea_factura SET cantidad = ?, precio_venta = ?, id_factura = ?, id_producto = ? WHERE id = ?");
			ps.setInt(1, lineaFactura.getCantidad());
			ps.setDouble(2, lineaFactura.getPrecio());
			ps.setInt(3, lineaFactura.getIdFactura());
			ps.setInt(4, lineaFactura.getIdProducto());
			ps.setInt(5, lineaFactura.getId());
			ps.executeUpdate();
			isUpdate = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isUpdate;
	}

	public List<TLineaFactura> readByIdFactura(int idFactura) {
		List<TLineaFactura> lista = new ArrayList<TLineaFactura>();
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT * FROM linea_factura WHERE id_factura = ? FOR UPDATE";
			PreparedStatement ps = conexion.prepareStatement(sentencia);
			ps.setInt(1, idFactura);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TLineaFactura lineaFactura;
				lineaFactura = new TLineaFactura(rs.getInt("id"), rs.getInt("cantidad"), rs.getDouble("precio_venta"),
						rs.getInt("id_factura"), rs.getInt("id_producto"));
				lista.add(lineaFactura);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

}