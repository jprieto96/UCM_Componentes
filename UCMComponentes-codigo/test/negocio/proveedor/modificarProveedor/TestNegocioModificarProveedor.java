package negocio.proveedor.modificarProveedor;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.proveedor.DAOProveedor;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.*;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioModificarProveedor {
	
	public static DAOProveedor daoProveedor;
	public static TProveedor proveedor;
	private static Transaction t;
	public static Context contexto;
	public static SAProveedor saProveedor;

	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		
		proveedor = new TProveedor(-1, "Proveedor", true);
		
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
	public void modificarProveedor(){
		contexto = new Context();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		//Intento modificar un proveedor que no existe -> el sa deberia dar error
		boolean mod = saProveedor.modificarProveedor(proveedor); 
		assertFalse(mod);
		
		//Añado un proveedor
		proveedor = new TProveedor(-1,"Proveedor");
		contexto.setDato(proveedor);
		proveedor.setId( saProveedor.altaProveedor((TProveedor) contexto.getDato()));
		
		//Lo modifico --> el SA deberia de modificarlo correctamente
		proveedor.setNombre("Nuevo nombre");
		contexto.setDato(proveedor);
		mod = saProveedor.modificarProveedor((TProveedor) contexto.getDato());
		assertTrue(mod);

		
	}
	
}
