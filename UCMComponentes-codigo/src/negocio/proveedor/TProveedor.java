/**
 * 
 */
package negocio.proveedor;

public class TProveedor {

	private int id;
	private String nombre;
	private boolean activo;

	public TProveedor() {
	}

	public TProveedor(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public TProveedor(String nombre) {
		this.nombre = nombre;
	}

	public TProveedor(int id, String nombre, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}