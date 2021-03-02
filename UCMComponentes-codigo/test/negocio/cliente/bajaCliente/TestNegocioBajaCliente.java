package negocio.cliente.bajaCliente;

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

public class TestNegocioBajaCliente {
	
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
	public void bajaCLiente(){
		contexto = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		
		//Intento dar de baja un cliente que NO existe (le paso un id = 5)
		boolean dadoDeBaja = saCliente.bajaCliente(5); 
		assertFalse(dadoDeBaja);
		
		
		//Añado un cliente particular
		particular = new TParticular(-1, "Cliente particular", "ES0986543", true, "27268754T", -1, true);
		contexto.setDato(particular);
		particular.setId( saCliente.altaCliente((TCliente) contexto.getDato()));

		
		//Lo doy de baja -> el SA deberia de darlo de baja
		dadoDeBaja= saCliente.bajaCliente(((TCliente) contexto.getDato()).getId()); 
		assertTrue(dadoDeBaja);

		//Intento dar de baja un cliente que ya esta dado de baja -> el SA devuelve false ya que no se puede dar de baja
		dadoDeBaja= saCliente.bajaCliente(((TCliente) contexto.getDato()).getId()); 
		assertFalse(dadoDeBaja);
		
		
	}
	
}
