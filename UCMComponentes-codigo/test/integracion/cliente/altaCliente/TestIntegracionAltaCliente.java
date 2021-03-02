package integracion.cliente.altaCliente;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.*;
import util.BBDDManip;

public class TestIntegracionAltaCliente {


	public static DAOCliente daoCliente;
	public static TParticular particular;
	public static TEmpresa empresa;
	private static Transaction t;
	
	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		//seleccionarCamposUnicos();

		//Creamos dos transfer, uno de cada
		particular = new TParticular(1, "Cliente particular", "ES0986543", true, "27268754T", 1, true);
		empresa = new TEmpresa(2, "Cliente Empresa", "ES09652412", true, "58012685S", 1, "Calle la malagueta");
		
		//Comprobamos que estan vacías las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TCliente> clientes = daoCliente.readAll();
		t.commit();
		if( !clientes.isEmpty() )
			fail("las pruebas unitarias de integración de productos requieren la tablas cliente, particular y empresa vacías");
		else
			util.BBDDManip.vaciarTodo();		
	}

	
	@Test
	public void altaCLiente(){
		//PARTICULAR
		t = dameTransaccion();
		t.start();
		particular.setId(daoCliente.create(particular)); //Añado un cliente particular
		t.commit();
		t = dameTransaccion();
		t.start();
		TParticular aux = (TParticular) daoCliente.readById(particular.getId()); //Lo leo
		t.commit();
			//Ahora comprobamos que todos los campos se han introducido correctamente
			assertEquals(true, aux.isActivo());
			assertEquals(particular.getCuentaBancaria(), aux.getCuentaBancaria());
			assertEquals(particular.getNombre(), aux.getNombre());
			assertEquals(particular.isFiel(), aux.isFiel());
		//EMPRESA
		t = dameTransaccion();
		t.start();
		empresa.setId(daoCliente.create(empresa)); //Añado un cliente de empresa
		t.commit();
		t = dameTransaccion();
		t.start();
		TEmpresa aux2 = (TEmpresa) daoCliente.readById(empresa.getId()); 
		t.commit();
			//Ahora comprobamos todos sus campos se han introducido correctamente
			assertEquals(true, aux2.isActivo());
			assertEquals(empresa.getCuentaBancaria(), aux2.getCuentaBancaria());
			assertEquals(empresa.getNombre(), aux2.getNombre());
			assertEquals(empresa.getDireccionSocial(), aux2.getDireccionSocial());
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
