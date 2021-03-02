/**
 * 
 */
package negocio.cliente.imp;

import negocio.cliente.ClienteTOA;
import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;

import java.util.ArrayList;
import java.util.List;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.query.Query;
import integracion.query.QueryEnum;
import integracion.query.factoria.FactoriaQuery;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;

public class SAClienteImp implements SACliente {

	public int altaCliente(TCliente tCliente) {
		int id = -1;
		if (tCliente != null) {
			DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
			Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
			if (transaction == null)
				transaction = TransactionManager.getInstancia().getTransaccion();

			transaction.start();

			/*Buscamos por ID por que si estamos reactivando solo se mete el ID al transfer
			 *  y si estamos creando un nuevo cliente solo le metemos bien el nombre*/
			TCliente tLeido = daoCliente.readById(tCliente.getId());
			if (tLeido == null) {
				if (tCliente.getId() == -1)
					id = daoCliente.create(tCliente);
			} else {
				if (!tLeido.isActivo()) {
					id = tCliente.getId();
					tLeido.setActivo(true);
					daoCliente.update(tLeido);
				}
			}
			if (id == -1)
				transaction.rollback();
			else
				transaction.commit();
		}
		return id;
	}

	public boolean bajaCliente(int id) {
		boolean ok = false;
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		TCliente cliente = daoCliente.readById(id);
		if (cliente != null && cliente.isActivo()) {
			ok = daoCliente.delete(id);
		}
		if (ok)
			transaction.commit();
		else
			transaction.rollback();
		return ok;
	}

	public boolean modificarCliente(TCliente tCliente) {
		boolean ok = false;
		if (tCliente != null) {
			DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
			Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
			if (transaction == null)
				transaction = TransactionManager.getInstancia().getTransaccion();

			transaction.start();
			TCliente exists = daoCliente.readById(tCliente.getId());
			if (exists != null) {
				ok = daoCliente.update(tCliente);
			}
			if (ok)
				transaction.commit();
			else
				transaction.rollback();
		}
		return ok;
	}

	public TDatosCompletosCliente mostrarCliente(int id) {
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		ClienteTOA clienteTOA = new ClienteTOA();
		TDatosCompletosCliente c = clienteTOA.datosCompletosCliente(id);
		transaction.commit();
		return c;
	}

	public List<TCliente> listarClientes(boolean activo) {
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		List<TCliente> listaClientes = daoCliente.readAll();
		if (activo) {
			listaClientes = seleccionarActivos(listaClientes);
		}
		transaction.commit();
		return listaClientes;
	}

	private List<TCliente> seleccionarActivos(List<TCliente> listaClientes) {
		List<TCliente> activos = new ArrayList<TCliente>();
		for (TCliente c : listaClientes) {
			if (c.isActivo())
				activos.add(c);
		}
		return activos;
	}

	public TCliente clienteQueMasGasta() {
		TCliente cliente = null;
		Query query = FactoriaQuery.getInstancia().nuevaQuery(QueryEnum.ClienteQueMasGasta);
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		cliente = (TCliente) query.execute(cliente);
		transaction.commit();
		return cliente;
	}

}