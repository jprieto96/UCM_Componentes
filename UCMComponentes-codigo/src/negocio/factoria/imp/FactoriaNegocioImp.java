/**
 * 
 */
package negocio.factoria.imp;

import negocio.cliente.SACliente;
import negocio.cliente.imp.SAClienteImp;
import negocio.departamento.SADepartamento;
import negocio.departamento.imp.SADepartamentoImp;
import negocio.empleado.SAEmpleado;
import negocio.empleado.imp.SAEmpleadoImp;
import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.imp.SAFacturaImp;
import negocio.inventario.SAInventario;
import negocio.inventario.imp.SAInventarioImp;
import negocio.material.SAMaterial;
import negocio.material.imp.SAMaterialImp;
import negocio.producto.SAProducto;
import negocio.producto.imp.SAProductoImp;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.imp.SAProveedorImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	public SAProveedor generaSAProveedor() {
		return new SAProveedorImp();
	}

	public SACliente generaSACliente() {
		return new SAClienteImp();
	}

	public SAProducto generaSAProducto() {
		return new SAProductoImp();
	}

	public SAFactura generaSAFactura() {
		return new SAFacturaImp();
	}

	@Override
	public SAMaterial generaSAMaterial() {
		return new SAMaterialImp();
	}

	public SAInventario generaSAInventario() {
		return new SAInventarioImp();
	}

	public SADepartamento generaSADepartamento() {
		return new SADepartamentoImp();
	}

	@Override
	public SAEmpleado generaSAEmpleado() {
		return new SAEmpleadoImp();
	}
}