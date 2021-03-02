package negocio.empleado;

public abstract class TEmpleado {

	protected Integer id;
	protected String dni;
	protected String nombre;
	protected String apellidos;
	protected double salario;
	protected boolean activo;
	protected TipoEmpleado tipo;
	protected int idDep;

	public TEmpleado() {
	}

	public TEmpleado(Integer id, String dni, String nombre, String apellidos, double salario, boolean activo) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
		this.activo = activo;
	}

	public TEmpleado(Integer id, String dni, String nombre, String apellidos, TipoEmpleado tipo, double salario,
			boolean activo, int idDep) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
		this.tipo = tipo;
		this.activo = activo;
		this.idDep = idDep;
	}

	public TEmpleado(String NIF, String nombre, String apellidos, TipoEmpleado tipo, double salario, boolean activo) {
		this.dni = NIF;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tipo = tipo;
		this.salario = salario;
		this.activo = activo;
	}

	public TEmpleado(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTipo() {
		if (this.tipo == TipoEmpleado.TiempoParcial)
			return "Tiempo parcial";
		else
			return "Tiempo completo";
	}

	public void setTipo(TipoEmpleado tipo) {
		this.tipo = tipo;
	}

	public boolean isActivo() {
		return activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param Parameter1
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActivo(Boolean Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIdDep() {
		return this.idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}

	@Override
	public String toString() {
		return "DATOS DEL EMPLEADO\n" + " Identificador:\n   > " + id + "\n" + " Nombre completo:\n   > " + nombre + " "
				+ apellidos + "\n" + " Estado actual:\n   > " + (!activo ? "NO " : "") + "ACTIVO\n" + " DNI:\n   > "
				+ dni + "\n" + " Salario:\n   > " + salario + "\n" + " Tipo Contrato:\n   > " + tipo + "\n"
				+ " Id departamento:\n   > " + idDep + "\n";
	}

	public abstract Empleado toEntity();

}
