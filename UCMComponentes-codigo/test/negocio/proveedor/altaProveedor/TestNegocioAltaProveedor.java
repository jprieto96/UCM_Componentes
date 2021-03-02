package negocio.proveedor.altaProveedor;

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

public class TestNegocioAltaProveedor {
	
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
	public void altaNegocio(){
		contexto = new Context();
		SAProveedor saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		
		//Intento añadir un proveedor que no existe -> lo deberia añadir el SA
		proveedor = new TProveedor(-1,"Proveedor");
		contexto.setDato(proveedor);
		proveedor.setId( saProveedor.altaProveedor((TProveedor) contexto.getDato()));
			//Me tiene que dar el id 1, ya que es el primer proveedor que se inserta
		assertEquals(1,proveedor.getId());
		
		
		//Intento añadir un cliente que existe pero esta inactivo -> lo deberia reactivar el SA
		saProveedor.bajaProveedor(proveedor.getId());//Doy de baja el proveedor añadido anteriormente
		
		contexto.setDato(proveedor);
			//Me tiene que devolver el mismo id, porque solo se ha reactivado
		int id = saProveedor.altaProveedor((TProveedor) contexto.getDato());
		assertEquals(id, proveedor.getId());

		
		//Intento añadir un proveedor, pero este ya existe y esta activo -> no deberia añadirlo, deberia dar error
		contexto.setDato(proveedor);
		//Me tiene que devolver el mismo id, porque solo se ha reactivado
		id = saProveedor.altaProveedor((TProveedor) contexto.getDato());
		assertEquals(id, -1); //Pongo -1 porque da error y el sa me lo indica con un -1
		

		
	}
	
}
