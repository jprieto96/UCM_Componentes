package negocio.factura;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import negocio.factura.altaFactura.TestNegocioAltaFactura;
import negocio.factura.devolverProducto.TestNegocioDevolverProducto;
import negocio.factura.listarFacturas.TestNegocioListarFacturas;
import negocio.factura.mostrarFactura.TestNegocioMostrarFactura;
import negocio.factura.mostrarFacturaPorCliente.TestNegocioMostrarFacturaPorCliente;

@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaFactura.class, TestNegocioListarFacturas.class, TestNegocioMostrarFactura.class,
	TestNegocioMostrarFacturaPorCliente.class, TestNegocioDevolverProducto.class})
public class SuiteTestNegocioFactura {}