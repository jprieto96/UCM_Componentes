package negocio.empleado;

public class TRepartidor extends TEmpleado {

	private String zonaReparto;

	public TRepartidor() {
	}

	public TRepartidor(Integer id, String dni, String nombre, String apellidos, double salario, boolean activo,
			String zonaReparto) {
		super(id, dni, nombre, apellidos, salario, activo);
		this.zonaReparto = zonaReparto;
	}

	public TRepartidor(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, String zonaReparto, int idDep) {
		super(id, dni, nombre, apellidos, tipo, salario, activo, idDep);
		this.zonaReparto = zonaReparto;
	}

	public TRepartidor(int id) {
		super(id);
	}

	public String getZonaReparto() {
		return zonaReparto;
	}

	public void setZonaReparto(String zonaReparto) {
		this.zonaReparto = zonaReparto;
	}

	public Empleado toEntity() {
		return new Repartidor(id, dni, nombre, apellidos, salario, tipo, activo, zonaReparto);
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void newTRepartidor() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public String toString() {
		return super.toString() + " Zona reparto:\n   > " + zonaReparto;
	}
}
