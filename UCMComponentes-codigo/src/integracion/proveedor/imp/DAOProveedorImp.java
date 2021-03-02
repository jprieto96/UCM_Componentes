/**
 * 
 */
package integracion.proveedor.imp;

import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.TProveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DAOProveedorImp implements DAOProveedor {

	public int create(TProveedor proveedor) {
		int id = -1;
		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO proveedor (nombre) VALUES (?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, proveedor.getNombre());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			id = -1;
		}
		return id;
	}

	public boolean delete(int id) {
		boolean baja = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement("UPDATE proveedor SET activo = 0 WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			baja = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return baja;
	}

	public TProveedor readById(int id) {
		TProveedor proveedor = null;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM proveedor WHERE id = ? FOR UPDATE");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				proveedor = new TProveedor(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return proveedor;
	}

	public List<TProveedor> readAll() {
		List<TProveedor> lista = new Vector<TProveedor>();
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM proveedor");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TProveedor proveedor = new TProveedor(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
				lista.add(proveedor);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return lista;

	}

	public boolean update(TProveedor tProveedor) {
		boolean isUpdate = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion
					.prepareStatement("UPDATE proveedor SET nombre = ?, activo = ? WHERE id = ?");
			ps.setString(1, tProveedor.getNombre());
			ps.setBoolean(2, tProveedor.isActivo());
			ps.setInt(3, tProveedor.getId());
			ps.executeUpdate();
			isUpdate = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isUpdate;
	}

}
