package negocio.proveedor.listarProveedores;


import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.*;

import integracion.proveedor.DAOProveedor;
import integracion.factoria.FactoriaIntegracion;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.*;
import negocio.factoria.FactoriaNegocio;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioListarProveedores {
	
	public static DAOProveedor daoProveedor;
	public static TProveedor proveedor;
	public static TProveedor proveedor2;
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
	public void ListarProveedores(){
		contexto = new Context();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		
		//Intento listar proveedores activos, pero no existe ninguno (ni activo ni inactivo)
		List<TProveedor> lista = saProveedor.listarProveedores(true);
		assertEquals(lista.size(), 0);
		
		//Añado un proveedor ACTIVO
		proveedor = new TProveedor(-1, "Proveedor", true);
		contexto.setDato(proveedor);
		proveedor.setId( saProveedor.altaProveedor((TProveedor) contexto.getDato()));

		
		//Añado un proveedor INACTIVO (lo doy de baja)
		proveedor2 = new TProveedor(-1, "Proveedor secundario", true);
		contexto.setDato(proveedor2);
		proveedor2.setId( saProveedor.altaProveedor((TProveedor) contexto.getDato()));
		boolean dadoDeBaja= saProveedor.bajaProveedor( ((TProveedor) contexto.getDato()).getId());
			if(dadoDeBaja){
				proveedor2.setActivo(false);
			}
		
		//Intento listar los proveedores activos, deberia devolverme uno
		List<TProveedor> lista2 = saProveedor.listarProveedores(true);
		assertEquals(lista2.size(), 1);
	
		//Intento listar todos los proveedores -> me devuelve dos
		List<TProveedor> lista3 = saProveedor.listarProveedores(false);
		assertEquals(lista3.size(), 2);
		
		
	}
	
}
