package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import integracion.transactions.Transaction;
import integracion.transactions.manager.TransactionManager;

public class BBDDManip {
	// Esta clase sirve para vaciar rapidamente la BD para los tests
	
	public static void vaciarClientes() {
		vaciarParticular();
		vaciarEmpresa();
		vaciarCliente();
	}
	public static void vaciarCliente() {
		vaciarTabla("cliente");
	}
	public static void vaciarEmpresa() {
		vaciarTabla("empresa");
	}
	
	public static void vaciarParticular() {
		vaciarTabla("particular");
	}
		
	public static void vaciarFactura() {
		vaciarTabla("factura");
	}
	public static void vaciarLineaFactura() {
		vaciarTabla("linea_factura");
	}
		
	public static void vaciarProveedor() {
		vaciarTabla("proveedor");
	}
	
	public static void vaciarProducto() {
		vaciarTabla("producto");
	}
		
	public static void vaciarTodo() {
		vaciarClientes();
		vaciarLineaFactura();
		vaciarProducto();
		vaciarProveedor();
		vaciarFactura();
	}
		
	private static void vaciarTabla(String tabla)
	{
		try {
			Transaction t = TransactionManager.getInstancia().nuevaTransaccion();
			if(t == null) t = TransactionManager.getInstancia().getTransaccion();
			t.start();
			Connection con1 = (Connection) t.getResource();
			PreparedStatement ps = con1.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
			ps.execute();
			PreparedStatement ps2 = con1.prepareStatement("TRUNCATE TABLE " + tabla + ";");
			ps2.executeUpdate();
			PreparedStatement ps3 = con1.prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
			ps3.execute();
			t.commit();
			
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}
