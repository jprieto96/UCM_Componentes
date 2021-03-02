package integracion.proveedor.mostrarProveedor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.TProveedor;
import util.BBDDManip;

public class TestIntegracionMostrarProveedor {
	
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
	public void testMostrarDatosProveedorCorrecto() {
		t = dameTransaccion();
		t.start();
		TProveedor p = daoProveedor.readById(idProveedor);
		t.commit();
		
		assertEquals(proveedor.getId(), p.getId());
		assertEquals(proveedor.getNombre(), p.getNombre());
	}
	
	@Test
	public void testMostrarDatosProveedorIncorrecto() {
		t = dameTransaccion();
		t.start();
		TProveedor p = daoProveedor.readById(-1);
		t.commit();
		
		assertNull(p);
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
