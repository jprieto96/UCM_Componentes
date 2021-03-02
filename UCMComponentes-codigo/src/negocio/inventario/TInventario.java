/**
 * 
 */
package negocio.inventario;

public class TInventario {
	
	private int idDepartamento;
	private int idMaterial;
	private int existencias;
	private boolean activo;
	
	public TInventario() {}
	
	public TInventario(int idDepartamento, int idMaterial, int existencias, boolean activo) {
		super();
		this.idDepartamento = idDepartamento;
		this.idMaterial = idMaterial;
		this.existencias = existencias;
		this.activo = activo;
	}

	public TInventario(int idDepartamento, int idMaterial) {
		this.idDepartamento = idDepartamento;
		this.idMaterial = idMaterial;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
	
	public Inventario toEntity() {
		return new Inventario(idDepartamento, idMaterial, existencias, activo);
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "DATOS DEL INVENTARIO\n"
				+ " ID Departamento:\n   > " + idDepartamento + "\n" + " ID Material:\n   > " + idMaterial + "\n"+ " Existencias:\n   > "+ existencias + "\n"+ " Estado actual:\n   > "
				+ (!activo ? "NO " : "") + "ACTIVO" + "\n";
	}
	
}