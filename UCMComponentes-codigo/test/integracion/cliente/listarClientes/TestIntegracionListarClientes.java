package integracion.cliente.listarClientes;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.*;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.*;
import util.BBDDManip;

public class TestIntegracionListarClientes {


	public static DAOCliente daoCliente;
	public static TParticular particular;
	public static TEmpresa empresa;
	private static Transaction t;
	
	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();

		//Creamos dos clientes, uno de cada. Por lo que los insertaremos activos
		particular = new TParticular(1, "Cliente particular", "ES0986543", true, "27268754T", 1, true);
		empresa = new TEmpresa(2, "Cliente Empresa", "ES09652412", true, "58012685S", 1, "Calle la malagueta");
		
		//Comprobamos que estan vacías las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TCliente> clientes = daoCliente.readAll(); //Aqui ya se busac en las 3 tablas
		t.commit();
		if( !clientes.isEmpty() )
			fail("las pruebas unitarias de integración de productos requieren la tablas cliente, particular y empresa vacías");
		else
			util.BBDDManip.vaciarTodo();		
	}

	
	@Test
	public void listarCLientes(){
		//PARTICULAR
		t = dameTransaccion();
		t.start();
		particular.setId(daoCliente.create(particular)); //Añado un cliente particular
		t.commit();
		//EMPRESA
		t = dameTransaccion();
		t.start();
		empresa.setId(daoCliente.create(empresa)); //Añado un cliente de empresa
		t.commit();
		
		//Los leo
		t = dameTransaccion();
		t.start();
		Collection<TCliente> collect = daoCliente.readAll();//Lo leo
		t.commit();
		Iterator<TCliente> it = collect.iterator();
		
		while(it.hasNext()){
			TCliente aux;
			aux = (TCliente) it.next();
			
			if(aux instanceof TParticular){
				//Compruebo los campos del cliente particular
			//Ahora comprobamos que todos los campos NO se han modificado y solo se ha leido
			assertEquals(true, aux.isActivo());
			assertEquals(particular.getCuentaBancaria(), aux.getCuentaBancaria());
			assertEquals(particular.getNombre(), aux.getNombre());
			assertEquals(particular.isFiel(), ((TParticular) aux).isFiel());

			}
			if(aux instanceof TEmpresa){
				//Compruebo los campos del cliente de empresa
			//Ahora comprobamos que todos los campos NO se han modificado y solo se ha leido
			assertEquals(true, aux.isActivo());
			assertEquals(empresa.getCuentaBancaria(), aux.getCuentaBancaria());
			assertEquals(empresa.getNombre(), aux.getNombre());
			assertEquals(empresa.getDireccionSocial(), ((TEmpresa) aux).getDireccionSocial());
			}

		}
			
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
