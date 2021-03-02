package negocio.producto.imp;

import java.util.ArrayList;

import java.util.List;

import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.query.Query;
import integracion.query.QueryEnum;
import integracion.query.factoria.FactoriaQuery;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import negocio.proveedor.TProveedor;

public class SAProductoImp implements SAProducto {

	@Override
	public int altaProducto(TProducto tProducto) {
		int id = -1;
		if (tProducto != null) {
			DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
			DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
			Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
			if (transaction == null)
				transaction = TransactionManager.getInstancia().getTransaccion();

			transaction.start();
			/*Buscamos por ID por que si estamos reactivando solo se mete el ID al transfer
			 *  y si estamos creando un nuevo producto solo le metemos bien el nombre*/
			TProducto tLeido = daoProducto.readById(tProducto.getId());
			if (tLeido == null) {
				if (tProducto.getId() == -1) {
					// Comprobar que el proveedor que nos da el producto este activo y dado de alta en BBDD
					TProveedor p = daoProveedor.readById(tProducto.getIdProveedor());
					if (p != null && p.isActivo())
						id = daoProducto.create(tProducto);
				}

			} else {
				if (!tLeido.isActivo()) {
					// Comprobar que el proveedor que nos da el producto este activo y dado de alta en BBDD
					TProveedor p = daoProveedor.readById(tLeido.getIdProveedor());
					if (p != null && p.isActivo()) {
						id = tProducto.getId();
						tLeido.setActivo(true);
						daoProducto.update(tLeido);
					}
				}
			}
			if (id == -1)
				transaction.rollback();
			else
				transaction.commit();
		}
		return id;
	}

	@Override
	public boolean bajaProducto(int id) {
		boolean ok = false;
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		TProducto producto = daoProducto.readById(id);
		if (producto != null && producto.isActivo()) {
			ok = daoProducto.delete(id);
		}
		if (ok)
			transaction.commit();
		else
			transaction.rollback();
		return ok;
	}

	@Override
	public boolean modificarProducto(TProducto tProducto) {
		boolean ok = false;
		DAOProveedor daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		TProducto producto = daoProducto.readById(tProducto.getId());
		if (producto != null) {
			// Comprobar que el proveedor que nos da el producto este activo y dado de alta en BBDD
			TProveedor p = daoProveedor.readById(tProducto.getIdProveedor());
			if (p != null && p.isActivo())
				ok = daoProducto.update(tProducto);
		}
		if (ok)
			transaction.commit();
		else
			transaction.rollback();
		return ok;
	}

	@Override
	public TProducto mostrarProducto(int id) {
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		TProducto producto = daoProducto.readById(id);
		transaction.commit();
		return producto;
	}

	@Override
	public List<TProducto> listarProductos(boolean activo) {
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		List<TProducto> listaProductos = daoProducto.readAll();
		if (activo) {
			listaProductos = seleccionarActivos(listaProductos);
		}
		transaction.commit();
		return listaProductos;
	}

	private List<TProducto> seleccionarActivos(List<TProducto> listaProductos) {
		List<TProducto> activos = new ArrayList<TProducto>();
		for (TProducto c : listaProductos) {
			if (c.isActivo())
				activos.add(c);
		}
		return activos;
	}

	public int productosPorProveedor(int id) {
		int nProductos = 0;
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		nProductos = daoProducto.productosPorProveedor(id);
		transaction.commit();
		return nProductos;
	}

	@Override
	public TProducto productoMasVendido() {
		TProducto producto = null;
		Query query = FactoriaQuery.getInstancia().nuevaQuery(QueryEnum.ProductoMasVendido);
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();

		transaction.start();
		producto = (TProducto) query.execute(null);
		transaction.commit();
		return producto;
	}

}
