package negocio.cliente.modificarCliente;


import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.*;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.*;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioModificarCliente {

	
	public static DAOCliente daoCliente;
	public static TParticular particular;
	public static TEmpresa empresa;
	private static Transaction t;
	public static Context contexto;
	public static SACliente saCliente;

	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		//seleccionarCamposUnicos();

		//Creamos dos transfer, uno de cada
		particular = new TParticular(-1, "Cliente particular", "ES0986543", true, "27268754T", -1, true);

		//Comprobamos que estan vacías las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TCliente> clientes = daoCliente.readAll();
		t.commit();
		if( !clientes.isEmpty() )
			fail("las pruebas unitarias de negocio de cliente requieren la tablas cliente, particular y empresa vacías");
		else
			util.BBDDManip.vaciarTodo();		
	}
	
	@Test
	public void modificarCLiente(){
		contexto = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		
		//Intento modificar un cliente que NO existe  --> El SA da error
		boolean mod = saCliente.modificarCliente(particular); 
		assertFalse(mod);
		
		
		//Añado un cliente particular
		particular = new TParticular(-1, "Cliente particular", "ES0986543", true, "27268754T", -1, true);
		contexto.setDato(particular);
		particular.setId( saCliente.altaCliente((TCliente) contexto.getDato()));

		
		//Lo modifico-> el SA deberia de modificarlo correctamente
			//La comprobacion sintactica de los datos la hace la capa de presentacion, estoy asumiendo que se meten datos correctos
		particular.setCuentaBancaria("ES09283745");
		particular.setNombre("Nuevo nombre");
		contexto.setDato(particular);
		mod = saCliente.modificarCliente((TCliente) contexto.getDato());
		assertTrue(mod);

		
	}
	
}
