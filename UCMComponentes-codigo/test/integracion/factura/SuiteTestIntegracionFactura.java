package integracion.factura;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integracion.factura.altaFactura.TestIntegracionAltaFactura;
import integracion.factura.listarFacturas.TestIntegracionListarFacturas;
import integracion.factura.modificarFactura.TestIntegracionModificarFactura;
import integracion.factura.mostrarFactura.TestIntegracionMostrarFactura;
import integracion.factura.mostrarFacturaPorCliente.TestIntegracionMostrarFacturaPorCliente;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaFactura.class, TestIntegracionMostrarFactura.class,
					TestIntegracionMostrarFacturaPorCliente.class, TestIntegracionListarFacturas.class,
						TestIntegracionModificarFactura.class })
public class SuiteTestIntegracionFactura {}
