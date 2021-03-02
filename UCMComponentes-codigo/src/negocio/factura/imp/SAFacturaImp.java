package negocio.factura.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.lineaFactura.DAOLineaFactura;
import integracion.producto.DAOProducto;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.TCliente;
import negocio.factura.*;
import negocio.producto.TProducto;

public class SAFacturaImp implements SAFactura {

	public int altaFactura(TFactura factura) {
		int id = -1;
		if (factura != null) {
			DAOFactura daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
			DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
			DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
			DAOLineaFactura daoLineaFactura = FactoriaIntegracion.getInstancia().generaDAOLineaFactura();
			Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
			if (transaction == null)
				transaction = TransactionManager.getInstancia().getTransaccion();
			transaction.start();

			//logica de negocio
			TFactura auxf = daoFactura.readById(factura.getId());

			if (auxf == null) {
				if (factura.getId() == -1) {
					//Calculamos el precio Total
					double precio = calculoPrecio(factura.getMapaProductos());
					factura.setTotal((float) precio);
					boolean disponibilidad = true;

					//Se comprueba que existe el cliente y este activo
					TCliente auxc = daoCliente.readById(factura.getIdCliente());
					if (auxc != null) {
						if (auxc.isActivo()) {
							//Que los productos estan disponibles y tiene existencias suficiente
							HashMap<Integer, Integer> pedido = factura.getMapaProductos();
							for (int idProducto : factura.getMapaProductos().keySet()) {
								TProducto p = daoProducto.readById(idProducto);
								if (p.isActivo()) {
									if (p.getExistencias() < pedido.get(idProducto)) {
										disponibilidad = false;
									}
								} else {
									disponibilidad = false;
								}
							}
							if (disponibilidad) {

								//Modificamos las existencias de los productos
								for (int idProducto : factura.getMapaProductos().keySet()) {
									TProducto p = daoProducto.readById(idProducto);
									if (p.getExistencias() >= pedido.get(idProducto)) {
										p.setExistencias(p.getExistencias() - pedido.get(idProducto));
										daoProducto.update(p);
									}
								}

								id = daoFactura.create(factura);
								//Creamos las lineas factura
								for (int idProducto : factura.getMapaProductos().keySet()) {
									TProducto p = daoProducto.readById(idProducto);
									int cantidad = factura.getMapaProductos().get(idProducto);
									double precioproducto = p.getPrecio() * cantidad;
									TLineaFactura lf = new TLineaFactura(-1, cantidad, precioproducto, id, idProducto);
									daoLineaFactura.create(lf);
								}

							}
						}
					}
				}
			}

			if (id == -1)
				transaction.rollback();
			else {

				transaction.commit();

			}
		}

		return id;
	}

	public TFactura mostrarFactura(int id) {
		TFactura factura = null;

		DAOFactura daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		DAOLineaFactura daoLineaFactura = FactoriaIntegracion.getInstancia().generaDAOLineaFactura();

		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start();

		List<TLineaFactura> lineasFactura = new ArrayList<>();
		factura = daoFactura.readById(id);
		if (factura != null) {
			lineasFactura = daoLineaFactura.readByIdFactura(factura.getId());
			factura.setLineasFactura(lineasFactura);
		}

		transaction.commit();
		return factura;
	}

	public List<TFactura> listarFactura(boolean activo) {
		DAOFactura daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start();

		List<TFactura> lista = daoFactura.readAll();
		if (activo)
			lista = seleccionarActivos(lista);

		transaction.commit();
		return lista;
	}

	private List<TFactura> seleccionarActivos(List<TFactura> lista) {
		List<TFactura> activos = new ArrayList<TFactura>();
		for (TFactura aux : lista) {
			if (aux.isActivo())
				activos.add(aux);
		}
		return activos;
	}

	public List<TFactura> mostrarFacturaPorCliente(int idCliente) {
		DAOFactura daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		DAOLineaFactura daoLineaFactura = FactoriaIntegracion.getInstancia().generaDAOLineaFactura();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start();

		List<TFactura> lista = daoFactura.readByCliente(idCliente);

		for (TFactura factura : lista) {
			List<TLineaFactura> lineasFactura = daoLineaFactura.readByIdFactura(factura.getId());
			factura.setLineasFactura(lineasFactura);
		}

		transaction.commit();
		return lista;
	}

	private double calculoPrecio(HashMap<Integer, Integer> mapaProductos) {
		double precioTotal = 0;
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		for (int i : mapaProductos.keySet()) {
			int id = i;
			TProducto p = daoProducto.readById(id);
			double pr = p.getPrecio() * mapaProductos.get(id);
			precioTotal += pr;
		}
		return precioTotal;
	}

	public boolean devolverProducto(TFactura factura) {
		boolean encontrado = false;
		DAOFactura daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		DAOProducto daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		DAOLineaFactura daoLineaFactura = FactoriaIntegracion.getInstancia().generaDAOLineaFactura();
		Transaction transaction = TransactionManager.getInstancia().nuevaTransaccion();
		if (transaction == null)
			transaction = TransactionManager.getInstancia().getTransaccion();
		transaction.start();
		TFactura f = daoFactura.readById(factura.getId());
		if (f != null) {
			List<TLineaFactura> lineasFactura = daoLineaFactura.readByIdFactura(factura.getId());
			if (!lineasFactura.isEmpty()) {
				int i = 0;
				while (i < lineasFactura.size() && !encontrado) {
					if (lineasFactura.get(i).getIdProducto() == factura.getIdProducto()) {
						//modificar existencias
						if (factura.getCantidad() <= lineasFactura.get(i).getCantidad()) {

							TProducto p = daoProducto.readById(factura.getIdProducto());
							p.setExistencias(p.getExistencias() + factura.getCantidad());
							daoProducto.update(p);
							//modificamos la linea factura
							int nuevaCantidad = lineasFactura.get(i).getCantidad() - factura.getCantidad();
							lineasFactura.get(i).setCantidad(nuevaCantidad);
							double nuevoPrecioLF = lineasFactura.get(i).getPrecio()
									- (factura.getCantidad() * p.getPrecio());
							lineasFactura.get(i).setPrecio((float) nuevoPrecioLF);
							daoLineaFactura.update(lineasFactura.get(i));
							//modificamos precio factura
							double descuento = p.getPrecio() * factura.getCantidad();
							double nuevoPrecio = f.getTotal() - descuento;
							f.setTotal((float) nuevoPrecio);
							daoFactura.update(f);
							encontrado = true;
						}
					}
					i++;
				}

			}
		}
		if (encontrado)
			transaction.commit();
		else
			transaction.rollback();
		return encontrado;

	}

}
