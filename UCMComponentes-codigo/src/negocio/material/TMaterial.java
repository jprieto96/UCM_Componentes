package negocio.material;

public class TMaterial {

	private int id;
	private boolean activo;

	private String nombre;
	private double precio;

	public TMaterial(int id) {
		this.id = id;
	}

	public TMaterial(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public TMaterial(int id, String nombre, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public TMaterial(int id, String nombre, double precio, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.activo = activo;
	}

	public TMaterial() {
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Material toEntity() {
		return new Material(this.nombre, this.precio, this.activo);
	}

}
