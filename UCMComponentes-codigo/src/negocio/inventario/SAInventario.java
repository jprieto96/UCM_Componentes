/**
 * 
 */
package negocio.inventario;

import java.util.List;

public interface SAInventario {

	public boolean altaInventario(TInventario inventario);

	public boolean modificarInventario(TInventario inventario);

	public TInventario mostrarInventario(int idMaterial, int idDepartamento);

	public List<TInventario> listarInventarios(boolean activo);

	public List<TInventario> mostrarInventarioPorMaterial(int id);

	public List<TInventario> mostrarInventarioPorDepartamento(int id);
	
	public boolean bajaInventario(int idMaterial, int idDepartamento);
	
}