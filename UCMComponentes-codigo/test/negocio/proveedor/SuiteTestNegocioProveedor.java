package negocio.proveedor;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import negocio.proveedor.altaProveedor.TestNegocioAltaProveedor;
import negocio.proveedor.bajaProveedor.TestNegocioBajaProveedor;
import negocio.proveedor.listarProveedores.TestNegocioListarProveedores;
import negocio.proveedor.modificarProveedor.TestNegocioModificarProveedor;
import negocio.proveedor.mostrarProveedor.TestNegocioMostrarProveedor;


@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaProveedor.class, TestNegocioBajaProveedor.class, TestNegocioListarProveedores.class, 
	TestNegocioModificarProveedor.class ,TestNegocioMostrarProveedor.class })
public class SuiteTestNegocioProveedor {}

