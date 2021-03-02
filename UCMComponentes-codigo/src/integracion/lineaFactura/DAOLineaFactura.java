/**
 * 
 */
package integracion.lineaFactura;

import java.util.List;

import negocio.factura.TLineaFactura;

public interface DAOLineaFactura {

	public int create(TLineaFactura lineaFactura);

	public boolean update(TLineaFactura lineaFactura);

	public List<TLineaFactura> readByIdFactura(int idFactura);

}