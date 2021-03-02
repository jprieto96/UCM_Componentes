package integracion.factura.altaFactura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

public class TestIntegracionAltaFactura {
	
	private static DAOFactura daoFactura;
	private static DAOCliente daoCliente;
	private static int idNuevaFactura;
	private static Transaction t;
	
	@Before
	public void before() {
		BBDDManip.vaciarTodo();
		daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
	}

	@Test
	public void testAltaFacturaCorrecto() {
		t = dameTransaccion();
		t.start();
		int idCliente = daoCliente.create(new TParticular(-1, "test", "34567", true, "05990113Q", -1, true));
		t.commit();
		
		t = dameTransaccion();
		t.start();
		idNuevaFactura = daoFactura.create(new TFactura(-1, "", 23, idCliente, true));
		t.commit();
		
		assertNotEquals(idNuevaFactura, -1);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testAltaFacturaExcepcion() throws NullPointerException {
		t = dameTransaccion();
		t.start();
		int id = daoFactura.create(null);
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
