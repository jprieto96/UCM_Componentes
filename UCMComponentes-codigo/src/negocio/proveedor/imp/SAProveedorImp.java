/**
 * 
 */
package negocio.proveedor.imp;

import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;

import java.util.ArrayList;
import java.util.List;

import integracion.factoria.FactoriaIntegracion;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;

public class SAProveedorImp implements SAProveedor {

	public int altaProveedor(TProveedor proveedor) {
		int id = -1;
		if (proveedor != null) {
			DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
			Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
			if (transaction == null)
				transaction = TransactionManager.getInstancia().getTransaccion();

			transaction.start();

			/*Buscamos por ID por que si estamos reactivando solo se mete el ID al transfer
			 *  y si estamos creando un nuevo proveedor solo le metemos bien el nombre*/
			TProveedor pLeido = daoProveedor.readById(proveedor.getId());
			if (pLeido == null) {
				if (proveedor.getId() == -1)
					id = daoProveedor.create(proveedor);
			} else {
				if (!pLeido.isActivo()) {
					id = proveedor.getId();
					pLeido.setActivo(true);
					daoProveedor.update(pLeido);
				}
			}
			if (id == -1)
				transaction.rollback();
			else
				transaction.commit();
		}
		return id;
	}

	public boolean bajaProveedor(int id) {
		boolean ok = false;
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		TProveedor proveedor = daoProveedor.readById(id);
		if (proveedor != null && proveedor.isActivo()) {
			// Restriccion que comprueba que no se pueden dar de baja proveedores con productos activos
			if (daoProducto.productosPorProveedor(id) == 0)
				ok = daoProveedor.delete(id);
		}
		if (ok)
			transaction.commit();
		else
			transaction.rollback();
		return ok;
	}

	public boolean modificarProveedor(TProveedor proveedor) {
		boolean ok = false;
		if (proveedor != null) {
			DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
			Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
			if (transaction == null)
				transaction = TransactionManager.getInstancia().getTransaccion();

			transaction.start();
			TProveedor exists = daoProveedor.readById(proveedor.getId());
			if (exists != null) {
				ok = daoProveedor.update(proveedor);
			}
			if (ok)
				transaction.commit();
			else
				transaction.rollback();
		}
		return ok;
	}

	public TProveedor mostrarProveedor(int id) {
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		TProveedor proveedor = daoProveedor.readById(id);
		transaction.commit();
		return proveedor;
	}

	public List<TProveedor> listarProveedores(boolean activo) {
		List<TProveedor> lista = null;
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		lista = daoProveedor.readAll();
		if (activo) {
			lista = seleccionarActivos(lista); // recuperar solo los activos
		}
		transaction.commit();
		return lista;
	}

	private List<TProveedor> seleccionarActivos(List<TProveedor> proveedores) {
		List<TProveedor> activos = new ArrayList<TProveedor>();
		for (TProveedor p : proveedores) {
			if (p.isActivo())
				activos.add(p);
		}
		return activos;
	}

}