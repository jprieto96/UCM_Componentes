
package integracion.cliente;

import java.util.List;
import negocio.cliente.TCliente;

public interface DAOCliente {

	public int create(TCliente cliente);

	public boolean delete(int id);

	public TCliente readById(int id);

	public List<TCliente> readAll();

	public boolean update(TCliente tCliente);

}