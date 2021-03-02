package negocio.producto;

public class TProducto {

	protected int id;
	protected String nombre;
	protected double precio;
	protected int existencias;
	protected int id_proveedor;
	protected boolean activo;

	public TProducto(int id, String nombre, double precio, int existencias, int id_proveedor, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.existencias = existencias;
		this.activo = activo;
		this.id_proveedor = id_proveedor;
	}

	public TProducto(int id) {
		this.id = id;
	}

	public TProducto(int id, int cantidad) {
		this.id = id;
		this.existencias = cantidad;
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

	public boolean isActivo() {
		return activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param activo
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActivo(Boolean activo) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdProveedor(int id) {
		this.id_proveedor = id;
	}

	public int getIdProveedor() {
		return this.id_proveedor;
	}

}
