package integracion.proveedor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import integracion.proveedor.altaProveedor.TestIntegracionAltaProveedor;
import integracion.proveedor.bajaProveedor.TestIntegracionBajaProveedor;
import integracion.proveedor.listarProveedor.TestIntegracionListarProveedor;
import integracion.proveedor.modificarProveedor.TestIntegracionModificarProveedor;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaProveedor.class, TestIntegracionBajaProveedor.class,
					TestIntegracionModificarProveedor.class, TestIntegracionListarProveedor.class,
						TestIntegracionModificarProveedor.class })
public class SuiteTestIntegracionProveedor {}
