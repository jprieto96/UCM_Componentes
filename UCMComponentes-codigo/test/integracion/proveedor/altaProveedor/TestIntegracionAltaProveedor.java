package integracion.proveedor.altaProveedor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.TProveedor;
import util.BBDDManip;

public class TestIntegracionAltaProveedor {
	
	private static DAOProveedor daoProveedor;
	private static int idNuevoProveedor;
	private static Transaction t;
	
	@Before
	public void before() {
		BBDDManip.vaciarTodo();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
	}

	@Test
	public void testeAltaProveedorCorrecto() {
		t = dameTransaccion();
		t.start();
		idNuevoProveedor = daoProveedor.create(new TProveedor("test"));
		t.commit();
		assertNotEquals(idNuevoProveedor, -1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAltaProveedorCorrectoEsperandoExcepcion() throws NullPointerException {
		t = dameTransaccion();
		t.start();
		int id = daoProveedor.create(null);
		t.commit();
		assertEquals(null, id);
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
