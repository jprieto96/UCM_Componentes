/**
 * 
 */
package integracion.proveedor;

import negocio.proveedor.TProveedor;
import java.util.List;

public interface DAOProveedor {

	public int create(TProveedor proveedor);

	public boolean delete(int id);

	public TProveedor readById(int id);

	public List<TProveedor> readAll();

	public boolean update(TProveedor tProveedor);

}