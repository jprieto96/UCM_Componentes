package integracion.lineaFactura;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integracion.lineaFactura.altaLineaFactura.TestIntegracionAltaLineaDeFactura;
import integracion.lineaFactura.leerPorIdFactura.TestIntegracionLeerPorIdFactura;
import integracion.lineaFactura.modificarLineaFactura.TestIntegracionModificarLineaFactura;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaLineaDeFactura.class, TestIntegracionLeerPorIdFactura.class,
	TestIntegracionModificarLineaFactura.class})
public class SuiteTestIntegracionLineaFactura {}
