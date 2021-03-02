/**
 * 
 */
package negocio.factura;

public class TLineaFactura {

	private int id;
	private int cantidad;
	private double precio;
	private int idFactura;
	private int idProducto;

	public TLineaFactura(int id, int cantidad, double precio, int idFactura, int idProducto) {
		this.id = id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.idFactura = idFactura;
		this.idProducto = idProducto;
	}

	public TLineaFactura(int id, int idFactura) {
		this.id = id;
		this.idFactura = idFactura;
	}

	public TLineaFactura(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param precio
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setPrecio(Double precio) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
}