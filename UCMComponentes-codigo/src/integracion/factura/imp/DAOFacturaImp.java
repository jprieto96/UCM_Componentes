package integracion.factura.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import integracion.factura.DAOFactura;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.factura.TFactura;

public class DAOFacturaImp implements DAOFactura {

	public int create(TFactura factura) {
		int id = -1;

		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();

			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO factura (fecha, precio_total, id_cliente) VALUES (?, ?, ?)", //TODO añadir aqui el activo??
					java.sql.Statement.RETURN_GENERATED_KEYS);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Calendar cal = Calendar.getInstance();
			//System.out.println(dateFormat.format(cal.getTime()));
			ps.setNString(1, dateFormat.format(cal.getTime()));
			ps.setFloat(2, factura.getTotal());
			ps.setInt(3, factura.getIdCliente());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next())
				id = rs.getInt(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return id;
	}

	public TFactura readById(int id) {
		TFactura factura = null;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT * FROM factura AS f WHERE f.id = ? FOR UPDATE";

			PreparedStatement ps = conexion.prepareStatement(sentencia);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				factura = new TFactura(rs.getInt("id"), rs.getString("fecha"), rs.getFloat("precio_total"),
						rs.getInt("id_cliente"), rs.getBoolean("activo"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return factura;
	}

	public List<TFactura> readAll() {
		List<TFactura> lista = new ArrayList<TFactura>();
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT * FROM factura";

			PreparedStatement ps = conexion.prepareStatement(sentencia);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TFactura factura;
				factura = new TFactura(rs.getInt("id"), rs.getString("fecha"), rs.getFloat("precio_total"),
						rs.getInt("id_cliente"), rs.getBoolean("activo"));
				lista.add(factura);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public boolean update(TFactura tfactura) {
		boolean isUpdate = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE factura SET fecha = ?, precio_total = ?, id_cliente = ?, activo = ? WHERE id = ?");
			ps.setString(1, tfactura.getFecha());
			ps.setFloat(2, tfactura.getTotal());
			ps.setInt(3, tfactura.getIdCliente());
			ps.setBoolean(4, tfactura.isActivo());
			ps.setInt(5, tfactura.getId());
			ps.executeUpdate();
			isUpdate = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isUpdate;
	}

	public List<TFactura> readByCliente(int cliente) {
		List<TFactura> lista = new ArrayList<TFactura>();
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentencia = "SELECT f.id, f.fecha, f.precio_total, f.id_cliente, f.activo FROM factura AS f WHERE id_cliente = ?";
			PreparedStatement ps = conexion.prepareStatement(sentencia);
			ps.setInt(1, cliente);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TFactura factura;
				factura = new TFactura(rs.getInt("id"), rs.getString("fecha"), rs.getFloat("precio_total"),
						rs.getInt("id_cliente"), rs.getBoolean("activo"));
				lista.add(factura);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
}
