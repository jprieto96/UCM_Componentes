package integracion.producto.bajaProducto;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.producto.*;
import negocio.proveedor.*;
import util.BBDDManip;


public class TestIntegracionBajaProducto {


	public static DAOProducto daoProducto;
	public static DAOProveedor daoProveedor;
	public static TProducto producto1;
	public static TProducto producto2;
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
		producto1 = new TProducto(1,"Primer producto", 123.5, 2, 1, true);

		
		//Comprobamos que estan vac�as las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TProducto> productos = daoProducto.readAll();
		t.commit();
		if( !productos.isEmpty() )
			fail("las pruebas unitarias de integraci�n de productos requieren la tabla de productos vac�a");
		else
			util.BBDDManip.vaciarTodo();		
	}

	
	@Test
	public void bajaProducto(){
		t = dameTransaccion();
		t.start();
		proveedor.setId(daoProveedor.create(proveedor)); //A�ado el proveedor
		t.commit();
		
		t = dameTransaccion();
		t.start();
		producto1.setId(daoProducto.create(producto1)); //A�ado el primer producto
		t.commit();
		
		t = dameTransaccion();
		t.start();
		daoProducto.delete(producto1.getId()); //Lo doy de baja
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TProducto aux = (TProducto) daoProducto.readById(producto1.getId()); //Leo el primero
		t.commit();
		//Ahora comprobamos que todos los campos NO se han modificado y solo se ha dado de baja (campo active a false)
			assertEquals(producto1.getNombre(), aux.getNombre());
			assertEquals(producto1.getPrecio(), aux.getPrecio(), 0.0);
			assertEquals(producto1.getExistencias(), aux.getExistencias());
			assertEquals(producto1.getIdProveedor(), aux.getIdProveedor());
			assertEquals(false, aux.isActivo());

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
