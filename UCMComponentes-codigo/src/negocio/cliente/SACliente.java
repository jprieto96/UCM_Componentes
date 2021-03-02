/**
 * 
 */
package negocio.cliente;

import java.util.List;

public interface SACliente {

	public int altaCliente(TCliente tCliente);

	public boolean bajaCliente(int id);

	public boolean modificarCliente(TCliente tCliente);

	public TDatosCompletosCliente mostrarCliente(int id);

	public List<TCliente> listarClientes(boolean activo);

	public TCliente clienteQueMasGasta();

}