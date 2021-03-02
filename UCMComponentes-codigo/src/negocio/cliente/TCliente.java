/**
 * 
 */
package negocio.cliente;

public class TCliente {

	protected int id;
	protected String nombre;
	protected String cuentaBancaria;
	protected boolean activo;
	protected String NIF;

	public TCliente(int id, String nombre, String cuentaBancaria, boolean activo, String NIF) {
		this.id = id;
		this.nombre = nombre;
		this.cuentaBancaria = cuentaBancaria;
		this.activo = activo;
		this.NIF = NIF;
	}

	public TCliente(int id) {
		this.id = id;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getNif() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nif
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNif(String nif) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}