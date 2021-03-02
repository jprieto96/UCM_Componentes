package negocio.proveedor.mostrarProveedor;


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

public class TestNegocioMostrarProveedor {
	
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
	public void mostrarProveedor(){
		contexto = new Context();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		
		//Añado un proveedor
		proveedor = new TProveedor(-1,"Proveedor", true);
		contexto.setDato(proveedor);
		proveedor.setId( saProveedor.altaProveedor((TProveedor) contexto.getDato()));
		
		//Ahora lo leo
		TProveedor aux = saProveedor.mostrarProveedor(proveedor.getId());
			//Compruebo que los campos no han sido modificados al leerlo
			assertEquals(proveedor.getNombre(), aux.getNombre());
			assertEquals(proveedor.isActivo(), aux.isActivo());

		
	}
	
}
