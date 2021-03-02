package negocio.producto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import negocio.producto.altaProducto.TestNegocioAltaProducto;
import negocio.producto.bajaProducto.TestNegocioBajaProducto;
import negocio.producto.listarProductos.TestNegocioListarProductos;
import negocio.producto.modificarProducto.TestNegocioModificarProducto;
import negocio.producto.mostrarProducto.TestNegocioMostrarProducto;
import negocio.producto.productosPorProveedor.TestNegocioProductosPorProveedor;


@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaProducto.class, TestNegocioBajaProducto.class, TestNegocioListarProductos.class,
				TestNegocioModificarProducto.class, TestNegocioMostrarProducto.class, TestNegocioProductosPorProveedor.class})
public class SuiteTestNegocioProducto {}