package integracion.proveedor.modificarProveedor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.TProveedor;
import util.BBDDManip;

public class TestIntegracionModificarProveedor {
	
	private static DAOProveedor daoProveedor;
	private static TProveedor proveedor;
	private static int idProveedor;
	private static Transaction t;
	
	@Before
	public void before() {
		BBDDManip.vaciarTodo();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		proveedor = new TProveedor("test");
		
		t = dameTransaccion();
		t.start();
		idProveedor = daoProveedor.create(proveedor);
		t.commit();
		proveedor.setId(idProveedor);
	}

	@Test
	public void testModificarProveedorCorrecto() {
		proveedor.setNombre("test2");
		
		t = dameTransaccion();
		t.start();
		boolean ok = daoProveedor.update(proveedor);
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TProveedor p = daoProveedor.readById(idProveedor);
		t.commit();
		
		assertTrue(ok);
		assertEquals(proveedor.getNombre(), p.getNombre());
	}
	
	@Test(expected = NullPointerException.class)
	public void testModificarProveedorIncorrecto() throws NullPointerException {
		t = dameTransaccion();
		t.start();
		boolean ok = daoProveedor.update(null);
		t.commit();
		
		assertEquals(null, ok);
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
