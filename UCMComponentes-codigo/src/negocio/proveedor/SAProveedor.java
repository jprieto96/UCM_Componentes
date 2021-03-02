/**
 * 
 */
package negocio.proveedor;

import java.util.List;

public interface SAProveedor {
	public int altaProveedor(TProveedor proveedor);

	public boolean bajaProveedor(int id);

	public boolean modificarProveedor(TProveedor proveedor);

	public TProveedor mostrarProveedor(int id);

	public List<TProveedor> listarProveedores(boolean activo);

}