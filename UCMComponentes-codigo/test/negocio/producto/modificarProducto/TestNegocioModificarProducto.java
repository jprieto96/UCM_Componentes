package negocio.producto.modificarProducto;


import static org.junit.Assert.*;

import java.util.Collection;

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

public class TestNegocioModificarProducto {
	
	private static DAOProducto daoProducto;
	private static TProducto producto;
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
	public void modificarProducto(){
		contexto = new Context();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		//Intento modificar un producto que no existe -> el SA deberia de dar error
		producto = new TProducto(1, "Producto", 99, 2, 1, true);
		producto.setPrecio(100);
		contexto.setDato(producto);
		boolean modificado = saProducto.modificarProducto((TProducto)contexto.getDato());
		if (modificado){
			fail();
		}
		
		//Ahora creo su proveedor y un producto 
		proveedor = new TProveedor(-1,"Proveedor", true);
		contexto.setDato(proveedor);
		proveedor.setId(saProveedor.altaProveedor((TProveedor)contexto.getDato()));
		producto = new TProducto(-1, "Producto", 99, 2, 1, false);
		contexto.setDato(producto);
		producto.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
	
		//Modifico el producto, deberia modificarlo sin problemas
		producto.setPrecio(200);
		contexto.setDato(producto);
		modificado = saProducto.modificarProducto((TProducto)contexto.getDato());
		if(!modificado){
			fail();
		}
		
		//Leo ese producto, para comprobar que se modifico en la BBDD
		TProducto aux = saProducto.mostrarProducto(producto.getId());
			assertEquals(producto.getNombre(), aux.getNombre());
			assertEquals(producto.getPrecio(), aux.getPrecio(), 0.0);
			assertEquals(producto.getExistencias(), aux.getExistencias());
			assertEquals(producto.isActivo(), aux.isActivo());
			
			
	}
	
}
