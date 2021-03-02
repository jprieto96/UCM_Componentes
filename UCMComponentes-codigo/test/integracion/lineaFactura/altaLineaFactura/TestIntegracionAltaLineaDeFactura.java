package integracion.lineaFactura.altaLineaFactura;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.*;

import integracion.lineaFactura.DAOLineaFactura;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.factura.DAOFactura;
import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;
import negocio.factura.TLineaFactura;
import util.BBDDManip;
import negocio.cliente.TCliente;
import negocio.cliente.TParticular;
import negocio.factura.*;
import negocio.producto.TProducto;
import negocio.proveedor.TProveedor;

public class TestIntegracionAltaLineaDeFactura {

	public static TFactura factura;
	public static TCliente clientePar;
	public static TProducto producto1;
	public static TProveedor proveedor;
	public static TLineaFactura lineaFactura;
	
	public static DAOFactura daoFactura;
	public static DAOCliente daoCliente;
	public static DAOProducto daoProducto;
	public static DAOProveedor daoProveedor;
	public static DAOLineaFactura daoLineaFactura;
	private static Transaction t;
	
	@BeforeClass
	public static void before() { //Antes de cada Test
		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();
		daoFactura = FactoriaIntegracion.getInstancia().generaDAOFactura();
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		daoProducto = FactoriaIntegracion.getInstancia().generaDAOProducto();
		daoProveedor = FactoriaIntegracion.getInstancia().generaDAOProveedor();
		daoLineaFactura =FactoriaIntegracion.getInstancia().generaDAOLineaFactura();
		//seleccionarCamposUnicos();

		//Creamos cuatro transfer, un cliente, un producto, una factura y una linea de factura
		clientePar = new TParticular(1, "ClientePart", "ES2134589", true, "59680968Q");
		proveedor = new TProveedor(1,"Proveedor");
		producto1 = new TProducto(1, "Producto1", 99, 2, 1, true);
		factura = new  TFactura(1, "01/12/2020", 99, 1, true);
		lineaFactura = new TLineaFactura(1, 1, 99, 1, 1);

		//Vaciamos la base de datos -> estado predecible
		BBDDManip.vaciarTodo();	
	}
	
	@Test
	public void altaLineaDeFactura(){
		
		//Añado un cliente particular
		t = dameTransaccion();
		t.start();
		clientePar.setId(daoCliente.create(clientePar)); 
		t.commit();		
		//añado un proveedor
		t = dameTransaccion();
		t.start();
		proveedor.setId(daoProveedor.create(proveedor)); 
		t.commit();
		//Añado un producto
		t = dameTransaccion();
		t.start();
		producto1.setId(daoProducto.create(producto1)); 
		t.commit();
		//Añado la factura
		t = dameTransaccion();
		t.start();
		factura.setId(daoFactura.create(factura)); 
		t.commit();
		
		//Añado la linea de factura
		t = dameTransaccion();
		t.start();
		lineaFactura.setId(daoLineaFactura.create(lineaFactura)); 
		t.commit();
		//La leo
		t = dameTransaccion();
		t.start();
		List<TLineaFactura> lista = daoLineaFactura.readByIdFactura(factura.getId()); //Lo leo
		t.commit();
		Iterator<TLineaFactura> it = lista.iterator();
		//Ahora comprobamos todos sus campos se han introducido correctamente, de la unica linea de venta que hemos creado
		while(it.hasNext()){
			TLineaFactura aux;
			aux = it.next();
			assertEquals(1, aux.getCantidad());
			assertEquals(producto1.getPrecio() * lineaFactura.getCantidad(), aux.getPrecio(), 0.0);
			assertEquals(factura.getId(), aux.getIdFactura());
			assertEquals(producto1.getId(), aux.getIdProducto());
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
