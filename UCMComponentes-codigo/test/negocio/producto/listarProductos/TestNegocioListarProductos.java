package negocio.producto.listarProductos;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.*;

import integracion.producto.DAOProducto;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.producto.*;
import negocio.proveedor.*;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioListarProductos {
	
	private static DAOProducto daoProducto;
	private static TProducto producto1;
	private static TProducto producto2;
	private static TProveedor proveedor;
	private static Transaction t;
	private static Context contexto;
	private static SAProducto saProducto;
	private static SAProveedor saProveedor;

	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();		
		
		//Comprobamos que estan vacías las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TProducto> productos = daoProducto.readAll();
		t.commit();
		if( !productos.isEmpty() )
			fail("las pruebas unitarias de negocio de productos vacías ");
		else
			util.BBDDManip.vaciarTodo();		
	}
	
	
	@Test
	public void listarProductos(){
		contexto = new Context();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		

		//Intento listarProductos activos, pero no existe ninguno (ni activo ni inactivo)
		List<TProducto> lista = saProducto.listarProductos(true);
		assertEquals(lista.size(), 0);
	
		//Ahora creo un proveedor y doy de alta dos productos
		proveedor = new TProveedor(-1,"Proveedor", true);
		contexto.setDato(proveedor);
		proveedor.setId(saProveedor.altaProveedor((TProveedor)contexto.getDato()));
		producto1 = new TProducto(-1, "Primer producto", 10, 1, 1, false);
		producto2 = new TProducto(-1, "Segundo producto", 20, 1, 1, false);
		contexto.setDato(producto1);
		producto1.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
		contexto.setDato(producto2);
		producto2.setId(saProducto.altaProducto((TProducto)contexto.getDato()));

		//Ahora doy de baja el segundo
		//Doy de baja el producto, el sa deberia de darlo de baja
		if( saProducto.bajaProducto(producto1.getId()) ){
			producto1.setActivo(false);
		}
			
		//Intento listar los PRODUCTOS activos, deberia devolverme uno
		List<TProducto> lista2 = saProducto.listarProductos(true);
		assertEquals(lista2.size(), 1);
		
		//Intento listar todos los productos -> me devuelve dos
		List<TProducto> lista3 = saProducto.listarProductos(false);
		assertEquals(lista3.size(), 2);	
		
	
			
	}
	
}
