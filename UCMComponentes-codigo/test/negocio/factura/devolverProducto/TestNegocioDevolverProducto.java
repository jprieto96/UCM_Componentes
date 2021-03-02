package negocio.factura.devolverProducto;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.SACliente;
import negocio.cliente.TParticular;
import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import util.BBDDManip;

public class TestNegocioDevolverProducto {
	
	private static DAOFactura daoFactura;
	private static SAProducto saProducto;
	private static SAProveedor saProveedor;
	private static SAFactura saFactura;
	private static SACliente saCliente;
	private static Transaction t;
	private static TFactura factura;
	private static TProducto producto;
	private static TProveedor proveedor;
	private static TParticular cliente;
	private static HashMap<Integer, Integer> mapaProductos;
	
	@BeforeClass
	public static void before() {
		BBDDManip.vaciarTodo();
		saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		
		 t = TransactionManager.getInstancia().nuevaTransaccion();
		 if(t == null)
			 t = TransactionManager.getInstancia().getTransaccion();
		 t.start();
		 Collection<TFactura> facturas = daoFactura.readAll();
		 t.commit();
		 if(!facturas.isEmpty())
			 fail("las pruebas unitarias de factura requiere vaciar la tabla facturas");
		 else
			 util.BBDDManip.vaciarTodo();
		 
		saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		mapaProductos = new HashMap<Integer,Integer> ();
		factura = new TFactura(-1, "1-1-2000", 2.f, -1, true);
		//Doy de alta un proveedor y un producto
		proveedor = new TProveedor(-1, "Proveedor");
		proveedor.setId(saProveedor.altaProveedor(proveedor));
		producto = new TProducto(-1, "Producto", 2.f, 1, 1, true);
		producto.setId(saProducto.altaProducto(producto));
		
		factura.setIdProducto(producto.getId());
		mapaProductos.put(producto.getId(), producto.getExistencias());
		factura.setMapaProductos(mapaProductos);
		
		//Creo el cliente
		cliente = new TParticular(-1, "Cliente", "ES1234", true, "71043951X");
		cliente.setId(saCliente.altaCliente(cliente));
		factura.setIdCliente(cliente.getId());
		
		factura.setId(saFactura.altaFactura(factura));
	
	}

	@Test
	public void testDevolverProductoCorrecto() {
		int cantidadDevolver = mapaProductos.get(producto.getId());
		factura.setCantidad(cantidadDevolver);
		producto.setExistencias(producto.getExistencias() - cantidadDevolver);
		boolean ok = saFactura.devolverProducto(factura);
		assertTrue(ok);
		
		TProducto p = saProducto.mostrarProducto(producto.getId());
		assertEquals(p.getExistencias(), cantidadDevolver + producto.getExistencias());
	}
	
	@Test
	public void testDevolverProductoIncorrectoProductoInexistente() {
		factura.setId(-1);
		boolean ok = saFactura.devolverProducto(factura);
		assertFalse(ok);
	}
	
	@Test
	public void testDevolverProductoIncorrectoCantidadErronea() {
		// Mapa productos tiene de cantidad las existencias entonces sumamos 1
		int cantidadDevolver = mapaProductos.get(producto.getId()) + 1;
		factura.setCantidad(cantidadDevolver);
		boolean ok = saFactura.devolverProducto(factura);
		assertFalse(ok);
		assertTrue(cantidadDevolver > producto.getExistencias());
	}

}
