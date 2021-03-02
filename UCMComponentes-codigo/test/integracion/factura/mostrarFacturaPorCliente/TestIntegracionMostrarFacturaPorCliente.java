package integracion.factura.mostrarFacturaPorCliente;

import static org.junit.Assert.*;

import java.util.List;

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

public class TestIntegracionMostrarFacturaPorCliente {
	
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
	public void testMostrarFacturaPorClienteCorrecto() {
		t = dameTransaccion();
		t.start();
		List<TFactura> f = daoFactura.readByCliente(idNuevoCliente);
		t.commit();
		
		assertFalse(f.isEmpty());
		assertEquals(f.size(), 1);
		assertEquals(String.valueOf(f.get(0).getTotal()), "23.0");
		assertEquals(f.get(0).getIdCliente(), idNuevoCliente);
		assertEquals(f.get(0).getId(), idNuevaFactura);
	}
	
	@Test
	public void testMostrarFacturaPorClienteIncorrecto() {
		t = dameTransaccion();
		t.start();
		List<TFactura> f = daoFactura.readByCliente(-1);
		t.commit();
		
		assertTrue(f.isEmpty());
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