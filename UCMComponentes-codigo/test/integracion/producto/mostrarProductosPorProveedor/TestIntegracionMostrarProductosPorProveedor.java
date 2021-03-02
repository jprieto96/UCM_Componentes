package integracion.producto.mostrarProductosPorProveedor;


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



public class TestIntegracionMostrarProductosPorProveedor {


	public static DAOProducto daoProducto;
	public static DAOProveedor daoProveedor;
	public static TProducto producto1;
	public static TProducto producto2;
	public static TProducto producto3;
	public static TProveedor proveedor1;
	public static TProveedor proveedor2;
	private static Transaction t;
	
	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		//seleccionarCamposUnicos();

		//Creamos los transfer
		proveedor1 = new TProveedor(1, "Primer proveedor");
		proveedor2 = new TProveedor(2, "Segundo proveedor");
		producto1 = new TProducto(1,"Primer producto", 123.5, 2, 1, true);
		producto2 = new TProducto(2,"Segundo producto", 99, 3, 2, true);
		producto3 = new TProducto(3,"Tercer producto", 643, 3, 2, true);

		
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
	public void listarProductos(){
		t = dameTransaccion();
		t.start();
		proveedor1.setId(daoProveedor.create(proveedor1)); //Añado el primer proveedor
		t.commit();
		t = dameTransaccion();
		t.start();
		proveedor2.setId(daoProveedor.create(proveedor2)); //Añado el segundo proveedor
		t.commit();
		t = dameTransaccion();
		t.start();
		producto1.setId(daoProducto.create(producto1)); //Añado el primer producto
		t.commit();
		t = dameTransaccion();
		t.start();
		producto2.setId(daoProducto.create(producto2)); //Añado el segundo producto
		t.commit();
		t = dameTransaccion();
		t.start();
		producto3.setId(daoProducto.create(producto3)); //Añado el tercer producto
		t.commit();
		
		//Los leo
		t = dameTransaccion();
		t.start();
		int numProductos = daoProducto.productosPorProveedor(2);//Solo deberia darme 2
		t.commit();

		assertEquals(2, numProductos);

			
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
