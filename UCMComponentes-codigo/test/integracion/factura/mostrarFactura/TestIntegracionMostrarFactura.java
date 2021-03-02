package integracion.factura.mostrarFactura;

import static org.junit.Assert.*;

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

public class TestIntegracionMostrarFactura {
	
	private static DAOFactura daoFactura;
	private static DAOCliente daoCliente;
	private static int idNuevaFactura;
	private static int idNuevoCliente;
	private static Transaction t;
	
	@Before
	public void before() {
		BBDDManip.vaciarTodo();
		daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		t = dameTransaccion();
		t.start();
		idNuevoCliente = daoCliente.create(new TParticular(-1, "test", "34567", true, "05990113Q", -1, true));
		t.commit();
		
		t = dameTransaccion();
		t.start();
		idNuevaFactura = daoFactura.create(new TFactura(-1, "", 23, idNuevoCliente, true));
		t.commit();
	}

	@Test
	public void testMostrarFacturaCorrecto() {
		t = dameTransaccion();
		t.start();
		TFactura f = daoFactura.readById(idNuevaFactura);
		t.commit();
		
		assertEquals(String.valueOf(f.getTotal()), "23.0");
		assertEquals(f.getIdCliente(), idNuevoCliente);
	}
	
	@Test
	public void testMostrarFacturaIncorrecto() {
		t = dameTransaccion();
		t.start();
		TFactura f = daoFactura.readById(-1);
		t.commit();
		
		assertNull(f);
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
