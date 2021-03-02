package negocio.producto.bajaProducto;

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

public class TestNegocioBajaProducto {
	
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
	public void bajaProducto(){
		contexto = new Context();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
	
		//Creo un proveedor y doy de alta un producto, el SA deberia de añadirlo
		proveedor = new TProveedor(-1,"Proveedor", true);
		contexto.setDato(proveedor);
		proveedor.setId(saProveedor.altaProveedor((TProveedor)contexto.getDato()));
		producto = new TProducto(-1, "Producto", 99, 2, 1, false);
		contexto.setDato(producto);
		producto.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
			assertEquals(1,producto.getId()); //Me tiene que devolver 1, primer producto creado
		
		//Doy de baja el producto, el sa deberia de darlo de baja
		if( !saProducto.bajaProducto(producto.getId()) ){
			fail("No se ha podido dar de baja el producto");
		}else{
			producto.setActivo(false);
		}
			
		//Intento dar de baja un cliente que ya esta dado de baja -> el SA devuelve false ya que no se puede dar de baja
		boolean dadoDeBaja = saProducto.bajaProducto( ((TProducto)contexto.getDato()).getId() ); 
		assertFalse(dadoDeBaja);
		
	}
	
}
