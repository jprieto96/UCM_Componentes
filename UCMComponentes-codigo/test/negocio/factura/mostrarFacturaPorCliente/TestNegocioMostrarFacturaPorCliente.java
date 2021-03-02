package negocio.factura.mostrarFacturaPorCliente;

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

public class TestNegocioMostrarFacturaPorCliente {
	
	private static DAOFactura daoFactura;
	private static SAProducto saProducto;
	private static SAProveedor saProveedor;
	private static SAFactura saFactura;
	private static SACliente saCliente;
	private static Transaction t;
	private Context contexto;
	private TFactura factura1;
	private TFactura factura2;
	private TProducto producto;
	private TProveedor proveedor;
	private TParticular cliente1;
	private TParticular cliente2;
	
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
		
		
		//Doy de alta un proveedor y un producto
		proveedor = new TProveedor(-1, "Proveedor");
		proveedor.setId(saProveedor.altaProveedor(proveedor));
		producto = new TProducto(-1, "Producto", 2.f, 1, 1, true);
		producto.setId(saProducto.altaProducto(producto));		
		
		/***************** Creo una factura asociada al cliente 1 ***********************/
		factura1 = new TFactura(-1, "1-1-2000", 3.f, -1, true);
		factura1.setIdProducto(1);
		factura1.setMapaProductos(new HashMap<Integer,Integer> ());
		//Creo el cliente 1
		cliente1 = new TParticular(-1, "Cliente 1", "ES4321", true, "71043951X");
		contexto.setDato(cliente1);
		cliente1.setId(saCliente.altaCliente((TCliente)contexto.getDato()));
		factura1.setIdCliente(cliente1.getId()); //Vinculo ese cliente a la factura
		factura1.setIdProducto(producto.getId()); //Vinculo ese producto a la factura
		contexto.setDato(factura1);
		
		/***************** Creo una factura asociada al cliente 2 ***********************/

		factura2 = new TFactura(-1, "1-1-2000", 2.f, -1, true);
		factura2.setIdProducto(1);
		factura2.setMapaProductos(new HashMap<Integer,Integer> ());
		//Creo el cliente 2
		cliente2 = new TParticular(-1, "Cliente 2", "ES1234", true, "31003297X");
		contexto.setDato(cliente2);
		cliente2.setId(saCliente.altaCliente((TCliente)contexto.getDato()));
		factura2.setIdCliente(cliente2.getId()); //Vinculo ese cliente a la factura
		factura2.setIdProducto(producto.getId()); //Vinculo ese producto a la factura
		
		
		//Doy de alta la factura 1
		contexto.setDato(factura1);
		factura1.setId(saFactura.altaFactura((TFactura)contexto.getDato()));
		// Compruebo que esa factura tiene asociado el cliente correcto
		assertEquals(cliente1.getId(), factura1.getIdCliente());
		
		//Doy de alta la factura 2
		contexto.setDato(factura2);
		factura2.setId(saFactura.altaFactura((TFactura)contexto.getDato()));
		// Compruebo que esa factura tiene asociado el cliente correcto
		assertEquals(cliente2.getId(), factura2.getIdCliente());
		
		
		
		//Ahora muestro la factura asociada al PRIMER cliente, y comparo que los datos que me da el SA, son los mismos que yo le di anteriormente
		TFactura aux = saFactura.mostrarFactura(factura1.getId());
			assertEquals(aux.getTotal(), factura1.getTotal(), 0.0);
			assertEquals(aux.getIdCliente(), factura1.getIdCliente());
			assertEquals(aux.getCantidad(), factura1.getCantidad());
			assertEquals(aux.isActivo(), factura1.isActivo());
			assertEquals(aux.getLineasFactura(), factura1.getLineasFactura());
		
		
	}
}
