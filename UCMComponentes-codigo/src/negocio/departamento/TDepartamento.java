package negocio.departamento;

public class TDepartamento {

	private int id;
	private boolean activo;
	private String nombre;

	public TDepartamento(int id) {
		this.id = id;
	}

	public TDepartamento() {
	}

	public TDepartamento(int id, String nombre, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public TDepartamento(String nombre) {
		this.nombre = nombre;
	}

	public TDepartamento(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Departamento toEntity() {
		return new Departamento(this.nombre, this.activo);
	}
}
