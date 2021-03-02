/**
 * 
 */
package presentacion.command;

import presentacion.command.cliente.CommandAltaCliente;
import presentacion.command.cliente.CommandBajaCliente;
import presentacion.command.cliente.CommandBuscarParaModificarCliente;
import presentacion.command.cliente.CommandListarClientes;
import presentacion.command.cliente.CommandModificarCliente;
import presentacion.command.cliente.CommandMostrarCliente;
import presentacion.command.cliente.CommandMostrarClienteQueMasGasta;
import presentacion.command.departamento.CommandAltaDepartamento;
import presentacion.command.departamento.CommandBajaDepartamento;
import presentacion.command.departamento.CommandBuscarParaModificarDepartamento;
import presentacion.command.departamento.CommandListarDepartamentos;
import presentacion.command.departamento.CommandModificarDepartamento;
import presentacion.command.departamento.CommandMostrarDepartamento;
import presentacion.command.departamento.CommandNominaDepartamento;
import presentacion.command.empleado.CommandAltaEmpleado;
import presentacion.command.empleado.CommandBajaEmpleado;
import presentacion.command.empleado.CommandBuscarParaModificarEmpleado;
import presentacion.command.empleado.CommandListarEmpleados;
import presentacion.command.empleado.CommandModificarEmpleado;
import presentacion.command.empleado.CommandMostrarEmpleado;
import presentacion.command.empleado.CommandMostrarEmpleadosPorDepartamento;
import presentacion.command.factura.CommandAbrirFactura;
import presentacion.command.factura.CommandAddProductoFactura;
import presentacion.command.factura.CommandAltaFactura;
import presentacion.command.factura.CommandBuscarParaModificarFactura;
import presentacion.command.factura.CommandDevolverProducto;
import presentacion.command.factura.CommandListarFacturas;
import presentacion.command.factura.CommandMostrarFactura;
import presentacion.command.factura.CommandMostrarFacturaPorCliente;
import presentacion.command.inventario.CommandAltaInventario;
import presentacion.command.inventario.CommandBajaInventario;
import presentacion.command.inventario.CommandBuscarParaModificarInventario;
import presentacion.command.inventario.CommandListarInventarios;
import presentacion.command.inventario.CommandModificarInventario;
import presentacion.command.inventario.CommandMostrarInventario;
import presentacion.command.inventario.CommandMostrarInventarioPorDepartamento;
import presentacion.command.inventario.CommandMostrarInventarioPorMaterial;
import presentacion.command.material.CommandAltaMaterial;
import presentacion.command.material.CommandBajaMaterial;
import presentacion.command.material.CommandBuscarParaModificarMaterial;
import presentacion.command.material.CommandListarMateriales;
import presentacion.command.material.CommandModificarMaterial;
import presentacion.command.material.CommandMostrarMaterial;
import presentacion.command.producto.CommandAltaProducto;
import presentacion.command.producto.CommandBajaProducto;
import presentacion.command.producto.CommandBuscarParaModificarProducto;
import presentacion.command.producto.CommandListarProductos;
import presentacion.command.producto.CommandModificarProducto;
import presentacion.command.producto.CommandMostrarProducto;
import presentacion.command.producto.CommandMostrarProductoMasVendido;
import presentacion.command.proveedor.CommandAltaProveedor;
import presentacion.command.proveedor.CommandBajaProveedor;
import presentacion.command.proveedor.CommandBuscarParaModificarProveedor;
import presentacion.command.proveedor.CommandListarProveedor;
import presentacion.command.proveedor.CommandModificarProveedor;
import presentacion.command.proveedor.CommandMostrarProveedor;

public enum CommandEnum {

	//Parte DAO

	CambiarVistaProveedor, CambiarVistaAltaProveedor, CambiarVistaBajaProveedor, CambiarVistaModificarProveedor, CambiarVistaMostrarProveedor, CambiarVistaListarProveedor, CambiarVistaCliente, CambiarVistaAltaCliente, CambiarVistaBajaCliente, CambiarVistaModificarCliente, CambiarVistaListarClientes, CambiarVistaMostrarCliente, CambiarVistaClienteQueMasGasta, CambiarVistaProducto, CambiarVistaAltaProducto, CambiarVistaBajaProducto, CambiarVistaModificarProducto, CambiarVistaMostrarProducto, CambiarVistaListarProducto, CambiarVistaProductoMasVendido, CambiarVistaFactura, CambiarVistaAltaFactura, CambiarVistaBajaFactura, CambiarVistaListarFacturas, CambiarVistaMostrarFacturaPorCliente, CambiarVistaMostrarFactura,

	AltaProveedor(new CommandAltaProveedor()), BajaProveedor(new CommandBajaProveedor()), MostrarProveedor(
			new CommandMostrarProveedor()), ListarProveedor(new CommandListarProveedor()), ModificarProveedor(
					new CommandModificarProveedor()), BuscarParaModificarProveedor(
							new CommandBuscarParaModificarProveedor()),

	AltaCliente(new CommandAltaCliente()), AltaClienteParticular(new CommandAltaCliente()), BajaCliente(
			new CommandBajaCliente()), MostrarCliente(new CommandMostrarCliente()), ListarClientes(
					new CommandListarClientes()), ModificarCliente(
							new CommandModificarCliente()), BuscarParaModificarCliente(
									new CommandBuscarParaModificarCliente()), MostrarClienteQueMasGasta(
											new CommandMostrarClienteQueMasGasta()),

	AltaProducto(new CommandAltaProducto()), BajaProducto(new CommandBajaProducto()), MostrarProducto(
			new CommandMostrarProducto()), ListarProductos(new CommandListarProductos()), ModificarProducto(
					new CommandModificarProducto()), BuscarParaModificarProdcuto(
							new CommandBuscarParaModificarProducto()), MostrarProductoMasVendido(
									new CommandMostrarProductoMasVendido()),

	AltaFactura(new CommandAltaFactura()), BuscarParaModificarFactura(
			new CommandBuscarParaModificarFactura()), MostrarFactura(
					new CommandMostrarFactura()), MostrarFacturaPorCliente(
							new CommandMostrarFacturaPorCliente()), ListarFacturas(
									new CommandListarFacturas()), AñadirProductoFactura(
											new CommandAddProductoFactura()), AbrirFactura(
													new CommandAbrirFactura()), DevolverProducto(
															new CommandDevolverProducto()), DevolverProductoFallo, DevolverProductoCorrecto,

	AltaProveedorCorrecta, AltaProveedorFallo, BajaProveedorCorrecta, BajaProveedorFallo, MostrarProveedorCorrecta, MostrarProveedorFallo, ListarProveedorCorrecto, ListarProveedoresIncorrecto, BuscarParaModificarProveedorCorrecto, BuscarParaModificarProveedorIncorrecto, ModificarProveedorCorrecto, ModificarProveedorIncorrecto,

	AltaClienteCorrecto, AltaClienteFallo, BajaClienteCorrecto, BajaClienteFallo, MostrarClienteCorrecto, MostrarClienteFallo, ListarClientesCorrecto, ListarClientesFallo, BuscarParaModificarClienteCorrecto, BuscarParaModificarClienteFallo, ModificarClienteCorrecto, ModificarCLienteFallo, MostrarClienteQueMasGastaCorrecto, MostrarClienteQueMasGastaFallo,

	AltaProductoCorrecto, AltaProductoFallo, BajaProductoCorrecto, BajaProductoFallo, MostrarProductoCorrecto, MostrarProductoFallo, ListarProductoCorrecto, ListarProductoFallo, BuscarParaModificarProductoCorrecto, BuscarParaModificarProductoFallo, ModificarProductoCorrecto, ModificarProductoFallo, MostrarProductoMasVendidoCorrecto, MostrarProductoMasVendidoFallo,

	AltaFacturaCorrecto, AltaFacturaFallo, BajaFacturaCorrecto, BajaFacturaError, ModificarFacturaCorrecto, ModificarFacturaError, ListarFacturaCorrecto, ListarFacturaError, MostrarFacturaCorrecto, MostrarFacturaError, MostrarFacturaPorClienteCorrecto, MostrarFacturaPorClienteError, BuscarParaModificarFacturaCorrecto, BuscarParaModificarFacturaError, AñadirProductoFacturaCorrecto, AñadirProductoFacturaError, AbrirFacturaCorrecto, AbrirFacturaError,

	//Parte JPA

	// MATERIAL
	CambiarVistaMaterial, CambiarVistaAltaMaterial, CambiarVistaBajaMaterial, CambiarVistaMostrarMaterial, Departamento, CambiarVistaModificarMaterial, CambiarVistaListarMateriales, CambiarVistaMostrarMaterialConMenosExistencias,

	AltaMaterial(new CommandAltaMaterial()), BajaMaterial(new CommandBajaMaterial()), BuscarParaModificarMaterial(
			new CommandBuscarParaModificarMaterial()), ModificarMaterial(
					new CommandModificarMaterial()), MostrarMaterial(new CommandMostrarMaterial()), ListarMateriales(
							new CommandListarMateriales()), 

	AltaMaterialCorrecto, AltaMaterialFallo, BajaMaterialCorrecto, BajaMaterialFallo, BuscarParaModificarMaterialCorrecto, BuscarParaModificarMaterialFallo, ModificarMaterialCorrecto, ModificarMaterialFallo, MostrarMaterialCorrecto, MostrarMaterialFallo,  ListarMaterialesCorrecto, ListarMaterialesFallo,

	// DEPARTAMENTO
	CambiarVistaDepartamento, CambiarVistaAltaDepartamento, CambiarVistaBajaDepartamento, CambiarVistaMostrarDepartamento, CambiarVistaListarDepartamentos, CambiarVistaModificarDepartamento,CambiarVistaNominaDepartamento,

	AltaDepartamento(new CommandAltaDepartamento()), BajaDepartamento(
			new CommandBajaDepartamento()), BuscarParaModificarDepartamento(
					new CommandBuscarParaModificarDepartamento()), ModificarDepartamento(
							new CommandModificarDepartamento()), MostrarDepartamento(
									new CommandMostrarDepartamento()), ListarDepartamento(
											new CommandListarDepartamentos()),NominaDepartamento(new CommandNominaDepartamento()),

	AltaDepartamentoCorrecto, AltaDepartamentoFallo, BajaDepartamentoCorrecto, BajaDepartamentoFallo, BuscarParaModificarDepartamentoCorrecto, BuscarParaModificarDepartamentoFallo, ModificarDepartamentoCorrecto, ModificarDepartamentoFallo, MostrarDepartamentoCorrecto, MostrarDepartamentoFallo, ListarDepartamentosCorrecto, ListarDepartamentosFallo,NominaDepartamentoCorrecto,NominaDepartamentoFallo,

	// EMPLEADO
	CambiarVistaEmpleado, CambiarVistaAltaEmpleado, CambiarVistaBajaEmpleado, CambiarVistaModificarEmpleado, CambiarVistaListarEmpleados, CambiarVistaMostrarEmpleado, CambiarVistaMostrarEmpleadosPorDepartamento,

	AltaEmpleado(new CommandAltaEmpleado()), BajaEmpleado(new CommandBajaEmpleado()), MostrarEmpleado(
			new CommandMostrarEmpleado()), ListarEmpleados(
					new CommandListarEmpleados()), ListarEmpleadosPorDepartamento(
							new CommandMostrarEmpleadosPorDepartamento()), ModificarEmpleado(
									new CommandModificarEmpleado()), BuscarParaModificarEmpleado(
											new CommandBuscarParaModificarEmpleado()),

	AltaEmpleadoCorrecto, AltaEmpleadoFallo, BajaEmpleadoCorrecto, BajaEmpleadoFallo, BuscarParaModificarEmpleadoCorrecto, BuscarParaModificarEmpleadoFallo, ModificarEmpleadoCorrecto, ModificarEmpleadoFallo, MostrarEmpleadoCorrecto, MostrarEmpleadoFallo, ListarEmpleadosCorrecto, ListarEmpleadosFallo, ListarEmpleadosPorDepartamentoCorrecto, ListarEmpleadosPorDepartamentoError,
	
	// INVENTARIO
	
	CambiarVistaInventario, CambiarVistaAltaInventario, CambiarVistaBajaInventario, CambiarVistaModificarInventario, CambiarVistaMostrarInventario, CambiarVistaMostrarInventarioPorMaterial, CambiarVistaMostrarInventarioPorDepartamento, CambiarVistaListarInventarios,
	
	AltaInventario(new CommandAltaInventario()),
	BajaInventario(new CommandBajaInventario()), 
	ModificarInventario(new CommandModificarInventario()), 
	ListarInventarios(new CommandListarInventarios()), 
	MostrarInventario(new CommandMostrarInventario()), 
	MostrarInventarioPorMaterial(new CommandMostrarInventarioPorMaterial()), 
	MostrarInventarioPorDepartamento(new CommandMostrarInventarioPorDepartamento()),
	BuscarParaModificarInventario(new CommandBuscarParaModificarInventario()),
	
	AltaInventarioCorrecto, AltaInventarioError, BajaInventarioCorrecto, BajaInventarioError, BuscarParaModificarInventarioCorrecto, BuscarParaModificarInventarioFallo, ModificarInventarioCorrecto, ModificarInventarioFallo,MostrarInventarioCorrecto,MostrarInventarioFallo,ListarInventariosCorrecto,ListarInventariosFallo, MostrarInventarioPorMaterialCorrecto, MostrarInventarioPorMaterialFallo, MostrarInventarioPorDepartamentoCorrecto, MostrarInventarioPorDepartamentoError;

	private Command comando;

	private CommandEnum() {
	}

	private CommandEnum(Command comando) {
		this.comando = comando;
	}

	public Command getComando() {
		return comando;
	}

}