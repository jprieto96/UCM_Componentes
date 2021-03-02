/**
 * 
 */
package integracion.factoria.imp;

import integracion.cliente.DAOCliente;
import integracion.cliente.imp.DAOClienteImp;
import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.factura.imp.DAOFacturaImp;
import integracion.lineaFactura.DAOLineaFactura;
import integracion.lineaFactura.imp.DAOLineaFacturaImp;
import integracion.producto.DAOProducto;
import integracion.producto.imp.DAOProductoImp;
import integracion.proveedor.DAOProveedor;
import integracion.proveedor.imp.DAOProveedorImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	public DAOProveedor generaDAOProveedor() {
		return new DAOProveedorImp();
	}

	public DAOCliente generaDAOCliente() {
		return new DAOClienteImp();
	}

	public DAOProducto generaDAOProducto() {
		return new DAOProductoImp();
	}

	public DAOFactura generaDAOFactura() {
		return new DAOFacturaImp();
	}

	public DAOLineaFactura generaDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}
}