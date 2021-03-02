/**
 * 
 */
package negocio.empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import negocio.departamento.Departamento;

import javax.persistence.NamedQueries;

@Entity
@PrimaryKeyJoinColumn(name = "id_empleado")
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Tienda.findByid", query = "select obj from Tienda obj where :id = obj.id "),
		@NamedQuery(name = "negocio.empleado.Tienda.findBypuesto", query = "select obj from Tienda obj where :puesto = obj.puesto ") })
public class Tienda extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	private String puesto;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int getVersion() {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param version
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setVersion(int version) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Tienda() {
	}

	public Tienda(Integer id, String dni, String nombre, String apellidos, double salario, boolean activo,
			String puesto) {
		super(id, dni, nombre, apellidos, salario, activo);
		this.puesto = puesto;
	}

	public Tienda(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, String puesto) {
		super(id, dni, nombre, apellidos, salario, tipo, activo);
		this.puesto = puesto;
	}

	public Tienda(Integer id, String dni, String nombre, String apellidos, double salario, TipoEmpleado tipo,
			boolean activo, String puesto, Departamento departamento) {
		super(id, dni, nombre, apellidos, salario, tipo, activo, departamento);
		this.puesto = puesto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public TEmpleado toTransfer() {
		return new TTienda(id, dni, nombre, apellidos, salario, tipo, activo, puesto, departamento.getId());
	}

	@Override
	public double calcularNomina() {
		return this.salario * 0.905; // Ejemplo de cálculo de salario polimórfico, restamos el irpf
	}
}