package integracion.producto.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.producto.DAOProducto;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.producto.TProducto;

public class DAOProductoImp implements DAOProducto {

	public int create(TProducto producto) {
		int idProducto = -1;
		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO producto (nombre, precio, existencias, id_proveedor) VALUES (?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setInt(3, producto.getExistencias());
			ps.setInt(4, producto.getIdProveedor());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next())
				idProducto = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			idProducto = -1;
		}
		return idProducto;
	}

	public boolean delete(int id) {
		boolean baja = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement("UPDATE producto SET activo = 0 WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			baja = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return baja;
	}

	public TProducto readById(int id) {
		TProducto producto = null;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT * FROM producto AS p WHERE p.id = ? FOR UPDATE";

			PreparedStatement ps = conexion.prepareStatement(sentencia);
			ps.setInt(1, id);

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

	public List<TProducto> readAll() {
		List<TProducto> listaProductos = new ArrayList<TProducto>();
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT p.id, p.nombre, p.precio, p.existencias, p.activo, p.id_proveedor FROM producto AS p";

			PreparedStatement ps = conexion.prepareStatement(sentencia);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TProducto producto;
				producto = new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getInt("existencias"), rs.getInt("id_proveedor"), rs.getBoolean("activo"));
				listaProductos.add(producto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listaProductos;
	}

	public boolean update(TProducto tProducto) {
		boolean isUpdate = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE producto SET nombre = ?, precio = ?, existencias = ?, id_proveedor = ?, activo = ? WHERE id = ?");
			ps.setString(1, tProducto.getNombre());
			ps.setDouble(2, tProducto.getPrecio());
			ps.setInt(3, tProducto.getExistencias());
			ps.setInt(4, tProducto.getIdProveedor());
			ps.setBoolean(5, tProducto.isActivo());
			ps.setInt(6, tProducto.getId());
			ps.executeUpdate();
			isUpdate = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isUpdate;
	}

	@Override
	public int productosPorProveedor(int id) {
		int nProductos = 0;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT count(*) as nPro FROM producto AS p WHERE p.id_proveedor = ? AND p.activo = true";

			PreparedStatement ps = conexion.prepareStatement(sentencia);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nProductos = rs.getInt("nPro");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return nProductos;
	}
}
