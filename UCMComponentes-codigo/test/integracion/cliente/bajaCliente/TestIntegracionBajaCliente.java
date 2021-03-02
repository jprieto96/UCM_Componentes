package integracion.cliente.bajaCliente;


import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.*;
import util.BBDDManip;

public class TestIntegracionBajaCliente {


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

		//Creamos dos clientes, uno de cada. Por lo que los insertaremos activos
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
	public void bajaCLiente(){
		//PARTICULAR
		t = dameTransaccion();
		t.start();
		particular.setId(daoCliente.create(particular)); //Añado un cliente particular activo
		t.commit();
		
		t = dameTransaccion();
		t.start();
		daoCliente.delete(particular.getId()); //Lo doy de baja
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TParticular aux = (TParticular) daoCliente.readById(particular.getId()); //Lo leo
		t.commit();
			//Ahora comprobamos que todos los campos NO se han modificado y solo se ha dado de baja (campo active a false)
			assertEquals(false, aux.isActivo());
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
		daoCliente.delete(empresa.getId()); //Lo doy de baja
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TEmpresa aux2 = (TEmpresa) daoCliente.readById(empresa.getId()); 
		t.commit();
			//Ahora comprobamos que todos los campos NO se han modificado y solo se ha dado de baja (campo active a false)
			assertEquals(false, aux2.isActivo());
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
