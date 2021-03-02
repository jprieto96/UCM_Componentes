package negocio.producto.productosPorProveedor;

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

public class TestNegocioProductosPorProveedor {
	
	private static DAOProducto daoProducto;
	private static TProducto producto1;
	private static TProducto producto2;
	private static TProducto producto3;
	private static TProveedor proveedor1;
	private static TProveedor proveedor2;
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
	public void productosPorProveedor(){
		contexto = new Context();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		

		//Ahora creo el primer proveedor y añado un producto
		proveedor1 = new TProveedor(-1,"Primer Proveedor", true);
		contexto.setDato(proveedor1);
		proveedor1.setId(saProveedor.altaProveedor((TProveedor)contexto.getDato()));
		producto1 = new TProducto(-1, "Primer Producto", 99, 3, 1, true);
		contexto.setDato(producto1);
		producto1.setId(saProducto.altaProducto((TProducto)contexto.getDato()));

		//Ahora creo el segundo proveedor y le añado otro producto
		proveedor2 = new TProveedor(-1,"Segundo Proveedor", true);
		contexto.setDato(proveedor2);
		proveedor2.setId(saProveedor.altaProveedor((TProveedor)contexto.getDato()));
		producto2 = new TProducto(-1, "Segundo Producto", 99, 4, 2, true);
		contexto.setDato(producto2);
		producto2.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
			//Creo otro producto y lo añado al proveedor 2
			producto3 = new TProducto(-1, "Tercer Producto", 99, 4, 2, true);
			contexto.setDato(producto3);
			producto3.setId(saProducto.altaProducto((TProducto)contexto.getDato()));
		
		//Ahora leo los productos del segundo proveedor, deberia darme 2 ->el segundo y tercer producto
			
		int numero = saProducto.productosPorProveedor(proveedor2.getId());
			assertEquals(2, numero);

	}
	
}
