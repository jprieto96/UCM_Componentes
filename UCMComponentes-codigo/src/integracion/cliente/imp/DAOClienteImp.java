
package integracion.cliente.imp;

import integracion.cliente.DAOCliente;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import negocio.cliente.TCliente;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;

public class DAOClienteImp implements DAOCliente {

	@SuppressWarnings("resource")
	public int create(TCliente cliente) {
		int id = -1, idCliente = -1;
		try {
			Transaction t = TransactionManager.getInstancia().getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO cliente (nombre, nif, cuenta_bancaria) VALUES (?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getNIF());
			ps.setString(3, cliente.getCuentaBancaria());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()) {
				idCliente = rs.getInt(1);
			}

			if (cliente instanceof TParticular) {
				ps = conexion.prepareStatement("INSERT INTO particular (fiel, id_cliente) VALUES (?, ?)",
						java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setBoolean(1, ((TParticular) cliente).isFiel());
				ps.setInt(2, idCliente);
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();

				while (rs.next()) {
					id = rs.getInt(1);
				}
			} else {
				ps = conexion.prepareStatement("INSERT INTO empresa (direccion_social, id_cliente) VALUES (?, ?)",
						java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, ((TEmpresa) cliente).getDireccionSocial());
				ps.setInt(2, idCliente);
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();

				while (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			idCliente = -1;
			id = -1;
		}

		return idCliente;
	}

	public boolean delete(int id) {
		boolean baja = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();
			PreparedStatement ps = conexion.prepareStatement("UPDATE cliente SET activo = 0 WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			baja = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return baja;
	}

	public TCliente readById(int id) {
		TCliente cliente = null;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentenciaPar = "SELECT c.*, p.id AS id_particular, p.fiel " + "FROM cliente AS c "
					+ "JOIN particular AS p ON c.id = p.id_cliente " + "WHERE c.id = ? GROUP BY c.id FOR UPDATE";
			String sentenciaEmp = "SELECT c.*, e.id AS id_empresa, e.direccion_social " + "FROM cliente AS c "
					+ "JOIN empresa AS e ON c.id = e.id_cliente " + "WHERE c.id = ? GROUP BY c.id FOR UPDATE";

			PreparedStatement psPar = conexion.prepareStatement(sentenciaPar);
			PreparedStatement psEmp = conexion.prepareStatement(sentenciaEmp);
			psPar.setInt(1, id);
			psEmp.setInt(1, id);

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

	public List<TCliente> readAll() {
		List<TCliente> listaClientes = new ArrayList<TCliente>();
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			//Sentenca para sacar los clientes particulares
			String sentenciaPar = "SELECT c.*, p.id AS id_particular, p.fiel FROM cliente AS c JOIN particular AS p ON c.id = p.id_cliente";
			//Sentencia para sacar los clientes de empresa
			String sentenciaEmp = "SELECT c.*, e.id AS id_empresa, e.direccion_social FROM cliente AS c JOIN empresa AS e ON c.id = e.id_cliente";

			PreparedStatement psPar = conexion.prepareStatement(sentenciaPar);
			PreparedStatement psEmp = conexion.prepareStatement(sentenciaEmp);

			ResultSet rsPar = psPar.executeQuery();
			while (rsPar.next()) {
				TCliente cliente;
				cliente = new TParticular(rsPar.getInt("id"), rsPar.getString("nombre"),
						rsPar.getString("cuenta_bancaria"), rsPar.getBoolean("activo"), rsPar.getString("nif"),
						rsPar.getInt("id_particular"), rsPar.getBoolean("fiel"));
				listaClientes.add(cliente);
			}

			ResultSet rsEmp = psEmp.executeQuery();
			while (rsEmp.next()) {
				TCliente cliente;
				cliente = new TEmpresa(rsEmp.getInt("id"), rsEmp.getString("nombre"),
						rsEmp.getString("cuenta_bancaria"), rsEmp.getBoolean("activo"), rsEmp.getString("nif"),
						rsEmp.getInt("id_empresa"), rsEmp.getString("direccion_social"));
				listaClientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		listaClientes.sort(new Comparator<TCliente>() {
			@Override
			public int compare(TCliente lhs, TCliente rhs) {
				return lhs.getId() == rhs.getId() ? 0 : lhs.getId() < rhs.getId() ? -1 : 1;
			}
		});

		return listaClientes;
	}

	@SuppressWarnings("resource")
	public boolean update(TCliente tCliente) {
		boolean isUpdate = false;
		try {
			TransactionManager tm = TransactionManager.getInstancia();
			Transaction t = tm.getTransaccion();
			Connection conexion = (Connection) t.getResource();

			String sentenciaCli = "UPDATE cliente SET nombre = ?, cuenta_bancaria = ?, nif = ?, activo = ? WHERE id = ?";
			String sentenciaEmp = "UPDATE empresa SET direccion_social = ? WHERE id_cliente = ?";
			String sentenciaPar = "UPDATE particular SET fiel = ? WHERE id_cliente = ?";

			PreparedStatement ps = conexion.prepareStatement(sentenciaCli);

			ps.setString(1, tCliente.getNombre());
			ps.setString(2, tCliente.getCuentaBancaria());
			ps.setString(3, tCliente.getNIF());
			ps.setBoolean(4, tCliente.isActivo());
			ps.setInt(5, tCliente.getId());

			ps.executeUpdate();

			if (tCliente instanceof TParticular) {//Se modifica la tabla particular
				ps = conexion.prepareStatement(sentenciaPar);
				ps.setBoolean(1, ((TParticular) tCliente).isFiel());
				ps.setInt(2, tCliente.getId());
				int cols = ps.executeUpdate();
				if (cols > 0)
					isUpdate = true;
			} else { //Se modifica la tabla empresa
				ps = conexion.prepareStatement(sentenciaEmp);
				ps.setString(1, ((TEmpresa) tCliente).getDireccionSocial());
				ps.setInt(2, tCliente.getId());
				int cols = ps.executeUpdate();
				if (cols > 0)
					isUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isUpdate;
	}

}