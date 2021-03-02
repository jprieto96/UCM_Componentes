package integracion.proveedor.listarProveedor;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.proveedor.DAOProveedor;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.proveedor.TProveedor;
import util.BBDDManip;

public class TestIntegracionListarProveedor {
	
	private static DAOProveedor daoProveedor;
	private static Transaction t;
	private static TProveedor p1;
	
	@BeforeClass
	public static void before() {
		BBDDManip.vaciarTodo();
		p1 = new TProveedor("test1");
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
	}

	@Test
	public void testListarProveedoresCorrecto() {
		t = dameTransaccion();
		t.start();
		daoProveedor.create(p1);
		t.commit();
		
		t = dameTransaccion();
		t.start();
		List<TProveedor> proveedores = daoProveedor.readAll();
		t.commit();
		
		assertEquals(proveedores.size(), 1);
		
		for (TProveedor p : proveedores) {
			assertNotNull(p);
			assertEquals(p.getNombre(), p1.getNombre());
		}
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
