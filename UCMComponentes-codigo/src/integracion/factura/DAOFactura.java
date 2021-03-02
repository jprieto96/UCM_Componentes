package integracion.factura;

import java.util.List;

import negocio.factura.TFactura;

public interface DAOFactura {

	public int create(TFactura factura);

	public TFactura readById(int id);

	public List<TFactura> readAll();

	public List<TFactura> readByCliente(int cliente);

	public boolean update(TFactura factura);

}
