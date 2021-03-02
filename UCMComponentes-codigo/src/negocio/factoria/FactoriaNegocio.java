/**
 * 
 */
package negocio.factoria;

import negocio.cliente.SACliente;
import negocio.departamento.SADepartamento;
import negocio.empleado.SAEmpleado;
import negocio.factoria.imp.FactoriaNegocioImp;
import negocio.factura.SAFactura;
import negocio.inventario.SAInventario;
import negocio.material.SAMaterial;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instancia;

	public synchronized static FactoriaNegocio getInstancia() {
		if (instancia == null)
			instancia = new FactoriaNegocioImp();
		return instancia;
	}

	public abstract SAProveedor generaSAProveedor();

	public abstract SACliente generaSACliente();

	public abstract SAProducto generaSAProducto();

	public abstract SAFactura generaSAFactura();

	public abstract SAMaterial generaSAMaterial();

	public abstract SAInventario generaSAInventario();

	public abstract SADepartamento generaSADepartamento();

	public abstract SAEmpleado generaSAEmpleado();
}