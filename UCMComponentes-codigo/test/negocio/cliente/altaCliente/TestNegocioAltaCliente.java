package negocio.cliente.altaCliente;

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

public class TestNegocioAltaCliente {
	
	private static DAOCliente daoCliente;
	private static TParticular particular;
	private static Transaction t;
	private static Context contexto;

	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		//seleccionarCamposUnicos();

		
		
		//Comprobamos que estan vac�as las tablas
		t = TransactionManager.getInstancia().nuevaTransaccion();
		if(t == null) t = TransactionManager.getInstancia().getTransaccion();
		t.start();
		Collection<TCliente> clientes = daoCliente.readAll();
		t.commit();
		if( !clientes.isEmpty() )
			fail("las pruebas unitarias de negocio de cliente requieren la tablas cliente, particular y empresa vac�as");
		else
			util.BBDDManip.vaciarTodo();		
	}
	
	
	@Test
	public void altaCLiente(){
		contexto = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		
		
		//Intento a�adir un cliente que no existe -> lo deberia a�adir el SA
		particular = new TParticular(-1, "Cliente particular", "ES0986543", true, "27268754T", -1, true);
		contexto.setDato(particular);
		particular.setId( saCliente.altaCliente((TCliente) contexto.getDato()));
			//Me tiene que dar el id 1, ya que es el primer cliente que se inserta
		assertEquals(1,particular.getId());
		
		
		//Intento a�adir un cliente que existe pero esta inactivo -> lo deberia reactivar el SA
		saCliente.bajaCliente(particular.getId()); //Doy de baja el cliente a�adido anteriormente
		
		contexto.setDato(particular);
			//Me tiene que devolver el mismo id, porque solo se ha reactivado
		int id = saCliente.altaCliente((TCliente) contexto.getDato());
		assertEquals(id, particular.getId());

		
		//Intento a�adir un cliente, pero este ya existe y esta activo -> no deberia a�adirlo, deberia dar error
		contexto.setDato(particular);
		//Me tiene que devolver el mismo id, porque solo se ha reactivado
		id = saCliente.altaCliente((TCliente) contexto.getDato());
		assertEquals(id, -1); //Pongo -1 porque da error y el sa me lo indica con un -1
		

		
	}
	
}
