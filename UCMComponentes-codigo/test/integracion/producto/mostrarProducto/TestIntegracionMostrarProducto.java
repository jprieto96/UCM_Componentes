package integracion.producto.mostrarProducto;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.TEmpresa;
import negocio.cliente.TParticular;
import negocio.producto.*;
import negocio.proveedor.*;
import util.BBDDManip;

public class TestIntegracionMostrarProducto {


	public static DAOProducto daoProducto;
	public static DAOProveedor daoProveedor;
	public static TProducto producto;
	public static TProveedor proveedor;
	private static Transaction t;
	
	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		//seleccionarCamposUnicos();

		//Creamos los transfer
		proveedor = new TProveedor(1, "Proveedor");
		producto = new TProducto(1,"Primer producto", 123.5, 2, 1, true);

		
		//Comprobamos que estan vacías las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TProducto> productos = daoProducto.readAll();
		t.commit();
		if( !productos.isEmpty() )
			fail("las pruebas unitarias de integración de productos requieren la tabla de productos vacía");
		else
			util.BBDDManip.vaciarTodo();		
	}


	
	@Test
	public void mostrarProducto(){
		t = dameTransaccion();
		t.start();
		proveedor.setId(daoProveedor.create(proveedor)); //Añado el proveedor
		t.commit();
		
		t = dameTransaccion();
		t.start();
		producto.setId(daoProducto.create(producto)); //Añado el producto
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TProducto aux = (TProducto) daoProducto.readById(producto.getId()); //Leo el primero
		t.commit();
		//Ahora comprobamos que se muestra correctamente
			assertEquals(producto.getNombre(), aux.getNombre());
			assertEquals(producto.getPrecio(), aux.getPrecio(), 0.0);
			assertEquals(producto.getExistencias(), aux.getExistencias());
			assertEquals(producto.getIdProveedor(), aux.getIdProveedor());
			assertEquals(true, aux.isActivo());
	}
	
	public Transaction dameTransaccion(){
		//Cogemos la transaccion existente, si no hay creamos una
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null){
			t = TransactionManager.getInstancia().getTransaccion();
		}
		return t;
	}



}
