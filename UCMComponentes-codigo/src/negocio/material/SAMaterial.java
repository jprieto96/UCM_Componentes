/**
 * 
 */
package negocio.material;

import java.util.List;

public interface SAMaterial {

	public int altaMaterial(TMaterial material);

	public boolean bajaMaterial(int id);

	public boolean modificarMaterial(TMaterial material);

	public TMaterial mostrarMaterial(int id);

	public List<TMaterial> listarMateriales(boolean activo);
	
}