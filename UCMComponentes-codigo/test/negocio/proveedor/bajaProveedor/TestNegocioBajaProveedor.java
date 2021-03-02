package negocio.proveedor.bajaProveedor;


import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.proveedor.DAOProveedor;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.*;
import negocio.factoria.FactoriaNegocio;
import negocio.producto.*;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioBajaProveedor {
	
	public static DAOProveedor daoProveedor;
	public static TProveedor proveedor;
	public static TProducto producto;
	private static Transaction t;
	public static Context contexto;
	public static SAProveedor saProveedor;
	public static SAProducto saProducto;

	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		
		
		//Comprobamos que estan vacías las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TProveedor> clientes = daoProveedor.readAll();
		t.commit();
		if( !clientes.isEmpty() )
			fail("las pruebas unitarias de negocio de proveedor requieren las tablas de proveedor vacias");
		else
			util.BBDDManip.vaciarTodo();		
	}
	
	
	@Test
	public void bajaProveedor(){
		contexto = new Context();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		
		//Intento dar de baja un proveedor que no existe -> lo deberia añadir el SA

		boolean dadoDeBaja= saProveedor.bajaProveedor(5);
			//Me tiene que dar el id 1, ya que es el primer proveedor que se inserta
		assertFalse(dadoDeBaja);
		
		//Añado un proveedor
		proveedor = new TProveedor(-1,"Proveedor");
		contexto.setDato(proveedor);
		proveedor.setId( saProveedor.altaProveedor((TProveedor) contexto.getDato()));
		
		//Lo doy de baja -> el SA deberia darlo de baja
		dadoDeBaja= saProveedor.bajaProveedor( ((TProveedor) contexto.getDato()).getId());
		assertTrue(dadoDeBaja);

		//Intento dar de baja un proveedor que ya esta dado de baja -> el SA devuelve false ya que no se puede dar de baja
		dadoDeBaja= saProveedor.bajaProveedor( ((TProveedor) contexto.getDato()).getId());
		assertFalse(dadoDeBaja);
		
		//Creo un producto activo con ese proveedor asociado
		producto = new TProducto(-1, "Producto", 99, 2, proveedor.getId(), true);
		contexto.setDato(producto);
		producto.setId(saProducto.altaProducto(producto));
		
		//Intento dar de baja el proveedor -> no deberia de poderse ya que tiene productos activos
		contexto.setDato(proveedor);
		dadoDeBaja= saProveedor.bajaProveedor( ((TProveedor) contexto.getDato()).getId());
		assertFalse(dadoDeBaja);
		

		
	}
	
}
