package negocio.factura.mostrarFactura;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;

import org.junit.*;

import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.cliente.SACliente;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import negocio.cliente.TCliente;
import negocio.cliente.TParticular;
import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.Context;
import util.BBDDManip;

public class TestNegocioMostrarFactura {
	
	private static DAOFactura daoFactura;
	private static SAProducto saProducto;
	private static SAProveedor saProveedor;
	private static SAFactura saFactura;
	private static SACliente saCliente;
	private static Transaction t;
	private Context contexto;
	private TFactura factura;
	private TProducto producto;
	private TProveedor proveedor;
	private TParticular cliente;
	
	@BeforeClass
	public static void before() {
		BBDDManip.vaciarTodo();
		saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		
		 t = TransactionManager.getInstancia().nuevaTransaccion();
		 if(t == null)
			 t = TransactionManager.getInstancia().getTransaccion();
		 t.start();
		 Collection<TFactura> facturas = daoFactura.readAll();
		 t.commit();
		 if(!facturas.isEmpty())
			 fail("las pruebas unitarias de factura requiere vaciar la tabla facturas");
		 else
			 util.BBDDManip.vaciarTodo();
	}

	@Test
	public void testMostrarFactura() {
		contexto = new Context();
		saFactura = FactoriaNegocio.getInstancia().generaSAFactura();
		saProducto = FactoriaNegocio.getInstancia().generaSAProducto();
		saProveedor = FactoriaNegocio.getInstancia().generaSAProveedor();
		
		factura = new TFactura(-1, "1-1-2000", 2.f, -1, true);
		factura.setIdProducto(1);
		factura.setMapaProductos(new HashMap<Integer,Integer> ());
		//Doy de alta un proveedor y un producto
		proveedor = new TProveedor(-1, "Proveedor");
		proveedor.setId(saProveedor.altaProveedor(proveedor));
		producto = new TProducto(-1, "Producto", 2.f, 1, 1, true);
		producto.setId(saProducto.altaProducto(producto));
	
		
		//Creo el cliente
		cliente = new TParticular(-1, "Cliente", "ES1234", true, "71043951X");
		contexto.setDato(cliente);
		cliente.setId(saCliente.altaCliente((TCliente)contexto.getDato()));
		
		
		factura.setIdCliente(cliente.getId()); //Vinculo ese cliente a la factura
		factura.setIdProducto(producto.getId()); //Vinculo ese producto a la factura
		contexto.setDato(factura);
		
		factura.setId(saFactura.altaFactura((TFactura)contexto.getDato()));
		// ahora el cliente existe y debería funcionar y dar de alta la factura con id 1
		assertEquals(1, factura.getId());
		
		//Ahora muestro la factura, y comparo que los datos que me da el SA, son los mismos que yo le di anteriormente
		TFactura aux = saFactura.mostrarFactura(factura.getId());
			assertEquals(aux.getTotal(), factura.getTotal(), 0.0);
			assertEquals(aux.getIdCliente(), factura.getIdCliente());
			assertEquals(aux.getCantidad(), factura.getCantidad());
			assertEquals(aux.isActivo(), factura.isActivo());
			assertEquals(aux.getLineasFactura(), factura.getLineasFactura());
	}
}
