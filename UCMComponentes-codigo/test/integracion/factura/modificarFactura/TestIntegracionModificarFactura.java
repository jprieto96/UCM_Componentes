package integracion.factura.modificarFactura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.TParticular;
import negocio.factura.TFactura;
import util.BBDDManip;

public class TestIntegracionModificarFactura {
	
	private static DAOFactura daoFactura;
	private static DAOCliente daoCliente;
	private static TFactura factura;
	private static int idFactura;
	private static int idCliente;
	private static Transaction t;
	
	@Before
	public void before() {
		BBDDManip.vaciarTodo();
		daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		daoCliente =  FactoriaIntegracion.getInstancia().generaDAOCliente();
		
		t = dameTransaccion();
		t.start();
		idCliente = daoCliente.create(new TParticular(-1, "test", "34567", true, "05990113Q", -1, true));
		t.commit();
		
		factura = new TFactura(-1, "", 23, idCliente, true);
		
		t = dameTransaccion();
		t.start();
		idFactura = daoFactura.create(factura);
		t.commit();
		
		t = dameTransaccion();
		t.start();
		factura = daoFactura.readById(idFactura);
		t.commit();
	}

	@Test
	public void testModificarProveedorCorrecto() {
		factura.setTotal(20);
		
		t = dameTransaccion();
		t.start();
		boolean ok = daoFactura.update(factura);
		t.commit();
		
		t = dameTransaccion();
		t.start();
		TFactura f = daoFactura.readById(idFactura);
		t.commit();
		
		assertTrue(ok);
		assertEquals(String.valueOf(factura.getTotal()), String.valueOf(f.getTotal()));
	}
	
	@Test(expected = NullPointerException.class)
	public void testModificarProveedorIncorrecto() throws NullPointerException {
		t = dameTransaccion();
		t.start();
		boolean ok = daoFactura.update(null);
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
