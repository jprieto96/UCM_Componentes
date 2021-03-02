package negocio.cliente.listarClientes;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.*;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.*;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioListarClientes {

	
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
	public void listarCLientes(){
		contexto = new Context();
		SACliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		
		//Intento listarClientes activos, pero no existe ninguno (ni activo ni inactivo)
		List<TCliente> lista = saCliente.listarClientes(true);
		assertEquals(lista.size(), 0);
		
		//Añado un cliente particular ACTIVO
		particular = new TParticular(-1, "Cliente particular", "ES0986543", true, "27268754T", -1, true);
		contexto.setDato(particular);
		particular.setId( saCliente.altaCliente((TCliente) contexto.getDato()));

		//Añado un cliente empresa INACTIVO (lo doy de baja)
		empresa = new TEmpresa(-1, "Cliente de empresa", "ES09238457", false, "17742138F", -1, "Calle de la empresa");
		contexto.setDato(empresa);
		empresa.setId( saCliente.altaCliente((TCliente) contexto.getDato()));
		if (saCliente.bajaCliente(((TCliente) contexto.getDato()).getId()) ){
			empresa.setActivo(false);
		}
		
		
		//Intento listar los clientes activos, deberia devolverme uno
		List<TCliente> lista2 = saCliente.listarClientes(true);
		assertEquals(lista2.size(), 1);
		//Ademas compruebo que es el particular
		Iterator<TCliente> it = lista2.iterator();
		while(it.hasNext()){
			TCliente aux;
			aux = (TCliente) it.next();
			
			if(aux instanceof TEmpresa){
				fail();
			}
		}
		
		//Intento listar todos los clientes -> me devuelve dos
		List<TCliente> lista3 = saCliente.listarClientes(false);
		assertEquals(lista3.size(), 2);		
		
	}
	
}
