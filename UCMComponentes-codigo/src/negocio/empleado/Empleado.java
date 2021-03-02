/**
 * 
 */
package negocio.empleado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;
import negocio.departamento.Departamento;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Empleado.findByid", query = "select obj from Empleado obj where :id = obj.id "),
		@NamedQuery(name = "negocio.empleado.Empleado.findBydni", query = "select obj from Empleado obj where :dni = obj.dni "),
		@NamedQuery(name = "negocio.empleado.Empleado.findBynombre", query = "select obj from Empleado obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.empleado.Empleado.findByapellidos", query = "select obj from Empleado obj where :apellidos = obj.apellidos "),
		@NamedQuery(name = "negocio.empleado.Empleado.findBysalario", query = "select obj from Empleado obj where :salario = obj.salario "),
		@NamedQuery(name = "negocio.empleado.Empleado.findByactivo", query = "select obj from Empleado obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.empleado.Empleado.findBytipo", query = "select obj from Empleado obj where :tipo = obj.tipo "),
		@NamedQuery(name = "negocio.empleado.Empleado.findAllActives", query = "select obj from Empleado obj where obj.activo = true "),
		@NamedQuery(name = "negocio.empleado.Empleado.findAll", query = "select obj from Empleado obj"),
		@NamedQuery(name = "negocio.empleado.Empleado.findBydepartamento", query = "select obj from Empleado obj where :departamento = obj.departamento.id ") })
public abstract class Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(unique = true, nullable = false, length = 9)
	protected String dni;
	@Column(nullable = false)
	protected String nombre;
	@Column(nullable = false)
	protected String apellidos;
	@Column(nullable = false)
	protected double salario;
	@Column(nullable = false)
	protected TipoEmpleado tipo;
	@Column(nullable = false)
	protected boolean activo;

	@Version
	protected int version;

	public Empleado() {
	}

	public Empleado(Integer id, String dni, String nombre, String apellidos, double salario, boolean activo) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
		this.activo = activo;
	}

	public Empleado(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
		this.tipo = tipo;
		this.activo = activo;
	}

	public Empleado(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, Departamento departamento) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
		this.tipo = tipo;
		this.activo = activo;
		this.departamento = departamento;
	}

	@ManyToOne
	protected Departamento departamento;

	public Integer getId() {
		return id;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setId(int id) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param dni
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setDni(int dni) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
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
			return "Tiempo Completo";
	}

	public void setTipo(TipoEmpleado tipo) {
		this.tipo = tipo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setDepartamentod() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public abstract TEmpleado toTransfer();

	public abstract double calcularNomina();
}