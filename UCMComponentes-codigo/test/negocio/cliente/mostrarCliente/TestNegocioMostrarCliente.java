package negocio.cliente.mostrarCliente;

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

public class TestNegocioMostrarCliente {
	
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
	public void mostrarCLiente(){
		contexto = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		
		
		//Añado un cliente
		particular = new TParticular(-1, "Cliente particular", "ES0986543", true, "27268754T", -1, true);
		contexto.setDato(particular);
		particular.setId( saCliente.altaCliente((TCliente) contexto.getDato()));
		
		//Ahora lo leo
		TCliente aux = saCliente.mostrarCliente(particular.getId()).getCliente();
			//Compruebo que los campos no han sido modificados al leerlo
			assertEquals(particular.getId(), aux.getId());
			assertEquals(particular.getNombre(), aux.getNombre());
			assertEquals(particular.getCuentaBancaria(), aux.getCuentaBancaria());
			assertEquals(particular.isActivo(), aux.isActivo());
			assertEquals(particular.getNIF(), aux.getNIF());
			assertEquals(particular.isActivo(), aux.isActivo());
		
	}
	
}
