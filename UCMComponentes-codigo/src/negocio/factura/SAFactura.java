package negocio.factura;

import java.util.List;

public interface SAFactura {

	public int altaFactura(TFactura factura);

	public TFactura mostrarFactura(int id);

	public List<TFactura> listarFactura(boolean activo);

	public List<TFactura> mostrarFacturaPorCliente(int idCliente);

	public boolean devolverProducto(TFactura factura);

}
