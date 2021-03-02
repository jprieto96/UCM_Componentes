/**
 * 
 */
package integracion.factoria;

import integracion.cliente.DAOCliente;
import integracion.factoria.imp.FactoriaIntegracionImp;
import integracion.factura.DAOFactura;
import integracion.lineaFactura.DAOLineaFactura;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instancia;

	public synchronized static FactoriaIntegracion getInstancia() {
		if (instancia == null)
			instancia = new FactoriaIntegracionImp();
		return instancia;
	}

	public abstract DAOProveedor generaDAOProveedor();

	public abstract DAOCliente generaDAOCliente();

	public abstract DAOProducto generaDAOProducto();

	public abstract DAOFactura generaDAOFactura();

	public abstract DAOLineaFactura generaDAOLineaFactura();
}