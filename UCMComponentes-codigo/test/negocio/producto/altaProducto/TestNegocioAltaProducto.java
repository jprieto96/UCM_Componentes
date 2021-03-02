package negocio.producto.altaProducto;

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

public class TestNegocioAltaProducto {
	
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
	public void altaProducto(){
		contexto = new Context();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		//Intento añadir un producto que no tiene proveedor existente (igual que si no esta activo) -> el SA deberia de dar fallo
		producto = new TProducto(-1, "Producto", 99, 2, 1, true);
		contexto.setDato(producto);
		producto.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
			assertEquals(-1,producto.getId()); //Me tiene que devolver -1 (error)

			
		//Ahora creo su proveedor y lo vuelvo a intentar dar de alta, el SA deberia de añadirlo
		proveedor = new TProveedor(-1,"Proveedor", true);
		contexto.setDato(proveedor);
		proveedor.setId(saProveedor.altaProveedor((TProveedor)contexto.getDato()));
		producto = new TProducto(-1, "Producto", 99, 2, 1, false);
		contexto.setDato(producto);
		producto.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
			assertEquals(1,producto.getId()); //Me tiene que devolver 1, primer producto creado
	
			
		//Producto ya existente, pero inactivo. Deberia darlo de alta correctamente el SA
		if(saProducto.bajaProducto(producto.getId()) ){
			producto.setActivo(false);
		}
		//Lo reactivo
		producto.setId( saProducto.altaProducto(producto) );
		assertEquals(1, producto.getId()); //Me devuelve el id del producto que ya existia
			
			
	}
	
}
