package integracion.producto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integracion.producto.altaProducto.TestIntegracionAltaProducto;
import integracion.producto.bajaProducto.TestIntegracionBajaProducto;
import integracion.producto.listarProductos.TestIntegracionListarProductos;
import integracion.producto.modificarProducto.TestIntegracionModificarProducto;
import integracion.producto.mostrarProducto.TestIntegracionMostrarProducto;
import integracion.producto.mostrarProductosPorProveedor.TestIntegracionMostrarProductosPorProveedor;


@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaProducto.class, TestIntegracionBajaProducto.class,
					TestIntegracionListarProductos.class, TestIntegracionModificarProducto.class,
						TestIntegracionMostrarProducto.class, TestIntegracionMostrarProductosPorProveedor.class })
public class SuiteTestIntegracionProducto {}
