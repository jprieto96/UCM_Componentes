package integracion.factura.listarFacturas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

public class TestIntegracionListarFacturas {
	
	private static DAOFactura daoFactura;
	private static DAOCliente daoCliente;
	private static int idNuevoCliente;
	private static TFactura f1;
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
		
		f1 = new TFactura(-1, "", 23, idNuevoCliente, true);
		
		t = dameTransaccion();
		t.start();
		daoFactura.create(f1);
		t.commit();
	}

	@Test
	public void testListarFacturasCorrecto() {
		t = dameTransaccion();
		t.start();
		List<TFactura> facturas = daoFactura.readAll();
		t.commit();
		
		assertEquals(facturas.size(), 1);
		
		for (TFactura f : facturas) {
			assertNotNull(f);
			assertEquals(String.valueOf(f.getTotal()), String.valueOf(f1.getTotal()));
			assertEquals(f.getIdCliente(), f1.getIdCliente());
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
