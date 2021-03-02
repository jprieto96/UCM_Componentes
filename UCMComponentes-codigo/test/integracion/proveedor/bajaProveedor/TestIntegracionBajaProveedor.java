package integracion.proveedor.bajaProveedor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.TProveedor;
import util.BBDDManip;

public class TestIntegracionBajaProveedor {

	private static DAOProveedor daoProveedor;
	private int idNuevoProveedor;
	private static Transaction t;
	
	@Before
	public void before() {
		BBDDManip.vaciarProveedor();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		t = dameTransaccion();
		t.start();
		idNuevoProveedor = daoProveedor.create(new TProveedor("test"));
		t.commit();
	}

	@Test
	public void testBajaProveedorCorrecto() {

		t = dameTransaccion();
		t.start();
		boolean ok = daoProveedor.delete(idNuevoProveedor);
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TProveedor p = daoProveedor.readById(idNuevoProveedor);
		t.commit();
		
		assertTrue(ok);
		assertFalse(p.isActivo());
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
